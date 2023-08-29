/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lsl.microservices.gateway.filter;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.lsl.constant.SecurityConstant;
import com.lsl.microservices.gateway.config.GatewayConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 密码解密工具类
 */
@SuppressWarnings("all")
@Slf4j
@RequiredArgsConstructor
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {

	//List<HttpMessageReader<?>>对象包含了用于读取HTTP请求体的消息读取器，消息读取器是Spring框架用来将请求体的字节流转换为具体的Java对象的组件。它们可以根据请求的`Content-Type`头部来选择合适的读取器来解析请求体为Java对象
	//HandlerStrategies.withDefaults()返回一个HandlerStrategies.Builder对象,该对象允许配置处理策略,withDefaults()方法创建了一个默认的处理策略实例，messageReaders()方法从默认的处理策略获取消息读取器列表,这些消息读取器已经配置好以支持常见的类型
	private static final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

	private static final String PASSWORD = "password";

	private static final String KEY_ALGORITHM = "AES";

	private final GatewayConfigProperties gatewayConfig;


	/**
	 * 完成解密并返回新的exchange
	 */
	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			// 1. 不是登录请求，直接向下执行
			if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstant.OAUTH_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			// 2. 刷新token类型，直接向下执行
			String grantType = request.getQueryParams().getFirst("grant_type");
			if (StrUtil.equals(SecurityConstant.REFRESH_TOKEN, grantType)) {
				return chain.filter(exchange);
			}

			// 3. 开始解密
			Class inClass = String.class;
			Class outClass = String.class;

			ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders); //生成新的request对象

			//Mono是一种响应式类型,表示一个包含0个或1个元素的异步数据流
			//bodyToMono方法将请求体(HTTP)的内容解析为指定类型的对象,这里将请求体的字节流解析为String类型的对象,并返回一个Mono对象,包含了解析后的数据
			//flatMap()是响应式编程中的一个操作符,它允许您将一个Mono转换给另一个Mono,同时可以对元素进行操作。在这里,flatMap将Mono中的数据(String)传递给了解密方法
			//modifiedBody即包含解密后请求体数据的数据流
			Mono<?> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(decryptAES());

			//BodyInserter接口用于将数据插入到HTTP响应体中,它允许将修改后的请求体或其他数据插入到HTTP响应体中,以便返回给客户端
			//fromPublisher()是一个静态工厂方法,用于将一个Mono或Flux(响应式数据流)包装为一个BodyInserter,以便插入到响应体中。该方法通常在构建HTTP响应时使用,将响应体的内容设置为异步数据流
			BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, outClass);

			HttpHeaders headers = new HttpHeaders();
			headers.putAll(exchange.getRequest().getHeaders()); //初始化为当前请求的头部
			headers.remove(HttpHeaders.CONTENT_LENGTH); //移除长度(解密后长度产生变化)
			headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE); //设置响应头的内容类型表单数据

			//CachedBodyOutputMessage允许在生成响应体内容时将数据缓存起来,而不必一次性将所有数据加载到内存中
			//初始化CachedBodyOutputMessage对象,准备了一个可以缓存响应体内容的容器,响应体的内容将在稍后通过BodyInserter插入到容器中,最终发送到客户端作为HTTP响应的一部分
			CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);

			//BodyInserterContext上下文主要负责在处理请求和响应时提供配置信息和策略,通过维护了的ExchangeStrategies对象来装配默认的消息编解码、读取写入器和非阻塞I/O策略等
			//BodyInserter接口对象执行insert方法返回了一个Mono<Void>,表示异步的插入操作结果,不包含任何数据
			return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> { //Mono.defer()创建了一个新的Mono对象
				ServerHttpRequest decorator = decorate(exchange, headers, outputMessage); //构建新的request
				return chain.filter(exchange.mutate().request(decorator).build()); //构建新的exchange对象
			}));
		};
	}

	/**
	 * 原文解密
	 *
	 * @return
	 */
	private Function decryptAES() {
		return s -> {
			// 构建前端对应解密AES 因子
			AES aes = new AES(Mode.CFB, Padding.NoPadding,
					new SecretKeySpec(gatewayConfig.getEncodeKey().getBytes(), KEY_ALGORITHM),
					new IvParameterSpec(gatewayConfig.getEncodeKey().getBytes()));

			// 获取请求密码并解密
			Map<String, String> inParamsMap = HttpUtil.decodeParamMap((String) s, CharsetUtil.CHARSET_UTF_8);
			if (inParamsMap.containsKey(PASSWORD)) {
				String password = aes.decryptStr(inParamsMap.get(PASSWORD));
				// 返回修改后报文字符
				inParamsMap.put(PASSWORD, password);
			} else {
				log.error("非法请求数据:{}", s);
			}
			return Mono.just(HttpUtil.toParams(inParamsMap, Charset.defaultCharset(), true));
		};
	}

	/**
	 * 报文转换
	 *
	 * @return
	 */
	private ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers,
												CachedBodyOutputMessage outputMessage) {
		return new ServerHttpRequestDecorator(exchange.getRequest()) {
			@Override
			public HttpHeaders getHeaders() {
				long contentLength = headers.getContentLength();
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.putAll(super.getHeaders());
				if (contentLength > 0) {
					httpHeaders.setContentLength(contentLength);
				} else {
					httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
				}
				return httpHeaders;
			}

			@Override
			public Flux<DataBuffer> getBody() {
				return outputMessage.getBody();
			}
		};
	}

}

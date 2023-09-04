package com.lsl.microservices.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsl.microservices.gateway.filter.PasswordDecoderFilter;
import com.lsl.microservices.gateway.filter.RequestGlobalFilter;
import com.lsl.microservices.gateway.handler.GlobalExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {

    @Bean
    public PasswordDecoderFilter passwordDecoderFilter(GatewayConfigProperties configProperties) {
        return new PasswordDecoderFilter(configProperties);
    }

    @Bean
    public RequestGlobalFilter pigRequestGlobalFilter() {
        return new RequestGlobalFilter();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
        return new GlobalExceptionHandler(objectMapper);
    }
}

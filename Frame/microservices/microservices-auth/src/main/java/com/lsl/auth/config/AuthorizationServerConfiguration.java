package com.lsl.auth.config;

import com.lsl.auth.core.CustomerOAuth2TokenCustomizer;
import com.lsl.auth.core.DaoAuthenticationProvider;
import com.lsl.auth.core.FormIdentityLoginConfigurer;
import com.lsl.auth.support.CustomerOAuth2AccessTokenGenerator;
import com.lsl.auth.support.handler.LoginFailureHandler;
import com.lsl.auth.support.handler.LoginSuccessHandler;
import com.lsl.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.lsl.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.lsl.constant.SecurityConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.Arrays;

//认证服务器配置
@SuppressWarnings("all")
@Configuration
public class AuthorizationServerConfiguration {

    @Bean
    public JdbcTemplate oauth2JdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate oauth2JdbcTemplate) {
        return new JdbcRegisteredClientRepository(oauth2JdbcTemplate);
    }

    @Bean
    public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcTemplate oauth2JdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(oauth2JdbcTemplate, registeredClientRepository);
    }

    @Bean
    public OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService(JdbcTemplate oauth2JdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(oauth2JdbcTemplate, registeredClientRepository);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, OAuth2AuthorizationService authorizationService) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http.apply(authorizationServerConfigurer
                .tokenEndpoint(tokenEndpoint -> { //自定义认证授权端点
                    tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter())  //注入自定义的授权认证转换器
                            .accessTokenResponseHandler(new LoginSuccessHandler()) //登录成功处理器
                            .errorResponseHandler(new LoginFailureHandler()); //登录失败处理器
                })
                .clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // 自定义客户端认证
                        oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new LoginFailureHandler()))// 处理客户端认证异常
                .authorizationEndpoint(authorizationEndpoint ->
                        authorizationEndpoint.consentPage(SecurityConstant.CUSTOM_CONSENT_PAGE_URI))); //授权码端点自定义确认页面

        //无须拦截的请求路径
        AntPathRequestMatcher[] requestMatchers = new AntPathRequestMatcher[]{
                AntPathRequestMatcher.antMatcher("/token/**"), AntPathRequestMatcher.antMatcher("/actuator/**"),
                AntPathRequestMatcher.antMatcher("/css/**"), AntPathRequestMatcher.antMatcher("/error")};

        http.authorizeHttpRequests(authorizeRequests -> {
                    // 自定义接口、端点暴露
                    authorizeRequests.requestMatchers(requestMatchers).permitAll();
                    authorizeRequests.anyRequest().authenticated();
                })
                .apply(authorizationServerConfigurer.authorizationService(authorizationService)); // Jdbc存储token的实现

        http.apply(new FormIdentityLoginConfigurer());
        DefaultSecurityFilterChain securityFilterChain = http.build();

        // 注入自定义授权模式实现
        addCustomOAuth2GrantAuthenticationProvider(http);
        return securityFilterChain;
    }

    /**
     * request -> xToken 注入请求转换器
     *
     * @return DelegatingAuthenticationConverter
     */
    private AuthenticationConverter accessTokenRequestConverter() {
        return new DelegatingAuthenticationConverter(Arrays.asList(
                new OAuth2ResourceOwnerPasswordAuthenticationConverter(), //自定义密码模式转换器
                new OAuth2RefreshTokenAuthenticationConverter(),
                new OAuth2ClientCredentialsAuthenticationConverter(),
                new OAuth2AuthorizationCodeAuthenticationConverter(),
                new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
    }

    /**
     * 注入授权模式实现提供方
     * 密码模式 </br>
     */
    private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

        OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
                authenticationManager, authorizationService, oAuth2TokenGenerator());

        // 处理 UsernamePasswordAuthenticationToken
        http.authenticationProvider(new DaoAuthenticationProvider());
        // 处理 OAuth2ResourceOwnerPasswordAuthenticationToken
        http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
    }

    /**
     * 令牌生成规则实现 </br>
     * client:username:uuid
     *
     * @return OAuth2TokenGenerator
     */
    @Bean
    public OAuth2TokenGenerator oAuth2TokenGenerator() {
        CustomerOAuth2AccessTokenGenerator accessTokenGenerator = new CustomerOAuth2AccessTokenGenerator();
        // 注入Token 增加关联用户信息
        accessTokenGenerator.setAccessTokenCustomizer(new CustomerOAuth2TokenCustomizer());
        return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
    }
}

package com.lsl.microservices.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.cloud.gateway", ignoreUnknownFields = false)
public class GatewayConfigProperties {

    private String encodeKey;

}

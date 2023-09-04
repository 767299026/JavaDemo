package com.lsl.auth;

import com.lsl.feign.annotation.EnableServerFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@EnableServerFeignClients
@SpringBootApplication(exclude = {ManagementWebSecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
public class MicroservicesAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesAuthApplication.class);
    }
}

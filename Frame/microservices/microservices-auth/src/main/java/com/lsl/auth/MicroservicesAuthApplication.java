package com.lsl.auth;

import com.lsl.feign.annotation.EnableServerFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@EnableServerFeignClients
@SpringBootApplication
public class MicroservicesAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesAuthApplication.class);
    }
}

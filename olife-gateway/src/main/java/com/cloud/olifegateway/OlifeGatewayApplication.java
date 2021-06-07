package com.cloud.olifegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OlifeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlifeGatewayApplication.class, args);
    }

}

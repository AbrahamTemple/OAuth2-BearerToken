package com.cloud.vongadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VongAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(VongAdminApplication.class, args);
    }

}

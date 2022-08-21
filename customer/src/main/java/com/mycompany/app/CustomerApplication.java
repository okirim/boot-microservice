package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {"com.mycompany.amqp","com.mycompany.app"}
)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.mycompany.client")
public class CustomerApplication {

    public static void main(String[] args) {
        System.out.println("CustomerApplication.main");
        SpringApplication.run(CustomerApplication.class, args);
    }
}

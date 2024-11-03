package com.optilab.laboratoire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LaboratoireApplication {
    public static void main(String[] args) {
        SpringApplication.run(LaboratoireApplication.class, args);
    }
}

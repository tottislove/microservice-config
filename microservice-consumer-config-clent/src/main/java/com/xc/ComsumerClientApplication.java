package com.xc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author totti
 * @date 2019/10/17.
 */

@SpringBootApplication
@EnableEurekaClient
public class ComsumerClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComsumerClientApplication.class, args);
    }
}

package com.xc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author totti
 * @date 2019/10/21.
 */
@SpringBootApplication
@EnableEurekaClient
public class HiberProvider1Appliacation {
    public static void main(String[] args) {
        SpringApplication.run(HiberProvider1Appliacation.class, args);
    }
}

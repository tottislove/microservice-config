package com.xc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author totti
 * @date 2019/10/16.
 */
@SpringBootApplication
@EnableEurekaClient  //本服务启动后会自动注册进eureka服务中
@EnableCircuitBreaker
public class HystrixProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixProviderApplication.class, args);
    }
}

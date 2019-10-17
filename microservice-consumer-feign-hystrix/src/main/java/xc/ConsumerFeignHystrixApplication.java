package xc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author totti
 * @date 2019/10/16.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignHystrixApplication.class, args);
    }
}

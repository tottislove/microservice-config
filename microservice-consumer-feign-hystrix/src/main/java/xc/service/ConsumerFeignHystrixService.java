package xc.service;

import com.xc.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
@FeignClient(value = "microservice-provider", fallbackFactory = ConsumerServiceFallbackFactory.class)
public interface ConsumerFeignHystrixService {
    /**
     * 调用接口中的get方法，即可以向microservicecloud-provider微服务发送/get/{id}请求
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    User get(@PathVariable("id") int id);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    boolean add(User user);

    @RequestMapping(value = "/getUser/list", method = RequestMethod.GET)
    List<User> getAll();

}

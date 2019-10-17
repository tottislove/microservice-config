package xc.service;

import com.xc.domain.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
@Component
public class ConsumerServiceFallbackFactory implements FallbackFactory<ConsumerFeignHystrixService> {
    @Override
    public ConsumerFeignHystrixService create(Throwable arg0) {
        // TODO Auto-generated method stub
        return new ConsumerFeignHystrixService() {

            @Override
            public List<User> getAll() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public User get(int id) {
                User user = new User(id, "该用户不存在", 0);
                return user;
            }

            @Override
            public boolean add(User user) {
                // TODO Auto-generated method stub
                return false;
            }
        };
    }


}

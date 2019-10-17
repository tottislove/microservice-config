package com.xc.action;

import com.xc.domain.User;
import com.xc.service.ConsumerFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
@RestController
public class ConsumerFeignAction {

    @Autowired
    private ConsumerFeignService service;

    @RequestMapping(value = "/consumer/add")
    public boolean addUser(User user) {
        Boolean flag = service.add(user);
        return flag;
    }

    @RequestMapping(value = "/consumer/get/{id}")
    public User get(@PathVariable("id") int id) {
        User user = service.get(id);
        return user;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/consumer/list")
    public List<User> getList() {
        List list = service.getAll();
        return list;
    }

}

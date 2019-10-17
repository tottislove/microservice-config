package com.xc.service.impl;

import com.xc.dao.UserDao;
import com.xc.domain.User;
import com.xc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        boolean flag;
        flag = userDao.addUser(user);
        return flag;
    }

    @Override
    public User getUser(int id) {
        User user = userDao.getUser(id);
        System.out.println("microservice-provider2微服务在响应客户端请求……");
        System.out.println("user : " + user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userDao.getUsers();
        return users;
    }
}

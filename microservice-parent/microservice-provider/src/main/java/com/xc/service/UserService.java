package com.xc.service;

import com.xc.domain.User;

import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
public interface UserService {
    boolean addUser(User user);

    User getUser(int id);

    List<User> getUsers();
}

package com.xc.dao;

import com.xc.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
@Mapper
public interface UserDao {
    boolean addUser(User user);

    User getUser(int id);

    List<User> getUsers();


}

package com.xc.action;

import com.xc.domain.User;
import com.xc.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author totti
 * @date 2019/10/16.
 */
@RestController
public class UserAction {

    @Resource
    private UserService userService;

    @PostMapping(value = "/add")
    public boolean addUser(@RequestBody User user) {
        boolean flag = userService.addUser(user);
        return flag;
    }

    @GetMapping(value = "/get/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/getUser/list", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

}

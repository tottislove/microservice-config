package com.xc.action;

import com.xc.service.HiberProvider1Service;
import com.xc.vo.UserTableVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author totti
 * @date 2019/10/21.
 */
@RestController
@RequestMapping(value = "/hiber")
public class HiberProvider1Action {

    @Resource
    private HiberProvider1Service hiberProvider1Service;


    @GetMapping(value = "/{id}")
    public UserTableVo get(@PathVariable int id) {
        return hiberProvider1Service.get(id);
    }

}

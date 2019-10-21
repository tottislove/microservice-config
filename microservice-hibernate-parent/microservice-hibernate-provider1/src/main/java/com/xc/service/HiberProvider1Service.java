package com.xc.service;

import com.xc.dao.HiberProvider1Dao;
import com.xc.vo.UserTableVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author totti
 * @date 2019/10/21.
 */
@Service
public class HiberProvider1Service {

    @Resource
    private HiberProvider1Dao hiberProvider1Dao;

    @Transactional
    public UserTableVo get(int id) {
        return hiberProvider1Dao.get(id).toVo();
    }
}

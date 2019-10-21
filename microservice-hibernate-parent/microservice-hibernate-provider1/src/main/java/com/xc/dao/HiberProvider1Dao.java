package com.xc.dao;

import com.xc.domain.User;
import com.xc.utils.HibernateUtils;
import org.springframework.stereotype.Repository;

/**
 * @author totti
 * @date 2019/10/21.
 */
@Repository
public class HiberProvider1Dao {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public User get(int id) {
//        return ((HibernateEntityManager)entityManager).getSession().get(User.class,id);
//    }

    public User get(int id) {
        return HibernateUtils.getSession().get(User.class, id);
    }
}

package com.xc.plugin;

/**
 * Created by xiongying on 16/5/9.
 */
public class SessionFactorySupport {

    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        System.out.println("SessionFactorySupport setSessionFactory...");
        SessionFactory.getInstance().sessionFactory = sessionFactory;
    }
}

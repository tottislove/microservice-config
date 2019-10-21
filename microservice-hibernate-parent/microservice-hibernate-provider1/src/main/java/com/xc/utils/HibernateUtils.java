package com.xc.utils;

import com.xc.plugin.SessionFactory;
import org.hibernate.Session;

/**
 * Created by xiongying on 16/7/19.
 */
public class HibernateUtils {

    public static Session getSession() {
        return SessionFactory.getInstance().getSession();
    }

    public static long nextSeq(String seqName) {
        return SessionFactory.getInstance().nextSeq(seqName);
    }
}

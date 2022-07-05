package com.pei.learn.basic.multiThread;

import org.hibernate.Session;


public class T02_00_ThreadLocal_Session {
//    private static final ThreadLocal threadSession = new ThreadLocal();
//
//    public static Session getSession() throws InfrastructureException {
//        Session s = (Session) threadSession.get();
//        try {
//            if (s == null) {
//                s = getSessionFactory().openSession();
//                threadSession.set(s);
//            }
//        } catch (HibernateException ex) {
//            throw new InfrastructureException(ex);
//        }
//        return s;
//    }
}

package com.pfe.gestioncarburant.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static HibernateUtil instance;
    private Configuration cfg;
    private SessionFactory sessionFactory;
 
    private HibernateUtil() throws HibernateException {
 
        // build the config
        cfg = new AnnotationConfiguration().configure();
         
        sessionFactory = cfg.buildSessionFactory();
    }
 
    public static  HibernateUtil getInstance() throws HibernateException {
    
        if (instance == null) {
        
            instance = new HibernateUtil();
        }
 
        return instance;
    }
 
    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
 
    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }
}

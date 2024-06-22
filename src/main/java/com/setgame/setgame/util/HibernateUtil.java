package com.setgame.setgame.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;

// Stellt eine Hibernate SessionFactory zur Verfügung
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Erstellt eine neue SessionFactory
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Gibt die SessionFactory zurück
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

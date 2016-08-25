/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Wendy
 */
public class HibernateFactory {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();

    @SuppressWarnings("deprecation")
    private static SessionFactory buildSessionFactory()
    {
        try
        {

            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            return sessionFactory;        
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    
}
//Configuration conf = new Configuration();
//SessionFactory firebirdSF = Configuration.configure("hibernateFB.cfg.xml").buildSessionFactory();
//
//SessionFactory mySQLSF = Configuration.configure("hibernateMS.cfg.xml").buildSessionFactory();
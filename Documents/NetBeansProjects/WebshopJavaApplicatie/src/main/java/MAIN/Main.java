package MAIN;

import Controller.HoofdMenuController;
import DAOs.AdresDao;
import DAOs.ArtikelDao;
import DAOGenerics.GenericDaoImpl;
import DAOs.KlantDao;
import Helpers.HibernateSessionFactory;
import POJO.Adres;
import POJO.Artikel;
import POJO.Klant;

import TestHibernate.HibernateTest;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Excen
 */

public class Main {       
   
   private static Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
   private static Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
  
   
    public static void main (String[]args) throws SQLException, ClassNotFoundException, FileNotFoundException {    

      HoofdMenuController start = new HoofdMenuController();
      start.start();
        
       
    } 
    
//     public static void editLog(){
//        Properties p = new Properties(System.getProperties());
//        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
//        p.put("com.mchange.v2.c3p0.SQLWarnings", "com.mchange.v2.c3p0");
//        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");// Off or any other level
//        //p.put ("org.firebirdsql.jdbc.FBSQLWarning:")
//        System.setProperties(p);  
//    }  

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_WebshopJavaApplicatie_jar_1.0-SNAPSHOTPU2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

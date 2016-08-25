package MAIN;

import TestHibernate.HibernateTest;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Properties;
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
         
 

//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        lc.setPackagingDataEnabled(false);
              
        HibernateTest start = new HibernateTest();
        System.out.println();
        start.hibernateSession();
      
//        HoofdMenuController start = new HoofdMenuController();
//        System.out.println();
//        testLogger.debug("Toetreden tot de webshop");
//        LOGGER.debug("U kunt werken in het bestand");
//        System.out.println();
//        editLog();
//        start.setConnectionPool();
        
        
       
    } 
    
//     public static void editLog(){
//        Properties p = new Properties(System.getProperties());
//        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
//        p.put("com.mchange.v2.c3p0.SQLWarnings", "com.mchange.v2.c3p0");
//        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");// Off or any other level
//        //p.put ("org.firebirdsql.jdbc.FBSQLWarning:")
//        System.setProperties(p);  
//    }  
}

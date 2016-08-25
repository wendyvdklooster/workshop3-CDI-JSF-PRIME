package MAIN;

import Controller.HoofdMenuController;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.db.names.*;
import java.util.Properties;

/**
 *
 * @author Excen
 */

public class Main {   
    
    Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    private static final ch.qos.logback.classic.Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop");  
   private static final ch.qos.logback.classic.Logger errorLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.err");
   private static final ch.qos.logback.classic.Logger testLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.test");
   static{
        // Logger.ROOT_LOGGER_NAME == "rootLogger" :Level OFF      
        LOGGER.setLevel(Level.DEBUG);   
        errorLogger.setLevel(Level.ERROR);   
        // testLogger inherits Level debug
       }   
    
    public static void main (String[]args) throws SQLException, ClassNotFoundException, FileNotFoundException {    
         
//        // assume SLF4J is bound to logback in the current environment
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        // print logback's internal status
//        StatusPrinter.print(lc);

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.setPackagingDataEnabled(true);
        
         
        
        
      
        HoofdMenuController start = new HoofdMenuController();
        System.out.println();
        testLogger.debug("Toetreden tot de webshop");
        LOGGER.debug("U kunt werken in het bestand");
        System.out.println();
        editLog();
        start.setConnectionPool();
        
        
       
    } 
    
     public static void editLog(){
        Properties p = new Properties(System.getProperties());
        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        p.put("com.mchange.v2.c3p0.SQLWarnings", "com.mchange.v2.c3p0");
        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");// Off or any other level
        //p.put ("org.firebirdsql.jdbc.FBSQLWarning:")
        System.setProperties(p);  
    }  
}

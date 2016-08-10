package MAIN;

import Controller.HoofdMenuController;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Excen
 */

public class Main {   
    
    Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    //private static ch.qos.logback.classic.Logger errorLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.err");
    private static ch.qos.logback.classic.Logger testLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.test");
    
    
   
    
    public static void main (String[]args) throws SQLException, ClassNotFoundException, FileNotFoundException {    
         
//        // assume SLF4J is bound to logback in the current environment
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        // print logback's internal status
//        StatusPrinter.print(lc);

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.setPackagingDataEnabled(true);
        
      
        HoofdMenuController start = new HoofdMenuController();
        start.setConnectionPool();
        logger.info("U kunt werken in het klanten bestand");
        testLogger.debug("Toetreden tot bestand werkt");
       
    } 
}

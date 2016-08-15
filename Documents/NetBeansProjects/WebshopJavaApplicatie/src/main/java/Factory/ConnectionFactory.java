/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import ConnectionPool.C3p0CPFB;
import ConnectionPool.C3p0CPSQL;
import ConnectionPool.HikariCPFB;
import ConnectionPool.HikariCPSQL;
import ch.qos.logback.classic.Level;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import javax.activation.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Anne
 */
public class ConnectionFactory {
    
   private static final ch.qos.logback.classic.Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop");  
   private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
   private static final ch.qos.logback.classic.Logger testLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.test");
   
   static{
        // Logger.ROOT_LOGGER_NAME == "rootLogger" :Level OFF      
        LOGGER.setLevel(Level.DEBUG);
        // testLogger inherits Level debug
       }   

      static DaoFactory daoFactory = new DaoFactory();
      static String databaseSetting;
            
      private static String connectionPoolSetting = "Hikari";
           
      
      public void setConnectionPool(String connectionPoolSetting) {
       ConnectionFactory.connectionPoolSetting = connectionPoolSetting;
      }
      
      public static String getConnectionPool() {
       return connectionPoolSetting;
      }   
      
      
  
            
    public static Connection getConnection() {
       HikariDataSource hikari = null;
       ComboPooledDataSource c3p0 = null;
       databaseSetting = daoFactory.getDatabaseSetting();
       testLogger.debug("databasesetting in getconnection()" + databaseSetting);
       Connection con = null;
        if (connectionPoolSetting.equals("Hikari")) {
            if (databaseSetting.equals("MySQL")) {
               hikari = HikariCPSQL.getDataSource();
               con = getHikariConnection(hikari);
               LOGGER.debug("test getconnection- hikari - mysql");
               return con;
            }
            else if (databaseSetting.equals("FireBird")) {
               hikari = HikariCPFB.GetDataSource();
               con = getHikariConnection(hikari);
               LOGGER.debug("test getconnection- hikari - fb");
               return con;
            }   
        }
        if (connectionPoolSetting.equals("C3p0")) {
            if (databaseSetting.equals("MySQL")) {
                c3p0 = C3p0CPSQL.getDataSource();
                con = getC3p0Connection(c3p0);
                LOGGER.debug("test getconnection- C3p0 - mysql");
                return con;
            }
            else if (databaseSetting.equals("FireBird")) {
                c3p0 = C3p0CPFB.getDataSource();
                con = getC3p0Connection(c3p0);
                LOGGER.debug("test getconnection- C3p0 - fb");
                return con;
            }
        }
        else {
             hikari = HikariCPSQL.getDataSource();
             con = getHikariConnection(hikari);
             return con;
        }
        LOGGER.debug("getConnection before return");
        return con;
    }
    
       public static Connection getHikariConnection(HikariDataSource hikari) {
        Connection con = null;
        try {
            con = hikari.getConnection();
        }
        catch (SQLException ex) {
            errorLogger.error(" ", ex);
        }
        return con;
    }

    public static Connection getC3p0Connection(ComboPooledDataSource c3p0) {
        Connection con = null;
        try {
            con = c3p0.getConnection();
        }
        catch (SQLException ex) {
            errorLogger.error(ex.toString());
        }
        return con;
    }
    
}

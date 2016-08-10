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
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import javax.activation.DataSource;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Anne
 */
public class ConnectionFactory {
      private static final Logger LOGGER = LoggerFactory.getLogger("com.webshop.connFactory");
      private static final Logger errorLogger = LoggerFactory.getLogger("com.webshop.err");

      static DaoFactory daoFactory = new DaoFactory();
      static String databaseSetting = daoFactory.getDatabaseSetting();
            
      private static String connectionPoolSetting = "Hikari";
           
      
      public void setConnectionPool(String connectionPoolSetting) {
       ConnectionFactory.connectionPoolSetting = connectionPoolSetting;
      }
      
      public String getConnectionPool() {
       return connectionPoolSetting;
      }   
      
            
    public static Connection getConnection() {
       HikariDataSource hikari = null;
       ComboPooledDataSource c3p0 = null;
       Connection con = null;
        if (connectionPoolSetting.equals("Hikari")) {
            if (databaseSetting.equals("MySQL")) {
               hikari = HikariCPSQL.getDataSource();
               con = getHikariConnection(hikari);
               return con;
            }
            else if (databaseSetting.equals("FireBird")) {
               hikari = HikariCPFB.GetDataSource();
               con = getHikariConnection(hikari);
               return con;
            }   
        }
        if (connectionPoolSetting.equals("C3p0")) {
            if (databaseSetting.equals("MySQL")) {
                c3p0 = C3p0CPSQL.getDataSource();
                con = getC3p0Connection(c3p0);
                return con;
            }
            else if (databaseSetting.equals("FireBird")) {
                c3p0 = C3p0CPFB.getDataSource();
                con = getC3p0Connection(c3p0);
                return con;
            }
        }
        else {
             hikari = HikariCPSQL.getDataSource();
             con = getHikariConnection(hikari);
             return con;
        }
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

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
import static ConnectionPool.C3p0CPFB.getDataSource;


/**
 *
 * @author Anne
 */
public class ConnectionFactory {
      

      static DaoFactory daoFactory = new DaoFactory();
      static String databaseSetting = daoFactory.getDatabaseSetting();
            
      private static String connectionPoolSetting = "Hikari";
           
      
      public void setConnectionPool(String connectionPoolSetting) {
       this.connectionPoolSetting = connectionPoolSetting;
      }
      
      public String getConnectionPool() {
       return connectionPoolSetting;
      }
    
      
            
    public static HikariDataSource getHikari() {
       if (connectionPoolSetting.equals("Hikari")) {
           if (databaseSetting.equals("MySQL")) {
               return new HikariCPSQL().getDataSource();
           }
           else if (databaseSetting.equals("FireBird"))
                return new HikariCPFB().GetDataSource();   
      }
       return new HikariCPSQL().getDataSource();
    }
    public static ComboPooledDataSource getC3p0() {
      if (connectionPoolSetting.equals("C3p0")) {
          if (databaseSetting.equals("MySQL")) 
                return new C3p0CPSQL().getDataSource();
          else if (databaseSetting.equals("FireBird"))
              return new C3p0CPFB().getDataSource();
      }
      return new C3p0CPSQL().getDataSource();
    }
    
}

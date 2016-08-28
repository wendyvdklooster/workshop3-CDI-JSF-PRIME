/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionPool;


import ch.qos.logback.classic.Level;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C3p0CPSQL {
    
   private static ch.qos.logback.classic.Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.dao");  
   private static ch.qos.logback.classic.Logger errorLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.err");
   private static ch.qos.logback.classic.Logger testLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.webshop.test");
   static{
        // Logger.ROOT_LOGGER_NAME == "rootLogger" :Level OFF      
        LOGGER.setLevel(Level.WARN);   
        errorLogger.setLevel(Level.ERROR);  
        testLogger.setLevel(Level.OFF);
        // testLogger inherits Level debug
       }   
    
    private C3p0CPSQL datasource;
    private static ComboPooledDataSource cpds;
    static Connection con;

    public static ComboPooledDataSource getDataSource() {
        cpds = new ComboPooledDataSource();
        // cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false");
        cpds.setUser("Anjewe");
        cpds.setPassword("Koetjes");

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(500);
        cpds.setMaxStatementsPerConnection(50);
        
        testLogger.debug("C3p0CPSQL url: " + cpds.getJdbcUrl() );
        
        return cpds;
    }
    
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            errorLogger.error("Error: ", ex);
        }

        cpds = getDataSource();

        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
            con = cpds.getConnection();
        } catch (SQLException ex) { 
            errorLogger.error("Error: ", ex);
        }
        
        return con;
    }
    
    /**
    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
*/
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.*;
import java.sql.Connection;
import java.sql.SQLException;
import javassist.bytecode.Bytecode;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import java.util.Properties;

/**
 *
 * @author Anne
 */
public class C3p0CPFB {    
     
      
   private static Logger LOGGER = (Logger) LoggerFactory.getLogger("com.webshop.dao");  
   private static Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
   private static Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   static{
        // Logger.ROOT_LOGGER_NAME == "rootLogger" :Level OFF      
        LOGGER.setLevel(Level.DEBUG);   
        errorLogger.setLevel(Level.ERROR);   
        // testLogger inherits Level debug
       }   
    
    private C3p0CPSQL datasource;
    private static ComboPooledDataSource cpds;
    static Connection con;

    public static ComboPooledDataSource getDataSource() {
        cpds = new ComboPooledDataSource();
        // cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:firebirdsql:localhost/3050:C:\\FBDB\\fbdb.FDB");
        cpds.setUser("Anjewe");
        cpds.setPassword("Koetjes");

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(500);
        cpds.setMaxStatementsPerConnection(50);
        
        
        testLogger.debug("C3p0CPF url: " + cpds.getJdbcUrl() );
        
        
        
        return cpds;
    }
    
    
    public static Connection getConnection() {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
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
            errorLogger.error(" ", ex);
        }
        
        return con;
    }

    
}


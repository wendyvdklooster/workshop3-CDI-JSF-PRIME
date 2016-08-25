/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anne
 */
public class HikariCPFB {
       
/*
    // MySQL
    String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
    String user = "Anjewe";
    String pw = "Koetjes";
    String driver = "com.mysql.jdbc.Driver";
    
    // FireBird
    private final String url = "jdbc:firebirdsql:localhost:C:\\FBDB\\FBDB.FDB";
    private final String user = "Anjewe";
    private final String pw = "Koetjes";
    private final String driver = "org.firebirdsql.jdbc.FBDriver";
   
    */
    private final static Logger LOGGER = LoggerFactory.getLogger(HikariCPFB.class.getName());
    
    private static Connection con;
    private static HikariConfig config = new HikariConfig();
    private static String url;
    private static String user;
    private static String password;
    
    // For now HardCoded info
    
    private static String urlHC = "jdbc:firebirdsql:localhost/3050:C:\\\\FBDB\\\\FBDB.FDB";
    private static String userHC = "Anjewe";
    private static String passwordHC = "Koetjes";
    private static String driver = "org.firebirdsql.jdbc.FBDriver";
    
    public static HikariDataSource GetDataSource() {

        config.setJdbcUrl(urlHC);
        config.setUsername(userHC);
        config.setPassword(passwordHC);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(3);
        config.setIdleTimeout(28740000);
        config.setMaxLifetime(28740000);
        config.setConnectionTimeout(34000);

        HikariDataSource ds = new HikariDataSource(config);

        return ds;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException cnfe) {
            LOGGER.error("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            LOGGER.error("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            LOGGER.error("Error: " + iae.getMessage());
        }

        HikariDataSource ds = GetDataSource();
        if (con != null && !con.isClosed()) {
            con.close();
            con = null;
        }
        con = ds.getConnection();

        return con;
    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Anne
 */
public class C3p0CPFB {
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
        
        System.out.println("Returning ComboPooledDataSource \n");
        return cpds;
    }
    
    
    public static Connection getConnection() {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        cpds = getDataSource();

        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
            con = cpds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Returning c3p0 Connection " + con);
        return con;
    }

    
}


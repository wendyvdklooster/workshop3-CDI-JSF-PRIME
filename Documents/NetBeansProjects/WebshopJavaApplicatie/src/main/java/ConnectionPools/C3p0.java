/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionPools;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0 {

    private C3p0 datasource;
    private static ComboPooledDataSource cpds;
    static Connection con;

    public static ComboPooledDataSource C3p0() {
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
        
        System.out.println("Returning ComboPooledDataSource \n");
        return cpds;
    }
    
    
    public static Connection connectWithC3p0() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        ComboPooledDataSource cpds = C3p0();

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
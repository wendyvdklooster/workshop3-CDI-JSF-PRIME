/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.MySQL;

import DAOs.Interface.BestellingDAOInterface;
import Factory.ConnectionFactory;
import POJO.Bestelling;
import java.sql.*;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Excen
 */
public class BestellingDAOSQL implements BestellingDAOInterface {
    
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BestellingDAOSQL.class.getName());
    // Info inlog SQL
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;
    
    
    @Override
    public int insertBestelling(long klantId)  {
        
        java.util.Date datum = new java.util.Date();
        int bestellingId = 0;
        
        // Schrijf waarden weg in SQL tabel.
        String sqlQuery = "insert into bestelling (klant_id, datum_aangemaakt) values (?, ?)";
        
       try {
        
        Connection con = ConnectionFactory.getConnection();
            pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, klantId);
            pstmt.setDate(2, new java.sql.Date(datum.getTime()));
            
            int affectedRows = pstmt.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    } 

                    rs = pstmt.getGeneratedKeys();
                    if (rs.isBeforeFirst()){
                        if (rs.next()) 
                            bestellingId = rs.getInt(1);                         
                    } 

        } 
        catch ( SQLException ex) {
            LOGGER.error("", ex);
        }
      return bestellingId;
    }
    
    
    @Override
    public ArrayList<Bestelling> findAll() {
        
        ArrayList<Bestelling>bestellinglijst = new ArrayList<>();
        
        String sqlQuery = "select * from Bestelling";
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
        
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Bestelling bestelling = new Bestelling();
                bestelling.setBestellingId(rs.getInt("bestelling_id"));
                bestelling.setKlantId(rs.getInt("klant_id"));
                // Timestamp?
                java.sql.Date sqlDate = rs.getDate("datum_aangemaakt");

                bestelling.setDatum(new java.util.Date(sqlDate.getTime()));

                // add bestelling in de list
                bestellinglijst.add(bestelling);
                }
        } catch (SQLException ex) {
            LOGGER.error("", ex);
        }
    return bestellinglijst;  
    }
    
    
    @Override
    public Bestelling findById(long bestellingId) {
        
        String sqlQuery = "select bestelling_id, klant_id from bestelling where bestelling_id = " + bestellingId;
        Bestelling bestelling = new Bestelling();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
        
        pstmt = con.prepareStatement(sqlQuery);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {            
            
            bestelling.setBestellingId(rs.getInt("bestelling_id"));
            bestelling.setKlantId(rs.getInt("klant_id"));
            }   
        
        } catch (SQLException ex) {
            LOGGER.error("", ex);
        }
     return bestelling;
    }
  
    
    @Override
    public boolean deleteBestelling(long bestellingId) {
        
        String sqlQuery = "delete * from bestelling where bestelling_id = " + bestellingId;
        boolean isDeleted = false;        
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.executeUpdate();
            boolean bestellingFound = rs.next();
            
            if (bestellingFound){
                isDeleted = true;
            }
            
        } catch ( SQLException ex) {
            LOGGER.error(" ", ex);
        }
        
        return isDeleted;
    }
    
    @Override
    public void deleteAll(){
        
        String sqlQuery = "delete from bestelling";
        
       try {
        
        Connection con = ConnectionFactory.getConnection();
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.executeUpdate();
                        
        } catch ( SQLException ex) {
           LOGGER.error(" ", ex);
        }
        
    }
    
    /* 
    Create a Java Connection to our MySQL database.
    Create a SQL DELETE query statement.
    Create a Java PreparedStatement for our SQL DELETE query.
    Set the fields on our Java PreparedStatement object.
    Execute our Java PreparedStatement.
    Close our Java MySQL database connection.
    Catch any SQL exceptions that may come up during the process.
    */

    @Override
    public ArrayList<Bestelling> findByKlantId(long klantId) {
        String sqlQuery = "select bestelling_id, klant_id from bestelling where klant_id = ? ";
        Bestelling bestelling = new Bestelling();
        ArrayList<Bestelling> bestellingLijst = new ArrayList();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
        
        pstmt = con.prepareStatement(sqlQuery);
        pstmt.setLong(1, klantId); 
        rs = pstmt.executeQuery();
        
        while (rs.next()) {            
            
            bestelling.setBestellingId(rs.getInt("bestelling_id"));
            bestelling.setKlantId(rs.getInt("klant_id"));
            bestellingLijst.add(bestelling);
        }   
        
        } catch (SQLException ex) {
            LOGGER.error("", ex);
        }
     return bestellingLijst; 
    }
    
    }
    
    

    

    
    

    


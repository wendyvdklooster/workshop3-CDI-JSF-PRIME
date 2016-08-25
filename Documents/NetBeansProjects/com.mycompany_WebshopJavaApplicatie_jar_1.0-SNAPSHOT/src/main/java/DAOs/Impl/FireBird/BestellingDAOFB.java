/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Impl.MySQL.BestellingArtikelDAOSQL;
import DAOs.Impl.MySQL.BestellingDAOSQL;
import DAOs.Interface.BestellingDAOInterface;
import Factory.ConnectionFactory;
import POJO.Bestelling;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Excen
 */
public class BestellingDAOFB implements BestellingDAOInterface{
    
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BestellingDAOFB.class.getName());
    // Info inlog FireBird    
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;

    
    // Methods
    
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
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    return bestellinglijst; 
    }

    
    @Override
    public Bestelling findById(int bestelling_id) {
       String sqlQuery = "select bestelling_id, klant_id from bestelling where bestelling_id = ?";
        Bestelling bestelling = new Bestelling();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, bestelling_id);
            rs = pstmt.executeQuery();
        
            while (rs.next()) {            
            
            bestelling.setBestellingId(rs.getInt("bestelling_id"));
            bestelling.setKlantId(rs.getInt("klant_id"));
            }   
        
         } catch (SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
         }
         return bestelling; 
    }

    
    @Override
    public int insertBestelling(int klant_id) {
        
        java.util.Date datum = new java.util.Date();
        int bestellingId = 0;
        String sqlQuery = "INSERT INTO bestelling(klant_id, datum_aangemaakt) VALUES (?, ?) RETURNING bestelling_id";

        try {
        
        Connection con = ConnectionFactory.getConnection();  
            pstmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, klant_id);
            pstmt.setDate(2, new java.sql.Date(datum.getTime()));
            
            int affectedRows = pstmt.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Bestelling aanmaken mislukt.");
                    } 

                    rs = pstmt.getGeneratedKeys();
                    if (rs.isBeforeFirst()){
                        if (rs.next()) 
                            bestellingId = rs.getInt(1);                         
                    } 

        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
      return bestellingId;
    }

    
    @Override
    public boolean deleteBestelling(int bestelling_id) {
        
    boolean isDeleted = false;

        try {
        
        Connection con = ConnectionFactory.getConnection();  
            
                String sqlQuery = "delete from bestelling where bestelling_id =  ? ";

                PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                preparedStmt.setInt(1, bestelling_id);
                // execute the preparedstatement
                int rowsAffected = preparedStmt.executeUpdate();
                if (rowsAffected != 0) {
                    isDeleted = true;
                }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return isDeleted;
    }

    
    @Override
    public void deleteAll() {
        String sqlQuery = "delete from bestelling";
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.executeUpdate();
                        
        } catch ( SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public ArrayList<Bestelling> findByKlantId(int klantId) {
        String sqlQuery = "select bestelling_id, klant_id from bestelling where klant_id = ? ";
        Bestelling bestelling = new Bestelling();
        ArrayList<Bestelling> bestellingLijst = new ArrayList();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
        
        pstmt = con.prepareStatement(sqlQuery);
        pstmt.setInt(1, klantId); 
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

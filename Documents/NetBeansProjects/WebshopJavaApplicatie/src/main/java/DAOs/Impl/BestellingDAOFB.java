/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

import DAOs.Interface.BestellingDAOInterface;
import POJO.Bestelling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Excen
 */
public class BestellingDAOFB implements BestellingDAOInterface{
    
    // Info inlog FireBird
    private final String url = "jdbc:firebirdsql:localhost:C:\\FBDB\\FBDB.FDB";
    private final String user = "Anjewe";
    private final String pw = "Koetjes";
    private final String driver = "org.firebirdsql.jdbc.FBDriver";
    
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    
    
    
    

    @Override
    public ArrayList<Bestelling> findAll() {
        ArrayList<Bestelling>bestellinglijst = new ArrayList<>();
        
        String sqlQuery = "select * from Bestelling";
        
        try {
            Class.forName(driver);
        
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

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
        } catch (ClassNotFoundException |SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    return bestellinglijst; 
    }

    @Override
    public Bestelling findById(int bestelling_id) {
       String sqlQuery = "select bestelling_id, klant_id from bestelling where bestelling_id = " + bestelling_id;
        Bestelling bestelling = new Bestelling();
        
        try {
            Class.forName(driver);        
        
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
        
            while (rs.next()) {            
            
            bestelling.setBestellingId(rs.getInt("bestelling_id"));
            bestelling.setKlantId(rs.getInt("klant_id"));
            }   
        
         } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
         }
         return bestelling; 
    }

    @Override
    public int insertBestelling(int klant_id) {
        java.util.Date datum = new java.util.Date();
        int bestellingId = 0;
        
        // Schrijf waarden weg in SQL tabel.
        String sqlQuery = "insert into bestelling (klant_id, datum_aangemaakt) values (?, ?)";
        
        try{
        // Maak connectie 
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, klant_id);
            stmt.setDate(2, new java.sql.Date(datum.getTime()));
            
            int affectedRows = stmt.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    } 

                    rs = stmt.getGeneratedKeys();
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
    public void deleteBestelling(int bestelling_id) {
        String sqlQuery = "delete * from bestelling where bestelling_id = " + bestelling_id;
        
        try{
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.executeUpdate();
            
        } catch ( SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll() {
        String sqlQuery = "delete from bestelling";
        
        try{
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.executeUpdate();
                        
        } catch ( SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

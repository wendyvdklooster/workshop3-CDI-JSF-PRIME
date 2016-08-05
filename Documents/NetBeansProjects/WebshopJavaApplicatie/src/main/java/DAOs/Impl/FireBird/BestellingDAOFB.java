/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Impl.MySQL.BestellingArtikelDAOSQL;
import DAOs.Impl.MySQL.BestellingDAOSQL;
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
    
    // Comment EH: Dit zijn configs, deze zou ik uit de code trekken in een "CONFIG" static class proppen
    // Dan kan je een template config inchecken, en jouw eigen config in de .gitignore zetten.
    private final String url = "jdbc:firebirdsql:localhost:C:\\FBDB\\FBDB.FDB";
    private final String user = "Anjewe";
    private final String pw = "Koetjes";
    private final String driver = "org.firebirdsql.jdbc.FBDriver";
    
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;

    
    // Methods
    
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
        String sqlQuery = "INSERT INTO bestelling(klant_id, datum_aangemaakt) VALUES (?, ?) RETURNING bestelling_id";

        try{
        // Maak connectie 
        
            try {
                Class.forName(driver); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BestellingDAOFB.class.getName()).log(Level.SEVERE, null, ex);
            }
       
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, klant_id);
            stmt.setDate(2, new java.sql.Date(datum.getTime()));
            
            int affectedRows = stmt.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Bestelling aanmaken mislukt.");
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
    public boolean deleteBestelling(int bestelling_id) {
        
    boolean isDeleted = false;

        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, user, pw)) {
            
                String sqlQuery = "delete from bestelling where bestelling_id =  ? ";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                preparedStmt.setInt(1, bestelling_id);
                // execute the preparedstatement
                int rowsAffected = preparedStmt.executeUpdate();
                if (rowsAffected != 0) {
                    isDeleted = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return isDeleted;
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

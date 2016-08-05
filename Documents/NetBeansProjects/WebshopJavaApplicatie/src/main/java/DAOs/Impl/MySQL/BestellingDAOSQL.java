/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.MySQL;

import DAOs.Interface.BestellingDAOInterface;
import POJO.Bestelling;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Excen
 */
public class BestellingDAOSQL implements BestellingDAOInterface {
    
    // Info inlog SQL
    
    //EH: Replication van config (zie comment bij BestellingDAOFB.java)
    String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
    String user = "Anjewe";
    String pw = "Koetjes";
    String driver = "com.mysql.jdbc.Driver";
    
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    
    @Override
    public int insertBestelling(int klantId)  {
        
        java.util.Date datum = new java.util.Date();
        int bestellingId = 0;
        
        // Schrijf waarden weg in SQL tabel.
        String sqlQuery = "insert into bestelling (klant_id, datum_aangemaakt) values (?, ?)";
        
        try{
        // Maak connectie 
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, klantId);
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
    public Bestelling findById(int bestellingId) {
        
        String sqlQuery = "select bestelling_id, klant_id from bestelling where bestelling_id = " + bestellingId;
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
    public boolean deleteBestelling(int bestellingId) {
        
        String sqlQuery = "delete * from bestelling where bestelling_id = " + bestellingId;
        boolean isDeleted = false;
        
        
        try{
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.executeUpdate();
            boolean bestellingFound = rs.next();
            
            if (bestellingFound){
                isDeleted = true;
            }
            
        } catch ( SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isDeleted;
    }
    
    @Override
    public void deleteAll(){
        
        String sqlQuery = "delete from bestelling";
        
        try{
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.executeUpdate();
                        
        } catch ( SQLException ex) {
            Logger.getLogger(BestellingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
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
    
    }
    
    

    

    
    

    


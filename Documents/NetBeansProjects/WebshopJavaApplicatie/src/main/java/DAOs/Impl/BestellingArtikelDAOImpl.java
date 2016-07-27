/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

import DAOs.Interface.BestellingDAOInterface;
import DAOs.Interface.BestellingArtikelDAOInterface;
import DAOs.Interface.ArtikelDAOInterface;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingArtikel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Excen
 */
public class BestellingArtikelDAOImpl implements BestellingArtikelDAOInterface {
       
    // Info inlog SQL
    private final String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
    private final String user = "Anjewe";
    private final String pw = "Koetjes";
    private final String driver = "com.mysql.jdbc.Driver";
    
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    
       
    @Override
    public void deleteArtikel(int bestellingId, int artikelId) {
        
        String sqlQuery = "delete artikel_id from koppelbestellingartikel where bestelling_id = " + bestellingId + " and artikel_id = " + artikelId ;
        
        try{             
        
        con = DriverManager.getConnection(url, user, pw);
        stmt = con.prepareStatement(sqlQuery);
        stmt.executeUpdate();
        
        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteBestellingArtikel(int bestellingId)  {

        String sqlQuery = "delete from koppelbestellingartikel where bestelling_id = " + bestellingId;
        
        try{
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.executeUpdate();

        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
    @Override
    public void createBestellingArtikel(BestellingArtikel bestellingArtikel) {
   
        // haal waardes uit BestellingArtikel object.
        int bestellingId = bestellingArtikel.getBestellingId();
        int artikelId = bestellingArtikel.getArtikelId();
        int artikelAantal = bestellingArtikel.getArtikelAantal();
        
        // schrijf ze weg in SQL tabel. 
        String sqlQuery = "insert into koppelbestellingartikel (bestelling_id, artikel_id, aantal)"
        + " values (?, ?, ?)";
        
        try{
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1, bestellingId);
            stmt.setInt(2, artikelId);
            stmt.setInt(3, artikelAantal);
            stmt.execute();
        
        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
     @Override
    public void updateBestellingArtikelAantal(int bestellingId, int artikelId, int nieuwArtikelAantal) {
        
        String sqlQuery = "update koppelbestellingartikel set aantal = ? where bestelling_id = ? and artikel_id = ?";

        try{ 
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1, nieuwArtikelAantal);
            stmt.setInt(2, bestellingId);
            stmt.setInt(3, artikelId);        
            stmt.executeUpdate();
        
        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
      @Override
    public void deleteAll() {
        
        String sqlQuery = "delete from koppelbestellingartikel";
        
        try {
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            stmt.executeUpdate();
        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public ArrayList<BestellingArtikel> findAll() {
        
        ArrayList<BestellingArtikel>bestellingArtikellijst = new ArrayList<>();
        
        String sqlQuery = "select * from koppelbestellingartikel";        
        
        try {
            Class.forName(driver);
            
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                BestellingArtikel bestelling = new BestellingArtikel();
                bestelling.setBestellingId(rs.getInt("bestelling_id"));
                bestelling.setArtikelId(rs.getInt("artikel_id"));
                bestelling.setArtikelAantal(rs.getInt("aantal"));

                // add bestelling in de list
                bestellingArtikellijst.add(bestelling);
                }
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      return bestellingArtikellijst;  
    }
        
    
    @Override
    public ArrayList<Artikel> findByBestellingId(int bestellingId) {
        
        ArrayList<Artikel>artikelLijst = new ArrayList<>();
        
        String sqlQuery = "select artikel_id from koppelbestellingartikel where bestelling_id = " + bestellingId;
        
        try {
            Class.forName(driver);        
        
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            ArtikelDAOInterface artikelDao = new ArtikelDAOImpl();

            while (rs.next()) {

                int artikelId = rs.getInt("artikel_id");

                Artikel artikeltje = artikelDao.findByArtikelID(artikelId);
                artikelLijst.add(artikeltje);

                }    
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    return artikelLijst;        
    }   
    
    
    @Override
    public ArrayList<Bestelling> findBestellingByArtikelId(int artikelId) {
        
        ArrayList<Bestelling>bestellingLijst = new ArrayList<>();
        
        String sqlQuery = "select bestelling_id from koppelbestellingartikel where artikel_id = " + artikelId;
        
        try {
            Class.forName(driver);        

            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            BestellingDAOInterface bestellingDao = new BestellingDAOImpl();

            while (rs.next()) {
                
                Bestelling bestelling = new Bestelling();
                bestelling = bestellingDao.findById(rs.getInt("bestelling_id"));
                // zet in arraylist
                bestellingLijst.add(bestelling);

            }
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bestellingLijst;
    }

 
    
    @Override
    public int findAantalByArtikelID(int bestellingId, int artikelId) {
        
        int artikelAantal = 0;
        
        String sqlQuery = "select aantal from koppelbestellingartikel where bestelling_id = " + bestellingId + " and artikel_id = " + artikelId;
        
        
        try {
            Class.forName(driver);        
        
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()){
                artikelAantal = rs.getInt("aantal"); 
            }
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return artikelAantal;
    }
       
}

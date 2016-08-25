/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Impl.MySQL.ArtikelDAOSQL;
import DAOs.Impl.MySQL.BestellingArtikelDAOSQL;
import DAOs.Impl.MySQL.BestellingDAOSQL;
import DAOs.Interface.ArtikelDAOInterface;
import DAOs.Interface.BestellingArtikelDAOInterface;
import DAOs.Interface.BestellingDAOInterface;
import Factory.ConnectionFactory;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingArtikel;
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

public class BestellingArtikelDAOFB implements BestellingArtikelDAOInterface {
    
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BestellingArtikelDAOFB.class.getName());
    // Info inlog FireBird
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;

    
    @Override
    public ArrayList<BestellingArtikel> findAll() {
        ArrayList<BestellingArtikel>bestellingArtikellijst = new ArrayList<>();
        
        String sqlQuery = "select bestelling_id, artikel_id, aantal from koppelbestellingartikel";        
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                BestellingArtikel bestelling = new BestellingArtikel();
                bestelling.setBestellingId(rs.getInt("bestelling_id"));
                bestelling.setArtikelId(rs.getInt("artikel_id"));
                bestelling.setArtikelAantal(rs.getInt("aantal"));

                // add bestelling in de list
                bestellingArtikellijst.add(bestelling);
                }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
      return bestellingArtikellijst;  
    }
    

    @Override
    public ArrayList<Artikel> findByBestellingId(int bestelling_id) {
        ArrayList<Artikel>artikelLijst = new ArrayList<>();
        
        String sqlQuery = "select artikel_id from koppelbestellingartikel where bestelling_id = ? ";
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, bestelling_id);
            rs = pstmt.executeQuery();

            ArtikelDAOInterface artikelDao = new ArtikelDAOSQL();

            while (rs.next()) {

                int artikelId = rs.getInt("artikel_id");

                Artikel artikeltje = artikelDao.findByArtikelID(artikelId);
                artikelLijst.add(artikeltje);

                }    
        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    return artikelLijst;        
    
    }

    
    @Override
    public ArrayList<Bestelling> findBestellingByArtikelId(int artikel_id) {
        ArrayList<Bestelling>bestellingLijst = new ArrayList<>();
        
        String sqlQuery = "select bestelling_id from koppelbestellingartikel where artikel_id = ? ";
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1,artikel_id);
            rs = pstmt.executeQuery();

            BestellingDAOInterface bestellingDao = new BestellingDAOSQL();

            while (rs.next()) {
                
                Bestelling bestelling = new Bestelling();
                bestelling = bestellingDao.findById(rs.getInt("bestelling_id"));
                // zet in arraylist
                bestellingLijst.add(bestelling);

            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bestellingLijst;
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
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, bestellingId);
            pstmt.setInt(2, artikelId);
            pstmt.setInt(3, artikelAantal);
            pstmt.execute();
        
        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    @Override
    public void updateBestellingArtikelAantal(int bestelling_id, int artikel_id, int newArtikel_aantal) {
        String sqlQuery = "update koppelbestellingartikel set aantal = ? where bestelling_id = ? and artikel_id = ?";

        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, newArtikel_aantal);
            pstmt.setInt(2, bestelling_id);
            pstmt.setInt(3, artikel_id);        
            pstmt.executeUpdate();
        
        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
    public int findAantalByArtikelID(int bestelling_id, int artikel_id) {
        int artikelAantal = 0;
        
        String sqlQuery = "select aantal from koppelbestellingartikel where bestelling_id = ? and artikel_id = ? ";
        
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, bestelling_id);
            pstmt.setInt(2, artikel_id);
            rs = pstmt.executeQuery();

            while (rs.next()){
                artikelAantal = rs.getInt("aantal"); 
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return artikelAantal;
    }

    @Override
    public void deleteAll() {
        String sqlQuery = "delete from koppelbestellingartikel";
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteArtikel(int bestelling_id, int artikel_id) {
        String sqlQuery = "delete from koppelbestellingartikel where bestelling_id = ? and artikel_id = ? ";
        
       try {
        
        Connection con = ConnectionFactory.getConnection(); 
        pstmt = con.prepareStatement(sqlQuery);
        pstmt.setInt(1, bestelling_id);
        pstmt.setInt(2, artikel_id);
        pstmt.executeUpdate();
        
        } 
        catch ( SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void deleteBestellingArtikel(int bestelling_id) {
        String sqlQuery = "delete from koppelbestellingartikel where bestelling_id = ?" ;
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, bestelling_id);
            pstmt.executeUpdate();

        } 
        catch (SQLException ex) {
            Logger.getLogger(BestellingArtikelDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    
}

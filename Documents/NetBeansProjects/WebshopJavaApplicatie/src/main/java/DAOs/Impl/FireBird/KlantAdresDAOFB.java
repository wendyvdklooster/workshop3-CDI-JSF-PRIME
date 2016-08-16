/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Impl.MySQL.KlantAdresDAOSQL;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import Factory.ConnectionFactory;
import POJO.Adres;
import POJO.Klant;
import POJO.KlantAdres;
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
 * @author Wendy
 */
public class KlantAdresDAOFB implements KlantAdresDAOInterface{

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KlantAdresDAOFB.class.getName());
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;
    
    
    @Override 
    public ArrayList<KlantAdres> findAll(){ 

     ArrayList<KlantAdres> klantAdresLijst = new ArrayList<>();
        
         try {
        
        Connection con = ConnectionFactory.getConnection();                
        
            String sqlQuery = "SELECT klant_id, adres_id FROM koppelklantadres";
            
            rs = pstmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    KlantAdres klantAdres = new KlantAdres();
                    klantAdres.setKlantId(rs.getInt("klant_id"));
                    klantAdres.setAdresId(rs.getInt("adres_id"));            

                    // add bestelling in de list
                    klantAdresLijst.add(klantAdres);
                }   
                con.close();
            } 
            catch(SQLException ex){
            System.out.println(ex.getMessage());
         }
        // arrayList van adressen 
          
    return klantAdresLijst; 
}

    
    @Override
    public ArrayList<Klant> findKlantByAdresId(long adresId) {
        
        ArrayList<Klant> klantenlijst = new ArrayList<>();
        KlantDAOInterface klantDao = new KlantDAOFB();
        
         try {
        
        Connection con = ConnectionFactory.getConnection();         
        
            String sqlQuery = "SELECT klant_id FROM Koppelklantadres WHERE adres_id = ?";

            pstmt = con.prepareStatement(sqlQuery);
        
            pstmt.setLong(1, adresId);      
            rs = pstmt.executeQuery();          
            
                while (rs.next()) {  
                    
                    int klantId = rs.getInt("klant_id");
                    Klant klant = klantDao.findByKlantId(klantId);
                    klantenlijst.add(klant);            
                    //voeg klant toe aan lijst :Klant klant = findByKlantId(int klantId); klantenlijst.add(klant);                       
                }  
                con.close();       
        }
        catch(SQLException ex){
            Logger.getLogger(KlantAdresDAOFB.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return klantenlijst;
    }

    
    @Override
    public ArrayList<Adres> findAdresByKlantId(long klantId) {
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        AdresDAOInterface adresDao = new AdresDAOFB();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
            
            String sqlQuery = "SELECT adres_id FROM Koppelklantadres WHERE klant_id = ?";
        
            pstmt = con.prepareStatement(sqlQuery);  
        
            pstmt.setLong(1, klantId);      
            rs = pstmt.executeQuery();          
            
                while (rs.next()) {  
                    
                    int adresId = rs.getInt("adres_id");
                    Adres adres = adresDao.findByAdresID(adresId);
                    adressenLijst.add(adres);
             
                }  
                con.close(); 
        } 
        catch(SQLException ex){
            Logger.getLogger(KlantAdresDAOFB.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return adressenLijst;
   }

    
    @Override
    public boolean insertKlantAdres(long klantId, long adresId) { 
        
        boolean created = false; 
         
        try {
        
        Connection con = ConnectionFactory.getConnection();  
                          
            // schrijf ze weg in SQL tabel. 
            String sqlQuery = "INSERT INTO koppelklantadres (klant_id, adres_id) values (?, ?)";
                   
            pstmt = con.prepareStatement(sqlQuery);
           
            pstmt.setLong(1, klantId);
            pstmt.setLong(2, adresId);
       
            pstmt.executeUpdate();
        
            created = true; 
        } 
        catch(SQLException ex ){
            Logger.getLogger(KlantAdresDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return created; 
    }
    

    @Override
    public boolean deleteAll() {
        
        boolean deleted = false;
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
                 
                 String sqlQuery = "delete from koppelklantadres";                         
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true; 
             
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return deleted; 
    
   }

    
    @Override
    public boolean deleteKlantAdresByAdresId(long adresId) {

    boolean deleted = false; 
    
       try {
        
        Connection con = ConnectionFactory.getConnection();  
                 
                 String sqlQuery = "delete from koppelklantadres where adres_id = ?" ;
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                    
                 preparedStmt.setLong(1, adresId);
                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true;                  
             
      }    
      catch (SQLException e){
        System.err.println(e.getMessage());
      }
      
      return deleted; 
  }

    
    @Override
    public int deleteKlantAdresByKlantId(long klantId) { int rowsAffected = 0; 
    
       try {
        
        Connection con = ConnectionFactory.getConnection();  
                 
                 String sqlQuery = "delete from koppelklantadres where klant_id = ? " ;
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                    
                 preparedStmt.setLong(1, klantId);
                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();       
      }    
      catch (SQLException e){
        System.err.println(e.getMessage());
      }
      
      return rowsAffected;  
    }
    
}

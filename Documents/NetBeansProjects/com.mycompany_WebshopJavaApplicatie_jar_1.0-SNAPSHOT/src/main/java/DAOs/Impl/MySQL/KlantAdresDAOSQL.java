/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.MySQL;

import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import POJO.Adres;
import POJO.Klant;
import POJO.KlantAdres;
import DAOs.Interface.KlantDAOInterface;
import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wendy
 */
public class KlantAdresDAOSQL implements KlantAdresDAOInterface {
    
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KlantAdresDAOSQL.class.getName());
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st; 
    
    
   @Override // werkt
   public ArrayList<KlantAdres> findAll(){        
        
        ArrayList<KlantAdres> klantAdresLijst = new ArrayList<>();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();                  
        
            String sqlQuery = "SELECT * FROM koppelklantadres";
            
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();
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
            LOGGER.error("", ex);
         }
        // arrayList van adressen 
          
    return klantAdresLijst;  
}
    

    @Override // werkt
    public ArrayList<Klant> findKlantByAdresId(int adresId) {
        
        ArrayList<Klant> klantenlijst = new ArrayList<>();
        KlantDAOInterface klantDao = new KlantDAOSQL();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();          
        
            String sqlQuery = "SELECT klant_id FROM Koppelklantadres WHERE adres_id = ?";

            pstmt = con.prepareStatement(sqlQuery);
        
            pstmt.setInt(1, adresId);      
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
           LOGGER.error("", ex);
        }     
        
        return klantenlijst;
    }
    

    @Override // werkt
    public ArrayList<Adres> findAdresByKlantId(int klantId) {
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        AdresDAOInterface adresDao = new AdresDAOSQL();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
            
            String sqlQuery = "SELECT adres_id FROM Koppelklantadres WHERE klant_id = ?";
        
            pstmt = con.prepareStatement(sqlQuery);  
        
            pstmt.setInt(1, klantId);      
            rs = pstmt.executeQuery();          
            
                while (rs.next()) {  
                    
                    int adresId = rs.getInt("adres_id");
                    Adres adres = adresDao.findByAdresID(adresId);
                    adressenLijst.add(adres);
             
                }  
                con.close(); 
        } 
        catch(SQLException ex){
           LOGGER.error("", ex);
        }  
        
        return adressenLijst;
    }
    

    @Override // werkt
    public boolean insertKlantAdres(int klantId, int adresId) {
        
        boolean created = false; 
        try {
        
        Connection con = ConnectionFactory.getConnection();  
             
            String sqlQuery = "INSERT INTO koppelklantadres (klant_id, adres_id) values (?, ?)";
                   
            pstmt = con.prepareStatement(sqlQuery);
           
            pstmt.setInt(1, klantId);
            pstmt.setInt(2, adresId);
       
            pstmt.executeUpdate();
        
            created = true; 
        } 
        catch(SQLException ex ){
            LOGGER.error("", ex);
        }
        
       return created; 
    }    
    

    @Override // werkt
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
             
        } catch (SQLException ex) {        
            LOGGER.error("", ex);
        }
      return deleted; 
    }
    

    @Override // werkt
    public boolean deleteKlantAdresByAdresId(int adresId) {

    boolean deleted = false; 
    
      try {
        
        Connection con = ConnectionFactory.getConnection();   {
                 
                 String sqlQuery = "delete from koppelklantadres where adres_id = ?" ;
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                    
                 preparedStmt.setInt(1, adresId);
                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true;                  
             }
      }
    
      catch (SQLException ex){
            LOGGER.error("", ex);
      }
      
      return deleted; 

    }
    
    
    @Override
    public int deleteKlantAdresByKlantId(int klantId) {

    int rowsAffected = 0; 
    
      try {
        
        Connection con = ConnectionFactory.getConnection();   
                 
            String sqlQuery = "delete from koppelklantadres where klant_id = ? " ;
            // create the mysql preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
            preparedStmt.setInt(1, klantId);

            // execute the preparedstatement
            rowsAffected = preparedStmt.executeUpdate();  
            
      }    
      catch (SQLException ex){
            LOGGER.error("", ex);
      }
      
      return rowsAffected; 

    }
  
}

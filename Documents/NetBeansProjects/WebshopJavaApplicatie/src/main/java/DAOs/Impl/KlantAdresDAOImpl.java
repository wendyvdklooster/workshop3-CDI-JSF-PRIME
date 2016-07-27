/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import POJO.Adres;
import POJO.Klant;
import POJO.KlantAdres;
import DAOs.Interface.KlantDAOInterface;
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
 * @author Wendy
 */
public class KlantAdresDAOImpl implements KlantAdresDAOInterface {
    
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
    String user = "Anjewe"; 
    String pw = "Koetjes"; 
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;    
    
    
   @Override // werkt
   public ArrayList<KlantAdres> findAll(){        
        
        ArrayList<KlantAdres> klantAdresLijst = new ArrayList<>();
        
        try{
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url,user, pw);                 
        
            String sqlQuery = "SELECT * FROM koppelklantadres";
            
            rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    KlantAdres klantAdres = new KlantAdres();
                    klantAdres.setKlantId(rs.getInt("klant_id"));
                    klantAdres.setAdresId(rs.getInt("adres_id"));            

                    // add bestelling in de list
                    klantAdresLijst.add(klantAdres);
                }   
                con.close();
            } 
            catch(ClassNotFoundException ex){
            Logger.getLogger(KlantAdresDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(SQLException ex){
            System.out.println(ex.getMessage());
         }
        // arrayList van adressen 
          
    return klantAdresLijst;  
}
    

    @Override // werkt
    public ArrayList<Klant> findKlantByAdresId(int adresId) {
        
        ArrayList<Klant> klantenlijst = new ArrayList<>();
        KlantDAOInterface klantDao = new KlantDAOImpl();
        
        try{
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url,user, pw);         
        
            String sqlQuery = "SELECT klant_id FROM Koppelklantadres WHERE adres_id = ?";

            stmt = con.prepareStatement(sqlQuery);
        
            stmt.setInt(1, adresId);      
            rs = stmt.executeQuery();          
            
                while (rs.next()) {  
                    
                    int klantId = rs.getInt("klant_id");
                    Klant klant = klantDao.findByKlantId(klantId);
                    klantenlijst.add(klant);            
                    //voeg klant toe aan lijst :Klant klant = findByKlantId(int klantId); klantenlijst.add(klant);                       
                }  
                con.close();       
        }
        catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(KlantAdresDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        return klantenlijst;
    }
    

    @Override // werkt
    public ArrayList<Adres> findAdresByKlantId(int klantId) {
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        AdresDAOInterface adresDao = new AdresDAOImpl();
        
        try{
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url,user, pw);
            
            String sqlQuery = "SELECT adres_id FROM Koppelklantadres WHERE klant_id = ?";
        
            stmt = con.prepareStatement(sqlQuery);  
        
            stmt.setInt(1, klantId);      
            rs = stmt.executeQuery();          
            
                while (rs.next()) {  
                    
                    int adresId = rs.getInt("adres_id");
                    Adres adres = adresDao.findByAdresID(adresId);
                    adressenLijst.add(adres);
             
                }  
                con.close(); 
        } 
        catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(KlantAdresDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return adressenLijst;
    }
    

    @Override // werkt
    public boolean insertKlantAdres(int klantId, int adresId) {
        
        boolean created = false; 
        try{
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url,
                user, pw);
                          
            // schrijf ze weg in SQL tabel. 
            String sqlQuery = "INSERT INTO koppelklantadres (klant_id, adres_id) values (?, ?)";
                   
            stmt = con.prepareStatement(sqlQuery);
           
            stmt.setInt(1, klantId);
            stmt.setInt(2, adresId);
       
            stmt.executeUpdate();
        
            created = true; 
        } 
        catch(ClassNotFoundException | SQLException ex ){
            Logger.getLogger(KlantAdresDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return created; 
    }    
    

    @Override // werkt
    public boolean deleteAll() {
        
        boolean deleted = false;
        
        try{  
            
        Class.forName(driver);
             // create a sql date object so we can use it in our statement
             try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                 
                 String sqlQuery = "delete from koppelklantadres";                         
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true; 
             }
        } catch (ClassNotFoundException | SQLException e)
        {
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
    }
    return deleted; 
    }
    

    @Override // werkt
    public boolean deleteKlantAdresByAdresId(int adresId) {

    boolean deleted = false; 
    
      try{  
        Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                 
                 String sqlQuery = "delete from koppelklantadres where adres_id = ?" ;
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                    
                 preparedStmt.setInt(1, adresId);
                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true;                  
             }
      }
    
      catch (ClassNotFoundException | SQLException e){
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
      }
      
      return deleted; 

    }
    
    
    public int deleteKlantAdresByKlantId(int klantId) {

    int rowsAffected = 0; 
    
      try{  
        Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                 
                 String sqlQuery = "delete from koppelklantadres where klant_id = ? " ;
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                    
                 preparedStmt.setInt(1, klantId);
                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();   
                                
             }
      }
    
      catch (ClassNotFoundException | SQLException e){
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
      }
      
      return rowsAffected; 

    }
  
}

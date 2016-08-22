/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Interface.AdresDAOInterface;
import Factory.ConnectionFactory;
import POJO.Adres;
import POJO.Adres.AdresBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Wendy
 */
public class AdresDAOFB implements AdresDAOInterface {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(AdresDAOFB.class.getName());
    
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;
    
    AdresBuilder adresBuilder = new AdresBuilder();
    
    
    @Override
    public ArrayList<Adres> findAllAdresses() {
        
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres (adresBuilder);       
        
       try {
        
        Connection con = ConnectionFactory.getConnection();  
        
        String sqlQuery = "SELECT ADRES_ID, STRAATNAAM, HUISNUMMER, TOEVOEGING, POSTCODE, WOONPLAATS FROM ADRES";        // select: wildcard can be used*
       
        pstmt = con.prepareStatement(sqlQuery);
        rs = pstmt.executeQuery();
            if (rs != null) {  
                
                while (rs.next()){
            
                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));
                // build adres
                adres = adresBuilder.build();    
                //voeg adres toe aan lijst
                adressenLijst.add(adres);
                }
            } 
            con.close();             
        }
        catch(SQLException ex){
            ex.printStackTrace();
         }
        // arrayList van adressen 
        return adressenLijst;
    }

    @Override
    public Adres findByAdresID(long adresId) {
        Adres adres = new Adres(adresBuilder);
        adresBuilder = new AdresBuilder();
       
        try {
        
        Connection con = ConnectionFactory.getConnection();   
            
         String sqlQuery = "select * from Adres where adres_id = ?";     
         pstmt = con.prepareStatement(sqlQuery);
         pstmt.setLong(1, adresId);
         rs = pstmt.executeQuery();
         while (rs.next()) {            
            
                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));
                // build adres
                adres = adresBuilder.build();    
               
            }        
            con.close();             
        }
        catch(SQLException ex){
            ex.printStackTrace();
         }
      
        return adres;
    }

    @Override
    public ArrayList<Adres> findByStraatNaam(String straatnaam) {
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres (adresBuilder);  
       
        try {
        
        Connection con = ConnectionFactory.getConnection();     
    
        String sqlQuery = "select * from adres a where a.straatnaam = ? ";
        pstmt = con.prepareStatement(sqlQuery);
         pstmt.setString(1, straatnaam);
         rs = pstmt.executeQuery();
         while (rs.next()) {            
            
                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));
                // build adres
                adres = adresBuilder.build();    
                //add to list
                adressenLijst.add(adres);
               
            }        
            con.close();             
        }
        catch(SQLException ex){
            ex.printStackTrace();
         }
        
        return adressenLijst;
    }

    @Override
    public ArrayList<Adres> findByPostcodeHuisNummer(String postcode, int huisnummer) {
       
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres (adresBuilder);  
       
        try {
        
        Connection con = ConnectionFactory.getConnection();    
    
        String sqlQuery = "select * from adres a where a.postcode = ? and a.huisnummer = ? "; 
        pstmt = con.prepareStatement(sqlQuery);
         pstmt.setString(1, postcode);
         pstmt.setInt(2, huisnummer);
         rs = pstmt.executeQuery();
         while (rs.next()) {            
            
                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));
                // build adres
                adres = adresBuilder.build(); 
                // add to list
                adressenLijst.add(adres);
               
            }        
            con.close();             
        }
        catch(SQLException ex){
            ex.printStackTrace();
         }      
       
        return adressenLijst;
    }

    @Override
    public ArrayList<Adres> findByWoonplaats(String woonplaats) {
        
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres (adresBuilder);  
       
        try {
        
        Connection con = ConnectionFactory.getConnection();  
    
        String sqlQuery = "select * from adres where woonplaats = ? ";
        pstmt = con.prepareStatement(sqlQuery);
         pstmt.setString(1, woonplaats);         
         rs = pstmt.executeQuery();
         while (rs.next()) {            
            
                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));
                // build adres
                adres = adresBuilder.build();  
                // add to list
                adressenLijst.add(adres);
               
            }        
            con.close();             
        }
        catch(SQLException ex){
            ex.printStackTrace();
         }      
       
        return adressenLijst;        
    }

    
    @Override
    public Adres insertAdres(Adres adres) {
        
        int adresId = 0; 
                
        String straatnaam = adres.getStraatnaam();
        int huisnummer = adres.getHuisnummer();
        String toevoeging = adres.getToevoeging();
        String postcode = adres.getPostcode();
        String woonplaats = adres.getWoonplaats();
         // the mysql update statement
                
        String sqlQuery = "insert into adres (straatnaam, huisnummer," +
                         " toevoeging, postcode, woonplaats) values (?, ?, ?, ?,?)";
       try {
        
        Connection con = ConnectionFactory.getConnection();              
                // create the mysql insert preparedstatement               
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery,
                         Statement.RETURN_GENERATED_KEYS) ;
                
                 preparedStmt.setString (1, straatnaam);
                 preparedStmt.setInt (2, huisnummer);
                 preparedStmt.setString (3, toevoeging);
                 preparedStmt.setString (4, postcode);
                 preparedStmt.setString (5, woonplaats);
                
                 int affectedRows = preparedStmt.executeUpdate();
                 if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                 } 
                
                rs = preparedStmt.getGeneratedKeys();
                if (rs.isBeforeFirst()){ 
                   if (rs.next())                    
                         adresId = rs.getInt(1);                               
                }     
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }     
                
                adresBuilder.adresId(adresId);
                adresBuilder.straatnaam(straatnaam);
                adresBuilder.huisnummer(huisnummer);
                adresBuilder.toevoeging(toevoeging);
                adresBuilder.postcode(postcode);
                adresBuilder.woonplaats(woonplaats);
                 
                adres = adresBuilder.build();
                
            }
            catch (SQLException e){
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
            
    return adres;
    
    }
    
    @Override
    public boolean deleteAdres(long adresId) {
        boolean deleted = false; 
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  

                String sqlQuery = "delete from adres where adres_id = ? " ;

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                preparedStmt.setLong(1, adresId);                      
                // execute the preparedstatement
                preparedStmt.executeUpdate();

                deleted = true; 
            
        }    
        catch (SQLException ex) {            
            ex.printStackTrace();
        }
        return deleted;
    }

    
    @Override
    public boolean deleteAll() {
         boolean deleted = false;
        
       try {
        
        Connection con = ConnectionFactory.getConnection();  
        
                 String sqlQuery = "delete from adres";                         
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true; 
             
        }    
        catch (SQLException ex){
            ex.printStackTrace();
        }
      return deleted; 
    }

    @Override   // werkt niet
    public Adres updateGegevens(Adres adres) {
        
        long adresId = adres.getId();
        String straatnaam = adres.getStraatnaam();
	int huisnummer = adres.getHuisnummer();
	String toevoeging = adres.getToevoeging();
	String postcode = adres.getPostcode();
	String woonplaats = adres.getWoonplaats(); 
        
    try {
        
        Connection con = ConnectionFactory.getConnection();  
             String sqlQuery = "update Adres set straatnaam = ?, huisnummer = ?, toevoeging = ?,"
                + "postcode = ?, woonplaats = ? where adres_id = ? ";
//                     + "returning adres_id,old .straatnaam, new.straatnaam";             

		// create the mysql  preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
				
                 preparedStmt.setString (1, straatnaam);
                 preparedStmt.setInt (2, huisnummer);
                 preparedStmt.setString (3, toevoeging);
                 preparedStmt.setString (4, postcode);
                 preparedStmt.setString (5, woonplaats);
                 preparedStmt.setLong(6, adresId);
				 
                 int affectedRows = preparedStmt.executeUpdate();
                 if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                 } 
				
               // Now you can extract all the records
               // to see the updated records
                
    
               sqlQuery = "select * from Adres where adres_id = ?";
                       
               preparedStmt = con.prepareStatement(sqlQuery);
               preparedStmt.setLong(1, adresId);
               rs = preparedStmt.executeQuery();   

               while(rs.next()){
                    adresBuilder.adresId(rs.getInt("adres_id"));
                    adresBuilder.straatnaam(rs.getString("straatnaam"));
                    adresBuilder.huisnummer(rs.getInt("huisnummer"));
                    adresBuilder.toevoeging(rs.getString("toevoeging"));
                    adresBuilder.postcode(rs.getString("postcode"));
                    adresBuilder.woonplaats(rs.getString("woonplaats"));

                    adres = adresBuilder.build();
			    
               }
            
        }
        catch (SQLException e) {
                    
                    System.err.println(e.getMessage());
        }
        
    return adres; 
}
}

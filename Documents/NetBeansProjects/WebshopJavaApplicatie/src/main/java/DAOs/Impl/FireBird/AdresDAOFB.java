/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Interface.AdresDAOInterface;
import POJO.Adres;
import POJO.Adres.AdresBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Wendy
 */
public class AdresDAOFB implements AdresDAOInterface {
    String driver = "org.firebirdsql.jdbc.FBDriver";
    String url = "jdbc:firebirdsql:localhost/3050:C:\\FBDB\\fbdb.FDB";    
  
    String user = "Anjewe"; 
    String pw = "Koetjes"; 
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    
    AdresBuilder adresBuilder = new AdresBuilder();
    
    
    @Override
    public ArrayList<Adres> findAllAdresses() {
        
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres (adresBuilder);       
        
        try {
        //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);       
        
        String sqlQuery = "SELECT ADRES_ID, STRAATNAAM, HUISNUMMER, TOEVOEGING, POSTCODE, WOONPLAATS FROM ADRES";        // select: wildcard can be used*
       
        stmt = con.prepareStatement(sqlQuery);
        rs = stmt.executeQuery();
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
        catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
         }
        // arrayList van adressen 
        return adressenLijst;
    }

    @Override
    public Adres findByAdresID(int adresId) {
        Adres adres = new Adres(adresBuilder);
        adresBuilder = new AdresBuilder();
       
        try {
        //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);       
            
         String sqlQuery = "select * from Adres where adres_id = ?";     
         stmt = con.prepareStatement(sqlQuery);
         stmt.setInt(1, adresId);
         rs = stmt.executeQuery();
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
        catch(SQLException | ClassNotFoundException ex){
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
        //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);       
    
        String sqlQuery = "select * from adres a where a.straatnaam = ? ";
        stmt = con.prepareStatement(sqlQuery);
         stmt.setString(1, straatnaam);
         rs = stmt.executeQuery();
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
        catch(SQLException | ClassNotFoundException ex){
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
        //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);       
    
        String sqlQuery = "select * from adres a where a.postcode = ? and a.huisnummer = ? "; 
        stmt = con.prepareStatement(sqlQuery);
         stmt.setString(1, postcode);
         stmt.setInt(2, huisnummer);
         rs = stmt.executeQuery();
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
        catch(SQLException | ClassNotFoundException ex){
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
        //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);       
    
        String sqlQuery = "select * from adres where woonplaats = ? ";
        stmt = con.prepareStatement(sqlQuery);
         stmt.setString(1, woonplaats);         
         rs = stmt.executeQuery();
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
        catch(SQLException | ClassNotFoundException ex){
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
        // create a mysql database connection
            Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
            Connection conn = DriverManager.getConnection(url, user, pw);                
                // create the mysql insert preparedstatement               
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery,
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
            catch (SQLException | ClassNotFoundException e){
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
            
    return adres;
    
    }
    
    @Override
    public boolean deleteAdres(int adresId) {
        boolean deleted = false; 
        
        try{  
          Class.forName(driver);
            // create a sql date object so we can use it in our INSERT statement
            try (Connection conn = DriverManager.getConnection(url, user, pw)) {

                String sqlQuery = "delete from adres where adres_id = ? " ;

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                preparedStmt.setInt(1, adresId);                      
                // execute the preparedstatement
                preparedStmt.executeUpdate();

                deleted = true; 

            }
        }    
        catch (ClassNotFoundException | SQLException ex) {            
            ex.printStackTrace();
        }
        return deleted;
    }

    
    @Override
    public boolean deleteAll() {
         boolean deleted = false;
        
        try{  
            
            Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                 // create a sql date object so we can use it in our INSERT statement
                 
                 // the mysql insert statement
                 String sqlQuery = "delete from adres";                         
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 preparedStmt.executeUpdate();
                 
                 deleted = true; 
             }
        }    
        catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
      return deleted; 
    }

    @Override   // werkt niet
    public Adres updateGegevens(Adres adres) {
        
        int adresId = adres.getAdresId();
        String straatnaam = adres.getStraatnaam();
	int huisnummer = adres.getHuisnummer();
	String toevoeging = adres.getToevoeging();
	String postcode = adres.getPostcode();
	String woonplaats = adres.getWoonplaats(); 
        
    try {
    // create a mysql database connection
      
    Class.forName(driver);
    // create a sql date object so we can use it in our INSERT statement
        try (Connection conn = DriverManager.getConnection(url, user, pw)) {

             String sqlQuery = "update Adres set straatnaam = ?, huisnummer = ?, toevoeging = ?,"
                + "postcode = ?, woonplaats = ? where adres_id = ? ";
//                     + "returning adres_id,old .straatnaam, new.straatnaam";             

		// create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
				
                 preparedStmt.setString (1, straatnaam);
                 preparedStmt.setInt (2, huisnummer);
                 preparedStmt.setString (3, toevoeging);
                 preparedStmt.setString (4, postcode);
                 preparedStmt.setString (5, woonplaats);
                 preparedStmt.setInt(6, adresId);
				 
                 int affectedRows = preparedStmt.executeUpdate();
                 if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                 } 
				
               // Now you can extract all the records
               // to see the updated records
                
    
               sqlQuery = "select * from Adres where adres_id = ?";
                       
               preparedStmt = conn.prepareStatement(sqlQuery);
               preparedStmt.setInt(1, adresId);
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
        }
        catch (ClassNotFoundException | SQLException e) {
                    
                    System.err.println(e.getMessage());
        }
        
    return adres; 
}
}

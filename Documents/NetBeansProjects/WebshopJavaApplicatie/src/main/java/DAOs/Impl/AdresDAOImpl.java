
package DAOs.Impl;

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
public class AdresDAOImpl implements AdresDAOInterface {

    //datafields 
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";    
    String user = "Anjewe"; 
    String pw = "Koetjes"; 
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    AdresBuilder adresBuilder = new AdresBuilder();
    
    @Override //werkt
    public ArrayList <Adres> findAllAdresses() {
        
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
       
        try {
        //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);       
        
        String sqlQuery = "select * from Adres";        
        
        stmt = con.prepareStatement(sqlQuery);
        rs = stmt.executeQuery();
            while (rs.next()) {            
            
                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));
                // build adres
                Adres adres = adresBuilder.build();    
                //voeg adres toe aan lijst
                adressenLijst.add(adres);
            }        
            con.close();             
        }
        catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
         }
        // arrayList van adressen 
        return adressenLijst;
    }
    

   @Override //werkt
    public Adres findByAdresID(int adresId)  {
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder);
        
        try {
            //load driver
        Class.forName(driver);        
        //establish a connection
        con = DriverManager.getConnection(url,user, pw);         
           
            String sqlQuery = "select adres_id,straatnaam,huisnummer,toevoeging,postcode, " + 
                "woonplaats from Adres where adres_id = ? ";
                    
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
            
                // build Klant
                adres = adresBuilder.build();                                   
            }                              
            con.close();              
        }// end try
        catch (ClassNotFoundException | SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
        }  
    return adres;        
}
      
    
    @Override //werkt
    public Adres findByStraatNaam(String straatNaam) {
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder);
        try { 
                //load driver
            Class.forName(driver);        
            //establish a connection
            con = DriverManager.getConnection(url,user, pw); 

                String sqlQuery = "select adres_id,straatnaam,huisnummer,toevoeging,postcode, " + 
                    "woonplaats from Adres where straatnaam = ? ";
                 stmt = con.prepareStatement(sqlQuery);

                stmt.setString(1, straatNaam);      
                rs = stmt.executeQuery();          

            while (rs.next()) {              

                adresBuilder.adresId(rs.getInt("adres_id"));
                adresBuilder.straatnaam(rs.getString("straatnaam"));
                adresBuilder.huisnummer(rs.getInt("huisnummer"));
                adresBuilder.toevoeging(rs.getString("toevoeging"));
                adresBuilder.postcode(rs.getString("postcode"));
                adresBuilder.woonplaats(rs.getString("woonplaats"));

                // build Klant
                adres = adresBuilder.build();
                con.close();            
            }        
        }
        catch (ClassNotFoundException | SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
        }                
        return adres;
    }
    
    
    @Override //werkt
    public Adres findByWoonplaats(String woonPlaats) {
                
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder); 
        try { 
                //load driver
            Class.forName(driver);        
            //establish a connection
            con = DriverManager.getConnection(url,user, pw); 

                String sqlQuery = "select adres_id,straatnaam,huisnummer,toevoeging,postcode, " + 
                    "woonplaats from Adres where woonplaats = ? ";
                stmt = con.prepareStatement(sqlQuery);


                stmt.setString(1, woonPlaats);      
                rs = stmt.executeQuery();          

                while (rs.next()) {    

                    adresBuilder.adresId(rs.getInt("adres_id"));
                    adresBuilder.straatnaam(rs.getString("straatnaam"));
                    adresBuilder.huisnummer(rs.getInt("huisnummer"));
                    adresBuilder.toevoeging(rs.getString("toevoeging"));
                    adresBuilder.postcode(rs.getString("postcode"));
                    adresBuilder.woonplaats(rs.getString("woonplaats"));

                    // build Klant
                    adres = adresBuilder.build();

            }    
                con.close();     
        }
        catch (ClassNotFoundException | SQLException e) {
               System.err.println("Got an exception!");
               System.err.println(e.getMessage());
        }
                
        return adres;
    }

    
    @Override //werkt
    public Adres findByPostcodeHuisNummer(String postcode, int huisnummer)  {
                
        AdresBuilder adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder); 
        try { 
                //load driver
            Class.forName(driver);        
            //establish a connection
            con = DriverManager.getConnection(url,user, pw); 

                String sqlQuery = "select adres_id,straatnaam,huisnummer,toevoeging,postcode, " + 
                    "woonplaats from Adres where postcode = ? and huisnummer = ?";
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

                    // build Klant
                    adres = adresBuilder.build();

                }  
                con.close();       
        }
        catch (ClassNotFoundException | SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
        }
        return adres;
    }
    
   
    @Override // werkt
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
    
        
    public Adres updateGegevens(Adres adres){
    
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

                String sqlQuery = "Update Adres set straatnaam = ? , huisnummer = ?, toevoeging = ?, postcode = ?, woonplaats = ? where adres_id = ?"; 

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
               sqlQuery = "SELECT adres_id, straatnaam, huisnummer, toevoeging, postcode, woonplaats FROM adres where adres_id = ? ";

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
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
        }
        
    return adres; 
}   
    

    @Override // werkt    
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
    catch (ClassNotFoundException | SQLException e) {
    System.err.println("Got an exception!");
    System.err.println(e.getMessage());
    }
  return deleted; 
}


    @Override
    //Got an exception!
//Cannot delete or update a parent row: a foreign key constraint fails (`winkel`.`koppelklantadres`, CONSTRAINT `koppelklantadres_ibfk_2` FOREIGN KEY (`adres_id`) REFERENCES `adres` (`adres_id`))
    public boolean  deleteAll() {
        boolean deleted = false;
        
        try{  
            
            Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                 // create a sql date object so we can use it in our INSERT statement
                 
                 // the mysql insert statement
                 String sqlQuery = "delete * from adres";                         
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                                                 
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
    
}

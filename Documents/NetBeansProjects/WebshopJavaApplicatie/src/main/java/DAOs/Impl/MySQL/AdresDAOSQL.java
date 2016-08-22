
package DAOs.Impl.MySQL;

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
public class AdresDAOSQL implements AdresDAOInterface {

    //datafields 
    private final static Logger LOGGER = LoggerFactory.getLogger(AdresDAOSQL.class.getName());
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con; 
    ResultSet rs;
    PreparedStatement stmt;
    AdresBuilder adresBuilder = new AdresBuilder();
    
    @Override //werkt
    public ArrayList <Adres> findAllAdresses() {
        
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
       
        try {
        
        Connection con = ConnectionFactory.getConnection();       
        
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
        catch(SQLException ex){
            System.out.println(ex.getMessage());
         }
        // arrayList van adressen 
        return adressenLijst;
    }
    

   @Override //werkt
    public Adres findByAdresID(long adresId)  {
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder);
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
        
            String sqlQuery = "select adres_id,straatnaam,huisnummer,toevoeging,postcode, " + 
                "woonplaats from Adres where adres_id = ? ";
                    
            stmt = con.prepareStatement(sqlQuery);                  
            
            stmt.setLong(1, adresId);      
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
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }  
    return adres;        
}
      
    
    @Override //werkt
    public ArrayList<Adres> findByStraatNaam(String straatNaam) {
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder);
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  

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
                // build adres
                adres = adresBuilder.build();    
                //voeg adres toe aan lijst
                adressenLijst.add(adres);
                con.close();            
            }        
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }                
        return adressenLijst;
    }
    
    
    @Override //werkt
    public ArrayList<Adres>  findByWoonplaats(String woonPlaats) {
                
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder); 
        
       try {
        
        Connection con = ConnectionFactory.getConnection();  

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
                    // build adres
                    adres = adresBuilder.build();    
                    //voeg adres toe aan lijst
                    adressenLijst.add(adres);

            }    
                con.close();     
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
                
        return adressenLijst;
    }

    
    @Override //werkt
    public ArrayList<Adres>  findByPostcodeHuisNummer(String postcode, int huisnummer)  {
                
        ArrayList<Adres> adressenLijst = new ArrayList<>();
        adresBuilder = new AdresBuilder();
        Adres adres = new Adres(adresBuilder); 
        try {
        
        Connection con = ConnectionFactory.getConnection();  

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
                    // build adres
                    adres = adresBuilder.build();    
                    //voeg adres toe aan lijst
                    adressenLijst.add(adres);

                }  
                con.close();       
        }
        catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
        }
        return adressenLijst;
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
    
        
    public Adres updateGegevens(Adres adres){
    
        long adresId = adres.getId();
        String straatnaam = adres.getStraatnaam();
	int huisnummer = adres.getHuisnummer();
	String toevoeging = adres.getToevoeging();
	String postcode = adres.getPostcode();
	String woonplaats = adres.getWoonplaats(); 
        
    try {
        
        Connection con = ConnectionFactory.getConnection();   

                String sqlQuery = "Update Adres set straatnaam = ? , huisnummer = ?, toevoeging = ?, postcode = ?, woonplaats = ? where adres_id = ?"; 

				// create the mysql insert preparedstatement
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
               sqlQuery = "SELECT adres_id, straatnaam, huisnummer, toevoeging, postcode, woonplaats FROM adres where adres_id = ? ";

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
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
        }
        
    return adres; 
}   
    

    @Override // werkt    
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
         catch (SQLException e) {
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
        
        try {
        
        Connection con = ConnectionFactory.getConnection();   
               
                String sqlQuery = "delete * from adres";                         

                PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                // execute the preparedstatement
                preparedStmt.executeUpdate();

                deleted = true;              
        }    
        catch (SQLException e){        
        System.err.println(e.getMessage());
        }
        
      return deleted; 
    }
    
}

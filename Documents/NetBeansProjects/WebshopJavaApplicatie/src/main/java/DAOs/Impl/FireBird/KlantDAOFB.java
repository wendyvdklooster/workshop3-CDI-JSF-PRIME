
package DAOs.Impl.FireBird;

import DAOs.Impl.MySQL.KlantDAOSQL;
import DAOs.Interface.KlantDAOInterface;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendy
 */
public class KlantDAOFB implements KlantDAOInterface {

    String driver = "org.firebirdsql.jdbc.FBDriver";
    String url = "jdbc:firebirdsql:localhost/3050:C:\\FBDB\\fbdb.FDB";

    String user = "Anjewe";
    String pw = "Koetjes";
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;

    KlantBuilder klantBuilder = new KlantBuilder();

    @Override
    public ArrayList<Klant> findAllKlanten() {
        ArrayList<Klant> klantenLijst = new ArrayList<>();

        try {
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url, user, pw);

            String sqlQuery = "SELECT Klant_id, voornaam, achternaam, tussenvoegsel, email FROM Klant";  // select: wildcard can be used*

            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                klantBuilder.klantId(rs.getInt("klant_id"));
                klantBuilder.voornaam(rs.getString("voornaam"));
                klantBuilder.achternaam(rs.getString("achternaam"));
                klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
                klantBuilder.email(rs.getString("email"));

                // build Klant
                Klant klant = klantBuilder.build();
                //voeg klant toe aan lijst
                klantenLijst.add(klant);
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(KlantDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klantenLijst;
    }

    @Override
    public Klant findByKlantId(int klantId) {

        Klant klant = new Klant(klantBuilder);

        try {
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url, user, pw);

            String sqlQuery = "select * from Klant where klant_id = ? ";
            stmt = con.prepareStatement(sqlQuery);

            stmt.setInt(1, klantId);
            rs = stmt.executeQuery();

            while (rs.next()) {

                klantBuilder.klantId(rs.getInt("klant_id"));
                klantBuilder.voornaam(rs.getString("voornaam"));
                klantBuilder.achternaam(rs.getString("achternaam"));
                klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
                klantBuilder.email(rs.getString("email"));

                // build Klant
                klant = klantBuilder.build();

            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
        }

        return klant;
    }

    @Override
    public ArrayList<Klant> findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {

        ArrayList<Klant> klantenLijst = new ArrayList();

        try {
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url, user, pw);

            String sqlQuery = "select * from Klant where voorNaam = ? and achterNaam = ? ";
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, voorNaam);
            stmt.setString(2, achterNaam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                klantBuilder.klantId(rs.getInt("klant_id"));
                klantBuilder.voornaam(rs.getString("voornaam"));
                klantBuilder.achternaam(rs.getString("achternaam"));
                klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
                klantBuilder.email(rs.getString("email"));

                // build Klant
                Klant klant = klantBuilder.build();
                //voeg klant toe aan lijst
                klantenLijst.add(klant);

            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return klantenLijst;
    }

    @Override
    public ArrayList<Klant> findByEmail(String email) {

        ArrayList<Klant> klantenLijst = new ArrayList();
        
        try {
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url, user, pw);

            String sqlQuery = "select * Klant where email = ? ";
            stmt = con.prepareStatement(sqlQuery);

            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                klantBuilder.klantId(rs.getInt("klant_id"));
                klantBuilder.voornaam(rs.getString("voornaam"));
                klantBuilder.achternaam(rs.getString("achternaam"));
                klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
                klantBuilder.email(rs.getString("email"));

                // build Klant
                Klant klant = klantBuilder.build();
                //voeg klant toe aan lijst
                klantenLijst.add(klant);
            }
            con.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return klantenLijst;
    }

    @Override
    public boolean deleteByKlantId(int klantId) {
        boolean deleted = false;

        try {
            Class.forName(driver);
            // create a sql date object so we can use it in our INSERT statement
            try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                // create a sql date object so we can use it in our INSERT statement

                // the mysql insert statement.first parent, than child
                String sqlQuery = "delete from klant where klant_id =  ? ";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                preparedStmt.setInt(1, klantId);
                // execute the preparedstatement
                int rowsAffected = preparedStmt.executeUpdate();
                if (rowsAffected != 0) {
                    deleted = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return deleted;
    }
    

    @Override
    public int deleteAll() {
       int rowsAffected = 0; 
        
        try{  
            
        Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, user, pw)) {
                 // create a sql date object so we can use it in our INSERT statement
                 
                 // the mysql insert statement
                 String sqlQuery = "delete from klant";                         
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();                    
            }
        }
        catch (ClassNotFoundException | SQLException e) {            
            System.err.println(e.getMessage());
        }    
        
      return rowsAffected; 
    }

    @Override /// nog doen
    public Klant updateGegevens(Klant klant) {
        
        int klantId = klant.getKlantId();
        System.out.println(klantId);
        String voornaam = klant.getVoornaam();
	String tussenvoegsel = klant.getTussenvoegsel();
	String achternaam = klant.getAchternaam();
	String email = klant.getEmail(); 
        
    try {
    // create a mysql database connection
      
    Class.forName(driver);
    // create a sql date object so we can use it in our INSERT statement
        try (Connection conn = DriverManager.getConnection(url, user, pw)) {

             String sqlQuery = "update Klant set voornaam = ?, tussenvoegsel = ?, " + 
                     "achternaam = ?, email = ? where klant_id = ? ";
//                     + "returning adres_id,old .straatnaam, new.straatnaam";             

		// create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
				
                 preparedStmt.setString (1, voornaam);
                 preparedStmt.setString (2, tussenvoegsel);
                 preparedStmt.setString (3, achternaam);
                 preparedStmt.setString (4, email);
                 preparedStmt.setInt(5, klantId);
				 
                 int affectedRows = preparedStmt.executeUpdate();
                 if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                 } 
				
               // Now you can extract all the records
               // to see the updated records
               sqlQuery = "select * from Adres where klant_id = ?";
                       
               preparedStmt = conn.prepareStatement(sqlQuery);
               preparedStmt.setInt(1, klantId);
               rs = preparedStmt.executeQuery();   

               while(rs.next()){
                    klantBuilder.klantId(rs.getInt("klant_id"));
                    klantBuilder.voornaam(rs.getString("voornaam"));
                    klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
                    klantBuilder.achternaam(rs.getString("achternaam"));
                    klantBuilder.email(rs.getString("email"));

                    klant = klantBuilder.build();
			    
               }
            }
        }
        catch (ClassNotFoundException | SQLException e) {      
            System.err.println(e.getMessage());
        }
        
    return klant; 
}

    @Override // nog doen: AANPASSEN!
    public Klant insertKlant(Klant klant) {
        
        int klantId = 0;        
        
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail();
        
        // the mysql insert statement
        String sqlQuery = "insert into Klant (voornaam, achternaam, tussenvoegsel, email)"
                         + " values (?, ?, ?, ?)";
    
        try{ 
        // create a mysql database connection      
        Class.forName(driver);
        // create a sql date object so we can use it in our INSERT statement
        try (Connection conn = DriverManager.getConnection(url, user, pw);
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt  = conn.prepareStatement(sqlQuery,
                Statement.RETURN_GENERATED_KEYS) ) {
                 
                preparedStmt.setString (1, voornaam);
                preparedStmt.setString (2, achternaam);
                preparedStmt.setString (3, tussenvoegsel);
                preparedStmt.setString (4, email);
                 // execute the preparedstatement
                 
                int affectedRows = preparedStmt.executeUpdate();
                 if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                 } 
                 
                 rs = preparedStmt.getGeneratedKeys();
                 if (rs.isBeforeFirst()){
                    if (rs.next()) 
                        klantId = rs.getInt(1);                         
                 } 
                 
                klantBuilder.klantId(klantId);
                klantBuilder.voornaam(voornaam);
                klantBuilder.achternaam(achternaam);
                klantBuilder.tussenvoegsel(tussenvoegsel);
                klantBuilder.email(email);
                 
                klant = klantBuilder.build();
         }     
        }
        catch (SQLException | ClassNotFoundException ex){
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
    return klant;
    
    }
    
}

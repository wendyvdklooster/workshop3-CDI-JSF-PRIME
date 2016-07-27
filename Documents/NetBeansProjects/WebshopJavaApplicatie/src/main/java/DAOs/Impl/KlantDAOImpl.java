/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

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
public class KlantDAOImpl implements KlantDAOInterface {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
    //String url = "jdbc:mysql://localhost3306/winkel";
    String gebruikersNaam = "Anjewe"; 
    String wachtwoord = "Koetjes"; 
    Connection con;
    ResultSet rs;
    PreparedStatement stmt;
    
    ArrayList <String> voornaamLijst = new ArrayList<>();
    ArrayList <String> achternaamLijst = new ArrayList<>();
    ArrayList <String> tussenvoegselLijst = new ArrayList<>();
    
    KlantBuilder klantBuilder = new KlantBuilder();
   

       
    @Override  
    public ArrayList<Klant> findAllKlanten() {
       
        ArrayList<Klant> klantenLijst = new ArrayList<>();
        
        try{
            //load driver
            Class.forName(driver);
            //establish a connection
            con = DriverManager.getConnection(url,gebruikersNaam, wachtwoord);
        
        String sqlQuery = "select * from Klant";        
        
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
       } 
        catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(KlantDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klantenLijst; 
    }
    
    @Override  // werkt
    public Klant findByKlantId(int klantId) {
       
       Klant klant = new Klant(klantBuilder);
       
       try{
        //load driver
        Class.forName(driver);
        //establish a connection
        con = DriverManager.getConnection(url,
                gebruikersNaam, wachtwoord);
                 
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from Klant where klant_id = ? ";
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
        }
        catch(SQLException | ClassNotFoundException ex){}
                
        return klant; 
    }
    


    @Override // werkt
    public Klant findByEmail(String email) {
        
        Klant klant = new Klant(klantBuilder);
       
        try{
        //load driver
        Class.forName(driver);
        //establish a connection
        con = DriverManager.getConnection(url,
                gebruikersNaam, wachtwoord);       
        
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from Klant where email = ? ";
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
            klant = klantBuilder.build();                        
        } 
        con.close();       
        }
        catch(SQLException | ClassNotFoundException ex){
        System.out.println(ex.getMessage());
        }
                
        return klant;
    }

    @Override // werkt
    public Klant findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {
       
        Klant klant = new Klant(klantBuilder);
        
        try{
        //load driver
        Class.forName(driver);
        //establish a connection
        con = DriverManager.getConnection(url,gebruikersNaam, wachtwoord);
               
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from Klant where voorNaam = ? and achterNaam = ? ";
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
            klant = klantBuilder.build();
                        
        } 
        con.close();       
        }
        catch(SQLException | ClassNotFoundException ex){
        System.out.println(ex.getMessage());
        }
                
        return klant;
    }
    
    
   @Override // werkt
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
        try (Connection conn = DriverManager.getConnection(url, gebruikersNaam, wachtwoord);
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
    
    
    public Klant updateGegevens(Klant klant){
    
    int klantId = klant.getKlantId();
    String voornaam = klant.getVoornaam();
    String achternaam = klant.getAchternaam();        
    String tussenvoegsel = klant.getTussenvoegsel();
    String email = klant.getEmail();            
        
    try {
    // create a mysql database connection
      
    Class.forName(driver);
    // create a sql date object so we can use it in our INSERT statement
        try (Connection conn = DriverManager.getConnection(url, gebruikersNaam, wachtwoord)) {

                String sqlQuery = "Update Klant set voornaam = ? , achternaam = ?, tussenvoegsel = ?, email = ? where klant_id = ?"; 

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                preparedStmt.setString (1, voornaam);
                preparedStmt.setString (2, achternaam);
                preparedStmt.setString (3, tussenvoegsel);
                preparedStmt.setString (4, email);
                preparedStmt.setInt(5, klantId);

                // execute the preparedstatement
                preparedStmt.executeUpdate();

               // Now you can extract all the records
               // to see the updated records
               sqlQuery = "SELECT klant_id, voornaam, tussenvoegsel, achternaam, email FROM klant where klant_id = ? ";

               preparedStmt = conn.prepareStatement(sqlQuery);
               preparedStmt.setInt(1, klantId);
               rs = preparedStmt.executeQuery();   

               while(rs.next()){
               klantBuilder.klantId(rs.getInt("klant_id"));
               klantBuilder.voornaam(rs.getString("voornaam"));
               klantBuilder.achternaam(rs.getString("achternaam"));
               klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
               klantBuilder.email(rs.getString("email"));

               // build Klant
               klant = klantBuilder.build();  
               }
        }
    }
    catch (ClassNotFoundException | SQLException e) {
		System.err.println("Got an exception!");
		System.err.println(e.getMessage());
    }
        
  return klant; 
}
    
    

    
    @Override  //werkt
    public boolean deleteByKlantId(int klantId) {
            
        boolean deleted = false; 
        
        try{  
        Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, gebruikersNaam, wachtwoord)) {
                 // create a sql date object so we can use it in our INSERT statement
                 
                 // the mysql insert statement.first parent, than child
                 String sqlQuery = "delete from klant where klant_id =  ? " ;                 
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);                
                   preparedStmt.setInt(1, klantId);
                 // execute the preparedstatement
                 int rowsAffected = preparedStmt.executeUpdate(); 
                 if (rowsAffected != 0)
                     deleted = true;
             }
        }    
        catch (ClassNotFoundException | SQLException e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    return deleted;
}   
        
    @Override // werkt
    public boolean deleteByKlantNaam(String achternaam, String tussenvoegsel, String voornaam) {
             
        boolean deleted = false;  
      
        try{
        Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
            try (Connection conn = DriverManager.getConnection(url, gebruikersNaam, wachtwoord)) {
                 // create a sql date object so we can use it in our INSERT statement
                 
                 // the mysql insert statement.first parent, than child
                 String sqlQuery = "delete from klant where (voornaam, achternaam, " 
                         + "tussenvoegsel) = (?,?,? )";                 
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);                
                 preparedStmt.setString(1,voornaam);
                 preparedStmt.setString(2,achternaam);
                 preparedStmt.setString(3,tussenvoegsel);
                 
                 // execute the preparedstatement
                 int rowsAffected = preparedStmt.executeUpdate();   
                 System.out.println("Aantal klanten verwijderd: " + rowsAffected);
                 if (rowsAffected >= 1)
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
    public int deleteAll() {
        
        int rowsAffected = 0; 
        
        try{  
            
        Class.forName(driver);
             // create a sql date object so we can use it in our INSERT statement
             try (Connection conn = DriverManager.getConnection(url, gebruikersNaam, wachtwoord)) {
                 // create a sql date object so we can use it in our INSERT statement
                 
                 // the mysql insert statement
                 String sqlQuery = "delete from klant";                         
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();   
                 
        }
        }
    
            catch (ClassNotFoundException | SQLException e)
            {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            }    
        return rowsAffected; 
    }  

    /*
    
    //public static boolean isValidEmailAddress(String email) {
//   boolean result = true;
//   try {
//      // Get an EmailValidator
//            EmailValidator validator = EmailValidator.getInstance();
//            
//            // Validate an email address
//            boolean isAddressValid = validator.isValid(email);
//
//            // Validate a variable containing an email address
//                 
//    } catch (Exception ex) {
//        System.out.println(email + " is not a valid E-mail address. Probeer opnieuw. ");   
//        result = false;
//    }
//        return result;
//}
    */
    /*
     @Override // gebruik methode insert van hier boven. 
    gebruik maken van gevulde lijsten werkt nog niet. methode an sich wel.
    
    public int[] addBatchKlanten() throws Exception {    
	KlantDAOImpl klantDAO = new KlantDAOImpl();
        //load driver
        Class.forName(driver);
        System.out.println("Driver loaded");
        //establish a connection
        con = DriverManager.getConnection(url,
                gebruikersNaam, wachtwoord);
        System.out.println("Database Connected"); 
	// Create statement object 
	
        String sqlQuery = "insert into Klant (voornaam, achternaam, tussenvoegsel)"
                         + " values (?, ?, ?)";  
						 
	stmt = con.prepareStatement(sqlQuery);
	
        con.setAutoCommit(false);					 
	
        klantDAO.vulVoornaamLijst();
        klantDAO.vulAchternaamLijst();
        klantDAO.vulTussenvoegselLijst();
        
        
	int x = (int)(Math.random()* voornaamLijst.size());
        System.out.println(voornaamLijst.size());
	
	for (int i = 0; i < x; i++){
            String voornaam = 
               voornaamLijst.get((int)(Math.random())*(voornaamLijst.size() + 1));
            String achternaam = 
               achternaamLijst.get((int)(Math.random())*(achternaamLijst.size() + 1));
            String tussenvoegsel = 
               tussenvoegselLijst.get ((int)(Math.random())*(tussenvoegselLijst.size() + 1));
	
	// Add above SQL statement in the batch.	
	stmt.setString (1, voornaam);
        stmt.setString (2, achternaam);
        stmt.setString (3, tussenvoegsel);
        
        stmt.addBatch(sqlQuery);
	}               
    
    // Create an int[] to hold returned values
    int[] count = stmt.executeBatch();
    //Explicitly commit statements to apply changes
    con.commit();
        

    return count;  
    }  

    @Override //werkt
    public void vulVoornaamLijst (){
	Scanner input = new Scanner(System.in);
        
        for (int i = 0; i< 20; i++){
        
        boolean continueInput = true; 
        
        
       do{
        try{	
	System.out.print("Voeg nieuwe voornaam toe: ");
	String voornaamNieuw = input.nextLine(); 
        
            if(!voornaamLijst.contains(voornaamNieuw)){
                voornaamLijst.add(voornaamNieuw);  
                continueInput = false;
            }
            else {
                System.out.print("Naam bestaat al, probeer opnieuw");
                input.nextLine();            
            }  		       
        
        }
        catch (InputMismatchException ex){
            System.out.println("Probeer opnieuw: foutieve input");
           input.nextLine();
        }
       }while(continueInput);
        }
    }
	
    @Override // werkt
    public void vulAchternaamLijst (){		
		
	Scanner input = new Scanner(System.in);
        for (int i = 0; i< 20; i++){
        boolean continueInput = true; 
        
       do{
        try{	
	System.out.print("Voeg nieuwe achternaam toe: ");
	String achternaamNieuw = input.nextLine(); 
        
            if(!achternaamLijst.contains(achternaamNieuw)){
                achternaamLijst.add(achternaamNieuw);  
                continueInput = false;
            }
            else {
                System.out.print("Naam bestaat al, probeer opnieuw");
                input.nextLine();            
            }  		       
        
        }
        catch (InputMismatchException ex){
            System.out.println("Probeer opnieuw: foutieve input");
           input.nextLine();
        }
       }while(continueInput);
        }
    }
	
    @Override // werkt
    public void vulTussenvoegselLijst (){		
	Scanner input = new Scanner(System.in);
        
        for (int i = 0; i< 10; i++){
        boolean continueInput = true; 
        
       do{
        try{	
	System.out.print("Voeg nieuwe voornaam toe: ");
	String tussenvoegselNieuw = input.nextLine(); 
        
            if(!tussenvoegselLijst.contains(tussenvoegselNieuw)){
                tussenvoegselLijst.add(tussenvoegselNieuw);  
                continueInput = false;
            }
            else {
                System.out.print("Tussenvoegsel bestaat al in de lijst, probeer opnieuw");
                input.nextLine();            
            }  		       
        
        }
        catch (InputMismatchException ex){
            System.out.println("Probeer opnieuw: foutieve input");
           input.nextLine();
        }
       }while(continueInput);
        }
    }

    /*
    @Override
    public void updateAdresKlant(int adresId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<Klant> findByAdresId(int adresId) throws Exception {
    
        ArrayList<Klant> klantenByAdres = new ArrayList<>();
                
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from klant where koppelklantadres.adres_id = ? " +
                "and klant.klant_id = koppelklantadres.klant_id";
        
        stmt = con.prepareStatement(sqlQuery);
        try{
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, gebruikersNaam, wachtwoord)) {
            stmt.setInt(1, adresId);      
            rs = stmt.executeQuery();          
            
                while (rs.next()) {       
            
                    KlantBuilder klantBuilder = new KlantBuilder();
                    klantBuilder.klantId(rs.getInt("klant_id"));
                    klantBuilder.voornaam(rs.getString("voornaam"));
                    klantBuilder.achternaam(rs.getString("achternaam"));
                    klantBuilder.tussenvoegsel(rs.getString("tussenvoegsel"));
                    klantBuilder.email(rs.getString("email"));
            
                    // build Klant
                    Klant klant = klantBuilder.build();                        
                }        
            }
        }
        catch(SQLException | ClassNotFoundException ex){}
                
        return klantenByAdres;        
    }
*/

		
} 



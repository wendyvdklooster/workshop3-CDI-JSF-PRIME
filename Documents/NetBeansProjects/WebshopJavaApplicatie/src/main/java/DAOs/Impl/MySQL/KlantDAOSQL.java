/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.MySQL;

import POJO.InsertReflection;
import DAOs.Interface.KlantDAOInterface;
import Factory.ConnectionFactory;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
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
public class KlantDAOSQL implements KlantDAOInterface {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KlantDAOSQL.class.getName());
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;
    
    ArrayList <String> voornaamLijst = new ArrayList<>();
    ArrayList <String> achternaamLijst = new ArrayList<>();
    ArrayList <String> tussenvoegselLijst = new ArrayList<>();
    
    KlantBuilder klantBuilder = new KlantBuilder();  

       
    
    @Override  
    public ArrayList<Klant> findAllKlanten() {
       
        ArrayList<Klant> klantenLijst = new ArrayList<>();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
        
        String sqlQuery = "select * from Klant";        
        
        pstmt = con.prepareStatement(sqlQuery);
        rs = pstmt.executeQuery();
            
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
        catch(SQLException ex){
            LOGGER.error(" ", ex);
        }
        return klantenLijst; 
    }
    
    @Override  // werkt
    public Klant findByKlantId(int klantId) {
       
       Klant klant = new Klant(klantBuilder);
       
       try {
        
        Connection con = ConnectionFactory.getConnection();
                 
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from Klant where klant_id = ? ";
        pstmt = con.prepareStatement(sqlQuery);
        
            pstmt.setInt(1, klantId);      
            rs = pstmt.executeQuery();          
            
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
        catch(SQLException ex){
            LOGGER.error(" ", ex);
        }
                
        return klant; 
    }
    

    @Override // werkt
    public ArrayList<Klant> findByEmail(String email) {
        
        ArrayList<Klant> klantenLijst = new ArrayList<>();
       
        try {
        
        Connection con = ConnectionFactory.getConnection();     
        
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from Klant where email = ? ";
        pstmt = con.prepareStatement(sqlQuery);
        
        
            pstmt.setString(1, email);      
            rs = pstmt.executeQuery();          
            
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
        catch(SQLException ex){
            LOGGER.error(" ", ex);
        }
                
        return klantenLijst;
    }

    
    @Override // werkt
    public ArrayList<Klant> findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {
       
        ArrayList<Klant> klantenLijst = new ArrayList<>();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();
               
        String sqlQuery = "select klant_id, voornaam, achternaam, tussenvoegsel, "
                + "email from Klant where voorNaam = ? and achterNaam = ? ";
        pstmt = con.prepareStatement(sqlQuery);
        
            pstmt.setString(1, voorNaam);
            pstmt.setString(2, achterNaam);
            rs = pstmt.executeQuery();          
            
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
        catch(SQLException ex){
            LOGGER.error(" ", ex);
        }
                
        return klantenLijst;
    }
    
    
   @Override // werkt
    public Klant insertKlant(Klant object) {
        
   Klant klant = new Klant (klantBuilder); 
        int klantId = 0; 
        
        // get sqlString
        InsertReflection insert = new InsertReflection();        
        String sqlQuery = insert.buildInsertStatementKlant(object);
        LOGGER.debug("Klant to be inserted: " + sqlQuery);
        
        try{  
        
        Connection con = ConnectionFactory.getConnection();
        
             //create the mysql insert preparedstatement
            PreparedStatement preparedStmt  = con.prepareStatement(sqlQuery,
                Statement.RETURN_GENERATED_KEYS); 
               
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
                klantBuilder.voornaam(object.getVoornaam());
                klantBuilder.achternaam(object.getAchternaam());
                klantBuilder.tussenvoegsel(object.getTussenvoegsel());
                klantBuilder.email(object.getEmail());
                 
                object = klantBuilder.build();
                klant = (Klant) object;
              
        }
        catch (SQLException ex){
            LOGGER.error(" ", ex);
        }
    return klant;
  }  
    
    
//    @Override
//    public Klant insertWithReflection (Klant object){
//        Klant klant = new Klant (klantBuilder); 
//        int klantId = 0; 
//        
//        // get sqlString
//        InsertReflection insert = new InsertReflection();        
//        String sqlQuery = insert.buildInsertStatementKlant(object);
//        LOGGER.debug("Klant to be inserted: " + sqlQuery);
//        
//        try{  
//        
//        Connection con = ConnectionFactory.getConnection();
//        
//             //create the mysql insert preparedstatement
//            PreparedStatement preparedStmt  = con.prepareStatement(sqlQuery,
//                Statement.RETURN_GENERATED_KEYS); 
//               
//                 int affectedRows = preparedStmt.executeUpdate();
//                 if (affectedRows == 0) {
//                    throw new SQLException("Creating user failed, no rows affected.");
//                 } 
//                 
//                 rs = preparedStmt.getGeneratedKeys();
//                 if (rs.isBeforeFirst()){
//                    if (rs.next()) 
//                        klantId = rs.getInt(1);                         
//                 } 
//                 
//                klantBuilder.klantId(klantId);
//                klantBuilder.voornaam(object.getVoornaam());
//                klantBuilder.achternaam(object.getAchternaam());
//                klantBuilder.tussenvoegsel(object.getTussenvoegsel());
//                klantBuilder.email(object.getEmail());
//                 
//                object = klantBuilder.build();
//                klant = (Klant) object;
//              
//        }
//        catch (SQLException ex){
//            LOGGER.error(" ", ex);
//        }
//    return klant;
//        
//    }
//    
    
    @Override
    public Klant updateGegevens(Klant klant){
    
        int klantId = klant.getKlantId();
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail();       

        try {

            Connection con = ConnectionFactory.getConnection(); 

                    String sqlQuery = "Update Klant set voornaam = ? , achternaam = ?, tussenvoegsel = ?, email = ? where klant_id = ?"; 

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
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

                   preparedStmt = con.prepareStatement(sqlQuery);
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
        catch (SQLException ex) {
            LOGGER.error(" ", ex);
        }

      return klant; 
}    

    
    @Override  //werkt
    public boolean deleteByKlantId(int klantId) {
            
        boolean deleted = false; 
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); {
                
                 String sqlQuery = "delete from klant where klant_id =  ? " ;              
                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);                
                   preparedStmt.setInt(1, klantId);
                 // execute the preparedstatement
                 int rowsAffected = preparedStmt.executeUpdate(); 
                 if (rowsAffected != 0)
                     deleted = true;
             }
        }    
        catch (SQLException ex){
            LOGGER.error(" ", ex);
        }
    return deleted;
}   
        
    
    @Override
    public int deleteAll() {
        
        int rowsAffected = 0; 
        
        try {
        
        Connection con = ConnectionFactory.getConnection(); 
        
                 String sqlQuery = "delete from klant";                       
                 
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);                                                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();   
        
        }catch (SQLException ex) {
           LOGGER.error(" ", ex);
         }  
        
      return rowsAffected; 
    }  

    /*
    

}
    */
    /*
     @Override // gebruik methode insert van hier boven. 
    gebruik maken van gevulde lijsten werkt nog niet. methode an sich wel.
    
    public int[] addBatchKlanten() throws Exception {    
	KlantDAOSQL klantDAO = new KlantDAOSQL();
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



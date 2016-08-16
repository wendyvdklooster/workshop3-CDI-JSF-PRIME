package DAOs.Impl.MySQL;

import DAOs.Interface.ArtikelDAOInterface;
import Factory.ConnectionFactory;
import POJO.Artikel;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ArtikelDAOSQL implements ArtikelDAOInterface {

    private final static Logger LOGGER = LoggerFactory.getLogger("com.webshop.test");
    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement st;
    

    
    @Override
    public ArrayList<Artikel> findAll() {
        ArrayList<Artikel> artikelList = new ArrayList<>();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
        LOGGER.debug("gebruikt sql" );
        
            String sqlQuery = "select * from artikel";
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // get the fields from one artikel and store it in an Artikel object
                Artikel artikel = new Artikel();
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikel_naam"));
                artikel.setArtikelPrijs(rs.getDouble("artikel_prijs"));

                // add the artikel in the list
                artikelList.add(artikel);
            }
        } catch (SQLException ex) {
            System.out.println("Data search failed");
        }

       return artikelList;
    }

    @Override
    public Artikel findByArtikelID(long artikelID) {
        Artikel artikel = new Artikel();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
        
            String sqlQuery = "select * from artikel where artikel_id = ? ";
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setLong(1, artikelID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikel_naam"));
                artikel.setArtikelPrijs(rs.getDouble("artikel_prijs"));
            }
            else {
                throw new NullPointerException(); // is nog nodig, of kan gewoon printstatement in else?
            }
        } 
        catch (NullPointerException ex) {
            System.out.println("Dit artikel naam bestaat niet, probeer opnieuw. ");
        } 
        catch (SQLException ex) {
            System.out.println("Data search failed");
        }
        return artikel;
    }

    @Override
    public Artikel findByArtikelNaam(String artikelNaam) {
        Artikel artikel = new Artikel();
        try {
        
        Connection con = ConnectionFactory.getConnection();  
        
            String sqlQuery = "select * from artikel where artikel_naam = '" + artikelNaam + "'";
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery(sqlQuery);
            if (rs.next()) {
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikel_naam"));
                artikel.setArtikelPrijs(rs.getDouble("artikel_prijs"));
            } 
            else {
                throw new NullPointerException(); // is nog nodig, of kan gewoon printstatement in else?
            }
        } 
        catch (NullPointerException ex) {
            System.out.println("Dit artikel naam bestaat niet, probeer opnieuw. ");
        } 
        catch (SQLException ex) {
            System.out.println("Data search failed");
        }
        return artikel;
    }

   
    @Override
    public Artikel findByArtikelPrijs(double artikelPrijs) {
        Artikel artikel = new Artikel();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
        
            String sqlQuery = "select * from artikel where artikel_prijs = " + artikelPrijs;
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikel_naam"));
                artikel.setArtikelPrijs(rs.getDouble("artikel_prijs"));
            }
            else {
                throw new NullPointerException(); // is nog nodig, of kan gewoon printstatement in else?
            }
        } 
        catch (NullPointerException ex) {
            System.out.println("Dit artikel naam bestaat niet, probeer opnieuw.");    
        } 
        catch (SQLException ex) {
            System.out.println("Data search failed");
        }
        return artikel;

    }

    @Override
    public Artikel insertArtikel(String artikelNaam, double artikelPrijs) {
        //boolean return als gelukt is? 
        Artikel artikel = new Artikel();
        int artikelId = 0;
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
        
        String sqlUpdate = "insert into artikel (artikel_naam, artikel_prijs)"
                    + "values (?, ?)";
            pstmt = con.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);

           
            pstmt.setString(1, artikelNaam);
            pstmt.setDouble(2, artikelPrijs);

            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.isBeforeFirst()) {
                if (rs.next()) {
                    artikelId = rs.getInt(1);
                }
            }
            artikel.setArtikelId(artikelId);
            artikel.setArtikelNaam(artikelNaam);
            artikel.setArtikelPrijs(artikelPrijs);
        } 
        catch (SQLException ex) {
            System.out.println("Data entry failed.");
            
        }                
        return artikel;
    }
    

    // delete methode
    @Override
    public boolean deleteArtikel(long artikelId) {
        
        boolean deleted = false; 
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
                
                 String sqlQuery = "delete from artikel where artikel_id =  ? " ;                 
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);                
                   preparedStmt.setLong(1, artikelId);
                 // execute the preparedstatement
                 int rowsAffected = preparedStmt.executeUpdate(); 
                 if (rowsAffected != 0)
                     deleted = true;
             
        }    
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    return deleted;        
       
    }
    
    
     @Override
    public int deleteAll() {
        int rowsAffected = 0; 
        
         try {
        
        Connection con = ConnectionFactory.getConnection();   
                 
                 String sqlQuery = "delete from artikel";    
                 // create the mysql insert preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);                                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();               
            
        }    
        catch (SQLException e){        
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }  
        
     return rowsAffected;
    }
    
    // update method
    @Override
    public boolean update(Artikel artikel) {
        
    long artikelId = artikel.getArtikelId();
    String artikelNaam = artikel.getArtikelNaam();
    double artikelPrijs = artikel.getArtikelPrijs();         
        
    boolean gelukt = false;
    
        try {
        
        Connection con = ConnectionFactory.getConnection();  
            String sqlUpdate = "update artikel set artikel_naam = ?, artikel_prijs = ? WHERE artikel_id = ?";
            pstmt = con.prepareStatement(sqlUpdate);
            
            pstmt.setString(1, artikelNaam);
            pstmt.setDouble(2, artikelPrijs);
            pstmt.setLong(3, artikelId);
            
            pstmt.executeUpdate();
            gelukt = true;
        } 
        catch (SQLException ex) {
            System.out.println("Update failed.");
        }
        
        return gelukt;
    }

}

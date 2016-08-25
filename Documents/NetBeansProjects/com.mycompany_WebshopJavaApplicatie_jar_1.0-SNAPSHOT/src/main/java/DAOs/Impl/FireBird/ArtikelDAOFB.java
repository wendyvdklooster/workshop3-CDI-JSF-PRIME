/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl.FireBird;

import DAOs.Interface.ArtikelDAOInterface;
import Factory.ConnectionFactory;
import POJO.Artikel;
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
public class ArtikelDAOFB implements ArtikelDAOInterface {

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
        
        LOGGER.debug("gebruikt fb");
            
            String sqlQuery = "select * from artikel";
            
            pstmt = con.prepareStatement(sqlQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // get the fields from one artikel and store it in an Artikl object
                Artikel artikel = new Artikel();
                artikel.setArtikelId(rs.getInt("artikel_id"));                
                artikel.setArtikelNaam(rs.getString("artikelnaam"));
                artikel.setArtikelPrijs(rs.getDouble("artikel_prijs"));

                // add the artikel in the list
                artikelList.add(artikel);
            }
        } catch (SQLException ex) {
            LOGGER.error("Data search failed", ex);
        }

       return artikelList;  
    }

    
    @Override
    public Artikel findByArtikelID(int artikelID) {
        
        Artikel artikel = new Artikel();
        
        try {
        
        Connection con = ConnectionFactory.getConnection();  
            
            String sqlQuery = "select * from artikel where artikel_id = ? ";
            
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setInt(1, artikelID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikelnaam"));
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
            
            String SQLZoeken = "select * from artikel where artikelnaam = ? ";
            
            pstmt = con.prepareStatement(SQLZoeken);
            pstmt.setString(1, artikelNaam);
            rs = pstmt.executeQuery(SQLZoeken);
            
            if (rs.next()) {
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikelnaam"));
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
            
            String sqlQuery = "select * from artikel where artikel_prijs = ? " ;
            
            pstmt = con.prepareStatement(sqlQuery);
            pstmt.setDouble(1, artikelPrijs);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                artikel.setArtikelId(rs.getInt("artikel_id"));
                artikel.setArtikelNaam(rs.getString("artikelnaam"));
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

    
    @Override //nog doen: AANPASSEN!
    public Artikel insertArtikel(String artikelNaam, double artikelPrijs) {

        Artikel artikel = new Artikel();
        int artikelId = 0;
        
      try {
        
        Connection con = ConnectionFactory.getConnection();  
            
            String sqlUpdate = "insert into artikel (artikelnaam, artikel_prijs)"
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
            
        }catch (SQLException ex) {
            System.out.println("Data entry failed.");            
        }                
        
      return artikel; 
    }

    
    @Override
    public boolean deleteArtikel(int artikelId) {
        
        
        boolean deleted = false; 
        
       try {
        
        Connection con = ConnectionFactory.getConnection();  
                 
                 String sqlQuery = "delete from artikel where artikel_id =  ? " ;                 
                 
                 // create the mysql  preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);                
                   preparedStmt.setInt(1, artikelId);
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
                 
                 // create the mysql preparedstatement
                 PreparedStatement preparedStmt = con.prepareStatement(sqlQuery);
                                                 
                 // execute the preparedstatement
                 rowsAffected = preparedStmt.executeUpdate();   
                 
            
        }catch (SQLException e){    
            System.err.println(e.getMessage());
        }  
        
     return rowsAffected;
     
   }

    
    @Override // nog doen
    public boolean update(Artikel artikel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

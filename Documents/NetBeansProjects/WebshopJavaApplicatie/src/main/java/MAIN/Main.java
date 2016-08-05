package MAIN;

import Controller.HoofdMenuController;
import Controller.KlantController;
import Controller.ArtikelController;
import DAOs.Impl.MySQL.AdresDAOSQL;
import DAOs.Impl.MySQL.ArtikelDAOSQL;
import DAOs.Impl.MySQL.KlantDAOSQL;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import POJO.Adres;
import POJO.Adres.AdresBuilder;
import POJO.Artikel;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Excen
 */

public class Main {     
    private static final Logger _log = LoggerFactory.getLogger(Main.class);
    
    public static void main (String[]args) throws SQLException, ClassNotFoundException, FileNotFoundException{
    
        
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
        //String url = "jdbc:mysql://localhost:3306/winkel";
        String user = "Anjewe"; 
        String pw = "Koetjes"; 
        Connection con;
        ResultSet rs;
        PreparedStatement stmt;   

//        KlantDAOXML start = new KlantDAOXML();
//        start.insertKlant();
//        start.findAll();
        
        
        HoofdMenuController start = new HoofdMenuController();
            start.setDatabase();
            
      
        /* 
        //private void connectToDB () {
            try{
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, pw);
                System.out.println("You are connected to " + url);
            }
            catch(ClassNotFoundException | SQLException ex){
                ex.printStackTrace();
            }          
        //  execute SQL commands
            private void executeSQL(){
            if (connection == null){
                System.out.print ("Please first connect to a database")
                return; 
            }
            else {         
            }        
    }   */          
    } 
}

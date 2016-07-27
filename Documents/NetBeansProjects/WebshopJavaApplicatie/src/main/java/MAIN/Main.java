package MAIN;

import Controller.HoofdMenuController;
import Controller.KlantController;
import Controller.ArtikelController;
import DAOs.Impl.AdresDAOImpl;
import DAOs.Impl.ArtikelDAOImpl;
import DAOs.Impl.KlantDAOImpl;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import POJO.Adres;
import POJO.Adres.AdresBuilder;
import POJO.Artikel;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Excen
 */

public class Main {       
    
    public static void main (String[]args) throws SQLException, ClassNotFoundException{
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/winkel?autoReconnect=true&useSSL=false";
        //String url = "jdbc:mysql://localhost:3306/winkel";
        String user = "Anjewe"; 
        String pw = "Koetjes"; 
        Connection con;
        ResultSet rs;
        PreparedStatement stmt;   


        HoofdMenuController start = new HoofdMenuController();
            start.start(); 
            
      
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

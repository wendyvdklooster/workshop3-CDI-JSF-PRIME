package MAIN;

import DAOs.AdresDao;
import DAOs.ArtikelDao;
import DAOGenerics.GenericDaoImpl;
import DAOs.KlantDao;
import Helpers.HibernateSessionFactory;
import POJO.Adres;
import POJO.Artikel;
import POJO.Klant;
import TestHibernate.HibernateTest;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Excen
 */

public class Main {       
   
   private static Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
   private static Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
  
   
    public static void main (String[]args) throws SQLException, ClassNotFoundException, FileNotFoundException {    
         
 
           Adres adres = new Adres(); 
        adres.setStraatnaam("bootlaan");
        adres.setHuisnummer(2);
        adres.setToevoeging("a");
        adres.setPostcode("7417PF");   
        adres.setWoonplaats("Heel");
  
          Adres adresa = new Adres(); 
                    adresa.setStraatnaam("kleiweg");
        adresa.setHuisnummer(22);
        adresa.setToevoeging("a");
        adresa.setPostcode("7417PF");   
        adresa.setWoonplaats("Yerseke");
        
        Klant klant = new Klant(); 
        klant.setKlantNummer("123654789");
        klant.setVoornaam("Gina");
        klant.setAchternaam("La Cruz");
        klant.setTussenvoegsel("de");
        klant.setEmail("gina@espanje.es");
        
        Klant klantf = new Klant();  
        klantf.setKlantNummer("987456321");
        klantf.setVoornaam("Paul");
        klantf.setAchternaam("vermeer");
        klantf.setTussenvoegsel("van");
        klantf.setEmail("paul@espanje.es");
//

            GenericDaoImpl adresDao;
            adresDao = new AdresDao();
            adresDao.create(adres);
            adresDao.create(adresa);
           //adresDao.readAll();
            
            GenericDaoImpl klantDao;
            klantDao = new KlantDao();
            klantDao.create(klant);
            klantDao.create(klantf);
           List<Klant> klantenlijst = new ArrayList<>();
           klantenlijst = klantDao.readAll();
           printKlantenLijst(klantenlijst);
        
       
    } 
    
    //static nu hier in de main
    public static void printKlantenLijst(List<Klant> lijst){
        System.out.println();
        System.out.println("Lijst met opgevraagde klanten");
        System.out.printf("%-10s%-10s%-18s%-15s%-25s%-15s%n","KlantId", "KlantNummer", "Voornaam", "Tussenvoegsel", "Achternaam", "Email");
        //System.out.println("KlantId\t\tVoornaam\t\tTussenvoegsel\t\tAchternaam\t\tEmail");
            for (int i = 0; i< lijst.size(); i++){
                System.out.printf("%-10s%-10s%-18s%-15s%-25s%-15s%n",
                        (lijst.get(i)).getKlantId(),(lijst.get(i)).getKlantNummer(),(lijst.get(i)).getVoornaam(),
                        (lijst.get(i)).getTussenvoegsel(),(lijst.get(i)).getAchternaam(),
                        (lijst.get(i)).getEmail());            
        }        
    }
    
//     public static void editLog(){
//        Properties p = new Properties(System.getProperties());
//        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
//        p.put("com.mchange.v2.c3p0.SQLWarnings", "com.mchange.v2.c3p0");
//        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");// Off or any other level
//        //p.put ("org.firebirdsql.jdbc.FBSQLWarning:")
//        System.setProperties(p);  
//    }  
}

package MAIN;

import DAOs.AdresDao;
import DAOs.ArtikelDao;
import DAOs.GenericDaoImpl;
import DAOs.KlantDao;
import Helpers.HibernateSessionFactory;
import POJO.Adres;
import POJO.Artikel;
import POJO.Klant;
import TestHibernate.HibernateTest;
import java.io.FileNotFoundException;
import java.sql.SQLException;
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
            
            GenericDaoImpl klantDao;
            klantDao = new KlantDao();
           klantDao.readAll();
            GenericDaoImpl adresDao;
            adresDao = new AdresDao();
            adresDao.readAll();
            
            
      
//        HoofdMenuController start = new HoofdMenuController();
//        System.out.println();
//        testLogger.debug("Toetreden tot de webshop");
//        LOGGER.debug("U kunt werken in het bestand");
//        System.out.println();
//        editLog();
//        start.setConnectionPool();
        
        
       
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

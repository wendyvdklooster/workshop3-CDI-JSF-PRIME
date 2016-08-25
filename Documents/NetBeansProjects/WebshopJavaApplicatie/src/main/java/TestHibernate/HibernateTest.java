/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package TestHibernate;

import POJO.Adres;
import POJO.Adres.AdresBuilder;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.Klant;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;


/**
 * @author Wendy
 */
public class HibernateTest {

    public void hibernateSession() {
//    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();  
//    SessionFactory firebirdSF = new Configuration().configure("hibernateFB.cfg.xml").buildSessionFactory();
    SessionFactory msSF = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
//        Artikel artikel = new Artikel();
//        artikel.setArtikelNaam("tuin");
//        artikel.setArtikelPrijs(60.00);      
        
//        Adres adres = new Adres(); 
//        adres.setStraatnaam("bootlaan");
//        adres.setHuisnummer(2);
//        adres.setToevoeging("a");
//        adres.setPostcode("7417PF");   
//        adres.setWoonplaats("Heel");
//  
//        Klant klant = new Klant();          
//        klant.setVoornaam("Gina");
//        klant.setAchternaam("La Cruz");
//        klant.setTussenvoegsel("de");
//        klant.setEmail("gina@espanje.es");
//        
//        
//        Set<Klant>klanten = adres.getKlanten();
//        klanten.add(klant);
//        adres.setKlanten(klanten);
//        
//        Set<Adres>adressen = klant.getAdressen();
//        adressen.add(adres);
//        klant.setAdressen(adressen);
//
//        Session session = msSF.openSession();
//        session.beginTransaction(); 
//        //session.save(artikel);
//        session.save(adres);
//        session.getTransaction().commit();
//        session.close(); 
//            
        Session session = msSF.openSession();
        session.beginTransaction(); 
//        session.save(klant);        
        session.getTransaction().commit();
        session.close(); 
//          
        
//        klant = null;
//        adres = null;
        
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        klant = (Klant)session.get(Klant.class, 1L);
//        adres = (Adres)session.get(Adres.class, 1L);
//        System.out.println("Klant is " + klant.getEmail() + " id: " + klant.getKlantId());
//        System.out.println("Adres is " + adres.getKlant());
        
    }
}

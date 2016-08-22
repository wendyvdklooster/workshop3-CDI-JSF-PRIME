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
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;


/**
 * @author Wendy
 */
public class HibernateTest {

    public void hibernateSession() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        
//        Artikel artikel = new Artikel();
//        artikel.setArtikelNaam("perenboom");
//        artikel.setArtikelPrijs(75.00);
        
//        Klant klant = new Klant();  
//        
//        klant.setVoornaam("Pien");
//        klant.setAchternaam("winter");
//        klant.setTussenvoegsel("de");
//        klant.setEmail("pienneke@vakantie.nl");
//        
//        Adres adres = new Adres(); 
//        adres.setStraatnaam("olievatstraat");
//        adres.setHuisnummer(11);
//        adres.setToevoeging("a");
//        adres.setPostcode("2908MX");   
//        adres.setWoonplaats("Oud-Beijerland");
//
//        klant.setAdres(adres);               

        Session session = sessionFactory.openSession();
        session.beginTransaction();        
//        session.save(klant);
        session.getTransaction().commit();
        session.close();     
        
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

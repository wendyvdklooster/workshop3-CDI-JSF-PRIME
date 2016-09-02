/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAOGenerics.GenericDaoImpl;
import DAOs.ArtikelDao;
import DAOs.BestellingDao;
import Helpers.HibernateSessionFactory;
import POJO.*;
import View.BestellingView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import View.ArtikelView;
import View.KlantView;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Excen
 */


public class BestellingController {
    
    // Data fields
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
    
    GenericDaoImpl<Bestelling, Long> bestellingDao; 
    GenericDaoImpl<Artikel, Long> artikelDao;
    GenericDaoImpl<Klant, Long> klantDao;
    GenericDaoImpl<BestellingArtikel, Long> bestellingArtikelDao;
    
    BestellingView bestellingView = new BestellingView();
    ArtikelView artikelView = new ArtikelView();
    KlantView klantView = new KlantView();
    // BestellingArtikelView?
    
    Scanner scanner = new Scanner(System.in);
    int userInput;
    
    // Session preparation
    public Session session;
    
    // sessionfactory aanroepen via de hibernatesessionfactory
    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    
    // session starten
    public Session getSession(){
        session = sessionFactory.openSession();
        session.beginTransaction();
       return session;
    }
    
    // Commit session
    protected void commitSession(Session session){
        session.getTransaction().commit();
    }
	
    // session afsluiten
    public void closeSession(Session session){            
            session.close();
    }
        
    public void bestellingMenu() {
        
        userInput = bestellingView.startBestellingMenu();
        
        switch (userInput) {
                // bestelling plaatsen
            case 1:
                plaatsBestelling();                   
                break;  
                
                // bestellinginfo ophalen
            case 2:      
                haalBestellingInfoOp();
                break;
                
                // Bestelling wijzigen
            case 3:
                wijzigBestelling();
                break;
                
                // Bestelling verwijderen
            case 4:
                verwijderBestelling();
                break;
                
                // Alle bestellingen tonen
            case 5:
                toonAlleBestellingen();       
                break;
                
                // verijder alle bestellingen
            case 6:
                verwijderAlleBestellingen();    
                break;
                
                // terug naar hoofdmenu
            case 7:
                terugNaarHoofdMenu();    
                break;
                    
                // default        
            default:    
                break;
        }
        terugNaarHoofdMenu();
    }
    
    public void terugNaarHoofdMenu() {
        HoofdMenuController hoofdMenu = new HoofdMenuController();
        hoofdMenu.start();
    }
    
    public void plaatsBestelling() {            
        
        
        
        
        
        session = getSession();
        bestellingDao = new BestellingDao();
        artikelDao = new ArtikelDao();
        
        
        // Bestaande klant opzoeken en toevoegen aan bestelling
        long klantId = bestellingView.voerKlantIdIn();
        Klant klant = (Klant) session.get(Klant.class, klantId);
        
        Bestelling bestelling = new Bestelling();
        bestelling.setKlant(klant);
        bestelling.setDatum(new java.util.Date());
       
        // Tonen beschikbare artikellen
        
        System.out.println("Beschikbare artikellen:");
        
        ArrayList<Artikel> artikelLijst = (ArrayList<Artikel>)artikelDao.readAll(Artikel.class, session);
        for (Artikel ar: artikelLijst){
            System.out.println(ar.getId() + " - " + ar.getArtikelNaam() + ": €" + ar.getArtikelPrijs());
        }
        
        // BestellingArtikel toevoeg loop
        
        
        
        int anotherOne = 0;
        boolean checker = true;
        
        do{
            try{
                
            System.out.println("Welk artikel wilt u aan de bestelling toevoegen?");
            userInput = scanner.nextInt();
                       
            }catch (InputMismatchException ex){
                System.out.println("Voer een integer in.");
            }
            
            BestellingArtikel bestellingArtikel = new BestellingArtikel();
            
            
            bestelling.getBestellingArtikellen().add(bestellingArtikel);
            
            System.out.println("Wilt u nog een artikel aan de bestelling toevoegen?");
            anotherOne = scanner.nextInt();
            if (anotherOne == 1){
                checker = true;
            }
            else {
                checker = false;
            }
 
        }while(checker);
        
        
        
        
        // DB insert
        
        Long bestellingId = (Long)bestellingDao.insert(bestelling, session);
        System.out.println("uw bestellingId: " + bestellingId);
        session.getTransaction().commit();
        session.close();
        
                
    }
    
    /*
    session = getSession();
        klantDao = new KlantDao();
        accountDao = new AccountDao();
        
        System.out.println("U gaat een account toevoegen. Voer uw klantId in: ");
        long klantId = klantView.voerKlantIdIn();
        
        klant = (Klant) session.get(Klant.class, klantId);
        Account account = new Account();
        // nieuwe account aanmaken.
        // account toevoegen in database
        klant.getAccounts().add(account);
        //update gevevens van klant
        session.getTransaction().commit();
        session = getSession();
        Set <Account> accounts = klant.getAccounts();
        
        // uitprinten van de set account 
    
    */
    
    public void haalBestellingInfoOp() {
           
    }
    
    public void wijzigBestelling() {
        
    }
    
    public void verwijderBestelling() {
        
        session = getSession();
        boolean deleted = false;
 
        Long bestellingId;
        
        ArrayList<Bestelling> bestellingLijst = (ArrayList<Bestelling>) bestellingDao.readAll(Bestelling.class, session);
        bestellingView.printBestellingLijst(bestellingLijst);
        System.out.println("Welke bestelling wilt u verwijderen?");
        bestellingId = bestellingView.voerBestellingIdIn();
        deleted = bestellingDao.deleteById(bestellingId, session);
        session.getTransaction().commit(); 
        System.out.println(bestellingId + " is verwijderd."); 
        session.close();
        bestellingMenu();        
    }
    

    public void toonAlleBestellingen() {
        
        ArrayList<Bestelling>bestellingLijst = (ArrayList<Bestelling>)bestellingDao.readAll(Bestelling.class, session);
        bestellingView.printBestellingLijst(bestellingLijst);
        
    }
    
    public void verwijderAlleBestellingen() {

        int verwijderConfirmatie = bestellingView.verwijderConfirmatie();
        
        if (verwijderConfirmatie == 1){
            session = getSession();
            bestellingDao.deleteAll(Bestelling.class, session);
            session.getTransaction().commit();
            session.close();
            System.out.println("Alles is verwijderd.\n");  
        }
        
        else {
            System.out.println("Verwijderen afgebroken.\n");
        }
        
    }
    
    // Optionele methoden
    
    public BestellingArtikel createBestellingArtikel(){
        
        long artikelId = bestellingView.voerArtikelIdIn();
        int artikelAantal = bestellingView.voerAantalIn();
                
        BestellingArtikel BS = new BestellingArtikel();
        // BS.setArtikelId(artikelId);
        BS.setArtikelAantal(artikelAantal);        
        
        return BS;
        
    }
  
}


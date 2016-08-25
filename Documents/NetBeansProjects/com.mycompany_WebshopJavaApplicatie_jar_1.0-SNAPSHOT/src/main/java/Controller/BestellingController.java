/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAOs.Interface.ArtikelDAOInterface;
import DAOs.Interface.BestellingArtikelDAOInterface;
import DAOs.Interface.BestellingDAOInterface;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingArtikel;
import View.BestellingView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Factory.DaoFactory;
import View.KlantView;
/**
 *
 * @author Excen
 */


public class BestellingController {
    
    BestellingView bestellingView = new BestellingView();
    BestellingDAOInterface bestellingDAO; 
    BestellingArtikelDAOInterface bestellingArtikelDAO; 
    ArtikelDAOInterface artikelDAO; 
    KlantView klantView = new KlantView();
    
    Scanner scanner = new Scanner(System.in);
    int userInput;
    
    public void bestellingMenu() {
        
        userInput = bestellingView.startBestellingMenu();
        
        switch (userInput) {
                // bestelling plaatsen
            
            // EH: Maak hier een Enum van (case 1, case 2, case 3 etc.)
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
        
        int klantID = klantView.voerKlantIdIn();
        int bestellingID = bestellingDAO.insertBestelling(klantID);
        int anotherOne = 0;
        boolean checker = true;
        
        bestellingDAO = DaoFactory.getBestellingDao();
        artikelDAO = DaoFactory.getArtikelDao();
        bestellingArtikelDAO = DaoFactory.getBestellingArtikelDao();

        // voeg artikelen toe aan bestelling
        ArrayList <BestellingArtikel> AL = new ArrayList<>();
        BestellingArtikel bestellingArtikel = new BestellingArtikel();

        // Overzicht beschikbare artikelen
        System.out.println("Beschikbare artikelen: ");
        ArrayList<Artikel>artikelLijst = new ArrayList<>();
        artikelLijst = artikelDAO.findAll();

        for (Artikel ar: artikelLijst){
            System.out.println(ar.getArtikelId() + " " + ar.getArtikelNaam());
        }

        // begin artikel toevoeg loop
        do {
            bestellingArtikel = createBestellingArtikel();
            bestellingArtikel.setBestellingId(bestellingID);
            bestellingArtikelDAO.createBestellingArtikel(bestellingArtikel);
            AL.add(bestellingArtikel);

            System.out.println("Wil je nog een artikel toevoegen?\n1 ja\n2 nee");

            try{
                anotherOne = scanner.nextInt();    
            } catch (InputMismatchException ex){
                System.out.println("Voer een integer in.");
            }

            if (anotherOne == 1) {
                checker = true;
            }

            else {
                checker = false;
            }
        } while (checker);

        System.out.println("De artikelen van Klant " + klantID + " zijn toegevoegd aan bestelling ID: " + bestellingID);
            System.out.println("De toegevoegde artikelen zijn: ");
            for (BestellingArtikel bar: AL){
            System.out.println(artikelDAO.findByArtikelID(bar.getArtikelId()).getArtikelNaam() + " " + bar.getArtikelAantal() + " keer");
        }

       bestellingMenu();         
    }
    
    public void haalBestellingInfoOp() {
        
        bestellingDAO = DaoFactory.getBestellingDao();
        artikelDAO = DaoFactory.getArtikelDao();
        bestellingArtikelDAO = DaoFactory.getBestellingArtikelDao();
        
        userInput = bestellingView.hoeWiltUZoeken();
        
        switch(userInput){
            case 1: // met bestellingID
                int bestellingID = bestellingView.zoekBestellingInfo();
                Bestelling bestelling = bestellingDAO.findById(bestellingID);

                bestellingView.printBestellingInfo(bestelling);

                ArrayList<Artikel>artikellijst = new ArrayList<>();
                artikellijst = bestellingArtikelDAO.findByBestellingId(bestellingID);
                System.out.println("Artikellen in bestelling: ");
                    for (Artikel ar: artikellijst){
                        System.out.println(ar.getArtikelNaam() + ": " 
                                + bestellingArtikelDAO.findAantalByArtikelID(bestellingID, ar.getArtikelId()) 
                                + " keer");
                    }
                break;
            case 2: // met klantId
                int klantId = klantView.voerKlantIdIn();
                ArrayList<Bestelling> bestellingLijst = bestellingDAO.findByKlantId(klantId);
                bestellingView.printBestellingLijstUitgebreid(bestellingLijst);               
                
        }
            
        bestellingMenu();            
    }
    
    public void wijzigBestelling() {
        
        bestellingDAO = DaoFactory.getBestellingDao();
        artikelDAO = DaoFactory.getArtikelDao();
        bestellingArtikelDAO = DaoFactory.getBestellingArtikelDao();
        
        // hoe wilt u zoeken?
        ArrayList<Artikel>artikelLijst = new ArrayList<>();        
        int bestellingId = bestellingView.zoekBestellingInfo();
        
        artikelLijst = bestellingArtikelDAO.findByBestellingId(bestellingId);
        
        int welkArtikel = bestellingView.wijzigBestellingInfo(artikelLijst, bestellingId);
        int watTeDoenMetArtikel = bestellingView.wijzigBestellingKeuze();
        
        if (watTeDoenMetArtikel == 1){
            // verwijder artikel uit bestellingArtikel. Gebruik dit alleen als er meerdere artikelen
            // in de bestelling staan. Gebruik anders verwijderBestelling.
            bestellingArtikelDAO.deleteArtikel(bestellingId, welkArtikel);
            System.out.println("Het artikel: " + artikelDAO.findByArtikelID(welkArtikel) + " is verwijderd uit Bestelling " + bestellingId);
        }
        else if (watTeDoenMetArtikel == 2){
            // pas aantal van artikel aan.
            int nieuwAantal = bestellingView.wijzigAantal();
            bestellingArtikelDAO.updateBestellingArtikelAantal(bestellingId, welkArtikel, nieuwAantal);
            System.out.println("Bestelling: " + bestellingId + " heeft een update gehad.");
            System.out.println("Het artikel " + artikelDAO.findByArtikelID(welkArtikel).getArtikelNaam() + " staat nu " + nieuwAantal + " keer in de bestelling.");
        }
    bestellingMenu();
    }
    
    public void verwijderBestelling() {
        
        bestellingDAO = DaoFactory.getBestellingDao();
        artikelDAO = DaoFactory.getArtikelDao();
        bestellingArtikelDAO = DaoFactory.getBestellingArtikelDao();
        
    int bestellingID = bestellingView.zoekBestellingInfo();
                bestellingDAO.deleteBestelling(bestellingID);
                
                // Als je een bestelling verwijderd zul je die altijd ook willen verwijderen uit de koppeltabel
                // het zou dus elegant zijn als de hierboven aangeroepen deleteBestelling zelf ook 
                // deleteBestellingArtikel zou aanroepen.
                
                bestellingArtikelDAO.deleteBestellingArtikel(bestellingID);
                System.out.println(bestellingID + " is verwijderd.");    
        bestellingMenu();        
    }
    
    public void toonAlleBestellingen() {
        
        bestellingDAO = DaoFactory.getBestellingDao();
        artikelDAO = DaoFactory.getArtikelDao();
        bestellingArtikelDAO = DaoFactory.getBestellingArtikelDao();
        
        //  bestellingen uit bestelling tabel
        ArrayList<Bestelling>bestellingLijst = bestellingDAO.findAll();
        bestellingView.printBestellingLijst(bestellingLijst);
        
        
        // TODO implementatie van onderstaande bestellingartikel lijst
        // bestellingen uit bestellingartikel koppel tabel
        // ArrayList<BestellingArtikel>bestellingArtikelLijst = bestellingArtikelDAO.findAll();
        // bestellingView.printBestellingLijst(bestellingArtikelLijst);
        
    }
    
    public void verwijderAlleBestellingen() {
        
        bestellingDAO = DaoFactory.getBestellingDao();
        artikelDAO = DaoFactory.getArtikelDao();
        bestellingArtikelDAO = DaoFactory.getBestellingArtikelDao();
        
        int verwijderConfirmatie = bestellingView.verwijderConfirmatie();
        
        if (verwijderConfirmatie == 1){
            bestellingDAO.deleteAll();
            bestellingArtikelDAO.deleteAll();
            System.out.println("Alles is verwijderd.\n");  
        }
        
        else {
            System.out.println("Verwijderen afgebroken.\n");
        }
        
    }
    
    // Optionele methoden
    
    public BestellingArtikel createBestellingArtikel(){
        
        int artikelId = bestellingView.voerArtikelIdIn();
        int artikelAantal = bestellingView.voerAantalIn();
                
        BestellingArtikel BS = new BestellingArtikel();
        BS.setArtikelId(artikelId);
        BS.setArtikelAantal(artikelAantal);        
        
        return BS;
        
    }
  
}

/*

// klantId is bekend:
        switch (input) {
            case 1:
                klantId = klantView.voerKlantIdIn();
                klant = klantDAO.findByKlantId(klantId);
                klantView.printKlantGegevens(klant);
                break;
            case 2:
                int keuze = klantView.hoeWiltUZoeken();
                switch (keuze) {
                    case 1: // zoeken op voor-/achternaam
                        String achterNaam = klantView.voerAchterNaamIn();
                        String voorNaam = klantView.voerVoorNaamIn();
                        klant = klantDAO.findByVoorNaamAchterNaam(achterNaam, voorNaam);
                        klantView.printKlantGegevens(klant);
                        break;
                    case 2: //zoeken op email
                        String email = klantView.voerEmailIn();
                        klant = klantDAO.findByEmail(email);
                        klantView.printKlantGegevens(klant);
                        break;
                    case 3: // direct door naar einde switch: methode naar inlogschermklant()
                        break;
                    default:
                        break;
                }   
            default:
                break;
        }

*/
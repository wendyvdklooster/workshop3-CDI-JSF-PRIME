/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.Impl.MySQL.KlantAdresDAOSQL;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import POJO.Adres;
import View.AdresView;
import View.HoofdMenuView;
import java.sql.SQLException;
import java.util.ArrayList;
import Factory.DaoFactory;
import POJO.Adres.AdresBuilder;

/**
 *
 * @author Anne
 */
public class AdresController {
            
   DaoFactory daoFactory = new DaoFactory();    
   AdresDAOInterface adresDao = DaoFactory.getAdresDao();    
   KlantAdresDAOInterface klantAdresDao = DaoFactory.getKlantAdresDao();        
   HoofdMenuView hoofdMenuView = new HoofdMenuView(); 
   
   AdresView adresView = new AdresView();
   Adres adres;    
   AdresBuilder adresBuilder = new AdresBuilder();
   KlantController klantController = new KlantController();
   ArrayList<Adres> adressenLijst = new ArrayList();
    
   int userInput;
    
   
    public void adresMenu()  {
        
        userInput = adresView.startAdresMenu();
        
        switch (userInput) {
            case 1:
                userInput = adresView.bentUNieuweKlant();
                if (userInput == 1) {
                    System.out.println("Vul eerst de klantgegevens in.");
                    System.out.println();
                    klantController.voegNieuweKlantToe();
                }
                else if (userInput == 2) {
                    voegNieuwAdresToe();
                }
                break;
            case 2:
                zoekAdresGegevens();
                break;
            case 3:
                wijzigAdresGegevens();
                break;
            case 4:
                verwijderAdresGegevens();
                break;
            case 5: 
                terugNaarHoofdMenu();                   
                break;
            default:
                System.out.println("Deze optie is niet beschikbaar.");
                break;
        } 
        
    }
    
    public int voegNieuwAdresToe() {
        System.out.println("U wilt een nieuw adres toevoegen. Voer hieronder de gegevens in.");
        int klantId = adresView.voerKlantIdIn();
        adres = createAdres();
        
        //voeg toe in adrestabel
        adres = adresDao.insertAdres(adres);
        int adresId = adres.getAdresId();
        System.out.println(adresId);
        // voeg toe in koppeltabel
        boolean toegevoegd = klantAdresDao.insertKlantAdres(klantId, adresId);
        
        System.out.println("U heeft het volgende adres toegevoegd.");
        adresView.printAdresOverzicht(adres);
        
        return adresId;            
    }
    
    public Adres createAdres() {
        
        String straatnaam = adresView.voerStraatnaamIn();
        int huisnummer = adresView.voerHuisnummerIn();
        String toevoeging = adresView.voerToevoegingIn();
        String postcode = adresView.voerPostcodeIn();
        String woonplaats = adresView.voerWoonplaatsIn();
        
        adresBuilder.straatnaam(straatnaam);
        adresBuilder.huisnummer(huisnummer);
        adresBuilder.toevoeging(toevoeging);
        adresBuilder.postcode(postcode);
        adresBuilder.woonplaats(woonplaats);
        
        adres = adresBuilder.build();
        
        return adres;
    }
    
    public void zoekAdresGegevens()  {
        userInput = adresView.menuAdresZoeken();
        switch (userInput) {
            case 1:        
                // één adres zoeken
                userInput = adresView.hoeWiltUZoeken();
                switch (userInput) {
                    case 1:
                        int adresId = adresView.voerAdresIdIn();
                        adres = adresDao.findByAdresID(adresId);
                        adresView.printAdresOverzicht(adres);
                        break;
                    case 2: 
                        String straatnaam = adresView.voerStraatnaamIn();
                        adressenLijst = adresDao.findByStraatNaam(straatnaam);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 3:
                        String postcode = adresView.voerPostcodeIn();
                        int huisnummer = adresView.voerHuisnummerIn();
                        adressenLijst = adresDao.findByPostcodeHuisNummer(postcode, huisnummer);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 4:
                        String woonplaats = adresView.voerWoonplaatsIn();
                        adressenLijst = adresDao.findByWoonplaats(woonplaats);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 5:
                        break; // doorsturen einde switch; terug naar adres menu
                    default: 
                        break;
                }
            break;
            case 2:
                // alle adressen zoeken
                ArrayList <Adres> adressenLijst = adresDao.findAllAdresses();
                System.out.println("Alle adressen in het adressenbestand");
                adresView.printAdressenLijst(adressenLijst);  
                break; 
            case 3:
                break;
            default:
                break;
        }
        adresMenu();
    }
    
    public void wijzigAdresGegevens() {
                
        userInput = adresView.hoeWiltUZoeken();
        switch (userInput) {
            case 1: 
                updateOpAdresId();
                break;
            case 2:
                updateOpStraatnaam();
                break;
            case 3:
                updateOpPostcodeHuisnummer();
                break;
            case 4:
                updateOpWoonplaats();
                break;
            case 5:
                break; // doorsturen einde switch; terug naar adres menu
            default:
                System.out.println("Die optie is niet beschikbaar, je keert terug naar het bestelling menu.");
                break;
        }
        adresMenu();
    }
    public void updateOpAdresId() {
        
        adres = new Adres();
        Adres gewijzigdAdres = new Adres();
        int adresId = adresView.voerAdresIdIn();
        
        adres = adresDao.findByAdresID(adresId);
        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
        adresDao.updateGegevens(gewijzigdAdres);
        
        System.out.println("Oude adresgegevens: ");
        adresView.printAdresOverzicht(adres);
        System.out.println("Nieuwe adresgegevens: ");                  
        adresView.printAdresOverzicht(gewijzigdAdres);
    }
    
    public void updateOpStraatnaam() {
        
        adres = new Adres();
        Adres gewijzigdAdres = new Adres();
        String straatnaam = adresView.voerStraatnaamIn();
        
        adressenLijst = adresDao.findByStraatNaam(straatnaam);
        updateOpAdresId();
//        int adresId = adresView.voerAdresIdIn();
//        adres = adresDao.findByAdresID(adresId);
//        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
//        gewijzigdAdres = adresDao.updateGegevens(gewijzigdAdres);
//        
//        System.out.println("Oude adresgegevens: ");
//        adresView.printAdresOverzicht(adres);
//        System.out.println("Nieuwe adresgegevens: ");                  
//        adresView.printAdresOverzicht(gewijzigdAdres);
        
    }
    
    public void updateOpPostcodeHuisnummer() {
        
        adres = new Adres();
        Adres gewijzigdAdres = new Adres();
        int huisnummer = adresView.voerHuisnummerIn();
        String postcode = adresView.voerPostcodeIn();
        
        adressenLijst = adresDao.findByPostcodeHuisNummer(postcode, huisnummer);
        updateOpAdresId();
//        int adresId = adresView.voerAdresIdIn();
//        adres = adresDao.findByAdresID(adresId);
//        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
//        gewijzigdAdres = adresDao.updateGegevens(gewijzigdAdres);
//        
//        System.out.println("Oude adresgegevens: ");
//        adresView.printAdresOverzicht(adres);
//        System.out.println("Nieuwe adresgegevens: ");                  
//        adresView.printAdresOverzicht(gewijzigdAdres);
    }
    
    
    public void updateOpWoonplaats() {
        
        adres = new Adres();
        Adres gewijzigdAdres = new Adres();
        String woonplaats = adresView.voerWoonplaatsIn();
        
        adressenLijst = adresDao.findByWoonplaats(woonplaats);
        updateOpAdresId();
//        int adresId = adresView.voerAdresIdIn();
//        adres = adresDao.findByAdresID(adresId);
//        gewijzigdAdres = invoerNieuweAdresGegevens(adres);
//        gewijzigdAdres = adresDao.updateGegevens(gewijzigdAdres);
//        
//        System.out.println("Oude adresgegevens: ");
//        adresView.printAdresOverzicht(adres);
//        System.out.println("Nieuwe adresgegevens: ");                  
//        adresView.printAdresOverzicht(gewijzigdAdres);
    }
    
    
    public Adres invoerNieuweAdresGegevens(Adres adres) {
        
       int juist = 0;
        
        String straatnaam = adres.getStraatnaam();
        juist = adresView.checkInputString(straatnaam);
        if (juist == 2) {
            straatnaam = adresView.voerStraatnaamIn();
        } 
        
        int huisnummer = adres.getHuisnummer();
        String huisnummerString = huisnummer + "";
        juist = adresView.checkInputString(huisnummerString);
        if (juist == 2) {
            huisnummer = adresView.voerHuisnummerIn();
        } 
        
        String toevoeging = adres.getToevoeging();
        juist = adresView.checkInputString(toevoeging);
        if (juist == 2) {
            toevoeging = adresView.voerToevoegingIn();
        } 
        
        String postcode = adres.getPostcode();
        juist = adresView.checkInputString(postcode);
        if (juist == 2) {
            postcode = adresView.voerPostcodeIn();
        } 
        
        String woonplaats = adres.getWoonplaats();
        juist = adresView.checkInputString(woonplaats);
        if (juist == 2) {
            woonplaats = adresView.voerWoonplaatsIn();
        } 
        
        Adres adres2 = new Adres(adresBuilder);
        adresBuilder.adresId(adres.getAdresId());
        adresBuilder.straatnaam(straatnaam);
        adresBuilder.huisnummer(huisnummer);
        adresBuilder.toevoeging(toevoeging);
        adresBuilder.postcode(postcode);
        adresBuilder.woonplaats(woonplaats);
        
        adres2 = adresBuilder.build();
        
        return adres2;
    }
    
    
    public void verwijderAdresGegevens() {
        
        klantAdresDao = new KlantAdresDAOSQL();
        boolean isDeletedInAdres = false;
        boolean isDeletedInKlantAdres = false;
        
        userInput = adresView.printVerwijderAdresMenu();
        switch (userInput) {
            case 1:
                System.out.println("Voer het bestelling id in: ");
                int adresId = adresView.voerAdresIdIn();
                adres = adresDao.findByAdresID(adresId);
                isDeletedInAdres = adresDao.deleteAdres(adresId);
                isDeletedInKlantAdres = klantAdresDao.deleteKlantAdresByAdresId(adresId);
                if (isDeletedInAdres == true && isDeletedInKlantAdres == true) {
                    System.out.println("Het volgende adres is verwijderd uit het bestand: ");
                    System.out.println();
                    adresView.printAdresOverzicht(adres);
                    System.out.println("Alle koppelingen van klant met adres zijn ook verwijderd.");
                }
                else {
                    System.out.println("Het volgende adres is NIET verwijderd uit het bestand: ");
                    System.out.println();
                    adresView.printAdresOverzicht(adres);
                }   
                break;
            case 2:
                userInput = adresView.bevestigingsVraag();
                if (userInput == 1) {
                    isDeletedInAdres = adresDao.deleteAll();
                    isDeletedInKlantAdres = klantAdresDao.deleteAll();
                    System.out.println("Alle adressen zijn verwijderd.");                    
                    System.out.println("alle koppelingen van klant en adres zijn verwijderd");
                }
            case 3:
                break;
            default:
                break;
        }
        adresMenu();      
           
    }
    
    public void terugNaarHoofdMenu() {
        HoofdMenuController hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }
}

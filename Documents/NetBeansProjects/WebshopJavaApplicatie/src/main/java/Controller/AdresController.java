/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import POJO.Adres;
import View.AdresView;
import View.HoofdMenuView;
import java.util.ArrayList;
import Factory.DaoFactory;
import POJO.Adres.AdresBuilder;
import POJO.Klant;
import POJO.KlantAdres;
import View.KlantView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anne
 */
public class AdresController {
     
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
   DaoFactory daoFactory = new DaoFactory();    
   AdresDAOInterface adresDao;    
   KlantDAOInterface klantDao; 
   KlantAdresDAOInterface klantAdresDao;         
   HoofdMenuView hoofdMenuView = new HoofdMenuView(); 
   
   AdresView adresView = new AdresView();
   KlantView klantView = new KlantView();
   Adres adres;    
   Klant klant;
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
            case 5: zoekAdresKlantGegevens();
                break;
            case 6: 
                terugNaarHoofdMenu();                   
                break;
            default:
                System.out.println("Deze optie is niet beschikbaar.");
                break;
        } 
        
    }
    
    public long voegNieuwAdresToe() {
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
        System.out.println("U wilt een nieuw adres toevoegen. Voer hieronder de gegevens in.");
        int klantId = adresView.voerKlantIdIn();
        adres = createAdres();
        
        //voeg toe in adrestabel
        adres = adresDao.insertAdres(adres);
        long adresId = adres.getId();
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
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
        userInput = adresView.menuAdresZoeken();
        switch (userInput) {
            case 1:        
                // één adres zoeken
                userInput = adresView.hoeWiltUZoeken();
                switch (userInput) {
                    case 1: // zoek op adresId
                        int adresId = adresView.voerAdresIdIn();
                        adres = adresDao.findByAdresID(adresId);
                        adresView.printAdresOverzicht(adres);
                        break;
                    case 2: // zoek adressen op klantId
                        int klantId = klantView.voerKlantIdIn();
                        ArrayList<Adres>adressenLijst = klantAdresDao.findAdresByKlantId(klantId);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 3: // zoek op straatnaam
                        String straatnaam = adresView.voerStraatnaamIn();
                        adressenLijst = adresDao.findByStraatNaam(straatnaam);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 4: // zoek op postcode huisnummer
                        String postcode = adresView.voerPostcodeIn();
                        int huisnummer = adresView.voerHuisnummerIn();
                        adressenLijst = adresDao.findByPostcodeHuisNummer(postcode, huisnummer);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 5: // zoek op woonplaats
                        String woonplaats = adresView.voerWoonplaatsIn();
                        adressenLijst = adresDao.findByWoonplaats(woonplaats);
                        adresView.printAdressenLijst(adressenLijst);
                        break;
                    case 6:
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
         
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
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
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
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
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
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
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
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
        adresBuilder.adresId(adres.getId());
        adresBuilder.straatnaam(straatnaam);
        adresBuilder.huisnummer(huisnummer);
        adresBuilder.toevoeging(toevoeging);
        adresBuilder.postcode(postcode);
        adresBuilder.woonplaats(woonplaats);
        
        adres2 = adresBuilder.build();
        
        return adres2;
    }
    
    
    public void verwijderAdresGegevens() {
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao();
        
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
    
    public void zoekAdresKlantGegevens(){
        
        klantAdresDao = DaoFactory.getKlantAdresDao(); 
        adresDao = DaoFactory.getAdresDao();
        klantDao = DaoFactory.getKlantDao(); 
        
        long klantId; 
        userInput = adresView.menuAdresKlantZoeken();
       
        switch(userInput){
           case 1: // adres(sen) bij klantId
               klantId = klantView.voerKlantIdIn();
               ArrayList<Adres>adressenLijst = klantAdresDao.findAdresByKlantId(klantId);
               adresView.printAdressenLijst(adressenLijst);
               break;
           case 2: // alle adressen bij klanten opzoeken
               ArrayList<KlantAdres> klantAdresLijst = klantAdresDao.findAll();
               adresView.printKlantAdresLijst(klantAdresLijst);
               int keuze = adresView.alleKoppellingenUitgeprint();
               switch(keuze){
                   case 1: // ja
                        for (int i = 0 ; i < klantAdresLijst.size(); i++){
                            long adresId = klantAdresLijst.get(i).getAdresId();
                            klantId = klantAdresLijst.get(i).getKlantId();
                            adres = adresDao.findByAdresID(adresId);
                            Klant klant = klantDao.findByKlantId(klantId);
                            System.out.println("Onderstaand adres:");
                            adresView.printAdresOverzicht(adres);                           
                            System.out.println("hoort bij: ");
                            klantView.printKlantGegevens(klant);
                            
                        }
                            break;
                    case 2: // nee   
                        break;
               
               }
               break;
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

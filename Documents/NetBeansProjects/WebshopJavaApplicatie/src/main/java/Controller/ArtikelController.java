
package Controller;



import DAOGenerics.GenericDaoImpl;
import DAOs.ArtikelDao;
import View.ArtikelView;

import POJO.Artikel;
import POJO.Bestelling;
import View.BestellingView;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ArtikelController {
  
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
    ArtikelView artikelView = new ArtikelView();   
    Artikel artikel = new Artikel();    
    //BestellingView bestellingView = new BestellingView();
    GenericDaoImpl artikelDao;
    
    
    public void artikelMenu()  {
        
        int userInput = artikelView.startArtikelMenu();
        
        switch (userInput) {
            case 1: 
                artikel = createArtikel();
                voegNieuwArtikelToe(artikel); 
                break;
            case 2: 
                zoekArtikelGegevens();
                break;
            case 3:
                wijzigArtikelGegevens();
                break;
            case 4:
                verwijderArtikelGegevens();
                break;
            case 5:
                terugNaarHoofdMenu();
                break;
            default:
                System.out.println("Deze optie is niet beschikbaar.");
                break;
        }     
        
    }
    
    
    public Artikel createArtikel() {
        
        System.out.println("Vul de artikel gegevens in: ");
        String artikelNaam = artikelView.voerArtikelNaamIn();
        double artikelPrijs = artikelView.voerAtrikelPrijsIn();
        
        artikel = new Artikel();
        artikel.setArtikelNaam(artikelNaam);
        artikel.setArtikelPrijs(artikelPrijs);
        return artikel;
    } 
    
    
    public void voegNieuwArtikelToe(Artikel artikel) {     
         
        artikelDao = new ArtikelDao();        
        artikel = (Artikel) artikelDao.create(artikel);
        
        System.out.println("U heeft de volgende gegevens ingevoerd:");
        artikelView.printArtikelOverzicht(artikel); 
        
        artikelMenu();
    }
         
    
    public void zoekArtikelGegevens()  {    
         
        artikelDao = new ArtikelDao();
        artikel = new Artikel();         
		
        int input = artikelView.menuArtikelZoeken();
        switch (input){
                case 1:  // naar 1 artikel zoeken
                        int userInput = artikelView.hoeWiltUZoeken();				
                        switch (userInput) {
                                case 1: 
                                    int artikelId = artikelView.voerArtikelIdIn();
                                    artikel = (Artikel) artikelDao.readById(artikelId);
                                    artikelView.printArtikelOverzicht(artikel);
                                    break;
//                                case 2:
//                                        String artikelNaam = artikelView.voerArtikelNaamIn();
//                                        artikel = artikelDAO.findByArtikelNaam(artikelNaam);
//                                        artikelView.printArtikelOverzicht(artikel);
//                                        break;
//                                case 3:
//                                        double artikelPrijs = artikelView.voerAtrikelPrijsIn();
//                                        artikel = artikelDAO.findByArtikelPrijs(artikelPrijs);
//                                        artikelView.printArtikelOverzicht(artikel);
//                                        break;
                                case 4: 
                                        break;
                                default: 
                                        break;
                        }
                        break; // einde naar 1 artikel zoeken
                case 2: // alle artikelen zoeken
                    ArrayList <Artikel> artikelenLijst = 
                            (ArrayList <Artikel>) artikelDao.readAll();
                    System.out.println("Alle artikelen in het bestand");
                    artikelView.printArtikelenLijst(artikelenLijst); 
                        break; 
                case 3: // naar artikelmenu
                        break; 
                default: // automatisch naar artikelmenu	
                        break; 
        }		
     artikelMenu();
    }
    
    
    public void wijzigArtikelGegevens() {             
        
        int userInput = artikelView.hoeWiltUZoeken();
        
        switch (userInput) {
            case 1: 
                updateOpArtikelId();
                break;
            case 2:
                updateOpArtikelNaam(); 
                break;
            case 3: 
                updateOpArtikelPrijs();
                break;
            case 4:
                artikelMenu();
                break;
        }
        artikelMenu();
    }
    
    public void updateOpArtikelId() {
        
        artikelDao = new ArtikelDao();
        
        Artikel gewijzigdArtikel = new Artikel();
        boolean gewijzigd;
        
        int artikelId = artikelView.voerArtikelIdIn();
        artikel = (Artikel) artikelDao.readById(artikelId);
        gewijzigdArtikel = invoerNieuweArtikelGegevens(artikel);
        
        artikelDao.update(gewijzigdArtikel); // geeft geen boolean terug
//        if (gewijzigd == true) {
//            System.out.println("De oude gegevens: ");
//            artikelView.printArtikelOverzicht(artikel);
//            System.out.println("De nieuwe gegevens: ");
//            gewijzigdArtikel = artikelDAO.findByArtikelID(artikelId);
//            artikelView.printArtikelOverzicht(gewijzigdArtikel);
//        }
    }
    
    
    public void updateOpArtikelNaam() {
        artikelDao = new ArtikelDao();
               
        Artikel gewijzigdArtikel = new Artikel();
        boolean gewijzigd;
    
        String artikelNaam = artikelView.voerArtikelNaamIn();
        artikel = (Artikel) artikelDao.readById(artikelNaam);
        gewijzigdArtikel = invoerNieuweArtikelGegevens(artikel);
        artikelDao.update(gewijzigdArtikel); // geeft nu geen boolean terug
//        if (gewijzigd == true) {
//            System.out.println("De oude gegevens: ");
//            artikelView.printArtikelOverzicht(artikel);
//            System.out.println("De nieuwe gegevens: ");
//            gewijzigdArtikel = artikelDAO.findByArtikelNaam(artikelNaam);
//            artikelView.printArtikelOverzicht(gewijzigdArtikel);
//        }
        // else opvangen als niet gelukt is                     
    }
    
    public void updateOpArtikelPrijs() {
        artikelDao = new ArtikelDao();
        
        Artikel gewijzigdArtikel = new Artikel();
        boolean gewijzigd;
        
        double artikelPrijs = artikelView.voerAtrikelPrijsIn();
        artikel = (Artikel) artikelDao.readById(artikelPrijs);
        gewijzigdArtikel = invoerNieuweArtikelGegevens(artikel);
        artikelDao.update(gewijzigdArtikel); // geen boolean returntype
//        if (gewijzigd == true) {
//            System.out.println("De oude gegevens: ");
//            artikelView.printArtikelOverzicht(artikel);
//            System.out.println("De nieuwe gegevens: ");
//            artikelView.printArtikelOverzicht(gewijzigdArtikel);
//        }
//                    // else opvangen als niet gelukt                       
            
    }
        
    public Artikel invoerNieuweArtikelGegevens(Artikel artikel) {
                
        int juist = 0;
        
        String artikelNaam = artikel.getArtikelNaam();
        juist = artikelView.checkInputString(artikelNaam);
        if (juist == 2) {
            artikelNaam = artikelView.voerArtikelNaamIn();
        }
        double artikelPrijs = artikel.getArtikelPrijs();
        String artikelPrijsString = artikelPrijs + "";
        juist = artikelView.checkInputString(artikelPrijsString);
        if (juist == 2) {
            artikelPrijs = artikelView.voerAtrikelPrijsIn();
        }
        
        long artikelId = artikel.getArtikelId();
        Artikel artikelNieuw = new Artikel(artikelId, artikelNaam, artikelPrijs);
        
        return artikelNieuw;        
    }
    
    
    public void verwijderArtikelGegevens()  {
        artikelDao = new ArtikelDao();
                
        int userInput = artikelView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 artikel verwijderen  
                artikelView.printArtikelenLijst((ArrayList<Artikel>) artikelDao.readAll());
                int artikelId = artikelView.printDeleteArtikelView();
                artikelDao.deleteById(artikelId);
//                artikelView.printDeleteResultaat(deleted, artikelId);
//                ArrayList <Bestelling> lijst = bestellingArtikelDAO.findBestellingByArtikelId(artikelId);
//                System.out.println("In de volgende bestelling(en) is het verwijderde artikel aanwezig");
//                bestellingView.printBestellingLijst(lijst);
//                break;
            case 2:// alle artikelen verwijderen                
                int x = artikelView.bevestigingsVraag();                
                if (x == 1){ // bevestiging is ja
                    artikelDao.deleteAll();                    
                    //System.out.println(rowsAffected + " totaal aantal artikelen zijn verwijderd");                       
                }                
                else { // bevestiging = nee
                    System.out.println("De artikel gegevens worden NIET verwijderd.");
                }
                break;                
            case 3:// door naar einde methode > naar artikelmenu();
                break;
            default:
                break;
        }
        artikelMenu();
    }
    
    public void terugNaarHoofdMenu() {
        HoofdMenuController hoofdMenu = new HoofdMenuController();
        hoofdMenu.start();
    }
     
    
}

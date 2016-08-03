
package Controller;

import DAOs.Impl.AdresDAOFB;
import DAOs.Impl.AdresDAOSQL;
import DAOs.Impl.KlantAdresDAOFB;
import DAOs.Impl.KlantAdresDAOSQL;
import DAOs.Impl.KlantDAOFB;
import DAOs.Impl.KlantDAOSQL;
import DAOs.Impl.KlantDAOXML;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import POJO.Adres;
import POJO.Adres.AdresBuilder;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import View.AdresView;
import View.HoofdMenuView;
import View.KlantView;
import java.util.ArrayList;

/**
 *
 * @author Anne
 */

public class KlantController {
    // datafields in klantcontroller
    KlantDAOInterface klantDAO = new KlantDAOXML();
    KlantView klantView = new KlantView();    
    KlantBuilder klantBuilder = new KlantBuilder();
    Klant klant;
    ArrayList<Klant>klantenLijst = new ArrayList();
    
    AdresDAOInterface adresDAO = new AdresDAOFB();
    AdresView adresView;
    AdresController adresController;
    AdresBuilder adresBuilder = new AdresBuilder();
    Adres adres; 
           
    KlantAdresDAOInterface klantAdresDAO = new KlantAdresDAOFB();
    
    HoofdMenuController hoofdMenuController;
    HoofdMenuView hoofdMenuView;
    
    
    public void klantMenu() {
        
        int keuze = klantView.startMenuKlant();
        
        switch(keuze){
            case 1: 
                voegNieuweKlantToe();
                break;
            case 2: 
                zoekKlantGegevens();
                break;
            case 3: 
                wijzigKlantGegevens();
                break;
            case 4: 
                verwijderKlantGegevens();
                break;
            case 5: 
                terugNaarHoofdMenu();
                break;
            case 6: // programma afsluiten
                hoofdMenuController.afsluiten();
                break;            
        }        
        
    }
    
    
    public int voegNieuweKlantToe() {
        
        adresController = new AdresController();
        
        klantView.printString("U gaat een klant toevoegen. Voer de gegevens in.");
        klant = createKlant();           
        klant = klantDAO.insertKlant(klant); //klant inclusief klantId
        int klantId = klant.getKlantId();                     
        
        // later vervangen door: int adresId = adresController.voegNieuwAdresToe();
        System.out.println("Voer uw adres in: ");
        adres = adresController.createAdres();
        adres = adresDAO.insertAdres(adres);
        int adresId = adres.getAdresId(); 
        boolean toegevoegd = klantAdresDAO.insertKlantAdres(klantId, adresId); 
        
        System.out.println("U heeft de klant- en adresgegevens toegevoegd van klantId: " 
                + klantId + " en adresId " + adresId); 
        System.out.println();
        
        klantMenu();
        
      return klantId;
    } // eind methode voegNieuweKlantToe
    
    
    public void zoekKlantGegevens() {
        
        int klantId = 0;   
        int x = 0;
        
        int input = klantView.menuKlantZoeken();
        switch (input) {
            case 1: // één klnat zoeken        
            x = klantView.isKlantIdBekend();           
            // klantId is bekend:
            switch (x) {
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
                            klantenLijst = klantDAO.findByVoorNaamAchterNaam(achterNaam, voorNaam);
                            klantView.printKlantenLijst(klantenLijst);
                            break;
                        case 2: //zoeken op email
                            String email = klantView.voerEmailIn();
                            klantenLijst = klantDAO.findByEmail(email);
                            klantView.printKlantenLijst(klantenLijst);
                            break;
                        case 3: // direct door naar einde switch: methode naar inlogschermklant()
                            break;
                        default:
                            break;
                    }   
                default:
                    break;
                    
            } // eind zoeken naar 1 klant
            break;
            case 2: // zoeken naar alle klanten
                klantenLijst = klantDAO.findAllKlanten();
                System.out.println("Alle klanten in het bestand");
                klantView.printKlantenLijst(klantenLijst);  
                break; 
            case 3: // direct door naar einde switch: methode naar inlogschermklant()
                break;
            default: 
                break; 
        } // eind zoeken naar 1 klant of alle klanten
        
        
        klantMenu();
    } // eind methode zoekKlantGegevens
    
    
    // bij adres: zijn submethoden gedefinieerd die meerdere malen geimplementeerd kunnen worden. 
    // zou hier ook kunnen. zie oa wijzig adres gegevens. 
    
    public void wijzigKlantGegevens(){
       
       Klant gewijzigdeKlant = new Klant();
       int klantId = 0;
        
       int input = klantView.isKlantIdBekend();
        // klantid is bekend:
        switch (input) {
            case 1:
                klantId = klantView.voerKlantIdIn();
                klant = klantDAO.findByKlantId(klantId);
                gewijzigdeKlant = voerWijzigingenKlantIn(klant);
                gewijzigdeKlant = klantDAO.updateGegevens(gewijzigdeKlant);                                               
                klantView.printString("Oude klantgegevens:");
                klantView.printKlantGegevens(klant);
                klantView.printString("Nieuwe klantgegevens:");                
                klantView.printKlantGegevens(gewijzigdeKlant);
                break;
            case 2:
                int keuze = klantView.hoeWiltUZoeken();
                switch (keuze) {
                    case 1: // wijzigen op basis voor- en achternaam                        
                        String achterNaam = klantView.voerAchterNaamIn();
                        String voorNaam = klantView.voerVoorNaamIn();
                        klantenLijst = klantDAO.findByVoorNaamAchterNaam(achterNaam, voorNaam);
                        klantView.printKlantenLijst(klantenLijst); 
                        klantId = klantView.voerKlantIdIn();
                        klant = klantDAO.findByKlantId(klantId);
                        
                        gewijzigdeKlant = voerWijzigingenKlantIn(klant);                         
                        gewijzigdeKlant = klantDAO.updateGegevens(gewijzigdeKlant);                                               
                        klantView.printString("Oude klantgegevens:");
                        klantView.printKlantGegevens(klant);
                        klantView.printString("Nieuwe klantgegevens:");                        
                        klantView.printKlantGegevens(gewijzigdeKlant); 
                        break;
                    case 2: // wijzigen op basis van email                        
                        String email = klantView.voerEmailIn();
                        klantenLijst = klantDAO.findByEmail(email);
                        klantView.printKlantenLijst(klantenLijst); 
                        klantId = klantView.voerKlantIdIn();
                        klant = klantDAO.findByKlantId(klantId);
                        
                        gewijzigdeKlant = voerWijzigingenKlantIn(klant);                         
                        gewijzigdeKlant = klantDAO.updateGegevens(gewijzigdeKlant);                       
                        System.out.println("Oude klantgegevens:");
                        klantView.printKlantGegevens(klant);
                        System.out.println("Nieuwe klantgegevens:");                        
                        klantView.printKlantGegevens(gewijzigdeKlant);          
                        break;
                    case 3: // direct door naar einde switch: methode naar inlogschermklant()
                        break;
                    default:
                        break;
                }
            default:
                break;    
        }
       
        klantMenu();
                          
    } // einde methode wijzigKlantGegevens
   
    
    public void verwijderKlantGegevens() {
       
        boolean deleted = false;
        int klantId = 0;
        int x = 0;
        
        int userInput = klantView.menuKlantVerwijderen();
        
        switch (userInput) {
            case 1: // één klnat verwijderen
                x = klantView.isKlantIdBekend();
                    // klantId bekend
                    if (x == 1){
                        klantId = klantView.voerKlantIdIn();
                        klant = klantDAO.findByKlantId(klantId);                    
                    }
                    // klantId niet bekend
                    else {
                        String achterNaam = klantView.voerAchterNaamIn();
                        String voorNaam = klantView.voerVoorNaamIn();
                        klantenLijst = klantDAO.findByVoorNaamAchterNaam(achterNaam, voorNaam);
                        klantView.printKlantenLijst(klantenLijst); 
                        klantId = klantView.voerKlantIdIn();                    
                    }
                    
                deleted = klantDAO.deleteByKlantId(klantId);  
                int verwijderd = klantAdresDAO.deleteKlantAdresByKlantId(klantId);
                klantView.printDeleteResultaat(deleted, klantId, verwijderd, klant);
                    
                break;                    
                                    
            case 2: // alle klanten verwijderen
                x = klantView.bevestigingsVraag();
                // bevestiging is ja
                if (x == 1){
                    int rowsAffected = klantDAO.deleteAll();
                    klantAdresDAO.deleteAll();
                    klantView.printInt(rowsAffected);
                    klantView.printString(" totaal aantal klanten zijn verwijderd");                    
                    klantView.printString("alle koppelingen van klant en adres zijn verwijderd");
                }
                // bevestiging = nee
                else {
                    klantView.printString("De klantgegevens worden niet verwijderd.");
                }
                break;                
            
            case 3: // terug naar klantenmenu - dmv break direct naar inlogschermklant()
                break;
            default:
                break;
        }
        klantMenu();
    }// eind methode verwijderKlantGegevens
    
    
    public void terugNaarHoofdMenu() {
        HoofdMenuController hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }    
    
    //----------------- waar moeten onderstaande methoden komen?
    public Klant createKlant(){
        
        //int klantId = 0;   
        String achternaam = klantView.voerAchterNaamIn();
        String voornaam = klantView.voerVoorNaamIn();
        String tussenvoegsel = klantView.voerTussenVoegselIn();
        String email = klantView.voerEmailIn();        
                  
        //klantBuilder.klantId(klantId); pas duidelijk na invoer in database
        klantBuilder.achternaam(achternaam);
        klantBuilder.voornaam(voornaam);
        klantBuilder.tussenvoegsel(tussenvoegsel);
        klantBuilder.email(email);

        // build klant
        klant = klantBuilder.build();

        return klant;        
    } // eind methode createKlant
 

    public Klant voerWijzigingenKlantIn(Klant klant){
        int juist = 0 ;
        
	String voornaam = klant.getVoornaam();
        System.out.println("Uw voornaam: ");
	juist = klantView.checkInputString(voornaam); // iets dergelijks als "is dit juist?: "+ voormaam 1/true 2/false
            if (juist == 2) { 
                voornaam = klantView.voerVoorNaamIn();
            }
                
	String achternaam = klant.getAchternaam();
	juist = klantView.checkInputString(achternaam);  // code schrijven voor methode iets als hierboven
            if (juist == 2) {
                achternaam = klantView.voerAchterNaamIn();
            }
                
	String tussenvoegsel = klant.getTussenvoegsel();
	juist = klantView.checkInputString(tussenvoegsel); // zie hierboven
            if(juist == 2) {
                tussenvoegsel = klantView.voerTussenVoegselIn(); 
            }
                
	String email = klant.getEmail();
	juist = klantView.checkInputString(email);
            if (juist == 2){ 
                email = klantView.voerEmailIn();
            }  
        
	klantBuilder.klantId(klant.getKlantId());
        klantBuilder.voornaam(voornaam);
        klantBuilder.achternaam(achternaam);
        klantBuilder.tussenvoegsel(tussenvoegsel);
        klantBuilder.email(email);

        // build Klant
        klant = klantBuilder.build();  
	return klant;  
    } // eind methode voerWijzigingenKlantIn

   
}  // end class KlantController

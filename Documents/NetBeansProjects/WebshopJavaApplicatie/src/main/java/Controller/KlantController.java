
package Controller;

import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import Factory.DaoFactory;
import POJO.Adres;
import POJO.Adres.AdresBuilder;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import View.AdresView;
import View.HoofdMenuView;
import View.KlantView;
import java.util.ArrayList;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author Anne
 */

public class KlantController {
    
    //data fields
    DaoFactory daoFactory = new DaoFactory();
    KlantDAOInterface klantDAO = DaoFactory.getKlantDao();
    KlantView klantView = new KlantView();    
    KlantBuilder klantBuilder = new KlantBuilder();
    Klant klant;
    ArrayList<Klant> klantenLijst;
    
    AdresDAOInterface adresDAO = DaoFactory.getAdresDao();
    AdresView adresView = new AdresView();
    AdresController adresController;
    AdresBuilder adresBuilder = new AdresBuilder();
    Adres adres; 
           
    KlantAdresDAOInterface klantAdresDAO = DaoFactory.getKlantAdresDao();
    
    HoofdMenuController hoofdMenuController;
    HoofdMenuView hoofdMenuView;
    
    EmailValidator validator = EmailValidator.getInstance(); 
    boolean isAddressValid = false;
    
    
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
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break;            
        }        
        
    }
    
    
    public int voegNieuweKlantToe() {
        
        adresController = new AdresController();
        
        System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
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
                            String voorNaam = klantView.voerVoorNaamIn();
                            String achterNaam = klantView.voerAchterNaamIn();
                            klantenLijst = klantDAO.findByVoorNaamAchterNaam(voorNaam, achterNaam);
                            if (klantenLijst != null)
                                klantView.printKlantenLijst(klantenLijst);   
                            else { 
                                String naam = voorNaam + " " + achterNaam;
                                klantView.printGeenKlanten(naam);
                            }                            
                            break;
                        case 2: //zoeken op email
                            String email = klantView.voerEmailIn();                             
                            isAddressValid = validator.isValid(email);
                            while (isAddressValid == false) {
                                System.out.println
                                    ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                                email = klantView.voerEmailIn();
                                validator = EmailValidator.getInstance();
                                isAddressValid = validator.isValid(email);
                            }
                                                       
                            klantenLijst = klantDAO.findByEmail(email);
                            if (klantenLijst != null)
                                klantView.printKlantenLijst(klantenLijst);   
                            else 
                                klantView.printGeenKlanten(email);                                                  
                            break;
                        case 3: // zoek met adresId
                            int adresId = adresView.voerAdresIdIn();
                            ArrayList<Klant>klantenLijst = klantAdresDAO.findKlantByAdresId(adresId);
                            break;
                        case 4: // direct door naar einde switch: methode naar inlogschermklant()
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
                if (klantenLijst != null){
                    System.out.println("Alle klanten in het bestand");
                    klantView.printKlantenLijst(klantenLijst);   
                }
                else { 
                    String naam = "alle klanten";
                    klantView.printGeenKlanten(naam);
                }  
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
                System.out.println("Oude klantgegevens:");
                klantView.printKlantGegevens(klant);
                System.out.println("Nieuwe klantgegevens:");                
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
                        System.out.println("Oude klantgegevens:");
                        klantView.printKlantGegevens(klant);
                        System.out.println("Nieuwe klantgegevens:");                        
                        klantView.printKlantGegevens(gewijzigdeKlant); 
                        break;
                    case 2: // wijzigen op basis van email                        
                        String email = klantView.voerEmailIn();                             
                        isAddressValid = validator.isValid(email);
                            while (isAddressValid == false) {
                                System.out.println
                                    ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                                email = klantView.voerEmailIn();
                                validator = EmailValidator.getInstance();
                                isAddressValid = validator.isValid(email);
                            }
                        klantenLijst = klantDAO.findByEmail(email);
                        klantView.printKlantenLijst(klantenLijst); 
                        klantId = klantView.voerKlantIdIn();
                        klant = klantDAO.findByKlantId(klantId);
                        
                        gewijzigdeKlant = voerWijzigingenKlantIn(klant);                         
                        klantDAO.updateGegevens(gewijzigdeKlant);                       
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
                    System.out.println(rowsAffected);
                    System.out.println(" totaal aantal klanten zijn verwijderd");                    
                    System.out.println("alle koppelingen van klant en adres zijn verwijderd");
                }
                // bevestiging = nee
                else {
                    System.out.println("De klantgegevens worden niet verwijderd.");
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
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }    
    
    //----------------- waar moeten onderstaande methoden komen?
    public Klant createKlant(){
        
        //int klantId = 0;   
        String achternaam = klantView.voerAchterNaamIn();
        String voornaam = klantView.voerVoorNaamIn();
        String tussenvoegsel = klantView.voerTussenVoegselIn();
        String email = klantView.voerEmailIn();                             
        isAddressValid = validator.isValid(email);
            while (isAddressValid == false) {
                System.out.println
                    ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                email = klantView.voerEmailIn();
                validator = EmailValidator.getInstance();
                isAddressValid = validator.isValid(email);
            }                       
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
        System.out.println("Achternaam: ");
	juist = klantView.checkInputString(achternaam);  // code schrijven voor methode iets als hierboven
            if (juist == 2) {
                achternaam = klantView.voerAchterNaamIn();
            }
                
	String tussenvoegsel = klant.getTussenvoegsel();
        System.out.println("Tussenvoegsel(s):");
	juist = klantView.checkInputString(tussenvoegsel); // zie hierboven
            if(juist == 2) {
                tussenvoegsel = klantView.voerTussenVoegselIn(); 
            }
                
	String email = klant.getEmail();
        System.out.println("emailadres:");
	juist = klantView.checkInputString(email);
            if (juist == 2){ 
                email = klantView.voerEmailIn();                             
                isAddressValid = validator.isValid(email);
                    while (isAddressValid == false) {
                        System.out.println
                            ("Ongeldig emailadres. Vul opnieuw uw emailadress in (bijv. hallo@hallo.com)");
                        email = klantView.voerEmailIn();
                        validator = EmailValidator.getInstance();
                        isAddressValid = validator.isValid(email);
                    }
            }  
        Klant klant2 = new Klant(klantBuilder);
	klantBuilder.klantId(klant.getKlantId());
        klantBuilder.voornaam(voornaam);
        klantBuilder.achternaam(achternaam);
        klantBuilder.tussenvoegsel(tussenvoegsel);
        klantBuilder.email(email);

        // build Klant
        
        klant2 = klantBuilder.build();  
	return klant2;  
    } // eind methode voerWijzigingenKlantIn

   
}  // end class KlantController

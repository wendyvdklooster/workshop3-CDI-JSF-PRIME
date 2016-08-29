/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;




import View.HoofdMenuView;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 *
 * @author Excen
 */
public class HoofdMenuController {
   // data fields 
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
   KlantController klantController; 
   ArtikelController artikelController;
   BestellingController bestellingController;
   AdresController adresController;
   
   HoofdMenuView hoofdMenuView = new HoofdMenuView(); 
      
   Scanner scanner = new Scanner(System.in);
   int userInput;
   
   

    // beginpunt
    public void start()  {  
        klantController = new KlantController();
        adresController = new AdresController();
        //bestellingController = new BestellingController();
        artikelController = new ArtikelController();
        
        userInput = hoofdMenuView.hoofdMenu();
        
            switch (userInput) {
                case 1: // ga naar klantMenu
                    klantController.klantMenu();
                    break;
                case 2: // ga naar artikelMenu
                    artikelController.artikelMenu();                    
                    break;
                case 3: // ga naar bestellingmenu
                    bestellingController.bestellingMenu();                                 
                    break;
                case 4:// ga naar adresmenu
                    adresController.adresMenu();                
                    break;
                case 5: // switch van db/connectionpool
                    
                    break;
                case 6: 
                    afsluiten();
                    break;
                default:
                    break;
            }
        } 
    
   
    
    public void afsluiten(){
       userInput = hoofdMenuView.bevestigingsVraag();
       
       if (userInput == 1){ // wel afsluiten
           System.exit(0);    
       }
       else if (userInput == 2){ // niet afsluiten
           userInput = hoofdMenuView.hoofdMenu();
       }           
        
    }
}

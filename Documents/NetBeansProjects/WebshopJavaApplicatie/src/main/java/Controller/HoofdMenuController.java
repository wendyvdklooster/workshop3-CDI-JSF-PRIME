/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.HoofdMenuView;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;





/**
 *
 * @author Excen
 */
public class HoofdMenuController implements ControllerInterface {
   // data fields 
   KlantController klantController = new KlantController(); 
   ArtikelController artikelController = new ArtikelController();
   BestellingController bestellingController = new BestellingController();
   AdresController adresController = new AdresController();
   
   HoofdMenuView hoofdMenuView = new HoofdMenuView();
    
   Scanner scanner = new Scanner(System.in);
   int userInput;
    
    // beginpunt
    public void start()  {
       
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
                case 5: 
                    afsluiten();
                    break;
                default:
                    break;
            }
        } 
    
    public void afsluiten(){
       userInput = hoofdMenuView.bevestigingsVraag();
       
       if (userInput == 1){ // wel afsluiten
           // 
           
       }
       else if (userInput == 2){ // niet afsluiten
           userInput = hoofdMenuView.hoofdMenu();
       }           
        
    }
    
 
}

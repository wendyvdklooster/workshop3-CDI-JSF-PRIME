/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOs.Impl.AdresDAOSQL;
import DAOs.Impl.KlantAdresDAOSQL;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import DAOs.Interface.ArtikelDAOInterface;
import DAOs.Interface.BestellingArtikelDAOInterface;
import DAOs.Interface.BestellingDAOInterface;
import DAOs.Interface.KlantDAOInterface;
import Factory.DaoFactory;
import POJO.Adres;
import View.AdresView;
import View.HoofdMenuView;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;





/**
 *
 * @author Excen
 */
public class HoofdMenuController {
   // data fields 
   KlantController klantController; 
   ArtikelController artikelController;
   BestellingController bestellingController;
   AdresController adresController;
   
   HoofdMenuView hoofdMenuView = new HoofdMenuView(); 
   DaoFactory daoFactory = new DaoFactory();
    
   Scanner scanner = new Scanner(System.in);
   int userInput;
   
   public void setDatabase() {
       
       userInput = hoofdMenuView.databaseKeuze();
       if (userInput == 1) {
           daoFactory.setDatabaseSetting("MySQL");
           start();
       }
       else if (userInput == 2) {
           daoFactory.setDatabaseSetting("FireBird");
           start();
       }
       else if (userInput == 3) {
           daoFactory.setDatabaseSetting("JSON");
           start();
       }
       else if (userInput == 4) {
           daoFactory.setDatabaseSetting("XML");
           start();
       }
       else
           daoFactory.setDatabaseSetting("MySQL");
           start();
   }
    

    // beginpunt
    public void start()  {  
        klantController = new KlantController();
        adresController = new AdresController();
        bestellingController = new BestellingController();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import Factory.DaoFactory;
import View.HoofdMenuView;
import java.util.Scanner;
import Factory.ConnectionFactory;
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
   DaoFactory daoFactory = new DaoFactory();
   ConnectionFactory connectionFactory = new ConnectionFactory();
    
   Scanner scanner = new Scanner(System.in);
   int userInput;
   
   
   public void setConnectionPool() {
       userInput = hoofdMenuView.connectionPoolKeuze();
        if (userInput == 1) {
           connectionFactory.setConnectionPool("Hikari");
           
           testLogger.debug("connectionpool is set to Hikari");
           logger.debug("connectionpool: Hikari");
           
           setDatabase();
        }
        else if (userInput == 2) {
           connectionFactory.setConnectionPool("C3p0");
           
           testLogger.debug("connectionpool is set to C3p0");
           logger.debug("connectionpool: C3p0");
           
           setDatabase();
        }
        else {
           connectionFactory.setConnectionPool("Hikari");
           
           testLogger.info("connectionpool is set to default");
           logger.debug("connectionpool: default");
           
           setDatabase();
       }
   }
   
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
                case 5: // switch van db/connectionpool
                    logger.debug("u zit nu in database " + daoFactory.getDatabaseSetting());
                    setDatabase();
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

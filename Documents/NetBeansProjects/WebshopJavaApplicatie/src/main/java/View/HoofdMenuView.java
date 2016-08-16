/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAOs.Impl.Json_XML.KlantDAOXML;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Anne
 */
public class HoofdMenuView {
    private final static Logger LOGGER = LoggerFactory.getLogger(HoofdMenuView.class.getName());
    int userInput;
    Scanner scanner = new Scanner(System.in);
    boolean checker;
    
    
      public int connectionPoolKeuze() {
       System.out.println("Met welke connection pool wilt u werken?");
       System.out.println("1. Hikari.");
       System.out.println("2. C3p0.");
       
       try{
            userInput = Integer.parseInt(scanner.nextLine()); 
            
        }catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }  
     
    
    public int databaseKeuze() {
        System.out.println("In welke database wilt u werken?");
        System.out.println("1. MySQL.");
        System.out.println("2. FireBird");
        System.out.println("3. JSON (alleen voor het klant bestand).");
        System.out.println("4. XML (alleen voor het klant bestand).");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine()); 
            
        }catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    // Eerste instantie van wat de console te zien krijgt. Keuzes in dit menu bepalen welke andere controllers
    // (en dus views) aangesproken gaan worden.
        
            
    public int hoofdMenu(){
        
        System.out.println("Wat wilt u doen? ");
        System.out.println("1. Werken in klantbestand");
        System.out.println("2. Werken in artikelbestand");
        System.out.println("3. Werken in bestellingbestand");
        System.out.println("4. Werken in adresbestand");
        System.out.println("5. Wisselen van Database");
        System.out.println("6. Programma afsluiten");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine()); 
            
        }catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    
    public int bevestigingsVraag(){
        
        boolean doorgaan = true; 
        
        System.out.println("Weet u zeker dat u af wilt sluiten?");
        System.out.println("1. ja");
        System.out.println("2. nee");       
        
        do{              
            try{
                userInput = Integer.parseInt(scanner.nextLine());  
                //userInput = scanner.nextInt();
                if (userInput == 1 || userInput == 2){
                    if (userInput == 1){
                        System.out.println("Het programma zal sluiten");
                    }
                    else
                        System.out.println("U gaat terug naar het hoofdmenu");
                    
                    doorgaan = false;
                }
                else {   
                    System.out.println("Foute input, kies van de opties hierboven.");
                    System.out.println();
                    scanner.nextLine();
                }                
            }
            catch(InputMismatchException ex){
                LOGGER.warn("Foute input, kies van de opties hierboven.");
                System.out.println();
                scanner.nextLine();
            }
        
        } while(doorgaan == true);
        
       return userInput;        
    }
    
}// eind  hoofdmenucontroller klasse   
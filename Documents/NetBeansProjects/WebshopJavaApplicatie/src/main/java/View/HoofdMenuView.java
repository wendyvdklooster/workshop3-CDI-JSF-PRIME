/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Anne
 */
public class HoofdMenuView {
    int userInput;
    Scanner scanner = new Scanner(System.in);
    boolean checker;
    
    // Eerste instantie van wat de console te zien krijgt. Keuzes in dit menu bepalen welke andere controllers
    // (en dus views) aangesproken gaan worden.
        
            
    public int hoofdMenu(){
        
        System.out.println("Wat wilt u doen? ");
        System.out.println("1. Werken in klantbestand");
        System.out.println("2. Werken in artikelbestand");
        System.out.println("3. Werken in bestellingbestand");
        System.out.println("4. Werken in adresbestand");
        System.out.println("5. Programma afsluiten");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine()); 
            
        }catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
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
                System.out.println("Foute input, kies van de opties hierboven.");
                System.out.println();
                scanner.nextLine();
            }
        
        } while(doorgaan == true);
        
       return userInput;        
    }
    
}// eind  hoofdmenucontroller klasse   



package View;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class AccountView {

private static final Logger log = LoggerFactory.getLogger(AccountView.class);
Scanner scanner = new Scanner(System.in);


    public int startMenuAccount() {
         int userInput = 0; 
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuw Account toevoegen.");        
        System.out.println("2. Accountgegevens opzoeken.");
        System.out.println("3. Accountgegevens wijzigen.");
        System.out.println("4. Accountgegevens verwijderen.");        
        System.out.println("5. Terug naar hoofdmenu");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;      
    }
    

   
    public String voerGebruikersnaamIn() {
        
        String gebruikersnaam;  
        System.out.println("Kies een geruikersnaam: ");
        gebruikersnaam = scanner.nextLine();
        return gebruikersnaam;
    }

    public String voerPasswordIn() {
    
           String password;
           System.out.println("kies een password: ");
           password = scanner.nextLine();
           return password; 
           
    }


}

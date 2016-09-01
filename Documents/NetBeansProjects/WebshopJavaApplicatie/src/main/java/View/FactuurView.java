


package View;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class FactuurView {
    
    

private static final Logger log = LoggerFactory.getLogger(FactuurView.class);

Scanner scanner = new Scanner(System.in);

   

    public int startMenuFactuur() {
        
        int userInput = 0; 
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuwe factuur toevoegen.");        
        System.out.println("2. Factuurgegevens opzoeken.");
        System.out.println("3. Factuurgegevens wijzigen.");
        System.out.println("4. Factuurgegevens verwijderen.");        
        System.out.println("5. Terug naar hoofdmenu");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;        
    
    }

 public String voerFactuurNummerIn() {
        String factuurnummer; 
        System.out.println("Wat is het factuurnummmer: ");
        factuurnummer = scanner.nextLine();
        return factuurnummer;
    }
      


}

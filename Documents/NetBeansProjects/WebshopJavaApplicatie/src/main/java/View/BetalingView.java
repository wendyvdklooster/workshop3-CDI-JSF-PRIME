


package View;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BetalingView {

private static final Logger log = LoggerFactory.getLogger(BetalingView.class);
Scanner scanner = new Scanner (System.in);


    public int startMenuBetaling() {
          int userInput = 0; 
        
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuwe Betaling toevoegen.");        
        System.out.println("2. Betalingsgegevens opzoeken.");
        System.out.println("3. Betalingsgegevens wijzigen.");
        System.out.println("4. Betalingsgegevens verwijderen.");        
        System.out.println("5. Terug naar hoofdmenu");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            log.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;      }


}

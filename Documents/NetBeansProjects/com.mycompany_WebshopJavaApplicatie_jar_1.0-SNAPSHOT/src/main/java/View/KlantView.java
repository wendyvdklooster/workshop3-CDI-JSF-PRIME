
package View;


import POJO.Klant;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anne
 */
public class KlantView {
    private final static Logger LOGGER = LoggerFactory.getLogger(KlantView.class.getName());
    Scanner scanner = new Scanner(System.in);    
    int userInput;
         
    
    public int startMenuKlant(){
        System.out.println();        
        System.out.println("Maak uw keuze:");
        System.out.println("1. Nieuwe klant toevoegen.");
        System.out.println("2. Klantgegevens opzoeken.");
        System.out.println("3. Klantgegevens wijzigen.");
        System.out.println("4. Klantgegevens verwijderen.");
        System.out.println("5. Terug naar het hoofdmenu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
            
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;        
    }
    
    public String voerAchterNaamIn(){
               
        System.out.print("Achternaam: ");
        String achternaam = scanner.nextLine();
        
        return achternaam.trim(); 
    }
    
    
    public String voerVoorNaamIn(){
               
        System.out.print("Voornaam: ");
        String voornaam = scanner.nextLine();
        
        return voornaam.trim(); 
    }
    
    
    public String voerTussenVoegselIn(){
        
        System.out.print("Tussenvoegsel: ");
        String tussenvoegsel = scanner.nextLine();
        
        return tussenvoegsel.trim(); 
    }
    
    
    public String voerEmailIn(){
        System.out.println("Email: ");
        String email = scanner.nextLine();
        
        //isValidEmailAddress(email);
        // doe iets als result = false 
        
        return email.trim();
    }
    
    public int voerKlantIdIn(){
        
        System.out.println("Voer klantId in: ");
        int klantId = Integer.parseInt(scanner.nextLine()); 
        
        return klantId;
    }

    
    // loop werkt niet
    public int isKlantIdBekend(){        
              
        boolean doorgaan = false; // false by default        
        
        do{
            try{
                System.out.println("Is het klantId bekend?");
                System.out.println("1. ja");    
                System.out.println("2. nee");
                System.out.println("3. Terug naar klant menu");
                userInput = Integer.parseInt(scanner.nextLine());            

                
                if (userInput == 1 || userInput == 2 || userInput == 3){
                    doorgaan = true;
                } 

                if(doorgaan == false){             
                    System.out.println("Foutieve input, voer 1, 2, 3 in.");
                } 
                // foutafhandeling werkt niet
            }catch(InputMismatchException ex){
                LOGGER.warn("Error. Voer het  cijfer 1, 2, 3 in. ");
                scanner.nextLine();
            }
            
        } while(doorgaan == false);
       
        return userInput;         
    }
    
    
    public void printKlantGegevens(Klant klant){
        
        System.out.println("De huidige gegevens van de klant met klantId: " + klant.getKlantId());
        System.out.println("Naam: " + klant.getVoornaam() + " " + klant.getTussenvoegsel() + " "
                + klant.getAchternaam());
        System.out.println("Email: "+ klant.getEmail());
        System.out.println();
        
    }
    
    
    public int hoeWiltUZoeken(){
        
        System.out.println("Hoe wilt u zoeken?");
        System.out.println("1. Zoek met voor- en achternaam");
        System.out.println("2. Zoek met email");  
        System.out.println("3. Zoek met adresId");
        System.out.println("4. Terug naar klantmenu");
        System.out.println("Maak een keuze:");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());    
        //scanner.nextLine();

        //checker = false;
        
        } catch (InputMismatchException ex){
            LOGGER.warn("Foutieve input, kies uit de opties 1,2,3.");
            //scanner.nextLine();
        }
         
       // } while (checker);
        
        return userInput;
        
    }
    
    public int checkInputString(String string){
                
        System.out.println(string + ". Is dit juist? : " );
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        // make do while loop
        try{
            userInput = Integer.parseInt(scanner.nextLine());            


            if (userInput == 1 || userInput == 2){
                //scanner.nextLine();
            }
            else {
                System.out.println("Foutieve input, voer 1 of 2 in.");
            } 
        }
        catch(InputMismatchException ex){
            LOGGER.warn("", ex);
        }
        
        return userInput; 
        
    }
   
    public int menuKlantVerwijderen() {
        System.out.println("Maak uw keuze: ");
        System.out.println("1. Één klant verwijderen.");
        System.out.println("2. Alle klanten verwijderen.");
        System.out.println("3. Terug naar klantmenu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    public int menuKlantZoeken(){
        
        System.out.println("Wat wilt u opzoeken: ");
        System.out.println("1. Gegevens één klant.");
        System.out.println("2. Gegevens alle klanten.");
        System.out.println("3. Terug naar klantmenu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
        
    
   
    public int bevestigingsVraag(){
        
        System.out.println("Weet u zeker dat u alle klantgegevens definitief verwijderen wil?");
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());        
        }
        catch(InputMismatchException ex){
            LOGGER.warn("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
        
    }
    
    public void printKlantenLijst(ArrayList<Klant> lijst){
        System.out.println();
        System.out.println("Lijst met opgevraagde klanten");
        System.out.printf("%-10s%-18s%-15s%-25s%-15s%n","KlantId", "Voornaam", "Tussenvoegsel", "Achternaam", "Email");
        //System.out.println("KlantId\t\tVoornaam\t\tTussenvoegsel\t\tAchternaam\t\tEmail");
            for (int i = 0; i< lijst.size(); i++){
                System.out.printf("%-10s%-18s%-15s%-25s%-15s%n",
                        (lijst.get(i)).getKlantId(),(lijst.get(i)).getVoornaam(),
                        (lijst.get(i)).getTussenvoegsel(),(lijst.get(i)).getAchternaam(),
                        (lijst.get(i)).getEmail());            
        }        
    }
    
    public void printDeleteResultaat(boolean deleted, int klantId, int verwijderd, Klant klant) {
        
        if (deleted == true){
            
            System.out.println("De volgende klant is verwijderd uit het bestand: ");
            printKlantGegevens(klant);
            System.out.println(verwijderd + " koppeling(en) van klant met een adres zijn verwijderd"); 
            System.out.println();
        }
    }
    
    public void printNotDeleted(Klant klant){    
        
            System.out.println("De volgende klant is NIET verwijderd uit het bestand: ");
            printKlantGegevens(klant);
            System.out.println();
          
    }   
    
    
    public void printGeenKlanten(String string){
        
        System.out.println("Het klantenbestand bevat niet de gezochte gegevens: " + string);
    }
        
}

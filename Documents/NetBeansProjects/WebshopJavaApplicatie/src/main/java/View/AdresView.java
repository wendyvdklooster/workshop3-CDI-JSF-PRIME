/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import POJO.Adres;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Anne
 */
public class AdresView {
    
    int userInput;
    Scanner scanner = new Scanner(System.in);
    
    public int startAdresMenu() {
        System.out.println();
        System.out.println("Maak uw keuze: ");
        System.out.println("1. Nieuw adres toevoegen. ");
        System.out.println("2. Adresgegevens opzoeken.");
        System.out.println("3. Adresgegevens wijzigen.");
        System.out.println("4. Adresgegevens verwijderen.");
        System.out.println("5. Terug naar het hoofdmenu.");
        
        try {
             userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        return userInput;
        
    }
    
    public int bentUNieuweKlant() {
        System.out.println("Bent u een nieuwe klant?");
        System.out.println("1. ja.");
        System.out.println("2. nee");
        
        try {
             userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
     public int voerKlantIdIn() {
        System.out.println("Klant id: ");
        int klantId = Integer.parseInt(scanner.nextLine());
        return klantId;
    }
    
     public int voerAdresIdIn() {
        System.out.println("Adres id: ");
        int adresId = Integer.parseInt(scanner.nextLine());
        return adresId;
    }
    
    public String voerStraatnaamIn() {
        System.out.println("Straatnaam: ");
        String straatnaam = scanner.nextLine();
        return straatnaam;
    }
    
    public int voerHuisnummerIn() {
        System.out.println("huisnummer: ");
        int huisnummer = Integer.parseInt(scanner.nextLine());
        return huisnummer;
    }
    public String voerToevoegingIn() {
        String toevoeging;
        
        System.out.println("Huisnummer toevoeging: ");
        toevoeging = scanner.nextLine().trim(); 

        return toevoeging;
    }
    
    public String voerPostcodeIn() {
        System.out.println("Postcode: ");
        String postcode = scanner.nextLine();
        return postcode;
    }
    public String voerWoonplaatsIn() {
        System.out.println("Woonplaats: ");
        String woonplaats = scanner.nextLine();
        return woonplaats;
    }
    
    public void printAdresOverzicht(Adres adres) {
        System.out.println("Het adres heeft de volgende gegevens:");
        System.out.println("Adres id: " + adres.getAdresId());
        System.out.println("Straatnaam: " + adres.getStraatnaam());
        System.out.println("Huisnummer: " + adres.getHuisnummer());
        System.out.println("Toevoeging: " + adres.getToevoeging());
        System.out.println("Postcode: " + adres.getPostcode());
        System.out.println("Woonplaats: " + adres.getWoonplaats());
    }
    
    public int menuAdresZoeken(){
        
        System.out.println("Wat wilt u zoeken in het adressenbestand?");
        System.out.println("1. Één adres opzoeken.");
        System.out.println("2. Alle adressen opzoeken.");
        System.out.println("3. Terug naar adres menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            

        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    public int hoeWiltUZoeken() {
        System.out.println("Kies met wat u wilt zoeken.");
        System.out.println("1. Bestelling id.");
        System.out.println("2. Straatnaam.");
        System.out.println("3. Postcode en huisnummer");
        System.out.println("4. Woonplaats.");
        System.out.println("5. Terug naar adres menu.");
        
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
    public void printAdressenLijst(ArrayList<Adres> adressenLijst) {
        System.out.println();
        System.out.println("AdresId\tStraatnaam\t\tHuisnummer\tToevoeging\tPostcode\tWoonplaats");
            for (int i = 0; i< adressenLijst.size(); i++){

            System.out.println ((adressenLijst.get(i)).getAdresId() + "\t\t" +
            (adressenLijst.get(i)).getStraatnaam() + "\t\t" +(adressenLijst.get(i)).getHuisnummer() +
            "\t" + (adressenLijst.get(i)).getToevoeging() + "\t" + (adressenLijst.get(i)).getPostcode() +
            "\t" + (adressenLijst.get(i)).getWoonplaats());
            }        
    }
    
    public int checkInputString(String input) {
        System.out.println("Is het volgende gegeven juist: " + input + " ?");
        System.out.println("1. Ja.");
        System.out.println("2. Nee.");
        
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        return userInput;
    }
    
    public int printVerwijderAdresMenu() {
        System.out.println("Wat wilt u verwijderen uit het adressenbestand?");
        System.out.println("1. één adres.");
        System.out.println("2. alle adressen.");
        System.out.println("3. terug naar het adres menu.");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());            
        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
    }
    
    
    
    public int bevestigingsVraag(){
        
        System.out.println("Wilt u alle adressen definitief verwijderen?");
        System.out.println("1. ja");
        System.out.println("2. nee");
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());        
        }
        catch(InputMismatchException ex){
            System.out.print("Foute input, kies van de opties hierboven.");
        }
        
        return userInput;
        
    }
        
}

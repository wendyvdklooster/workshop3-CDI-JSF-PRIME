/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAOs.Impl.BestellingArtikelDAOImpl;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingArtikel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Excen
 */
public class BestellingView {

BestellingArtikelDAOImpl bestellingArtikelDAO = new BestellingArtikelDAOImpl();    
    
    // data fields
    Scanner scanner = new Scanner(System.in);
    int userInput;
    int artikelId;
    int artikelAantal;
    
    
    public int startBestellingMenu(){
        
        try{
            System.out.println("Maak een keuze:");
            System.out.println("1 bestelling plaatsen");
            System.out.println("2 bestellingsinformatie ophalen");
            System.out.println("3 bestelling wijzigen");
            System.out.println("4 bestelling verwijderen");
            System.out.println("5 toon alle bestellingen");
            System.out.println("6 verwijder alle bestellingen");
            System.out.println("7 terug naar hoofdmenu");       

            userInput = Integer.parseInt(scanner.nextLine());
            
        } catch (InputMismatchException ex){
            System.out.print("Foute input, kies één van de opties hierboven.");
        }    

    return userInput;
    }
    
    
    public int voerKlantIdIn (){
        
        System.out.println("U wilt een nieuwe bestelling aanmaken.");
        System.out.println("Wat is uw klant ID?");
        int klantID = Integer.parseInt(scanner.nextLine());
        return klantID;
    }

    
    public int zoekBestellingInfo(){
        
        System.out.println("Wat is het bestelling ID?");
        userInput = 0;
        
        try{
            userInput = Integer.parseInt(scanner.nextLine());         
        } catch (InputMismatchException ex){
            System.out.println("Vul een getal in.");
        }
        
        return userInput;
    }
    
    
    public int voerArtikelIdIn(){
        
        System.out.println("Voer een artikel ID in:");
        artikelId = Integer.parseInt(scanner.nextLine());
        
        return artikelId;
    }
    
    
    public int voerAantalIn(){
        
        System.out.println("Hoe vaak wil je dit artikel toevoegen?");
        artikelAantal = Integer.parseInt(scanner.nextLine());
        
        return artikelAantal;
    }
    
    
    public int wijzigBestellingInfo(ArrayList<Artikel>artikelLijst, int bestellingId){
        
        artikelId = 0;        
        
        System.out.println("Artikellen aanwezig in bestelling " + bestellingId);
        
        for (Artikel ar: artikelLijst){
            System.out.println(ar.getArtikelId() + " " + ar.getArtikelNaam() + ": " + bestellingArtikelDAO.findAantalByArtikelID(bestellingId, ar.getArtikelId()) + " keer");
        } 
        
        System.out.println("Welk artikel ID wil je wijzigen?");
        try{
            artikelId = Integer.parseInt(scanner.nextLine());  
        } catch (InputMismatchException ex){
            System.out.println("Voer een getal in.");
        }

        return artikelId;

    }
    
    public int wijzigBestellingKeuze (){
        
        System.out.println("Wat wil je doen met dit artikel?\n1. Verwijderen\n2. Aantal wijzigen");
        userInput = 0;
        
        try{
            userInput = Integer.parseInt(scanner.nextLine()); 
        } catch (InputMismatchException ex){
            System.out.println("Vul een getal in.");
        }

        return userInput;
        
    }
    
    public int wijzigAantal() {
        
        System.out.println("Wat moet het nieuwe aantal worden?");
        artikelAantal = 0;
        
        try{
        artikelAantal = Integer.parseInt(scanner.nextLine());
        } catch (InputMismatchException ex){
            System.out.println("Vul een getal in.");
        }

        return artikelAantal;
        
    }
    
    public void printBestellingLijst(ArrayList<Bestelling>lijst){       
        // TODO
        // ook koppelbestellingartikel aanspreken om aantallen bij te kunnen voegen
        
        System.out.println("Aanwezige bestellingen: ");
        for (Bestelling best: lijst){
            System.out.println("Bestelling ID: " + best.getBestellingId() + " - Klant ID: " + best.getKlantId());
        }
        
        
        
        
    }
}

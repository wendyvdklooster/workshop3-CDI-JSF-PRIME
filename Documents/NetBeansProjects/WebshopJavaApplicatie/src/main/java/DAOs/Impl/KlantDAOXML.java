/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

/**
 *
 * @author Wendy
 */
   
import DAOs.Interface.KlantDAOInterface;
import MAIN.KlantXMLdev;
import MAIN.KlantXMLdev.KlantBuilderXML;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class KlantDAOXML implements KlantDAOInterface {
    
    KlantBuilder klantBuilder = new KlantBuilder();
    Klant klantB = new Klant(klantBuilder);
    Klant klant = new Klant();
    ArrayList<Klant>klantenLijst;
    // decode: read 
    // encode: create, update, delete
        
    // setters to build object
    // getters - get information from xml file

    // genereer id
  
 @Override   
public Klant insertKlant (Klant klant) {          
            
        int klantId = 0;        
        
        String voornaam = klantB.getVoornaam();
        String achternaam = klantB.getAchternaam();        
        String tussenvoegsel = klantB.getTussenvoegsel();
        String email = klantB.getEmail();
            
        klantB = klantBuilder.build();
                       
        // try with resources
        try (
            FileOutputStream fos = new FileOutputStream("klant.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder xmlEncoder = new XMLEncoder(bos)
        ){
            xmlEncoder.writeObject(klantB);            
        
            // lees de invoer terug uit + klantId
            
        }catch(FileNotFoundException ex){
             ex.getMessage();
        }
        catch(IOException ex){
            ex.getMessage();
        }
        // return klant, incl klantId
        return klant;
}

@Override
    public ArrayList<Klant> findAllKlanten() {          
        // code voor decode
        klantenLijst = new ArrayList();
        
        try(
            FileInputStream fis = new FileInputStream("klant.xml");
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmlDecoder = new XMLDecoder(bis);
        ){        
            klant = (Klant) xmlDecoder.readObject();
            System.out.println(klant.getVoornaam() + " " + klant.getTussenvoegsel() 
                    + " " + klant.getAchternaam() + " " + klant.getEmail());
        }catch(FileNotFoundException ex){
            ex.getMessage();
        }
        catch(IOException ex){
            ex.getMessage();
        }
           
       // print arraylist< klant> klantenlijst uit
        return klantenLijst;
	}

    @Override
    public int deleteAll(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public Klant findByKlantId(int klantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Klant> findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Klant> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteByKlantId(int klantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Klant updateGegevens(Klant klant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 //    KlantBuilderXML klantBuilderXML = new KlantBuilderXML();
//    KlantXMLdev klantXML = new KlantXMLdev(klantBuilderXML);   
//    KlantXMLdev klant = new KlantXMLdev(); 
//            Scanner input = new java.util.Scanner(System.in);
//            System.out.println("voornaam: ");
//            String voornaam = input.nextLine();
//            System.out.println("tussenvoegsle: ");
//            String tussenvoegsel = input.nextLine();
//            System.out.println("acheternaam: ");
//            String achternaam = input.nextLine();
//            System.out.println("email: ");
//            String email = input.nextLine();
//            
//            KlantXML klant2 = new KlantXMLdev(voornaam, achternaam, tussenvoegsel, email);
//            klant.setVoornaam(voornaam);
//            klant.setTussenvoegsel(tussenvoegsel);
//            klant.setAchternaam(achternaam);
//            klant.setEmail(email);            
//            
//            klantBuilderXML.voornaam(voornaam);
//            klantBuilderXML.tussenvoegsel(tussenvoegsel);
//            klantBuilderXML.achternaam(achternaam);
//            klantBuilderXML.email(email);
    
    
}



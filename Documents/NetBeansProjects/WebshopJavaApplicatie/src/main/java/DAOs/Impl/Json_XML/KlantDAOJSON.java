
package DAOs.Impl.Json_XML;

import DAOs.Interface.KlantDAOInterface;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wendy
 */
public class KlantDAOJSON implements KlantDAOInterface {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KlantDAOJSON.class.getName());
    //String fileName = "C:\\Users\\Anne\\Documents\\test4";
    String fileName = "C:\\Users\\Anne\\Documents\\test2";
    
    @Override
    public ArrayList<Klant> findAllKlanten() {
        
    ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
           
            try {
                obj = parser.parse(reader);
                } catch (org.json.simple.parser.ParseException ex) {
                    Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
               } //catch (NullPointerException ex){
//                    ex.printStackTrace();
//                    obj = null;                    
//                } catch (JSONException ex){
//                    ex.printStackTrace();
//                }
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
          }
         
 
            JSONObject KlantDatabase = (JSONObject)obj;
            if (KlantDatabase != null) {
            // de gegevens van de klanten ophalen en in jsonArray zetten
                try {
                JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
                for (int i = 0; i < klantenIn.size(); i++) {
                    JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                    long klantId = (long)(klantIn.get("klant_id"));
                    String voornaam = (String)(klantIn.get("voornaam"));
                    String achternaam = (String)(klantIn.get("achternaam"));
                    String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                    String email = (String)(klantIn.get("email"));

                    // maak klantbuilder aan en maak klant van gegevens.
                    KlantBuilder klantBuilder = new KlantBuilder();
                    klantBuilder.klantId((int)(klantId));
                    klantBuilder.voornaam(voornaam);
                    klantBuilder.achternaam(achternaam);
                    klantBuilder.tussenvoegsel(tussenvoegsel);
                    klantBuilder.email(email);

                    Klant klant = new Klant();
                    klant = klantBuilder.build();
                    klantenLijst.add(klant);
                }
            
            } catch(Exception ex){
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            klantenLijst = null;
            System.out.println("Er zijn nog geen klanten.");
        
        return klantenLijst;
    /**
    JSONParser parser = new JSONParser();
    
    try {
        File file = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNext()) {
            JSONObject klantObject;
            try {
                klantObject = (JSONObject)(parser.parse(scanner.nextLine()));
                klantenLijst.add(klantObject);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                    Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    catch (FileNotFoundException ex)  {
        System.out.println(ex.toString());
    }
        return klantenLijst;
        */
    }

    @Override
    public Klant findByKlantId(long klantId) {
        
   ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                
                // als klant id overeenkomt met gezochte, dan klant bouuwen.
                if (klantIdInput == klantId) {
                    String voornaam = (String)(klantIn.get("voornaam"));
                    String achternaam = (String)(klantIn.get("achternaam"));
                    String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                    String email = (String)(klantIn.get("email"));
                   
                    // maak klantbuilder aan en maak klant van gegevens.
                    KlantBuilder klantBuilder = new KlantBuilder();
                    klantBuilder.klantId((int)(klantId));
                    klantBuilder.voornaam(voornaam);
                    klantBuilder.achternaam(achternaam);
                    klantBuilder.tussenvoegsel(tussenvoegsel);
                    klantBuilder.email(email);
                
                    klant = klantBuilder.build();
                }
                else {
                    //System.out.println("Klant kan niet gevonden worden in de database.");
                }
            }
        return klant;
    }
    
    

    @Override
    public ArrayList<Klant> findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {
        
        ArrayList<Klant> klantenLijst = new ArrayList(); 
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de naam en achternaam van de klant ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                
                // als overeenkomt met gezochte namen dan klant ophalen
                    voornaam = (String)(klantIn.get("voornaam"));
                    achternaam = (String)(klantIn.get("achternaam"));
                    int klantId = (int)((long)(klantIn.get("klant_id")));
                    String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                    String email = (String)(klantIn.get("email"));
                   
                    // maak klantbuilder aan en maak klant van gegevens.
                    KlantBuilder klantBuilder = new KlantBuilder();
                    klantBuilder.klantId(klantId);
                    klantBuilder.voornaam(voornaam);
                    klantBuilder.achternaam(achternaam);
                    klantBuilder.tussenvoegsel(tussenvoegsel);
                    klantBuilder.email(email);
                    klant = klantBuilder.build();
                    
                    if (klant.getVoornaam().equals(voorNaam) && klant.getAchternaam().equals(achterNaam)) {
                      
                        klantenLijst.add(klant);
                    }
                else {
                    // manier bedenken om af te vangen als leeg is?  
                    //System.out.println("Klant kan niet gevonden worden in de database.");
                }
              
            }
            /**catch (IOException ex) {
                 System.out.println(ex.toString());       
            }
        */
    
            return klantenLijst;
    }   
    

    @Override
    public ArrayList<Klant> findByEmail(String email) {
       
        ArrayList<Klant> klantenLijst = new ArrayList();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de naam en achternaam van de klant ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantId = (int)((long)(klantIn.get("klant_id")));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                String emailInput = (String)(klantIn.get("email"));
                   
                // maak klantbuilder aan en maak klant van gegevens.
                KlantBuilder klantBuilder = new KlantBuilder();
                klantBuilder.klantId(klantId);
                klantBuilder.voornaam(voornaam);
                klantBuilder.achternaam(achternaam);
                klantBuilder.tussenvoegsel(tussenvoegsel);
                klantBuilder.email(email);
                klant = klantBuilder.build();
                    
                if (klant.getEmail().equals(emailInput)) {
                    klantenLijst.add(klant);
                }
                else {
                    // hier ook; manier zoeken om af te vangen als hij niets kan vinden
                   // System.out.println("Klant kan niet gevonden worden in de database.");
                }
        }
        return klantenLijst;
                
    }

    @Override
    public Klant insertKlant(Klant klant) {
        
        // eerst alle klanten ophalen in een lijst
        ArrayList<Klant> klantenLijst = new  ArrayList();
        Object obj = new Object();
        long nieuweKlantId = 0;
        File file = new File(fileName);
        if (file.exists()) {
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
            
            JSONObject KlantDatabase = (JSONObject)(obj);
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
        if (klantenIn.isEmpty()) {
            nieuweKlantId = 1;
        }
        else {
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                long klantId = (long)(klantIn.get("klant_id"));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                String email = (String)(klantIn.get("email"));
                
                // maak klantbuilder aan en maak klant van gegevens.
                KlantBuilder klantBuilder = new KlantBuilder();
                klantBuilder.klantId((int)(klantId));
                klantBuilder.voornaam(voornaam);
                klantBuilder.achternaam(achternaam);
                klantBuilder.tussenvoegsel(tussenvoegsel);
                klantBuilder.email(email);
                
                Klant klantInput = new Klant();
                klantInput = klantBuilder.build();
                klantenLijst.add(klantInput);
            }
           
        // check wat laatste klantId is om volgende te maken
        Klant laatsteKlant = klantenLijst.get(klantenLijst.size()-1);
        long laatsteKlantId = laatsteKlant.getKlantId();
        nieuweKlantId = ++laatsteKlantId;
        }
        }
        else {
            System.out.println("Bestand kan niet gevonden worden. Een nieuw bestand wordt aangemaakt.");
            nieuweKlantId = 1; 
        }
        Klant nieuweKlant = new Klant();
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail(); 
        
        // create klant en zet het in de arrayList
        KlantBuilder klantBuilder = new KlantBuilder();
        klantBuilder.klantId(nieuweKlantId);
        klantBuilder.voornaam(voornaam);
        klantBuilder.achternaam(achternaam);
        klantBuilder.tussenvoegsel(tussenvoegsel);
        klantBuilder.email(email);
        
        nieuweKlant = klantBuilder.build();
        klantenLijst.add(nieuweKlant);
        
        // loop door de arraylist om alle klanten in jsonformat te zetten
        JSONArray klantenOutput = new JSONArray();
        for (int i = 0; i <klantenLijst.size(); i++) {
            
            Klant klantOutput = klantenLijst.get(i);
            JSONObject klantObject = new JSONObject();
            klantObject.put("klant_id", klantOutput.getKlantId());
            klantObject.put("voornaam", klantOutput.getVoornaam());
            klantObject.put("achternaam", klantOutput.getAchternaam());
            klantObject.put("tussenvoegsel", klantOutput.getTussenvoegsel());
            klantObject.put("email", klantOutput.getEmail());
            
            klantenOutput.add(klantObject);
        }    
        JSONObject nieuweKlantDatabase = new JSONObject();
        nieuweKlantDatabase.put("klanten", klantenOutput);
        
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File(fileName);
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(nieuweKlantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
               
        return klant;
    }
    

    @Override
    public boolean deleteByKlantId(long klantId) {
        
        boolean isDeleted = false;
        ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject klantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(klantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                
                // als klant id overeenkomt met gezochte, dan klant verwijderen.
                if (klantIdInput == klantId) {
                    
                    klantIn.remove("klant_id");
                    klantIn.remove("voornaam");
                    klantIn.remove("achternaam");
                    klantIn.remove("tussenvoegsel");
                    klantIn.remove("email");
                    klantenIn.remove(klantIn);
                    
                    isDeleted = true;
                }
            }
        klantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File(fileName);
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(klantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
       return isDeleted;
    }
    

    
    public boolean deleteByKlantNaam(String achternaam, String tussenvoegsel, String voornaam) {
        
        boolean isDeleted = false;
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject klantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        JSONArray klantenIn = (JSONArray)(klantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                String klantVoornaam = (String)(klantIn.get("voornaam"));
                String klantAchternaam = (String)(klantIn.get("achternaam"));
                String klantTussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                
                
                // als klant id overeenkomt met gezochte, dan klant verwijderen.
                if (klantVoornaam.equals(voornaam) && klantTussenvoegsel.equals(tussenvoegsel) 
                        && klantAchternaam.equals(achternaam)) {
                    
                    klantIn.remove("klant_id");
                    klantIn.remove("voornaam");
                    klantIn.remove("achternaam");
                    klantIn.remove("tussenvoegsel");
                    klantIn.remove("email");
                    klantenIn.remove(klantIn);
                    
                    isDeleted = true;
                }
            }
        klantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File(fileName);
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(klantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
       return isDeleted;
    }

    
    @Override
    public int deleteAll() {
        
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        int count = 0;
        JSONObject klantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        JSONArray klantenIn = (JSONArray)(klantDatabase.get("klanten"));
        for (int i = 0; i < klantenIn.size(); i++) {
            count++;
        }
        klantenIn.removeAll(klantenIn);
              
        klantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File(fileName);
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(klantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
       return count;
        
    }

    
    @Override
    public Klant updateGegevens(Klant klant) {
        // lijst ophalen
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        JSONObject KlantDatabase = (JSONObject)obj;
        
        //get details van update klant
        long klantId = klant.getKlantId();
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail();   
        
        
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                
                // als klant id overeenkomt met gezochte, dan klant updaten.
                if (klantIdInput == klantId) {
                    klantIn.put("voornaam", voornaam);
                    klantIn.put("achternaam", achternaam);
                    klantIn.put("tusssenvoegsel", tussenvoegsel);
                    klantIn.put("email", email);
                }
            } 
        
        // schrijf gewijzigde lijst weer in json bestand
        KlantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File(fileName);
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(KlantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        
         return klant;  
        
    }

    
}

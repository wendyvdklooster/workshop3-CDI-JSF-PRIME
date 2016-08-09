
package DAOs.Impl.Json_XML;
/**
 *
 * @author Wendy
 */
   

import DAOs.Interface.KlantDAOInterface;
import POJO.KlantenLijst;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import ch.qos.logback.classic.Level;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;


public class KlantDAOXML implements KlantDAOInterface {
   // trace, debug, info, warn, error
   private static Logger LOGGER = (Logger) LoggerFactory.getLogger("com.webshop.dao");  
   private static Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
   private static Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   static{
        // Logger.ROOT_LOGGER_NAME == "rootLogger" :Level OFF      
        LOGGER.setLevel(Level.DEBUG);   
        errorLogger.setLevel(Level.ERROR);   
        // testLogger inherits Level debug
       }   
   
   KlantBuilder klantBuilder = new KlantBuilder();
   Klant klant = new Klant (klantBuilder);
    
   KlantenLijst klantenLijst = new KlantenLijst(); 

  
 @Override   
 public Klant insertKlant (Klant klant) {          
    Klant klantMetId = null;
    int klantId = 0;     
        
    try {
            ArrayList<Klant> alleKlanten = findAllKlanten();  // fout opvangen (in methode zelf) wanneer de lijst nog leeg is: bij de eerste klant
            
            if (alleKlanten != null){   
                testLogger.debug("klanten lijst is niet leeg");
                for (int i = alleKlanten.size()-1 ; i < alleKlanten.size() ; i++ ){
                    Klant klantLaatst = alleKlanten.get(i);
                    klantId = klantLaatst.getKlantId();
                    klantId++;
                } 
             }
             else { 
                testLogger.debug("klantenlijst is leeg");
                // maak nieuwe klantenlijst aan en een eerste klantId
                KlantenLijst KL = new KlantenLijst();
                klantenLijst = KL; 
                klantId = 1; 
                }       
   
            String voornaam = klant.getVoornaam();
            String achternaam = klant.getAchternaam();        
            String tussenvoegsel = klant.getTussenvoegsel();
            String email = klant.getEmail();  
            klantMetId = new Klant(klantId, voornaam, achternaam, tussenvoegsel, email); 
             
            klantenLijst.add(klantMetId);
            
            FileOutputStream fos = new FileOutputStream("klant.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos;// = new ObjectOutputStream(bos);
            
            XStream xstream = new XStream();
            xstream.alias("klant", Klant.class);
            xstream.alias("klanten", KlantenLijst.class);
            xstream.addImplicitCollection(KlantenLijst.class, "klantenLijst");
            
            String xml = xstream.toXML(klantenLijst);        
               testLogger.info("String xml aangemaakt met waarde: "+ xml);  // checken wat dit teruggeeft  
               oos = xstream.createObjectOutputStream(bos);
               oos.writeObject(xml);
               oos.close();
           
        } catch (FileNotFoundException ex) {
            errorLogger.error("A FileNotFoundException occured at this method", ex);
            LOGGER.debug("A FileNotFoundException occured at this method", ex);
        } catch (IOException ex) {
            errorLogger.debug("A IOException occured at this method", ex);
            LOGGER.debug("A IOException occured at this method", ex);
        }
        
        // return klant, incl klantId naar controller
        return klantMetId;
}

 
@Override
    public ArrayList<Klant> findAllKlanten() {          
        // code voor decode
        //klantenLijst = new KlantenLijst();
        //klant = null;
        ArrayList<Klant> klanten = new ArrayList(); 
       
        try {      
            FileInputStream fis = new FileInputStream("klant.xml");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois;
       
            XStream xstream = new XStream();
            xstream.alias("klant", Klant.class);
            xstream.alias("klanten", KlantenLijst.class);
            xstream.addImplicitCollection(KlantenLijst.class, "klantenLijst");
            //String xml = "<klanten><klant>...</klant></klanten>";
            
            ois = xstream.createObjectInputStream(bis);
            
            if (ois != null){
                testLogger.debug("er zijn al klanten");
            String xml = (String) ois.readObject();
            
            //deserialise
              
            klantenLijst = (KlantenLijst)xstream.fromXML(xml);
            klanten = klantenLijst.getKlantenLijst();
            
            }
            else{
                testLogger.info("er zijn nog geen klanten");
                klanten = null;
            }
            // fout opvangen wanneer de lijst nog leeg is >> ok n controller: if arraylist == null or !=null
            } catch (FileNotFoundException ex) {
                errorLogger.error("A FileNotFoundException occured at this method", ex);
                LOGGER.debug("A FileNotFoundException occured at this method", ex);
            } catch (IOException ex) {
                errorLogger.error("A IOException occured at this method", ex);
                LOGGER.debug("A IOException occured at this method", ex);
            } catch (ClassNotFoundException ex) {
                errorLogger.error("A ClassNotFoundException occured at this point", ex);
                LOGGER.debug("A ClassNotFoundException occured at this point", ex);
       }
        
        return klanten;
    }
    

    @Override
    public Klant findByKlantId(int klantId) {
        
        Klant klant = null;  
        
        ArrayList <Klant> klanten = findAllKlanten();
        if (klanten != null){
        

        // if klanten is leeg: klant == null;
            for(int i = 0; i < klanten.size(); i++){            
                if (klanten.get(i).getKlantId() == klantId){
                    String voornaam = klanten.get(i).getVoornaam();
                    String achternaam = klanten.get(i).getAchternaam();        
                    String tussenvoegsel = klanten.get(i).getTussenvoegsel();
                    String email = klanten.get(i).getEmail();  
                    klant =  new Klant(klantId, voornaam, achternaam, tussenvoegsel, email);
                    break;
                }
            }                
        }
        else{
            LOGGER.info("Geen klanten aanwezig in het bestand.");
        }
        
        return klant;
    }

    @Override 
    public ArrayList<Klant> findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {
        
        //!! aanpassen, bij controller de volgorde van voor en achternaam
        
        Klant klant;
        ArrayList <Klant> klanten2 = new ArrayList();
        
        ArrayList <Klant> klanten = findAllKlanten();        
        if(klanten != null){
            for(int i = 0; i < klanten.size(); i++){            
                if (klanten.get(i).getVoornaam().equals(voorNaam) 
                        || klanten.get(i).getAchternaam().equals(achterNaam) ){
                    int klantId = klanten.get(i).getKlantId();        
                    String tussenvoegsel = klanten.get(i).getTussenvoegsel();
                    String email = klanten.get(i).getEmail();  
                    klant =  new Klant(klantId, voorNaam, achterNaam, tussenvoegsel, email);
                    klanten2.add(klant);
                }
            }
        }
        else{
            LOGGER.info("Geen klanten aanwezig in het bestand.");
        } 
        return klanten2; 
    }
    

    @Override
    public ArrayList<Klant> findByEmail(String email) {
        
        Klant klant;
        ArrayList <Klant> klanten2 = new ArrayList();
         
        ArrayList <Klant> klanten = findAllKlanten();       
        
        if (klanten != null){
                for(int i = 0; i< klanten.size(); i++){            
                if (klanten.get(i).getEmail().equals(email) ){
                    int klantId = klanten.get(i).getKlantId();        
                    String tussenvoegsel = klanten.get(i).getTussenvoegsel();
                    String voornaam = klanten.get(i).getVoornaam();
                    String achternaam = klanten.get(i).getAchternaam();     
                    klant =  new Klant(klantId, voornaam, achternaam, tussenvoegsel, email);
                    klanten2.add(klant);
                }
            } 
        }
        else{
            LOGGER.info("Geen klanten aanwezig in het bestand.");
        }
        return klanten2;  
    }

    
    @Override
    public boolean deleteByKlantId(int klantId) {
        
        boolean deleted = false; 
        Klant klant = null;
        
        ArrayList <Klant> klanten = findAllKlanten();
        if (klanten != null){
            for(int i = 0; i < klanten.size(); i++){            
                if (klanten.get(i).getKlantId() == klantId){
                    String voornaam = klanten.get(i).getVoornaam();
                    String achternaam = klanten.get(i).getAchternaam();        
                    String tussenvoegsel = klanten.get(i).getTussenvoegsel();
                    String email = klanten.get(i).getEmail();  
                    klant =  new Klant(klantId, voornaam, achternaam, tussenvoegsel, email);
                    deleted = klanten.remove(klanten.get(i));

                }
            }
        }
        else{
            LOGGER.info("Geen klanten aanwezig in het bestand.");
        }
        
        klantenLijst.setKlantenLijst(klanten);
               
        try{ 
            FileOutputStream fos = new FileOutputStream("klant.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos;// = new ObjectOutputStream(bos);

            XStream xstream = new XStream();
            xstream.alias("klant", Klant.class);
            xstream.alias("klanten", KlantenLijst.class);
            xstream.addImplicitCollection(KlantenLijst.class, "klantenLijst");

            String xml = xstream.toXML(klantenLijst);        
            testLogger.info("String xml aangemaakt met waarde: "+ xml);  // checken wat dit teruggeeft  
 
               oos = xstream.createObjectOutputStream(bos);
               oos.writeObject(xml);
               oos.close();

        } catch (FileNotFoundException ex) {
            errorLogger.error("A FileNotFoundException occured at this method", ex);
            LOGGER.debug("A FileNotFoundException occured at this method", ex);
        } catch (IOException ex) {
            errorLogger.debug("A IOException occured at this method", ex);
            LOGGER.debug("A IOException occured at this method", ex);
        }
                
       return deleted;          
    } 
    
    
    @Override
    public int deleteAll(){        
        
        ArrayList<Klant> klanten = findAllKlanten();
        int aantalKlanten = klanten.size();
        klanten.clear();
        klantenLijst.setKlantenLijst(klanten);
        
        try{
            FileOutputStream fos = new FileOutputStream("klant.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos;// = new ObjectOutputStream(bos);
            
            XStream xstream = new XStream();
            xstream.alias("klant", Klant.class);
            xstream.alias("klanten", KlantenLijst.class);
            xstream.addImplicitCollection(KlantenLijst.class, "klantenLijst");
            
            String xml = xstream.toXML(klantenLijst);        
               testLogger.info("String xml aangemaakt met waarde: "+ xml);  // checken wat dit teruggeeft  

               oos = xstream.createObjectOutputStream(bos);
               oos.writeObject(xml);
               oos.close();
           
       } catch (FileNotFoundException ex) {
            errorLogger.error("A FileNotFoundException occured at this method", ex);
            LOGGER.debug("A FileNotFoundException occured at this method", ex);
        } catch (IOException ex) {
            errorLogger.debug("A IOException occured at this method", ex);
            LOGGER.debug("A IOException occured at this method", ex);
        }
        
        return aantalKlanten;
    }

    
    @Override
    public Klant updateGegevens(Klant klant) {
        
        int klantId = klant.getKlantId();
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail();  
        
        ArrayList<Klant>klanten = findAllKlanten();
        
         for(int i = 0; i< klanten.size(); i++){  
            if(klanten.get(i).getKlantId() == klantId){
                klanten.get(i).setVoornaam(voornaam);
                klanten.get(i).setAchternaam(achternaam);
                klanten.get(i).setTussenvoegsel(tussenvoegsel);
                klanten.get(i).setEmail(email);    
                //klant =  new Klant(klantId, voornaam, achternaam, tussenvoegsel, email);
            }
         }
        klantenLijst.setKlantenLijst(klanten); 
        
        
        try{
            FileOutputStream fos = new FileOutputStream("klant.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos;// = new ObjectOutputStream(bos);
            
            XStream xstream = new XStream();
            xstream.alias("klant", Klant.class);
            xstream.alias("klanten", KlantenLijst.class);
            xstream.addImplicitCollection(KlantenLijst.class, "klantenLijst");
            
            String xml = xstream.toXML(klantenLijst);        
               testLogger.info("String xml aangemaakt met waarde: "+ xml);  // checken wat dit teruggeeft  

               oos = xstream.createObjectOutputStream(bos);
               oos.writeObject(xml);
               oos.close();
           
        } catch (FileNotFoundException ex) {
            errorLogger.error("A FileNotFoundException occured at this method", ex);
            LOGGER.debug("A FileNotFoundException occured at this method", ex);
        } catch (IOException ex) {
            errorLogger.debug("A IOException occured at this method", ex);
            LOGGER.debug("A IOException occured at this method", ex);
        }
        
        Klant klant2 = findByKlantId(klantId);  
        
        return klant2;
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
    
    
    //            System.out.println(klant.getKlantId() + " " + klant.getVoornaam() + 
//                    " " + klant.getTussenvoegsel() + " " + klant.getAchternaam() + 
//                    " " + klant.getEmail());
    
        
//        klantBuilder.klantId(klantId);            
//        klantBuilder.voornaam(klant.getVoornaam() );
//        klantBuilder.tussenvoegsel(klant.getTussenvoegsel() );
//        klantBuilder.achternaam(klant.getAchternaam());
//        klantBuilder.email(klant.getEmail());               
        
    
    // decode: read 
    // encode: create, update, delete
        
    // setters to build object
    // getters - get information from xml file

    // genereer id

    }



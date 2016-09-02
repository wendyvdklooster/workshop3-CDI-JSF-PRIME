


package Controller;

import DAOGenerics.GenericDaoImpl;
import DAOs.BetalingDao;
import DAOs.FactuurDao;
import Helpers.HibernateSessionFactory;
import POJO.Bestelling;
import POJO.Betaalwijze;
import POJO.Betaling;
import POJO.Factuur;
import POJO.Klant;
import View.BetalingView;
import View.FactuurView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BetalingController {

private static final Logger log = LoggerFactory.getLogger(BetalingController.class);

    FactuurView  factuurView = new FactuurView();
    BetalingView betalingView = new BetalingView();
    
    HoofdMenuController hoofdMenuController = new HoofdMenuController();
     
    GenericDaoImpl <Betaling, Long> betalingDao = new BetalingDao(); 
    
    Betaling betaling; 
    Klant klant; 
    Factuur factuur; 
    
    
    public Session session;
    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    
    public Session getSession(){
        session = sessionFactory.openSession();
        session.beginTransaction();
       return session;
    }
    
    protected void commitSession(Session session){
        session.getTransaction().commit();
    }
    
    public void closeSession(Session session){            
            session.close();
    }
    
    
    
    public void betalingMenu() {
        
        int keuze = betalingView.startMenuBetaling();
        
        switch(keuze){
            case 1: 
                voegNieuweBetalingToe();
                break;
            case 2:
                zoekBetalingGegevens();
                break;  
            case 3: 
                wijzigingenInBetaling();
                break;
            case 4: 
                verwijderenVanBetaling();
                break; 
            case 5:
                terugNaarHoofdMenu();
                break;            
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break;            
            
        }                
    }
        
    
        
    public Betaling createBetaling(){
        
        Betaling betaling = new Betaling();  
       
        long betalingId;
        Klant klant = new Klant();
        Date betaaldatum; 
        Betaalwijze betaalwijze = new Betaalwijze(); 
        String betalingsGegevens = null; 
        Factuur factuur = new Factuur();                       
        
        betaling.setBetaalwijze(betaalwijze);
        betaling.setBetaaldatum(new Date());
        betaling.setKlant(klant);
        betaling.setFactuur(factuur);
        betaling.setBetalingsGegevens(betalingsGegevens);      

        return betaling;          
    }
    
    
    public long voegNieuweBetalingToe(){
          
        session =  getSession();
        betalingDao = new BetalingDao(); 
        
        System.out.println("U gaat een betaling toevoegen. Voer de gegevens in.");
        Betaling betaling = createBetaling(); 
        Long betalingId = (Long)betalingDao.insert(betaling, session);            
        session.getTransaction().commit();
        
        System.out.println("U heeft de gegevens van de betaling toegevoegd met betalingId: " 
            + betalingId); 
        System.out.println();        
        
        betalingMenu();
        
        return betalingId;
    }
    
    
     private void zoekBetalingGegevens() {
     
        betalingDao = new BetalingDao();
        betaling = new Betaling();         
		
        int input = betalingView.menuBetalingZoeken();
        switch (input){
                case 1:  // naar 1 betaling zoeken
                     long betalingId = betalingView.voerBetalingIdIn();
                        session = getSession();
                        betaling = (Betaling) betalingDao.readById(betalingId, session);
                        session.getTransaction().commit();
                        betalingView.printBetalingOverzicht(betaling);                     
                    break; // einde naar 1 betaling zoeken
                case 2: // alle betalingen zoeken
                    session = getSession();
                    ArrayList <Betaling> betalingenLijst = 
                            (ArrayList <Betaling>) betalingDao.readAll(Betaling.class, session);
                    session.getTransaction().commit();
                    System.out.println("Alle artikelen in het bestand");
                    betalingView.printBetalingLijst(betalingenLijst); 
                        break; 
                case 3: // naar betalingmenu
                        break; 
                default: // automatisch naar betalingmenu	
                        break; 
        }	
        closeSession(session);
     betalingMenu();
    
     
     }

    //id, betaaldatum, betaalwijze, klant, factuur, betalingsgegevens
     
    private void wijzigingenInBetaling() {
           
        session = getSession();
        betalingDao = new BetalingDao();
        
        Betaling gewijzigdeBetaling = new Betaling();
        boolean gewijzigd;
        
        long betalingId = betalingView.voerBetalingIdIn();
        betaling = (Betaling) betalingDao.readById(betalingId, session);
        session.getTransaction().commit();
        closeSession(session);
        gewijzigdeBetaling = invoerNieuweBetalingGegevens(betaling);
        
        session = getSession();
        session.getTransaction().commit();
        closeSession(session); }

    
    
     public Betaling invoerNieuweBetalingGegevens(Betaling betaling) {
                
        int juist = 0;
        // niet wijzigen: id, datum
        // get BetaalWijze betaalwijze, daar de String betaalwijze
        Betaalwijze bw = betaling.getBetaalwijze();
        String betaalwijze = bw.getBetaalwijze();        
        juist = betalingView.checkInputString(betaalwijze);
        if (juist == 2) {
            betaalwijze = betalingView.voerBetaalwijzeIn(); // deze methode in view werkt nog niet 
            // convert to bean betaalwijze
        }
        
        String betalingsGegevens = betaling.getBetalingsGegevens();        
        juist = betalingView.checkInputString(betalingsGegevens);
        if (juist == 2) {
            //toevoegen
            betalingsGegevens = betalingView.voegBetalingsGevensToe(betalingsGegevens);
            // verwijderen
            betalingsGegevens = betalingView.verwijderBetalingsGegevens(betalingsGegevens);
        }
        
        // willen we klant en factuur eventueel kunnen wijzigen?
        Klant klant = betaling.getKlant(); 
        String klantToString = klant.toString();
     
        juist = betalingView.checkInputString(klantToString);
         if (juist == 2) {
            long klantId = betalingView.voerKlantIdIn();
               // hier nu de klantgegevens aanpassen, niet een andere klant toevoegen. wisselen
//                klantController.wijzigKlantGegevens();
//                session = getSession();
//                klant = (Klant) klantDao.readById(klantId, session);
//                session.getTransaction().commit();
         }
         
         // willen we klant en factuur eventueel kunnen wijzigen?
        Factuur factuur = betaling.getFactuur(); 
        String factuurToString = factuur.toString();
     
        juist = betalingView.checkInputString(factuurToString);
         if (juist == 2) {
            long factuurId = betalingView.voerFactuurIdIn();
               // hier nu de fatuurgegevens aanpassen, niet een andere facuur toevoegen. /wisselen van
//               factuurController.wijzigFactuurGegevens();
//                session = getSession();
//                factuur = (Factuur) factuurDao.readById(factuurId, session);
//                session.getTransaction().commit();
         }        
         
        // setters betaalwijze, betalingsGegevens
        betaling.setBetaalwijze(bw);
        betaling.setBetalingsGegevens(betalingsGegevens);          
        betaling.setKlant(klant);
        betaling.setFactuur(factuur);
        
        return betaling;        
    }
     
         
    private void verwijderenVanBetaling() {
        
        betalingDao = new BetalingDao();
                
        int userInput = betalingView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 betaling verwijderen  
                betalingView.printBetalingLijst((ArrayList<Betaling>) betalingDao.readAll(Betaling.class, session));
                long betalingId = betalingView.printDeleteBetalingById();
                betalingDao.deleteById(betalingId, session);//             
            case 2:// alle betalingen verwijderen                
                int x = betalingView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        int verwijderd = betalingDao.deleteAll(Betaling.class, session);                    
                        System.out.println(verwijderd + " totaal aantal artikelen zijn verwijderd");                       
                    }                
                    else { // bevestiging = nee
                        System.out.println("De artikel gegevens worden NIET verwijderd.");
                    }
                break;                
            case 3:// door naar einde methode > naar betalingmenu();
                break;
            default:
                break;
        }
        betalingMenu(); }
    
    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }  

   

   




}

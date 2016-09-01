


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
//        factuur.setBestelling(bestelling);        

        return betaling;          
    }
    
    
    public long voegNieuweBetalingToe(){
          
        session =  getSession();
        betalingDao = new BetalingDao(); 
        
        System.out.println("U gaat een betaling toevoegen. Voer de gegevens in.");
        Betaling betaling = createBetaling(); 
        Long betalingId = (Long)betalingDao.insert(betaling, session);            
        session.getTransaction().commit();
        
        System.out.println("U heeft de factuurgegevens toegevoegd met factuurId: " 
            + betalingId); 
        System.out.println();        
        
        betalingMenu();
        
        return betalingId;
    }
    
    
     private void zoekBetalingGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void wijzigingenInBetaling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void verwijderenVanBetaling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }  

   

   




}




package Controller;

import DAOGenerics.GenericDaoImpl;
import DAOs.FactuurDao;
import Helpers.HibernateSessionFactory;
import POJO.Bestelling;
import POJO.Betaling;
import POJO.Factuur;
import POJO.Klant;
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

public class FactuurController {

private static final Logger log = LoggerFactory.getLogger(FactuurController.class);

    FactuurView factuurView = new FactuurView();
    GenericDaoImpl <Factuur, Long> factuurDao = new FactuurDao();  
    HoofdMenuController hoofdMenuController = new HoofdMenuController();
    
    
    public Session session;
    // sessionfactory aanroepen via de hibernatesessionfactory
    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    
    // session starten
    public Session getSession(){
        session = sessionFactory.openSession();
        session.beginTransaction();
       return session;
    }
    
    protected void commitSession(Session session){
        session.getTransaction().commit();
    }
	
    // session afsluiten
    public void closeSession(Session session){            
            session.close();
    }
    
    
    
    public void factuurMenu() {
        
        int keuze = factuurView.startMenuFactuur();
        
        switch(keuze){
            case 1: 
                voegNieuweFactuurToe();
                break;
            case 2:
                zoekFactuurGegevens();
                break;  
            case 3:
                wijzigFactuurGegevens();
                break;
            case 4:
                verwijderFactuurGegevens();
                break;
            case 5:
                terugNaarHoofdMenu();
                break;
            
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break;            
            
        }                
    }
    
    
    
    public Factuur createFactuur(){
        
        Factuur factuur = new Factuur();        
        
        String factuurnummer = factuurView.voerFactuurNummerIn();
        Klant klant = new Klant();
        long klantId;
        Set<Betaling> betalingset = new HashSet<Betaling>(); 
        Bestelling bestelling = new Bestelling();                          
        
        factuur.setFactuurnummer(factuurnummer);       
        factuur.setFactuurdatum(new Date());
        factuur.setKlant(klant);
        factuur.setBetalingset(betalingset);
        factuur.setBestelling(bestelling);        

        return factuur;  
        
    }
    
    public long voegNieuweFactuurToe(){
          
        session =  getSession();
        factuurDao = new FactuurDao(); 
        
        System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
        Factuur factuur = createFactuur(); 
        Long factuurId = (Long)factuurDao.insert(factuur, session);            
        session.getTransaction().commit();
        
        System.out.println("U heeft de factuurgegevens toegevoegd met factuurId: " 
            + factuurId); 
        System.out.println();        
        
        factuurMenu();
        
        return factuurId;
    }
    
    
    private void zoekFactuurGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    }  

    private void wijzigFactuurGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void verwijderFactuurGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}

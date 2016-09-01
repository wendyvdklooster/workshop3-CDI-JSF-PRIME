


package Controller;


import DAOGenerics.GenericDaoImpl;
import DAOs.AccountDao;
import Helpers.HibernateSessionFactory;
import POJO.Account;
import POJO.Klant;
import View.AccountView;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class AccountController {

private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    
    AccountView accountView = new AccountView();
    GenericDaoImpl <Account, Long> accountDao = new AccountDao(); 
    
    HoofdMenuController hoofdMenuController = new HoofdMenuController();
    KlantController klantController = new KlantController();
    
    
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
    
    
    public void accountMenu() {
        
        int keuze = accountView.startMenuAccount();
        
        switch(keuze){
            case 1: 
                voegNieuwAccountToe();
                break;
            case 2:
                zoekAccountGegevens();
                break;  
            case 3:
                wijzigAccountGegevens();
                break;
            case 4: 
                verwijderAccountGegevens();
                break; 
            case 5:
                terugNaarHoofdMenu();
                break;
            
            default: 
                System.out.println("Deze optie is niet beschikbaar.");
                break; 
        }                
    }
    
    
    // hier nieuwe klant met account. 
    // kan ook nieuw account maken voor een bestaande klant
    public Account createAccount(){
        
        Account account = new Account();        
        Date creatieDatum; 
        
        String gebruikersnaam = accountView.voerGebruikersnaamIn();
        String password = accountView.voerPasswordIn();
        

        // nieuwe klant tegelijkertijd met nieuw account        
        long klantId = klantController.voegNieuweKlantToe();
        session = getSession(); 
        Klant klant = (Klant) session.get(Klant.class, klantId);                                       
        
        account.setUsername(gebruikersnaam);
        account.setPassword(password);
        account.setCreatieDatum(new Date());
        account.setKlant(klant);
//        factuur.setBestelling(bestelling);        

        return account;          
    }
    
    
    public long voegNieuwAccountToe(){
          
        session =  getSession();
        accountDao = new AccountDao(); 
        
        System.out.println("U gaat een klant toevoegen. Voer de gegevens in.");
        Account account = createAccount(); 
        Long accountId = (Long)accountDao.insert(account, session);            
        session.getTransaction().commit();
        
        System.out.println("U heeft de factuurgegevens toegevoegd met factuurId: " 
            + accountId); 
        System.out.println();        
        
        accountMenu();
        
        return accountId;
    }
    
    
    private void zoekAccountGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }       

    private void wijzigAccountGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void verwijderAccountGegevens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    } 


}

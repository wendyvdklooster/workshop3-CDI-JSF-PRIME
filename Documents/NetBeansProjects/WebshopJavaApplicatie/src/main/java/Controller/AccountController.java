


package Controller;

import DAOGenerics.GenericDaoImpl;
import DAOs.AccountDao;
import DAOs.FactuurDao;
import DAOs.KlantDao;
import Helpers.HibernateSessionFactory;
import POJO.Account;
import POJO.Bestelling;
import POJO.Betaling;
import POJO.Factuur;
import POJO.Klant;
import View.AccountView;
import View.FactuurView;
import View.KlantView;
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
    
    
    
    public Account createAccount(){
        
        Account account = new Account();        
        Date creatieDatum; 
        
        String gebruikersnaam = accountView.voerGebruikersnaamIn();
        String password = accountView.voerPasswordIn();
                
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
        accountDao = new AccountDao();
        Account account = new Account();
        
        int userInput = accountView.menuAccountZoeken();
        switch(userInput) {
            case 1: 
                // zoeken naar een account
                 session = getSession();
                 long accountId = accountView.voerAccountIdIn();
                 account = (Account)accountDao.readById(accountId, session);
                 session.getTransaction().commit();
                 
                 accountView.printAccountGegevens(account);
                 session.close();
                 break;
            case 2:
                // zoeken naar alle gegevens
                session= getSession();
                ArrayList <Account> accountenLijst = (ArrayList<Account>) accountDao.readAll(Account.class, session);
                session.getTransaction().commit();
                if (accountenLijst != null){
                    System.out.println("Alle klanten in het bestand");
                    accountView.printAccountenLijst(accountenLijst);   
                }
                break;
        }
    }       

    private void wijzigAccountGegevens() {
        accountDao = new AccountDao();
        Account account = new Account();
        session = getSession();
        
        long accountId = accountView.voerAccountIdIn();
        account = (Account)accountDao.readById(accountId, session);
        Account gewijzigdAccount = voerNieuweAccountGegevensIn(account);
        accountDao.update(gewijzigdAccount, session);
        session.getTransaction().commit();
        
        gewijzigdAccount = (Account)accountDao.readById(accountId, session);
        System.out.println("Nieuwe accountgegevens");
        accountView.printAccountGegevens(gewijzigdAccount);
        session.close();
    }

    private void verwijderAccountGegevens() {
        session = getSession();
        AccountDao accountDao = new AccountDao();
        boolean deleted = false;
        
        int userInput = accountView.menuAccountVerwijderen();
        switch (userInput) {
            case 1: 
                // een account verwijderen
                long accountId = accountView.voerAccountIdIn();
                deleted = accountDao.deleteById(accountId, session);
                session.getTransaction().commit(); 
                System.out.println("Verwijderen van account: " + deleted);
                break;
            case 2:
                // verwijderen alle klanten
                int keuze = accountView.bevestigingsVraag(); 
                if (keuze == 1) {
                    
                    int rowsAffected = accountDao.deleteAll(Account.class, session);
                    session.getTransaction().commit();
                    System.out.print(rowsAffected);
                    System.out.println(" totaal aantal accounts zijn verwijderd");                    
                    System.out.println("alle koppelingen van klant en account zijn verwijderd");
                }
                else if (keuze == 2) {
                    System.out.println("De accountgegevens worden niet verwijderd.");
                }
                break;
        }
    }

    
    public void terugNaarHoofdMenu() {
        hoofdMenuController = new HoofdMenuController();
        hoofdMenuController.start();
    } 

    public Account voerNieuweAccountGegevensIn(Account account) {
    int juist = 0;
        
        String username = account.getUsername();
        juist = accountView.checkInputString(username);
        if (juist == 2) {
            username = accountView.voerGebruikersnaamIn();
        } 
        
        KlantView klantView = new KlantView();
        KlantController klantController = new KlantController();
        KlantDao klantDao = new KlantDao();
        
        // later splitsen in klant controller, midner duplicate code
        Klant klant = account.getKlant();
        Klant gewijzigdeKlant = klantController.voerWijzigingenKlantIn(klant);
        klantDao.update(gewijzigdeKlant, session);  
        
        account.setKlant(klant);
        account.setUsername(username);
        
        return account;
        } 
}

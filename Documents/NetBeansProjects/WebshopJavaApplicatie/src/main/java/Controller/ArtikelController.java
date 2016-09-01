
package Controller;



import DAOGenerics.GenericDaoImpl;
import DAOs.ArtikelDao;
import Helpers.HibernateSessionFactory;
import View.ArtikelView;
import POJO.Artikel;
import POJO.Bestelling;
import View.BestellingView;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ArtikelController {  
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.webshop");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.webshop.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.webshop.test");
   
    
    public Session session;   
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
    
    ArtikelView artikelView = new ArtikelView();   
    Artikel artikel = new Artikel();    
    BestellingView bestellingView = new BestellingView();
    GenericDaoImpl<Artikel, Long> artikelDao;
    
    
    public void artikelMenu()  {
        
        int userInput = artikelView.startArtikelMenu();
        
        switch (userInput) {
            case 1: 
                artikel = createArtikel();
                voegNieuwArtikelToe(artikel); 
                break;
            case 2: 
                zoekArtikelGegevens();
                break;
            case 3:
                wijzigArtikelGegevens();
                break;
            case 4:
                verwijderArtikelGegevens();
                break;
            case 5:
                terugNaarHoofdMenu();
                break;
            default:
                System.out.println("Deze optie is niet beschikbaar.");
                break;
        }  
    }
    
    
    public Artikel createArtikel() {
        
        System.out.println("Vul de artikel gegevens in: ");
        String artikelNaam = artikelView.voerArtikelNaamIn();
        double artikelPrijs = artikelView.voerAtrikelPrijsIn();
        
        artikel = new Artikel();
        artikel.setArtikelNaam(artikelNaam);
        artikel.setArtikelPrijs(artikelPrijs);
        return artikel;
    } 
    
    
    public void voegNieuwArtikelToe(Artikel artikel) {     
         
        session = getSession();
        artikelDao = new ArtikelDao();        
        long id = artikelDao.insert(artikel, session);
        session.getTransaction().commit();
        closeSession(session);
        session = getSession();
        artikel = (Artikel) artikelDao.readById(id, session);
        session.getTransaction().commit();
        System.out.println("U heeft de volgende gegevens ingevoerd: " );
        artikelView.printArtikelOverzicht(artikel); 
        
        closeSession(session);
        artikelMenu();
    }
         
    
    public void zoekArtikelGegevens()  {    
         
        artikelDao = new ArtikelDao();
        artikel = new Artikel();         
		
        int input = artikelView.menuArtikelZoeken();
        switch (input){
                case 1:  // naar 1 artikel zoeken
                     long artikelId = artikelView.voerArtikelIdIn();
                        session = getSession();
                        artikel = (Artikel) artikelDao.readById(artikelId, session);
                        session.getTransaction().commit();
                        artikelView.printArtikelOverzicht(artikel);                     
                    break; // einde naar 1 artikel zoeken
                case 2: // alle artikelen zoeken
                    session = getSession();
                    ArrayList <Artikel> artikelenLijst = 
                            (ArrayList <Artikel>) artikelDao.readAll(Artikel.class, session);
                    session.getTransaction().commit();
                    System.out.println("Alle artikelen in het bestand");
                    artikelView.printArtikelenLijst(artikelenLijst); 
                        break; 
                case 3: // naar artikelmenu
                        break; 
                default: // automatisch naar artikelmenu	
                        break; 
        }	
        closeSession(session);
     artikelMenu();
    }
    
    
    public void wijzigArtikelGegevens() {   
        
        session = getSession();
        artikelDao = new ArtikelDao();
        
        Artikel gewijzigdArtikel = new Artikel();
        boolean gewijzigd;
        
        long artikelId = artikelView.voerArtikelIdIn();
        artikel = (Artikel) artikelDao.readById(artikelId, session);
        session.getTransaction().commit();
        closeSession(session);
        gewijzigdArtikel = invoerNieuweArtikelGegevens(artikel);
        
        session = getSession();
        session.getTransaction().commit();
        closeSession(session);
        artikelDao.update(gewijzigdArtikel, session); 
    }
    
        
    public Artikel invoerNieuweArtikelGegevens(Artikel artikel) {
                
        int juist = 0;
        
        String artikelNaam = artikel.getArtikelNaam();
        juist = artikelView.checkInputString(artikelNaam);
        if (juist == 2) {
            artikelNaam = artikelView.voerArtikelNaamIn();
        }
        double artikelPrijs = artikel.getArtikelPrijs();
        String artikelPrijsString = artikelPrijs + "";
        juist = artikelView.checkInputString(artikelPrijsString);
        if (juist == 2) {
            artikelPrijs = artikelView.voerAtrikelPrijsIn();
        }
        
        long artikelId = artikel.getId();
        Artikel artikelNieuw = new Artikel(artikelId, artikelNaam, artikelPrijs);
        
        return artikelNieuw;        
    }
    
    
    public void verwijderArtikelGegevens()  {
        artikelDao = new ArtikelDao();
                
        int userInput = artikelView.printVerwijderMenu();
        switch (userInput) {
            case 1:// 1 artikel verwijderen  
                artikelView.printArtikelenLijst((ArrayList<Artikel>) artikelDao.readAll(Artikel.class, session));
                long artikelId = artikelView.printDeleteArtikelById();
                artikelDao.deleteById(artikelId, session);//             
            case 2:// alle artikelen verwijderen                
                int x = artikelView.bevestigingsVraag();                
                    if (x == 1){ // bevestiging is ja
                        int verwijderd = artikelDao.deleteAll(Artikel.class, session);                    
                        System.out.println(verwijderd + " totaal aantal artikelen zijn verwijderd");                       
                    }                
                    else { // bevestiging = nee
                        System.out.println("De artikel gegevens worden NIET verwijderd.");
                    }
                break;                
            case 3:// door naar einde methode > naar artikelmenu();
                break;
            default:
                break;
        }
        artikelMenu();
    }
    
    
    public void terugNaarHoofdMenu() {
        HoofdMenuController hoofdMenu = new HoofdMenuController();
        hoofdMenu.start();
    }   
    
} // eind artikelcontroller

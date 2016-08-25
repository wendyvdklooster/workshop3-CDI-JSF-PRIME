


package DAOs;

import Helpers.HibernateSessionFactory;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger; 

/**
 * @author Wendy
 * @param <E>
 * @param <PK>
 */

public class GenericDaoImpl <E, PK extends Serializable> implements GenericDaoInterface <E, PK> {
 
    
    private static final Logger log = LoggerFactory.getLogger(GenericDaoImpl.class);
    
    // data fields
    private Class<E> type;
    Session session;
    
    // sessionfactory aanroepen via de hibernatesessionfactory
    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    
    // session starten
    protected Session getSession(){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
        return session;
    }
	
    // session afsluiten
    protected void closeSession(Session session){
            session.getTransaction().commit();
            session.close();
    }
    
    
    // code om klasse type E te specificeren.
    public GenericDaoImpl(Class<E> type){
           this.type = type;
   }
    
    // CRUD methodes // 
    
    @Override // .save();
    public Object create(Object e) {
        log.info(type.getSimpleName() + " creeeren in de database");
        session = getSession();
        
        // speciefiek deel voor elke crud methode
        Object object = session.save(e); // is dit okay??        
        
        closeSession(session);
        return object; 
        
    }

    @Override// .get();
    public Object readById(Serializable id) {
        log.info(type.getSimpleName() + " via Id creeeren in de database");
        session = getSession();
        
        // speciefiek deel voor elke crud methode
        // hoe gaat deze om met Long?
        Object object = session.get(type, id);
        
        closeSession(session);
        return object; 
        }

    @Override // .get();
    public List read(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // .get();
    public List readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override // .update();
//    public Object update(Object e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override// .delete();
    public void delete(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // .delete();
    public void deleteById(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // .delete();
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

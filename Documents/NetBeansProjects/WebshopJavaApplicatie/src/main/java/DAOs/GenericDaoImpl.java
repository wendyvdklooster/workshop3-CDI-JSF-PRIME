


package DAOs;

import Helpers.HibernateSessionFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger; 

/**
 * @author Wendy
 * @param <T>
 * @param <PK>
 */

public abstract class GenericDaoImpl <T, PK extends Serializable> implements GenericDaoInterface <T, PK> {
 
    
    private static final Logger log = LoggerFactory.getLogger(GenericDaoImpl.class);
    
    // data fields
    private Class<T> beanType;
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
    
    
    // code om klasse beanType E te specificeren.
    public GenericDaoImpl(Class<T> type){
           this.beanType = type;
   }
    
    
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.beanType = ((Class) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);
    }
    
    
    // CRUD methodes // 
    
    
    @Override // .save();

    public T create(T e) {        
        log.info(beanType.getSimpleName() + " creeeren in de database");
        session = getSession();
        
        // speciefiek deel voor elke crud methode
        Object object = session.save(e); // is dit okay??        
        
        closeSession(session);
        return e; 
        
    }
//
    
    @Override// .get();
    public T readById(PK id) {

        log.info(beanType.getSimpleName() + " via Id creeeren in de database");
        session = getSession();
        
        // speciefiek deel voor elke crud methode
        // hoe gaat deze om met Long?
        Object object = session.get(beanType, id);
        
        closeSession(session);
        return (T)object; 
       }

    @Override 
    public List<T> read(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // .update();
    public T update(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // delete();
    public void deleteById(PK id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  


    


}

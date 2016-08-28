


package DAOGenerics;

import DAOGenerics.GenericDaoInterface;
import Helpers.HibernateSessionFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
    
    @Override 
    public PK create(T t) {        
        log.info(beanType.getSimpleName() + " creeeren in de database. Return id");
        session = getSession();
        
        PK instantieId = (PK) session.save(t);        
        
        closeSession(session);
        return instantieId;           
    }
    
    
    @Override
    public T readById(PK id) {

        log.info(beanType.getSimpleName() + " via Id lezen uit de database");
        session = getSession();
        
        T instantie = (T)session.get(beanType, id);
        
        closeSession(session);
        return (T)instantie; 
    }
    
    
    @Override 
    public List<T> read(PK id, T t) { // voeg een beantype in als parameter. en krijg een list terug van een gekoppelde klasse
        
        log.info(beanType.getSimpleName() + " via Id lijst opvragen uit de database");
        session = getSession();
        
        Query query = session.createSQLQuery("select from " + t.getClass().getName() + " where " + beanType + "_"+ id );
        
        final List<T> lijst = query.list();
        closeSession(session);
        
        if (lijst.size() < 1 ){
            return null;
        } else {
            return lijst;
        }      
        
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> readAll() {
         // drie manieren om lijst op te halen; even kijken wat voor ons werkt.
        // je moet er een op een
        
        String className = beanType.getSimpleName();
               
        log.info(className + ": Lijst met alle objecten ophalen");
        session = getSession();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM " + className.toUpperCase() );
        //query.addEntity(className + ".class");
        final List<T> objects = (List<T>) query.list();
        
            if (objects.isEmpty()){
                System.out.println("leeg");
            }
            else {
                System.out.println("niet leeg");
            }
        
        return (List<T>)objects;
    } 
        
    

   @Override // .update();
    public void update(T t) {
        
        log.info(beanType.getSimpleName() + " Object update.");
        session = getSession();        
        session.update(t);        
        closeSession(session);       
    }

    
    @Override // boolean als returntype?
    public void delete(T t) {
        log.info(beanType.getSimpleName() + " Object delete.");
        session = getSession();         
        session.delete(t);        
        closeSession(session);      
    }

    @Override // boolean als returntype?
    public void deleteById(PK id) {
        
        log.info(beanType.getSimpleName() + " Object delete.");
        session = getSession();   
        session.delete(id); // delete van primarykey of gehele entity?
        closeSession(session);
    }

    @Override // boolean als return type? of hoeveeel entities er zijn verwijderd
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  


    


}

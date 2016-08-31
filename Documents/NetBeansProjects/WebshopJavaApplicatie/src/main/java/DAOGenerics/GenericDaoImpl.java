


package DAOGenerics;

import DAOGenerics.GenericDaoInterface;
import Helpers.HibernateSessionFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    protected Class<T> beanType;
    
    
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.beanType = ((Class) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);
//        Type t = getClass().getGenericSuperclass();
//        ParameterizedType pt = (ParameterizedType) t;
//        beanType = (Class) pt.getActualTypeArguments()[0];
    }
    
    
    // CRUD methodes //     
    
    @Override // -- werkt
    public long insert(T t, Session session) {        
        log.info(beanType.getSimpleName() + " creeeren in de database. Return id");
        //session = getSession();
        
        long id = (Long) session.save(t);        
        
        //commitSession(session);
        //closeSession(session);
        return id;           
    }
    
    
    @Override // -- werkt
    public T readById(PK id, Session session) {

        System.out.println(beanType.getSimpleName() + " via Id lezen uit de database");
        //session = getSession();
        
        T instantie = (T)session.get(beanType, id);
        
//        commitSession(session);
//        closeSession(session);
        return (T)instantie; 
    }
    
    
    @Override 
    public <T> List<T> read(PK id, T t, Session session) { // voeg een beantype in als parameter. en krijg een list terug van een gekoppelde klasse
        
        log.info(beanType.getSimpleName() + " via Id lijst opvragen uit de database");
        //session = getSession();
        
        Query query = session.createSQLQuery("select from " + t.getClass().getName() + " where " + beanType + "_"+ id );
        
        final List<T> lijst = (List<T>)query.list();
        //closeSession(session);
        
        if (lijst.size() < 1 ){
            return null;
        } else {
            return lijst;
        }      
        
    }

    @SuppressWarnings("unchecked")
    @Override // -- werkt
    public <T> List<T> readAll(Class<T> type, Session session){
         // drie manieren om lijst op te halen; even kijken wat voor ons werkt.
        // je moet er een op een
        
        String className = beanType.getSimpleName();
               
        log.info(className + ": Lijst met alle objecten ophalen");
        //session = getSession();
        
        final Criteria crit = session.createCriteria(type);
        return crit.list();
        
    }  
//    

   @Override // -- werkt
    public void update(T t, Session session) {
        
        log.info(beanType.getSimpleName() + " Object update.");
        //session = getSession();        
        session.update(t);        
        //closeSession(session);       
    }

    
    @Override // boolean als returntype?
    public void delete(T t, Session session) {
        log.info(beanType.getSimpleName() + " Object delete.");
        //session = getSession();         
        session.delete(t);        
        //closeSession(session);      
    }

    @Override //-- werkt 
    public void deleteById(PK id, Session session) {
        
        log.info(beanType.getSimpleName() + " Object delete.");
        //session = getSession();   
        session.delete(id); // delete van primarykey of gehele entity?
        //closeSession(session);
    }

    @Override // boolean als return type? of hoeveeel entities er zijn verwijderd
    public void deleteAll(Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

  


    


}

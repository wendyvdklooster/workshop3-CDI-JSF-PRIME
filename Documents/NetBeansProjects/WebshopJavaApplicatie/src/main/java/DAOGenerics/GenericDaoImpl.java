


package DAOGenerics;



import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger; 

/**
 * @author Wendy
 * @param <T>
 * @param <PK>
 */

public abstract class GenericDaoImpl <T, PK extends Serializable> implements GenericDaoInterface <T, PK> { 
    
    private static final Logger log = LoggerFactory.getLogger(GenericDaoImpl.class);
    
    // data fields //
    protected Class<T> beanType;
    
    // constructor //
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.beanType = ((Class) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);
    }
    
    
    // **CRUD methodes** //     
    
    @Override // -- werkt
    public long insert(T t, Session session) {        
        log.info(beanType.getSimpleName() + " creeeren in de database. Return id");
        
        long id = (Long) session.save(t);        
        
        return id;           
    }
    
    
    @Override // -- werkt
    public T readById(PK id, Session session) {
        System.out.println(beanType.getSimpleName() + " via Id lezen uit de database");
        
        T instantie = (T)session.get(beanType, id);
        
        return (T)instantie; 
    }
    
    
    @Override // -- nog niet getest  >> bv adressen behorende bij klant ophalen
    public <T> List<T> read(PK id, Class<T> type, Session session) {         
        log.info(beanType.getSimpleName() + " via Id lijst opvragen uit de database");
        
        Query query = session.createSQLQuery("select from " + type + " where " + beanType + "_ID" + id );
        
        final List<T> lijst = (List<T>)query.list();
        
        if (lijst.size() < 1 ){
            return null;
        } else {
            return lijst;
        } 
    }

    @SuppressWarnings("unchecked")
    @Override // -- werkt
    public <T> List<T> readAll(Class<T> type, Session session){ 
        log.info(beanType.getSimpleName()+ ": Lijst met alle objecten ophalen");       
        
        final Criteria crit = session.createCriteria(type);
        return crit.list();        
    }  
   

   @Override // -- werkt
    public void update(T t, Session session) {        
        log.info(beanType.getSimpleName() + " Object update.");  
        
        session.update(t);                 
    }

    
    @Override // -- nog  neit getest. overbodig??
    public boolean delete(T t, Session session) {        
       log.info(beanType.getSimpleName() + " Object delete.");   
       
       if (t != null){
         session.delete(t); 
         return true;
       }       
        return false;  
    }

    @Override //-- werkt 
    public boolean deleteById(PK id, Session session) {        
        log.info(beanType.getSimpleName() + " Object delete."); 
        
        Object persistanceInstance = session.get(beanType, id);
        if (persistanceInstance != null){
           session.delete(persistanceInstance); 
           return true;
        }       
            return false;       
    }    

    @Override // -- werkt
    public int deleteAll(Class<T> type, Session session) {
        log.info(beanType.getSimpleName() + "delete all"); 
        int count = 0; 
               
         final List<T> instances = session.createCriteria(type).list();
            for (Object object : instances) {
                count++;
            session.delete(object);
            }        
        return count;         
    }

    

  


    


}

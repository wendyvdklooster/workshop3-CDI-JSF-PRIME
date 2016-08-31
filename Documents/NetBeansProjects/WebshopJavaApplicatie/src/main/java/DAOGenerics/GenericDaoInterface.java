/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOGenerics;


import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author Wendy
 * @param <T>
 * @param <PK>
 */

// PK primary key

public interface GenericDaoInterface <T, PK extends Serializable>{
   
    // alle dao methoden? 
    long insert(T t, Session session);
    
    T readById(PK id, Session session);
    <T>List<T> read(PK id, T t, Session session);
    <T>List<T> readAll(Class<T> type, Session session);
    
    void update(T t, Session session);
    
    boolean delete(T t, Session session);
    boolean deleteById(PK id, Session session);
    int deleteAll(Class<T> type, Session session);
    
    
}

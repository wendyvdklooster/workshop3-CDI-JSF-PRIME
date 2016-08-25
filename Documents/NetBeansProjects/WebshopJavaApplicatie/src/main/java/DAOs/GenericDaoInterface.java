/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Wendy
 * @param <E>
 * @param <PK>
 */

// PK primary key

public interface GenericDaoInterface <T, PK extends Serializable>{
   
    // alle dao methoden? 
    T create(T e);
    
    T readById(PK id);
    List<T> read(T t);
    List<T> readAll();
    
    T update(T t);
    
    void delete(T t);
    void deleteById(PK id);
    void deleteAll();
    
    
}

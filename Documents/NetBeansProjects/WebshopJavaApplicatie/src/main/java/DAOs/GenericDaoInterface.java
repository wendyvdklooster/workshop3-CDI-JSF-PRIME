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

public interface GenericDaoInterface <E, PK extends Serializable>{
   
    // alle dao methoden? 
    E create(E e);
    
    E readById(PK id);
    List<E> read(E e);
    List<E> readAll();
    
    E update(E e);
    
    void delete(E e);
    void deleteById(PK id);
    void deleteAll();
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;


import DAOGenerics.GenericDaoImpl;
import POJO.Klant;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anne
 */
public class KlantDao extends GenericDaoImpl <Klant, Long>{
    
    private static final Logger log = LoggerFactory.getLogger(ArtikelDao.class);
    
    public KlantDao() {
        super();
    }
    
    
     
    
}

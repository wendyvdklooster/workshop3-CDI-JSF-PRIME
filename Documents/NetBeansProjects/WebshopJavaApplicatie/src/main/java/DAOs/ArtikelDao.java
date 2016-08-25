


package DAOs;

import POJO.Artikel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author Wendy
 */

public class ArtikelDao extends GenericDaoImpl <Artikel, Long> {

    
private static final Logger log = LoggerFactory.getLogger(ArtikelDao.class);
 
    //Artikel artikel;
    
    public ArtikelDao(){
        super();
    }

    public ArtikelDao(Class<Artikel> type) {
        super(type);
    }

   
    

}

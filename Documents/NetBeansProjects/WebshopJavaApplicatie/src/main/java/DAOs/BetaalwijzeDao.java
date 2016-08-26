


package DAOs;

import DAOGenerics.GenericDaoImpl;
import POJO.Betaalwijze;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BetaalwijzeDao  extends GenericDaoImpl <Betaalwijze, Long>{

private static final Logger log = LoggerFactory.getLogger(Betaalwijze.class);
    
    public BetaalwijzeDao(){
        super();
    }

}

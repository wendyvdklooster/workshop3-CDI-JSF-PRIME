


package DAOs;

import DAOGenerics.GenericDaoImpl;
import POJO.Factuur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class FactuurDao  extends GenericDaoImpl <Factuur, Long>{

private static final Logger log = LoggerFactory.getLogger(FactuurDao.class);

    public FactuurDao(){
        super();
    }

}

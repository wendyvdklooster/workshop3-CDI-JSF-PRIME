


package DAOs;

import DAOGenerics.GenericDaoImpl;
import POJO.Betaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BetalingDao  extends GenericDaoImpl <Betaling, Long> {

private static final Logger log = LoggerFactory.getLogger(BetalingDao.class);

    public BetalingDao(){
        super();
    }

}

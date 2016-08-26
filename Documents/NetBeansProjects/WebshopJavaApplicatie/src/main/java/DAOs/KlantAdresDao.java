


package DAOs;

import DAOGenerics.GenericDaoImpl;
import POJO.KlantAdres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class KlantAdresDao  extends GenericDaoImpl <KlantAdres, Long> {

private static final Logger log = LoggerFactory.getLogger(KlantAdresDao.class);

    public KlantAdresDao(){
        super();
    }

}

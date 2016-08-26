


package DAOs;

import DAOGenerics.GenericDaoImpl;
import POJO.Account;
import POJO.Klant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class AccountDao extends GenericDaoImpl <Account, Long>{

private static final Logger log = LoggerFactory.getLogger(AccountDao.class);


    public AccountDao(){
        super();
    }

}

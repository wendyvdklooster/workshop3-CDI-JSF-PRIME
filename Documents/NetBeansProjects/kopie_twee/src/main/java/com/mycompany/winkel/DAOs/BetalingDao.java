


package com.mycompany.winkel.DAOs;

import com.mycompany.winkel.DAOGenerics.GenericDaoImpl;
import com.mycompany.winkel.POJO.Betaling;
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

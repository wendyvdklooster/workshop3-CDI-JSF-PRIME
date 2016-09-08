


package com.mycompany.winkel.DAOs;

import com.mycompany.winkel.DAOGenerics.GenericDaoImpl;
import com.mycompany.winkel.POJO.Bestelling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class BestellingDao  extends GenericDaoImpl <Bestelling, Long> {

private static final Logger log = LoggerFactory.getLogger(BestellingDao.class);

    public BestellingDao(){
        super();
    }
}

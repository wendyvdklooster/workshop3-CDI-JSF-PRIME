


package com.mycompany.winkel.DAOs;

import com.mycompany.winkel.DAOGenerics.GenericDaoImpl;
import com.mycompany.winkel.POJO.AdresType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class AdresTypeDao extends GenericDaoImpl <AdresType, Long> {

private static final Logger log = LoggerFactory.getLogger(AdresTypeDao.class);

    public AdresTypeDao(){
        super();
    }
}

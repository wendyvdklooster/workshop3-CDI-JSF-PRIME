


package com.mycompany.winkel.DAOs;

import com.mycompany.winkel.DAOGenerics.GenericDaoImpl;
import com.mycompany.winkel.POJO.BestellingArtikel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Wendy
 */
//@Repository
public class BestellingArtikelDao  extends GenericDaoImpl <BestellingArtikelDao, Long>{

private static final Logger log = LoggerFactory.getLogger(BestellingArtikelDao.class);

    public BestellingArtikelDao(){
        super();
    }

}

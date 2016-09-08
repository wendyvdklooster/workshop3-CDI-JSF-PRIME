


package com.mycompany.winkel.DAOs;

import com.mycompany.winkel.DAOGenerics.GenericDaoImpl;
import com.mycompany.winkel.POJO.KlantAdres;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Wendy
 */

public class KlantAdresDao  extends GenericDaoImpl <KlantAdres, Long> {

private static final Logger log = LoggerFactory.getLogger(KlantAdresDao.class);


    @Autowired
    private SessionFactory sessionFactory;
    
    public KlantAdresDao(){
        super();
    }

}

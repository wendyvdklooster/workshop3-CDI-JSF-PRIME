


package com.mycompany.winkel.DAOs;

import com.mycompany.winkel.DAOGenerics.GenericDaoImpl;
import com.mycompany.winkel.POJO.Betaalwijze;
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

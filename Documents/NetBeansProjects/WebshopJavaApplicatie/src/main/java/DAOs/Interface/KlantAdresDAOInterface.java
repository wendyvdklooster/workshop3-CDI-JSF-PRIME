/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Interface;

import POJO.Adres;
import POJO.Klant;
import POJO.KlantAdres;
import java.util.ArrayList;

/**
 *
 * @author Wendy
 */
public interface KlantAdresDAOInterface {
    public ArrayList<KlantAdres> findAll() ;
    public ArrayList<Klant> findKlantByAdresId(long adresId) ;
    public ArrayList<Adres> findAdresByKlantId(long klantId);    
    public boolean insertKlantAdres(long klantId, long adresId);        
    public boolean deleteAll();
    public boolean deleteKlantAdresByAdresId(long adresId);
    public int deleteKlantAdresByKlantId(long klantId);
    
}


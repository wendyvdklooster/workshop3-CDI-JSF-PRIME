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
    public ArrayList<Klant> findKlantByAdresId(int bestelling_id) ;
    public ArrayList<Adres> findAdresByKlantId(int artikel_id);    
    public boolean insertKlantAdres(int klantId, int adresId);        
    public boolean deleteAll();
    public boolean deleteKlantAdresByAdresId(int adresId);
    
}


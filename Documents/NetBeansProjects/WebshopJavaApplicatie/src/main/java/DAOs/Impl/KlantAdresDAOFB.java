/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

import DAOs.Interface.KlantAdresDAOInterface;
import POJO.Adres;
import POJO.Klant;
import POJO.KlantAdres;
import java.util.ArrayList;

/**
 *
 * @author Wendy
 */
public class KlantAdresDAOFB implements KlantAdresDAOInterface{

    @Override
    public ArrayList<KlantAdres> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Klant> findKlantByAdresId(int bestelling_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Adres> findAdresByKlantId(int artikel_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertKlantAdres(int klantId, int adresId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteKlantAdresByAdresId(int adresId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteKlantAdresByKlantId(int klantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

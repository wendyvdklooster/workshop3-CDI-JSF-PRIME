/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

import DAOs.Interface.BestellingArtikelDAOInterface;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingArtikel;
import java.util.ArrayList;

/**
 *
 * @author Wendy
 */
public class BestellingArtikelDAOFB implements BestellingArtikelDAOInterface {

    @Override
    public ArrayList<BestellingArtikel> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Artikel> findByBestellingId(int bestelling_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Bestelling> findBestellingByArtikelId(int artikel_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createBestellingArtikel(BestellingArtikel bestellingArtikel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBestellingArtikelAantal(int bestelling_id, int artikel_id, int newArtikel_aantal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findAantalByArtikelID(int bestelling_id, int artikel_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteArtikel(int bestelling_id, int artikel_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBestellingArtikel(int bestelling_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

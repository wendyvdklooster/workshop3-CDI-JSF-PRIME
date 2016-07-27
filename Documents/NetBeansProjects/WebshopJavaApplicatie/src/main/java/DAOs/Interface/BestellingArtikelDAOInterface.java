/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Interface;

import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingArtikel;
import java.util.ArrayList;

/**
 *
 * @author Excen
 */

public interface BestellingArtikelDAOInterface {
    
    public ArrayList<BestellingArtikel> findAll();
    public ArrayList<Artikel> findByBestellingId(int bestelling_id) ;
    public ArrayList<Bestelling> findBestellingByArtikelId(int artikel_id);
    public void createBestellingArtikel(BestellingArtikel bestellingArtikel) ;
    public void updateBestellingArtikelAantal(int bestelling_id, int artikel_id, int newArtikel_aantal) ;
    public int findAantalByArtikelID(int bestelling_id, int artikel_id) ;
    public void deleteAll();
    public void deleteArtikel(int bestelling_id, int artikel_id) ;
    public void deleteBestellingArtikel(int bestelling_id) ;
    
}
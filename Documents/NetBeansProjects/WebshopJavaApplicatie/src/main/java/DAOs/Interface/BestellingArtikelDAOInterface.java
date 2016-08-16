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
    public ArrayList<Artikel> findByBestellingId(long bestelling_id) ;
    public ArrayList<Bestelling> findBestellingByArtikelId(long artikel_id);
    public void createBestellingArtikel(BestellingArtikel bestellingArtikel) ;
    public void updateBestellingArtikelAantal(long bestelling_id, long artikel_id, int newArtikel_aantal) ;
    public int findAantalByArtikelID(long bestelling_id, long artikel_id) ;
    public void deleteAll();
    public void deleteArtikel(long bestelling_id, long artikel_id) ;
    public void deleteBestellingArtikel(long bestelling_id) ;
    
}
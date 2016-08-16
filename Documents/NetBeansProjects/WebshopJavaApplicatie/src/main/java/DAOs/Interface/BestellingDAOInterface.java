/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Interface;


import POJO.Bestelling;
import java.util.ArrayList;
/**
 *
 * @author Excen
 * 
 * EH: Maak hier een abstract class van. Dan kan je een aantal connection opzet
 * dingen in die abstract class afhandelen
 */
public interface BestellingDAOInterface {
    
    public ArrayList<Bestelling> findAll();
    public Bestelling findById(long bestelling_id);
    public int insertBestelling(long klant_id);
    public boolean deleteBestelling(long bestelling_id);
    public void deleteAll();  
    public ArrayList<Bestelling> findByKlantId(long klantId);
}


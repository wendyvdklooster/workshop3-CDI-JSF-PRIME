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
 */
public interface BestellingDAOInterface {
    
    public ArrayList<Bestelling> findAll();
    public Bestelling findById(int bestelling_id);
    public int insertBestelling(int klant_id);
    public void deleteBestelling(int bestelling_id);
    public void deleteAll();  
}


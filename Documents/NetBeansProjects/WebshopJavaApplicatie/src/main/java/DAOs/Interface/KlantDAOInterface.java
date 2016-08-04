/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Interface;

import POJO.Klant;
import java.util.ArrayList;
/**
 *
 * @author Wendy
 */
public interface KlantDAOInterface {
    
    public ArrayList <Klant> findAllKlanten();
    public Klant findByKlantId(int klantId) ;
    public ArrayList<Klant> findByVoorNaamAchterNaam(String voorNaam, String achterNaam);            
    public ArrayList<Klant> findByEmail(String email);    
    public Klant insertKlant(Klant klant) ;    
    public boolean deleteByKlantId(int klantId) ;     
    public int deleteAll() ; 
    public Klant updateGegevens(Klant klant);

    /*public int[] addBatchKlanten() throws Exception; >> later bij tijd over
    public void vulVoornaamLijst ();
    public void vulAchternaamLijst ();
    public void vulTussenvoegselLijst ();*/
   
}

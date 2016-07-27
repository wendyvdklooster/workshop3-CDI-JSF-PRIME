/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Interface;
import POJO.Adres;
import java.util.ArrayList;

/**
 *
 * @author Wendy
 */
public interface AdresDAOInterface {
    
    public ArrayList <Adres> findAllAdresses();
    public Adres findByAdresID(int adresId);
    public Adres findByStraatNaam(String straatNaam);
    public Adres findByPostcodeHuisNummer(String postCode, int huisNummer);
    public Adres findByWoonplaats(String woonPlaats);    
    public Adres insertAdres(Adres adres);
    public boolean deleteAdres(int adresId);
    public boolean deleteAll();
    
    //public ArrayList<Adres> findByKlantId (int klantId) throws Exception;
}

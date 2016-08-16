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
    public Adres findByAdresID(long adresId);
    public ArrayList<Adres> findByStraatNaam(String straatNaam);
    public ArrayList<Adres> findByPostcodeHuisNummer(String postCode, int huisNummer);
    public ArrayList<Adres> findByWoonplaats(String woonPlaats);    
    public Adres insertAdres(Adres adres);
    public boolean deleteAdres(long adresId);
    public boolean deleteAll();
    public Adres updateGegevens(Adres adres);
    
    //public ArrayList<Adres> findByKlantId (int klantId) throws Exception;
}

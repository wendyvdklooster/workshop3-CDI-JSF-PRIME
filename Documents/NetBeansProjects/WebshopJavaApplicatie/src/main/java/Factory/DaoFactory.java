/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Controller.HoofdMenuController;
import DAOs.Impl.AdresDAOFB;
import DAOs.Impl.AdresDAOSQL;
import DAOs.Impl.ArtikelDAOFB;
import DAOs.Impl.ArtikelDAOSQL;
import DAOs.Impl.BestellingArtikelDAOFB;
import DAOs.Impl.BestellingArtikelDAOSQL;
import DAOs.Impl.BestellingDAOFB;
import DAOs.Impl.BestellingDAOSQL;
import DAOs.Impl.KlantAdresDAOFB;
import DAOs.Impl.KlantAdresDAOSQL;
import DAOs.Impl.KlantDAOFB;
import DAOs.Impl.KlantDAOJSON;
import DAOs.Impl.KlantDAOSQL;
import DAOs.Impl.KlantDAOXML;
import DAOs.Interface.AdresDAOInterface;
import DAOs.Interface.ArtikelDAOInterface;
import DAOs.Interface.BestellingArtikelDAOInterface;
import DAOs.Interface.BestellingDAOInterface;
import DAOs.Interface.KlantAdresDAOInterface;
import DAOs.Interface.KlantDAOInterface;

/**
 *
 * @author Anne
 */
public class DaoFactory {
    
  
  
    private static String databaseSetting = "MySQL";
    
   
    public void setDatabaseSetting(String databaseSetting) {
        this.databaseSetting = databaseSetting;
    }
    
    public String getDatabaseSetting() {
        return databaseSetting;
    }
    
    public static KlantDAOInterface getKlantDao() {
        if (databaseSetting.equals("MySQL")) {
           return new KlantDAOSQL(); 
        }
        else if (databaseSetting.equals("FireBird")) {
            return new KlantDAOFB();
        }
        else if (databaseSetting.equals("JSON")) {
            return new KlantDAOJSON();
        }
        else if (databaseSetting.equals("XML")) {
            return new KlantDAOXML();
        }
        else
            return new KlantDAOSQL();
    }
    
    public static ArtikelDAOInterface getArtikelDao() {
        if (databaseSetting.equals("MySQL")) {
           return new ArtikelDAOSQL(); 
        }
        else if (databaseSetting.equals("FireBird")) {
            return new ArtikelDAOFB();
        }
        else
            return new ArtikelDAOSQL();
    }
    
     public static BestellingDAOInterface getBestellingDao() {
        if (databaseSetting.equals("MySQL")) {
           return new BestellingDAOSQL(); 
        }
        else if (databaseSetting.equals("FireBird")) {
            return new BestellingDAOFB();
        }
        else
            return new BestellingDAOSQL();
    }
     
     public static AdresDAOInterface getAdresDao() {
        if (databaseSetting.equals("MySQL")) {
           return new AdresDAOSQL(); 
        }
        else if (databaseSetting.equals("FireBird")) {
            return new AdresDAOFB();
        }
        else
            return new AdresDAOSQL();
    }
     
     public static BestellingArtikelDAOInterface getBestellingArtikelDao() {
        if (databaseSetting.equals("MySQL")) {
           return new BestellingArtikelDAOSQL(); 
        }
        else if (databaseSetting.equals("FireBird")) {
            return new BestellingArtikelDAOFB();
        }
        else
            return new BestellingArtikelDAOSQL();
    }
     
     public static KlantAdresDAOInterface getKlantAdresDao() {
        if (databaseSetting.equals("MySQL")) {
           return new KlantAdresDAOSQL(); 
        }
        else if (databaseSetting.equals("FireBird")) {
            return new KlantAdresDAOFB();
        }
        else
            return new KlantAdresDAOSQL();
    }
}

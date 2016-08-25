/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Excen
 */
public class BestellingArtikel {
    private int bestellingId;
    private int artikelId;
    private int artikelAantal;
    
    // Constructor
    public BestellingArtikel(int bestellingId, int artikelId, int artikelAantal){
        this.bestellingId = bestellingId;
        this.artikelId = artikelId;
        this.artikelAantal = artikelAantal;
    }
    
    public BestellingArtikel(){
        
    }    
    
    /**
     * @return the bestellingId
     */
    public int getBestellingId() {
        return bestellingId;
    }

    /**
     * @param bestellingId the bestellingId to set
     */
    public void setBestellingId(int bestellingId) {
        this.bestellingId = bestellingId;
    }

    /**
     * @return the artikelId
     */
    public int getArtikelId() {
        return artikelId;
    }

    /**
     * @param artikelId the artikelId to set
     */
    public void setArtikelId(int artikelId) {
        this.artikelId = artikelId;
    }

    /**
     * @return the artikelAantal
     */
    public int getArtikelAantal() {
        return artikelAantal;
    }

    /**
     * @param artikelAantal the artikelAantal to set
     */
    public void setArtikelAantal(int artikelAantal) {
        this.artikelAantal = artikelAantal;
    }
    
    
    
    
    
    
}

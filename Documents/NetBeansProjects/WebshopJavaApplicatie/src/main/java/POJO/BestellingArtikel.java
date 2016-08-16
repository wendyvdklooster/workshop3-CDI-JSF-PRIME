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
    private long bestellingId;
    private long artikelId;
    private int artikelAantal;
    
    // Constructor
    public BestellingArtikel(long bestellingId, long artikelId, int artikelAantal){
        this.bestellingId = bestellingId;
        this.artikelId = artikelId;
        this.artikelAantal = artikelAantal;
    }
    
    public BestellingArtikel(){
        
    }    
    
    /**
     * @return the bestellingId
     */
    public long getBestellingId() {
        return bestellingId;
    }

    /**
     * @param bestellingId the bestellingId to set
     */
    public void setBestellingId(long bestellingId) {
        this.bestellingId = bestellingId;
    }

    /**
     * @return the artikelId
     */
    public long getArtikelId() {
        return artikelId;
    }

    /**
     * @param artikelId the artikelId to set
     */
    public void setArtikelId(long artikelId) {
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

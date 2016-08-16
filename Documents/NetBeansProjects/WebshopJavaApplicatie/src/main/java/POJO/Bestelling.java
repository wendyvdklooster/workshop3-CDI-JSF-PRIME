package POJO;

import java.util.Date;

/**
 *
 * @author Excen
 */
public class Bestelling {
    
    private long bestellingId;
    private int klantId;
    private Date bestellingDatum;
    
    // Constructor
    public Bestelling(long bestellingId, int klantId){
        this.bestellingId = bestellingId;
        this.klantId = klantId;
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(bestellingDatum.getTime());
    }
    
    public Bestelling(){
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(bestellingDatum.getTime());
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
     * @return the klantId
     */
    public int getKlantId() {
        return klantId;
    }

    /**
     * @param klantId the klantId to set
     */
    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    /**
     * @return the datum
     */
    public Date getDatum() {
        return bestellingDatum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(Date datum) {
        this.bestellingDatum = datum;
    }
    
    
    
    
   




}

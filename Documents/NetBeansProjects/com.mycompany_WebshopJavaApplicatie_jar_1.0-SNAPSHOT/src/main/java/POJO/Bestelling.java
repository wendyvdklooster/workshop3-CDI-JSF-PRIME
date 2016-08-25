package POJO;

import java.util.Date;

/**
 *
 * @author Excen
 */
public class Bestelling {
    
    private int bestellingId;
    private int klantId;
    private Date datum;
    
    // Constructor
    public Bestelling(int bestellingId, int klantId){
        this.bestellingId = bestellingId;
        this.klantId = klantId;
        datum = new Date();
        Date sqlDatum = new java.sql.Date(datum.getTime());
    }
    
    public Bestelling(){
        datum = new Date();
        Date sqlDatum = new java.sql.Date(datum.getTime());
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
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }
    
    
    
    
   




}

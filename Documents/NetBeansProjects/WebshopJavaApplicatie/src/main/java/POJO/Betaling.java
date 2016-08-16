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
public class Betaling {

private long betalingId;
private java.util.Date betaaldatum;
// public Betaalwijze betalingswijze;  
private Klant klant;
private Factuur factuur;
private String betalingsGegevens;

    /**
     * @return the BetalingId
     */
    public long getBetalingId() {
        return betalingId;
    }

    /**
     * @param BetalingId the BetalingId to set
     */
    public void setBetalingId(long BetalingId) {
        this.betalingId = BetalingId;
    }

    /**
     * @return the betaaldatum
     */
    public java.util.Date getBetaaldatum() {
        return betaaldatum;
    }

    /**
     * @param betaaldatum the betaaldatum to set
     */
    public void setBetaaldatum(java.util.Date betaaldatum) {
        this.betaaldatum = betaaldatum;
    }

    /**
     * @return the klant
     */
    public Klant getKlant() {
        return klant;
    }

    /**
     * @param klant the klant to set
     */
    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    /**
     * @return the factuur
     */
    public Factuur getFactuur() {
        return factuur;
    }

    /**
     * @param factuur the factuur to set
     */
    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }

    /**
     * @return the betalingsGegevens
     */
    public String getBetalingsGegevens() {
        return betalingsGegevens;
    }

    /**
     * @param betalingsGegevens the betalingsGegevens to set
     */
    public void setBetalingsGegevens(String betalingsGegevens) {
        this.betalingsGegevens = betalingsGegevens;
    }
    
    
}




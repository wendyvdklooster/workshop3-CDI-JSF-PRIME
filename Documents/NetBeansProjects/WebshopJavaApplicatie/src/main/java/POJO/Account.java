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
public class Account {
  
private long accountId;
private String naam;
private Klant klant;
private java.util.Date creatieDatum;

    /**
     * @return the AccountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @param AccountId the AccountId to set
     */
    public void setAccountId(long AccountId) {
        this.accountId = AccountId;
    }

    /**
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @param naam the naam to set
     */
    public void setNaam(String naam) {
        this.naam = naam;
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
     * @return the creatieDatum
     */
    public java.util.Date getCreatieDatum() {
        return creatieDatum;
    }

    /**
     * @param creatieDatum the creatieDatum to set
     */
    public void setCreatieDatum(java.util.Date creatieDatum) {
        this.creatieDatum = creatieDatum;
    }
 
}

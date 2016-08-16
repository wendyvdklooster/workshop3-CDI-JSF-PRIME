/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Excen
 */
public class Factuur {    
    public long factuurId;
    public String factuurnummer;
    public java.util.Date factuurdatum;
    public Set<Betaling> betalingset;
    public Bestelling bestelling;

    public long getID() {
        return factuurId;
    }

    public void setID(long ID) {
        this.factuurId = ID;
    }

    public String getFactuurnummer() {
        return factuurnummer;
    }

    public void setFactuurnummer(String factuurnummer) {
        this.factuurnummer = factuurnummer;
    }

    public Date getFactuurdatum() {
        return factuurdatum;
    }

    public void setFactuurdatum(Date factuurdatum) {
        this.factuurdatum = factuurdatum;
    }

    public Set<Betaling> getBetalingset() {
        return betalingset;
    }

    public void setBetalingset(Set<Betaling> betalingset) {
        this.betalingset = betalingset;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
    
    
    
    
    
}

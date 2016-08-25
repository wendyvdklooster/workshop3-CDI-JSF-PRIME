/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Excen
 */
@Entity
@Table (name = "FACTUREN")
public class Factuur implements Serializable {   
    
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(unique = true, nullable = false, name = "FACTUUR_ID")
    private long Id;
    
    private String factuurnummer;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date factuurdatum;
    
    @OneToMany(mappedBy = "factuur")
    private Set<Betaling> betalingset;
    
    @OneToOne (fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    protected Bestelling bestelling;
    
    public long getID() {
        return Id;
    }

    public void setID(long ID) {
        this.Id = ID;
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

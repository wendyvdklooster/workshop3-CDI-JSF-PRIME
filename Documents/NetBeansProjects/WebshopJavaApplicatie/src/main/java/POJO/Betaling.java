
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Excen
 */

@Entity
@Table(name = "BETALINGEN" )
public class Betaling implements Serializable {

@Id
@GeneratedValue(strategy = AUTO)
@Column(unique = true, nullable = false)
private long Id;

@Temporal(javax.persistence.TemporalType.DATE)
private java.util.Date betaaldatum;

public Betaalwijze betalingswijze;  

@ManyToOne
private Klant klant;

    @ManyToOne
    private Factuur factuur;

private String betalingsGegevens;

    /**
     * @return the BetalingId
     */
    public long getBetalingId() {
        return Id;
    }

    /**
     * @param BetalingId the BetalingId to set
     */
    public void setBetalingId(long BetalingId) {
        this.Id = BetalingId;
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




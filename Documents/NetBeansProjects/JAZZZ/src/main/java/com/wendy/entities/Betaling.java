/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "betaling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Betaling.findAll", query = "SELECT b FROM Betaling b"),
    @NamedQuery(name = "Betaling.findById", query = "SELECT b FROM Betaling b WHERE b.id = :id"),
    @NamedQuery(name = "Betaling.findByBetaaldatum", query = "SELECT b FROM Betaling b WHERE b.betaaldatum = :betaaldatum"),
    @NamedQuery(name = "Betaling.findByBetaalwijze", query = "SELECT b FROM Betaling b WHERE b.betaalwijze = :betaalwijze"),
    @NamedQuery(name = "Betaling.findByBetalingsGegevens", query = "SELECT b FROM Betaling b WHERE b.betalingsGegevens = :betalingsGegevens")})
public class Betaling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "betaaldatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date betaaldatum;
    @Size(max = 255)
    @Column(name = "betaalwijze")
    private String betaalwijze;
    @Size(max = 255)
    @Column(name = "betalingsGegevens")
    private String betalingsGegevens;
    @JoinColumn(name = "KLANT_ID", referencedColumnName = "KLANT_ID")
    @ManyToOne
    private Klant klantId;
    @JoinColumn(name = "FACTUUR_ID", referencedColumnName = "FACTUUR_ID")
    @ManyToOne(optional = false)
    private Factuur factuurId;

    public Betaling() {
    }

    public Betaling(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBetaaldatum() {
        return betaaldatum;
    }

    public void setBetaaldatum(Date betaaldatum) {
        this.betaaldatum = betaaldatum;
    }

    public String getBetaalwijze() {
        return betaalwijze;
    }

    public void setBetaalwijze(String betaalwijze) {
        this.betaalwijze = betaalwijze;
    }

    public String getBetalingsGegevens() {
        return betalingsGegevens;
    }

    public void setBetalingsGegevens(String betalingsGegevens) {
        this.betalingsGegevens = betalingsGegevens;
    }

    public Klant getKlantId() {
        return klantId;
    }

    public void setKlantId(Klant klantId) {
        this.klantId = klantId;
    }

    public Factuur getFactuurId() {
        return factuurId;
    }

    public void setFactuurId(Factuur factuurId) {
        this.factuurId = factuurId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Betaling)) {
            return false;
        }
        Betaling other = (Betaling) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.Betaling[ id=" + id + " ]";
    }
    
}

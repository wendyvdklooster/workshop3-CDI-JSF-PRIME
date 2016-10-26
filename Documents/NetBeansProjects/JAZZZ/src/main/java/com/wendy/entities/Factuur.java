/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "factuur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factuur.findAll", query = "SELECT f FROM Factuur f"),
    @NamedQuery(name = "Factuur.findByFactuurId", query = "SELECT f FROM Factuur f WHERE f.factuurId = :factuurId"),
    @NamedQuery(name = "Factuur.findByFactuurdatum", query = "SELECT f FROM Factuur f WHERE f.factuurdatum = :factuurdatum"),
    @NamedQuery(name = "Factuur.findByFactuurnummer", query = "SELECT f FROM Factuur f WHERE f.factuurnummer = :factuurnummer")})
public class Factuur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FACTUUR_ID")
    private Long factuurId;
    @Column(name = "factuurdatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factuurdatum;
    @Size(max = 255)
    @Column(name = "factuurnummer")
    private String factuurnummer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factuurId")
    private Collection<Betaling> betalingCollection;
    @JoinColumn(name = "KLANT_ID", referencedColumnName = "KLANT_ID")
    @ManyToOne
    private Klant klantId;
    @JoinColumn(name = "BESTELLING_ID", referencedColumnName = "BESTELLING_ID")
    @ManyToOne
    private Bestelling bestellingId;

    public Factuur() {
    }

    public Factuur(Long factuurId) {
        this.factuurId = factuurId;
    }

    public Long getFactuurId() {
        return factuurId;
    }

    public void setFactuurId(Long factuurId) {
        this.factuurId = factuurId;
    }

    public Date getFactuurdatum() {
        return factuurdatum;
    }

    public void setFactuurdatum(Date factuurdatum) {
        this.factuurdatum = factuurdatum;
    }

    public String getFactuurnummer() {
        return factuurnummer;
    }

    public void setFactuurnummer(String factuurnummer) {
        this.factuurnummer = factuurnummer;
    }

    @XmlTransient
    public Collection<Betaling> getBetalingCollection() {
        return betalingCollection;
    }

    public void setBetalingCollection(Collection<Betaling> betalingCollection) {
        this.betalingCollection = betalingCollection;
    }

    public Klant getKlantId() {
        return klantId;
    }

    public void setKlantId(Klant klantId) {
        this.klantId = klantId;
    }

    public Bestelling getBestellingId() {
        return bestellingId;
    }

    public void setBestellingId(Bestelling bestellingId) {
        this.bestellingId = bestellingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factuurId != null ? factuurId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factuur)) {
            return false;
        }
        Factuur other = (Factuur) object;
        if ((this.factuurId == null && other.factuurId != null) || (this.factuurId != null && !this.factuurId.equals(other.factuurId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.Factuur[ factuurId=" + factuurId + " ]";
    }
    
}

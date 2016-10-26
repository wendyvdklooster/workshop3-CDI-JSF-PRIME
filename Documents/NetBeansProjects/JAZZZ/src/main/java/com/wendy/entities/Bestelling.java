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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "bestelling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bestelling.findAll", query = "SELECT b FROM Bestelling b"),
    @NamedQuery(name = "Bestelling.findByBestellingId", query = "SELECT b FROM Bestelling b WHERE b.bestellingId = :bestellingId"),
    @NamedQuery(name = "Bestelling.findByBestellingDatum", query = "SELECT b FROM Bestelling b WHERE b.bestellingDatum = :bestellingDatum")})
public class Bestelling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BESTELLING_ID")
    private Long bestellingId;
    @Column(name = "bestellingDatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bestellingDatum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bestelling")
    private Collection<Bestellingartikel> bestellingartikelCollection;
    @JoinColumn(name = "KLANT_ID", referencedColumnName = "KLANT_ID")
    @ManyToOne
    private Klant klantId;
    @OneToMany(mappedBy = "bestellingId", cascade = CascadeType.PERSIST)
    private Collection<Factuur> factuurCollection;

    public Bestelling() {
    }

    public Bestelling(Long bestellingId) {
        this.bestellingId = bestellingId;
    }

    public Long getBestellingId() {
        return bestellingId;
    }

    public void setBestellingId(Long bestellingId) {
        this.bestellingId = bestellingId;
    }

    public Date getBestellingDatum() {
        return bestellingDatum;
    }

    public void setBestellingDatum(Date bestellingDatum) {
        this.bestellingDatum = bestellingDatum;
    }

    @XmlTransient
    public Collection<Bestellingartikel> getBestellingartikelCollection() {
        return bestellingartikelCollection;
    }

    public void setBestellingartikelCollection(Collection<Bestellingartikel> bestellingartikelCollection) {
        this.bestellingartikelCollection = bestellingartikelCollection;
    }

    public Klant getKlantId() {
        return klantId;
    }

    public void setKlantId(Klant klantId) {
        this.klantId = klantId;
    }

    @XmlTransient
    public Collection<Factuur> getFactuurCollection() {
        return factuurCollection;
    }

    public void setFactuurCollection(Collection<Factuur> factuurCollection) {
        this.factuurCollection = factuurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bestellingId != null ? bestellingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bestelling)) {
            return false;
        }
        Bestelling other = (Bestelling) object;
        if ((this.bestellingId == null && other.bestellingId != null) || (this.bestellingId != null && !this.bestellingId.equals(other.bestellingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.Bestelling[ bestellingId=" + bestellingId + " ]";
    }
    
}

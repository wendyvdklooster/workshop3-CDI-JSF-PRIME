/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "artikel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"),
    @NamedQuery(name = "Artikel.findByArtikelId", query = "SELECT a FROM Artikel a WHERE a.artikelId = :artikelId"),
    @NamedQuery(name = "Artikel.findByArtikelnaam", query = "SELECT a FROM Artikel a WHERE a.artikelnaam = :artikelnaam"),
    @NamedQuery(name = "Artikel.findByArtikelnr", query = "SELECT a FROM Artikel a WHERE a.artikelnr = :artikelnr"),
    @NamedQuery(name = "Artikel.findByArtikelPrijs", query = "SELECT a FROM Artikel a WHERE a.artikelPrijs = :artikelPrijs"),
    @NamedQuery(name = "Artikel.findByOmschrijving", query = "SELECT a FROM Artikel a WHERE a.omschrijving = :omschrijving")})
public class Artikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTIKEL_ID")
    private Long artikelId;
    @Size(max = 255)
    @Column(name = "ARTIKELNAAM")
    private String artikelnaam;
    @Size(max = 255)
    @Column(name = "ARTIKELNR")
    private String artikelnr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "artikelPrijs")
    private double artikelPrijs;
    @Size(max = 255)
    @Column(name = "omschrijving")
    private String omschrijving;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel")
    private Collection<Bestellingartikel> bestellingartikelCollection;

    public Artikel() {
    }

    public Artikel(Long artikelId) {
        this.artikelId = artikelId;
    }

    public Artikel(Long artikelId, double artikelPrijs) {
        this.artikelId = artikelId;
        this.artikelPrijs = artikelPrijs;
    }

    public Artikel(String artikelnaam, double artikelPrijs, String omschrijving) {
        this.artikelnaam = artikelnaam;
        this.artikelPrijs = artikelPrijs;
        this.omschrijving = omschrijving;
    }
    
    

    public Long getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Long artikelId) {
        this.artikelId = artikelId;
    }

    public String getArtikelnaam() {
        return artikelnaam;
    }

    public void setArtikelnaam(String artikelnaam) {
        this.artikelnaam = artikelnaam;
    }

    public String getArtikelnr() {
        return artikelnr;
    }

    public void setArtikelnr(String artikelnr) {
        this.artikelnr = artikelnr;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    @XmlTransient
    public Collection<Bestellingartikel> getBestellingartikelCollection() {
        return bestellingartikelCollection;
    }

    public void setBestellingartikelCollection(Collection<Bestellingartikel> bestellingartikelCollection) {
        this.bestellingartikelCollection = bestellingartikelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelId != null ? artikelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikel)) {
            return false;
        }
        Artikel other = (Artikel) object;
        if ((this.artikelId == null && other.artikelId != null) || (this.artikelId != null && !this.artikelId.equals(other.artikelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.Artikel[ artikelId=" + artikelId + " ]";
    }
    
}

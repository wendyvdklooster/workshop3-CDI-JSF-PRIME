/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "bestellingartikel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bestellingartikel.findAll", query = "SELECT b FROM Bestellingartikel b"),
    @NamedQuery(name = "Bestellingartikel.findByArtikelAantal", query = "SELECT b FROM Bestellingartikel b WHERE b.artikelAantal = :artikelAantal"),
    @NamedQuery(name = "Bestellingartikel.findByBestellingartikelId", query = "SELECT b FROM Bestellingartikel b WHERE b.bestellingartikelId = :bestellingartikelId"),    
    @NamedQuery(name = "Bestellingartikel.findByBestellingId", query = "SELECT b FROM Bestellingartikel b WHERE b.bestelling.bestellingId = :bestellingId"),
    @NamedQuery(name = "Bestellingartikel.findByArtikelId", query = "SELECT b FROM Bestellingartikel b WHERE b.artikel.artikelId = :artikelId")})
public class Bestellingartikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BESTELLINGARTIKEL_ID")
    protected Long bestellingartikelId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "artikelAantal")
    private int artikelAantal;
    @JoinColumn(name = "ARTIKEL_ID", referencedColumnName = "ARTIKEL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Artikel artikel;
    @JoinColumn(name = "BESTELLING_ID", referencedColumnName = "BESTELLING_ID")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Bestelling bestelling;

    public Bestellingartikel() {
    }

    public Bestellingartikel(Long bestellingartikelId) {
        this.bestellingartikelId = bestellingartikelId;
    }

    public Bestellingartikel(Long bestellingartikelId, int artikelAantal, Artikel artikel, Bestelling bestelling) {
        this.bestellingartikelId = bestellingartikelId;
        this.artikelAantal = artikelAantal;
        this.artikel = artikel;
        this.bestelling = bestelling;
    }

    public Bestellingartikel(Artikel artikel, int artikelAantal ) {
        this.artikelAantal = artikelAantal;
        this.artikel = artikel;
    }
 
     public Bestellingartikel(int artikelAantal, Artikel artikel, Bestelling bestelling) {
        this.artikelAantal = artikelAantal;
        this.artikel = artikel;
        this.bestelling = bestelling;
    }

    public int getArtikelAantal() {
        return artikelAantal;
    }

    public void setArtikelAantal(int artikelAantal) {
        this.artikelAantal = artikelAantal;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bestellingartikelId != null ? bestellingartikelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bestellingartikel)) {
            return false;
        }
        Bestellingartikel other = (Bestellingartikel) object;
        if ((this.bestellingartikelId == null && other.bestellingartikelId != null) || (this.bestellingartikelId != null && !this.bestellingartikelId.equals(other.bestellingartikelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.Bestellingartikel[ bestellingartikelPK=" + bestellingartikelId + " ]";
    }
    
}

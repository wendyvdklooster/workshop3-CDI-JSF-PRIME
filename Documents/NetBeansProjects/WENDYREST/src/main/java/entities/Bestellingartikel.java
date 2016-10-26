/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
    @NamedQuery(name = "Bestellingartikel.findByBestellingartikelId", query = "SELECT b FROM Bestellingartikel b WHERE b.bestellingartikelId = :bestellingartikelId"),
    @NamedQuery(name = "Bestellingartikel.findByArtikelAantal", query = "SELECT b FROM Bestellingartikel b WHERE b.artikelAantal = :artikelAantal")})
public class Bestellingartikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BESTELLINGARTIKEL_ID")
    private Long bestellingartikelId;
    @Column(name = "artikelAantal")
    private Integer artikelAantal;
    @JoinColumn(name = "ARTIKEL_ID", referencedColumnName = "ARTIKEL_ID")
    @ManyToOne
    private Artikel artikelId;
    @JoinColumn(name = "BESTELLING_ID", referencedColumnName = "BESTELLING_ID")
    @ManyToOne
    private Bestelling bestellingId;

    public Bestellingartikel() {
    }

    public Bestellingartikel(Long bestellingartikelId) {
        this.bestellingartikelId = bestellingartikelId;
    }

    public Long getBestellingartikelId() {
        return bestellingartikelId;
    }

    public void setBestellingartikelId(Long bestellingartikelId) {
        this.bestellingartikelId = bestellingartikelId;
    }

    public Integer getArtikelAantal() {
        return artikelAantal;
    }

    public void setArtikelAantal(Integer artikelAantal) {
        this.artikelAantal = artikelAantal;
    }

    public Artikel getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Artikel artikelId) {
        this.artikelId = artikelId;
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
        return "entities.Bestellingartikel[ bestellingartikelId=" + bestellingartikelId + " ]";
    }
    
}

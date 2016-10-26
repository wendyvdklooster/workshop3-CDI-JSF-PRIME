/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Wendy
 */
@Embeddable
public class BestellingartikelPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "BESTELLING_ID")
    private long bestellingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTIKEL_ID")
    private long artikelId;

    public BestellingartikelPK() {
    }

    public BestellingartikelPK(long bestellingId, long artikelId) {
        this.bestellingId = bestellingId;
        this.artikelId = artikelId;
    }

    public long getBestellingId() {
        return bestellingId;
    }

    public void setBestellingId(long bestellingId) {
        this.bestellingId = bestellingId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(long artikelId) {
        this.artikelId = artikelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) bestellingId;
        hash += (int) artikelId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BestellingartikelPK)) {
            return false;
        }
        BestellingartikelPK other = (BestellingartikelPK) object;
        if (this.bestellingId != other.bestellingId) {
            return false;
        }
        if (this.artikelId != other.artikelId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.BestellingartikelPK[ bestellingId=" + bestellingId + ", artikelId=" + artikelId + " ]";
    }
    
}

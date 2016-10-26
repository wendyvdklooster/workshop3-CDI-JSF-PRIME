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
public class KlantadresPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ADRES_ID")
    private long adresId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KLANT_ID")
    private long klantId;

    public KlantadresPK() {
    }

    public KlantadresPK(long adresId, long klantId) {
        this.adresId = adresId;
        this.klantId = klantId;
    }

    public long getAdresId() {
        return adresId;
    }

    public void setAdresId(long adresId) {
        this.adresId = adresId;
    }

    public long getKlantId() {
        return klantId;
    }

    public void setKlantId(long klantId) {
        this.klantId = klantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) adresId;
        hash += (int) klantId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KlantadresPK)) {
            return false;
        }
        KlantadresPK other = (KlantadresPK) object;
        if (this.adresId != other.adresId) {
            return false;
        }
        if (this.klantId != other.klantId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.entities.KlantadresPK[ adresId=" + adresId + ", klantId=" + klantId + " ]";
    }
    
}

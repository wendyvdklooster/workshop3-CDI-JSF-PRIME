/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "klantadres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klantadres.findAll", query = "SELECT k FROM Klantadres k"),
    @NamedQuery(name = "Klantadres.findByCreatedBy", query = "SELECT k FROM Klantadres k WHERE k.createdBy = :createdBy"),
    @NamedQuery(name = "Klantadres.findByCreatedDate", query = "SELECT k FROM Klantadres k WHERE k.createdDate = :createdDate"),
    @NamedQuery(name = "Klantadres.findByKlantadresId", query = "SELECT k FROM Klantadres k WHERE k.klantadresId = :klantadresId"),
    @NamedQuery(name = "Klantadres.findByAdresId", query = "SELECT k FROM Klantadres k WHERE k.adres.adresId = :adresId"),
    @NamedQuery(name = "Klantadres.findByKlantId", query = "SELECT k FROM Klantadres k WHERE k.klant.klantId = :klantId")
})
public class Klantadres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "KLANTADRES_ID")
    protected Long klantadresId;
    @Size(max = 255)
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "KLANT_ID", referencedColumnName = "KLANT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Klant klant;
    @JoinColumn(name = "ADRES_ID", referencedColumnName = "ADRES_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Adres adres;

    public Klantadres() {
    }

    public Klantadres(Long klantadresId) {
        this.klantadresId = klantadresId;
    }

    public Klantadres(Long klantadresId, String createdBy, Date createdDate, Klant klant, Adres adres) {
        this.klantadresId = klantadresId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.klant = klant;
        this.adres = adres;
    }

    
    public Long getKlantadresId() {
        return klantadresId;
    }

    public void setKlantadresId(Long klantadresId) {
        this.klantadresId = klantadresId;
    }

   
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (klantadresPK != null ? klantadresPK.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Klantadres)) {
//            return false;
//        }
//        Klantadres other = (Klantadres) object;
//        if ((this.klantadresPK == null && other.klantadresPK != null) || (this.klantadresPK != null && !this.klantadresPK.equals(other.klantadresPK))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "com.wendy.entities.Klantadres[ klantadresId=" + klantadresId + " ]";
    }
    
}

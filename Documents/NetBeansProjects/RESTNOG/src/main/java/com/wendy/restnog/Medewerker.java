/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.restnog;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "medewerker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medewerker.findAll", query = "SELECT m FROM Medewerker m"),
    @NamedQuery(name = "Medewerker.findByMedewerkerId", query = "SELECT m FROM Medewerker m WHERE m.medewerkerId = :medewerkerId"),
    @NamedQuery(name = "Medewerker.findByEmail", query = "SELECT m FROM Medewerker m WHERE m.email = :email"),
    @NamedQuery(name = "Medewerker.findByFunctie", query = "SELECT m FROM Medewerker m WHERE m.functie = :functie"),
    @NamedQuery(name = "Medewerker.findByNaam", query = "SELECT m FROM Medewerker m WHERE m.naam = :naam")})
public class Medewerker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MEDEWERKER_ID")
    private Long medewerkerId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "functie")
    private String functie;
    @Size(max = 255)
    @Column(name = "naam")
    private String naam;

    public Medewerker() {
    }

    public Medewerker(Long medewerkerId) {
        this.medewerkerId = medewerkerId;
    }

    public Long getMedewerkerId() {
        return medewerkerId;
    }

    public void setMedewerkerId(Long medewerkerId) {
        this.medewerkerId = medewerkerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medewerkerId != null ? medewerkerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medewerker)) {
            return false;
        }
        Medewerker other = (Medewerker) object;
        if ((this.medewerkerId == null && other.medewerkerId != null) || (this.medewerkerId != null && !this.medewerkerId.equals(other.medewerkerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.restnog.Medewerker[ medewerkerId=" + medewerkerId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.restnog;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wendy
 */
@Entity
@Table(name = "klant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klant.findAll", query = "SELECT k FROM Klant k"),
    @NamedQuery(name = "Klant.findByKlantId", query = "SELECT k FROM Klant k WHERE k.klantId = :klantId"),
    @NamedQuery(name = "Klant.findByAccountId", query = "SELECT k FROM Klant k WHERE k.accountId = :accountId"),
    @NamedQuery(name = "Klant.findByAchternaam", query = "SELECT k FROM Klant k WHERE k.achternaam = :achternaam"),
    @NamedQuery(name = "Klant.findByEmail", query = "SELECT k FROM Klant k WHERE k.email = :email"),
    @NamedQuery(name = "Klant.findByKlantNummer", query = "SELECT k FROM Klant k WHERE k.klantNummer = :klantNummer"),
    @NamedQuery(name = "Klant.findByTussenvoegsel", query = "SELECT k FROM Klant k WHERE k.tussenvoegsel = :tussenvoegsel"),
    @NamedQuery(name = "Klant.findByVoornaam", query = "SELECT k FROM Klant k WHERE k.voornaam = :voornaam")})
public class Klant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KLANT_ID")
    private Long klantId;
    @Column(name = "ACCOUNT_ID")
    private BigInteger accountId;
    @Size(max = 255)
    @Column(name = "achternaam")
    private String achternaam;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "klantNummer")
    private String klantNummer;
    @Size(max = 255)
    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;
    @Size(max = 255)
    @Column(name = "voornaam")
    private String voornaam;
    @OneToMany(mappedBy = "klantId")
    private Collection<Betaling> betalingCollection;
    @OneToMany(mappedBy = "klantId")
    private Collection<Bestelling> bestellingCollection;
    @OneToMany(mappedBy = "klantId")
    private Collection<Factuur> factuurCollection;
    @OneToMany(mappedBy = "klantId")
    private Collection<Account> accountCollection;
    @OneToMany(mappedBy = "klantId")
    private Collection<Klantadres> klantadresCollection;

    public Klant() {
    }

    public Klant(Long klantId) {
        this.klantId = klantId;
    }

    public Long getKlantId() {
        return klantId;
    }

    public void setKlantId(Long klantId) {
        this.klantId = klantId;
    }

    public BigInteger getAccountId() {
        return accountId;
    }

    public void setAccountId(BigInteger accountId) {
        this.accountId = accountId;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKlantNummer() {
        return klantNummer;
    }

    public void setKlantNummer(String klantNummer) {
        this.klantNummer = klantNummer;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @XmlTransient
    public Collection<Betaling> getBetalingCollection() {
        return betalingCollection;
    }

    public void setBetalingCollection(Collection<Betaling> betalingCollection) {
        this.betalingCollection = betalingCollection;
    }

    @XmlTransient
    public Collection<Bestelling> getBestellingCollection() {
        return bestellingCollection;
    }

    public void setBestellingCollection(Collection<Bestelling> bestellingCollection) {
        this.bestellingCollection = bestellingCollection;
    }

    @XmlTransient
    public Collection<Factuur> getFactuurCollection() {
        return factuurCollection;
    }

    public void setFactuurCollection(Collection<Factuur> factuurCollection) {
        this.factuurCollection = factuurCollection;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @XmlTransient
    public Collection<Klantadres> getKlantadresCollection() {
        return klantadresCollection;
    }

    public void setKlantadresCollection(Collection<Klantadres> klantadresCollection) {
        this.klantadresCollection = klantadresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klantId != null ? klantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klant)) {
            return false;
        }
        Klant other = (Klant) object;
        if ((this.klantId == null && other.klantId != null) || (this.klantId != null && !this.klantId.equals(other.klantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wendy.restnog.Klant[ klantId=" + klantId + " ]";
    }
    
}

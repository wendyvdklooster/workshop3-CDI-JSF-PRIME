/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 *
 * @author Excen
 */
@Entity
@Table(name = "ADRESSEN", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"huisnummer","toevoeging","postcode"}) })
public class Adres implements Serializable {       
    
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(unique = true, nullable = false, name = "ADRES_ID")
    private Long Id;
        
    @Column(nullable = false)
    private String straatnaam;
    @Column(nullable = false, length = 6)
    private String postcode;
    private String toevoeging;
    @Column(nullable = false)
    private int huisnummer;  
    @Column(nullable = false)
    private String woonplaats;  
    //default? bij niets invullen => bezorg en factuuradres.. 
    private int adresType; 
    
    @ManyToMany(mappedBy = "adressen")
    protected Set<Klant> klanten = new HashSet<>();
    
    @OneToMany(mappedBy = "adres")
    protected Set<KlantAdres> klantAdressen = new HashSet<>();

    
    public Set<Klant> getKlanten() {
        return klanten;
    }

    public void setKlanten(Set<Klant> klanten) {
        this.klanten = klanten;
    }
    
 
    public Adres(){
    }
    
    public Adres(String straatnaam, String postcode, 
            String toevoeging, int huisnummer, String woonplaats) {         
        this.straatnaam = straatnaam;
        this.postcode = postcode;
        this.toevoeging = toevoeging;
        this.huisnummer = huisnummer;
        this.woonplaats = woonplaats;         
    } 
   
    
    /*public static Adres getInstance(){
        return new Adres();
    }*/
    
    public long getId(){
        return Id;
    }
    
    public String getStraatnaam() {
        return straatnaam;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public int getHuisnummer() {
        return huisnummer;
    }
    
    public String getWoonplaats() {
        return woonplaats;
    }
    
    public void setId(long Id) {
        this.Id = Id;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
   
       
    public Adres(AdresBuilder adresBuilder){
        this.Id = adresBuilder.Id;
        this.straatnaam = adresBuilder.straatnaam;
        this.postcode = adresBuilder.postcode;
        this.toevoeging = adresBuilder.toevoeging;
        this.huisnummer = adresBuilder.huisnummer;
        this.woonplaats = adresBuilder.woonplaats;
    }

    /**
     * @return the adresType
     */
    public int getAdresType() {
        return adresType;
    }

    /**
     * @param adresType the adresType to set
     */
    public void setAdresType(int adresType) {
        this.adresType = adresType;
    }
    
    public static class AdresBuilder {       
            
        private long Id;
        private String straatnaam;
        private String postcode;
        private String toevoeging;
        private int huisnummer;
        private String woonplaats;       
    
        public AdresBuilder(){
            
        }

        public void setId(long Id) {
            this.Id = Id;
        }
        
        public AdresBuilder(long Id){
            this.Id = Id;
        }
        
        public AdresBuilder adresId(long adresId){
            this.Id = adresId;
                return this;
        }
        
        public AdresBuilder straatnaam(String straatnaam){
           this.straatnaam = straatnaam;
                return this; 
        }
        
        public AdresBuilder postcode (String postcode){
            this.postcode = postcode;
                return this;        
        }
        
        public AdresBuilder toevoeging (String toevoeging){
            this.toevoeging = toevoeging; 
                return this; 
        }
        
        public AdresBuilder huisnummer (int huisnummer){
            this.huisnummer = huisnummer;
                return this;
        }
        
        public AdresBuilder woonplaats (String woonplaats){
            this.woonplaats = woonplaats;
                return this;
        }
        
        public Adres build (){
            return new Adres (this);
        }       
        
        
    }  
  
    
}

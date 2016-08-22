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
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 *
 * @author Excen
 */
@Entity
@Table(name = "ADRESSEN")
//@Embeddable
public class Adres implements Serializable {   
    
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;
        
//    @Column(nullable = false)
    private String straatnaam;
//    @Column(nullable = false, length = 6)
    private String postcode;
    private String toevoeging;
    private int huisnummer;    
    private String woonplaats;
    
    @ManyToMany(mappedBy = "klanten")
    protected Set<Klant> klanten = new HashSet<>();
    
    
    public Adres(AdresBuilder adresBuilder){
        this.Id = adresBuilder.Id;
        this.straatnaam = adresBuilder.straatnaam;
        this.postcode = adresBuilder.postcode;
        this.toevoeging = adresBuilder.toevoeging;
        this.huisnummer = adresBuilder.huisnummer;
        this.woonplaats = adresBuilder.woonplaats;
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

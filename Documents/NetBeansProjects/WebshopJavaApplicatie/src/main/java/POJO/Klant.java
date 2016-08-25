package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Wendy
 */

@Entity
@Table(name = "KLANTEN", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = { "klantnummer", "voornaam", "achternaam", "tussenvoegsel"} )
})  // nog iets met account > constraint?
public class Klant implements Serializable {
    

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(unique = true, nullable = false, name = "KLANT_ID")
    private Long Id;
    @Column(nullable = false)
    private String klantNummer; 
    @Column(nullable = false)
    private String voornaam;
    @Column(nullable = false)
    private String achternaam;
    private String tussenvoegsel;
    @Column(nullable = false)
    private String email;     
     
    @OneToMany 
    protected Set<Account> accounts = new HashSet<>();    
    
    @OneToMany (fetch = FetchType.LAZY)
    protected Set<Betaling> betalingen = new HashSet<>();     
    
    @OneToMany (mappedBy = "klant")
    protected Set<KlantAdres> klantAdressen = new HashSet<>();    
    
    @OneToMany (mappedBy = "klant", fetch = FetchType.LAZY)
    protected Set <Bestelling> bestellingen = new HashSet<>();

    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "KLANT_ADRES",
            joinColumns = @JoinColumn (name = "KLANT_ID"),
            inverseJoinColumns = @JoinColumn (name = "ADRES_ID")
    )
    protected Set<Adres> adressen = new HashSet<>();    

    
    //protected Map<Adres, AdresType> adresType; 
//(of andere manieren om de ternaire relatie

    
    
    //constructors
    public Klant(){        
    }
    
    public Klant(long klantId, String voornaam, String achternaam, 
             String tussenvoegsel, String email){   
        this.Id = klantId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;        
    }
        
     public Klant(long klantId, String voornaam, String achternaam, 
             String tussenvoegsel, String email, Set<Bestelling> bestellingen, Set<Adres> adressen){   
        this.Id = klantId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;   
        this.bestellingen = bestellingen; 
        this.adressen = adressen;
    }
    
    public Set<Adres> getAdressen() {
        return adressen;
    }

    public void setAdressen(Set<Adres> adressen) {
        this.adressen = adressen;
    }
    
    public void setKlantId(Long klantId) {
        this.Id = klantId;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
   
    public Long getKlantId() {
        return Id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getEmail() {
        return email;
    }    
  

    public Set<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(Set<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }

    
    
     public Klant (KlantBuilder builder){
        this.Id = builder.klantId;
        this.voornaam = builder.voornaam;
        this.achternaam = builder.achternaam; 
        this.tussenvoegsel = builder.tussenvoegsel;
        this.email = builder.email;       
    }

    /**
     * @return the klantNummer
     */
    public String getKlantNummer() {
        return klantNummer;
    }

    /**
     * @param klantNummer the klantNummer to set
     */
    public void setKlantNummer(String klantNummer) {
        this.klantNummer = klantNummer;
    }

   
    
    public static class KlantBuilder {
        private long klantId;
        private String voornaam;
        private String achternaam;
        private String tussenvoegsel;
        private String email;       
    
        public KlantBuilder(){
        }
        
        public KlantBuilder klantId(long klantId){
            this.klantId = klantId;
                return this;
        }
    
        public KlantBuilder voornaam(String voornaam){
            this.voornaam = voornaam;
                return this;
        }
        
        public KlantBuilder achternaam(String achternaam){
            this.achternaam = achternaam;
                return this;
        }
        
        public KlantBuilder tussenvoegsel(String tussenvoegsel){
            this.tussenvoegsel = tussenvoegsel;
                return this;
        }
        
        public KlantBuilder email(String email){
            this.email = email;
                return this;
        }       
        
        public Klant build(){
            return new Klant(this);
        }
        
        
}
}
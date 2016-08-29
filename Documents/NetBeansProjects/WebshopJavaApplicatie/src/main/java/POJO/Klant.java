package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "KLANT")
public class Klant implements Serializable, Iterable <Klant>{
    
    @GenericGenerator(name = "klantGenerator",strategy = "foreign",
        parameters = @Parameter(name = "property", value = "adres"))
    @Id
    @GeneratedValue(generator = "klantGenerator")        
    private long Id;
    private String klantNummer;
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
    
    public void setId(Long Id) {
        this.Id = Id;
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
   
    public Long getId() {
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


    @Override
    public Iterator<Klant> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     
}

    
    

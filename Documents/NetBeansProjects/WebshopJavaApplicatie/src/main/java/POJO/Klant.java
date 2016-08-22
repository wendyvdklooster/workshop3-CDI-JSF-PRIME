package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
		@UniqueConstraint(columnNames = "email") })
public class Klant implements Serializable {
    
    @GenericGenerator(name = "klantGenerator",strategy = "foreign",
        parameters = @Parameter(name = "property", value = "adres"))
    @Id   
    @GeneratedValue(generator = "klantGenerator")        
    private long klantId;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String email;      
   
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "KLANT_ADRES",
            joinColumns = @JoinColumn (name = "KLANT_ID"),
            inverseJoinColumns = @JoinColumn (name = "ADRES_ID")
    )
    protected Set<Adres> adressen = new HashSet<>();    
    
    public Klant(){        
    }
    
     public Klant(long klantId, String voornaam, String achternaam, String tussenvoegsel, String email){   
        this.klantId = klantId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;        
    }
    
    
    public Klant (KlantBuilder builder){
        this.klantId = builder.klantId;
        this.voornaam = builder.voornaam;
        this.achternaam = builder.achternaam; 
        this.tussenvoegsel = builder.tussenvoegsel;
        this.email = builder.email;       
    }
    
    
    public void setKlantId(long klantId) {
        this.klantId = klantId;
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
   
    public long getKlantId() {
        return klantId;
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
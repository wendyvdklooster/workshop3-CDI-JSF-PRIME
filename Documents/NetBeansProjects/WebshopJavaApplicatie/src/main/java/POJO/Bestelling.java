package POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Excen
 */
@Entity
@Table(name = "BESTELLINGEN")
public class Bestelling implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (name = "BESTELLING_ID")
    private long Id;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "KLANT_ID")
    private Klant klant;   
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bestellingDatum;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "BESTELLING_ARTIKEL",
            joinColumns = @JoinColumn (name = "BESTELLING_ID"),
            inverseJoinColumns = @JoinColumn (name = "ARTIKEL_ID")
    )
    protected Set<Artikel> artikellen = new HashSet<>();    
    
    @OneToOne (mappedBy = "bestelling")
    protected Factuur factuur; 
    
    
    
    // Constructor
       
    public Bestelling(long bestellingId,  Klant klant){
        this.Id = bestellingId;
        this.klant = klant;
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(bestellingDatum.getTime());
    }
    
    public Bestelling(){
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(bestellingDatum.getTime());
    }    
    
    /**
     * @return the Id
     */
    public long getBestellingId() {
        return Id;
    }

    /**
     * @param bestellingId the Id to set
     */
    public void setBestellingId(long bestellingId) {
        this.Id = bestellingId;
    }

    /**
     * @return the klantId
     */
   

    /**
     * @return the datum
     */
    public Date getDatum() {
        return bestellingDatum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(Date datum) {
        this.bestellingDatum = datum;
    }

    /**
     * @return the klant
     */
    public Klant getKlant() {
        return klant;
    }

    /**
     * @param klant the klant to set
     */
    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    
    
    
    
   




}

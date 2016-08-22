package POJO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Excen
 */
@Entity
@Table(name = "BESTELLINGEN")
public class Bestelling {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long bestellingId;
    private int klantId;
    private Date bestellingDatum;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "BESTELLING_ARTIKEL",
            joinColumns = @JoinColumn (name = "BESTELLING_ID"),
            inverseJoinColumns = @JoinColumn (name = "ARTIKEL_ID")
    )
    protected Set<Artikel> artikellen = new HashSet<>();
    
    
    
    // Constructor
    public Bestelling(long bestellingId, int klantId){
        this.bestellingId = bestellingId;
        this.klantId = klantId;
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(bestellingDatum.getTime());
    }
    
    public Bestelling(){
        bestellingDatum = new Date();
        Date sqlDatum = new java.sql.Date(bestellingDatum.getTime());
    }    
    
    /**
     * @return the bestellingId
     */
    public long getBestellingId() {
        return bestellingId;
    }

    /**
     * @param bestellingId the bestellingId to set
     */
    public void setBestellingId(long bestellingId) {
        this.bestellingId = bestellingId;
    }

    /**
     * @return the klantId
     */
    public int getKlantId() {
        return klantId;
    }

    /**
     * @param klantId the klantId to set
     */
    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

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
    
    
    
    
   




}

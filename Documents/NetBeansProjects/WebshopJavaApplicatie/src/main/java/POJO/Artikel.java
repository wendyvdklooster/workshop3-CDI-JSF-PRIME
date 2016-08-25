package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ARTIKELLEN", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ARTIKELNR"),
                @UniqueConstraint(columnNames = "ARTIKELNAAM")})
public class Artikel implements Serializable{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "ARTIKEL_ID")
    private long Id; 
    @Column (name = "ARTIKELNR")
    private String artikelNummmer; 
    @Column (name = "ARTIKELNAAM")
    private String artikelNaam;
    private String omschrijving; 
    private double artikelPrijs;
    
    @ManyToMany(mappedBy = "artikellen")
    private Set<Bestelling> bestellingen = new HashSet<>();
    
    
    
    // constructors
    public Artikel(){
    }
    
    public Artikel(Long artikelId, String artikelNaam, double artikelPrijs) {
        this.Id = artikelId;
        this.artikelNaam = artikelNaam;
        this.artikelPrijs = artikelPrijs;
    }

    public Long getArtikelId() {
        return Id;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setArtikelId(Long artikelID) {
        this.Id = artikelID;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }

    /**
     * @return the artikelNummmer
     */
    public String getArtikelnummmer() {
        return artikelNummmer;
    }

    /**
     * @param artikelnummmer the artikelNummmer to set
     */
    public void setArtikelnummmer(String artikelnummmer) {
        this.artikelNummmer = artikelnummmer;
    }

    /**
     * @return the omschrijving
     */
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     * @param omschrijving the omschrijving to set
     */
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    /**
     * @return the bestellingen
     */
    public Set<Bestelling> getBestellingen() {
        return bestellingen;
    }

    /**
     * @param bestellingen the bestellingen to set
     */
    public void setBestellingen(Set<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
    
}

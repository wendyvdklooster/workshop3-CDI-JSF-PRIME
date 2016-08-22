package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ARTIKELLEN")
public class Artikel implements Serializable{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long artikelId;
    private String artikelNaam;
    private double artikelPrijs;
    
    @ManyToMany(mappedBy = "artikellen")
    protected Set<Bestelling> bestellingen = new HashSet<>();
    
    public Artikel(){
    }
    
    public Artikel(long artikelId, String artikelNaam, double artikelPrijs) {
        this.artikelId = artikelId;
        this.artikelNaam = artikelNaam;
        this.artikelPrijs = artikelPrijs;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setArtikelId(long artikelID) {
        this.artikelId = artikelID;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Excen
 */
public class BestellingArtikel {
    
    
    @Embeddable
    public static class Id implements Serializable {
       
        @Column (name = "BESTELLING_ID")
        private Long bestellingId;
        @Column (name = "ARTIKEL_ID")
        private Long artikelId; 
    
        public Id(){            
        }
        
        public Id(Long bestellingId, Long artikelId){
            this.bestellingId = bestellingId;
            this.artikelId = artikelId;
        }
    
        @Override
        public boolean equals (Object o){
            if (o != null && o instanceof Id){
                Id that = (Id) o;
                return this.artikelId.equals(that.artikelId) && 
                        this.bestellingId.equals(that.bestellingId);
            }
            return false; 
        }
        @Override
        public int hashCode(){
            return artikelId.hashCode() + bestellingId.hashCode();
        }
    }
    
    @EmbeddedId
    private Id Id = new Id();
    
    @ManyToOne
    @JoinColumn (name = "ARTIKEL_ID", insertable = false, updatable = false)
    private Artikel artikel;
    
    @ManyToOne
    @JoinColumn (name = "BESTELLING_ID", insertable = false, updatable = false)
    protected Bestelling bestelling;
    
    @Column 
    @NotNull
    private int artikelAantal;
    
    // Constructor
    public BestellingArtikel(Artikel artikel, Bestelling bestelling, int artikelAantal){        
        this.artikel = artikel;
        this.bestelling = bestelling;
        this.artikelAantal = artikelAantal;
    }
    
    public BestellingArtikel(){
        
    }    
  

    /**
     * @return the artikelAantal
     */
    public int getArtikelAantal() {
        return artikelAantal;
    }

    /**
     * @param artikelAantal the artikelAantal to set
     */
    public void setArtikelAantal(int artikelAantal) {
        this.artikelAantal = artikelAantal;
    }
    
    
    
    
    
    
}

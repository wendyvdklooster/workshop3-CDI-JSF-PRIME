

package POJO;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

/**
 *
 * @author Wendy
 */

@Entity
@Table (name ="KLANT_ADRES")
@Immutable
public class KlantAdres {
     
    @Embeddable
    public static class Id implements Serializable {
       
        @Column (name = "KLANT_ID")
        private Long klantId;
        @Column (name = "ADRES_ID")
        private Long adresId; 
    
        public Id(){            
        }
        
        public Id(Long klantId, Long adresId){
            this.klantId = klantId;
            this.adresId = adresId;
        }
    
        @Override
        public boolean equals (Object o){
            if (o != null && o instanceof Id){
                Id that = (Id) o;
                return this.adresId.equals(that.adresId) && 
                        this.klantId.equals(that.klantId);
            }
            return false; 
        }
        @Override
        public int hashCode(){
            return adresId.hashCode() + klantId.hashCode();
        }
    }
    
    @EmbeddedId
    protected Id id = new Id();
    
    @Column (updatable = false)
    @NotNull
    @Type(type="org.jadira.usertype.dateandtime.legacyjdk.PersistentDate")
    protected Date datumAangemaakt = new Date();
    
    @ManyToOne
    @JoinColumn (name = "KLANT_ID", insertable = false, updatable = false)
    protected Klant klant;
    
    @ManyToOne
    @JoinColumn (name = "ADRES_ID", insertable = false, updatable = false)
    protected Adres adres;
    
    
    public KlantAdres(Klant klant, Adres adres){       
        this.klant = klant;
        this.adres = adres;
        datumAangemaakt = new Date();
//        
//        this.id.klantId = klant.getKlantId();
//        this.id.adresId = adres.getId();
        
    }
    
    public KlantAdres(){  
        datumAangemaakt = new Date();
//        
//        this.id.klantId = klant.getKlantId();
//        this.id.adresId = adres.getId();
        
    }
    
} 
//    
//    
//    public KlantAdres(Long klantId, Long adresId) {
//        this.klantId = klantId;
//        this.adresId = adresId;        
//        }
//
//    public long getKlantId() {
//        return klantId;
//    }
//
//    public void setKlantId(long klantId) {
//        this.klantId = klantId;
//    }
//
//    public long getAdresId() {
//        return adresId;
//    }
//
//    public void setAdresId(long adresId) {
//        this.adresId = adresId;
//    }
    
    


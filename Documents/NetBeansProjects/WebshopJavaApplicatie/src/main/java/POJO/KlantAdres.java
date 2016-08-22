

package POJO;

/**
 *
 * @author Wendy
 */
public class KlantAdres {
     
    private long klantId;
    private long adresId;
    
    public KlantAdres(){       
    }
    
    public KlantAdres(long klantId, long adresId) {
        this.klantId = klantId;
        this.adresId = adresId;        
        }

    public long getKlantId() {
        return klantId;
    }

    public void setKlantId(long klantId) {
        this.klantId = klantId;
    }

    public long getAdresId() {
        return adresId;
    }

    public void setAdresId(long adresId) {
        this.adresId = adresId;
    }
    
    
}

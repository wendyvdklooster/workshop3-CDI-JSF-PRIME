
package POJO;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Wendy
 */


@Entity
@Table(name="BETAALWIJZE")
public class Betaalwijze implements Serializable {
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    private int Id;
    private String betaalwijze;
    private static String[] betaalWijzes = {"GoogleWallet","iDeal","Creditcard","PayPal","MoneyBookers","Natura"};
    
    
    public int getId(){
        return this.Id;
    }
    
    public void setId(int id){
        this.Id = id;
    }
    
    public String getBetaalwijze(){
        return this.betaalwijze;
    }
    
    protected void setBetaalwijze(String betaalwijze){
        this.betaalwijze = betaalwijze;
    }
    
    public void setBetaalwijze(int betaalwijze){
        this.betaalwijze = betaalWijzes[betaalwijze];
    }
   
    public String[] getAllBetaalWijzes(){
        return betaalWijzes;
    }
    
    private void setAllBetaalWijzes(String[] betaalWijzen){
        betaalWijzes = betaalWijzen;
    }
}
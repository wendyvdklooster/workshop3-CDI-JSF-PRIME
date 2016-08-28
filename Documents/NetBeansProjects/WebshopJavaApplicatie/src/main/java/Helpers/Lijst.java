


package Helpers;

import POJO.Adres;
import POJO.Klant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */

public class Lijst {

private static final Logger log = LoggerFactory.getLogger(Lijst.class);


    public static <T extends Iterable<E>, E> void print(List<T> list){
        
                System.out.println("klasse " + list.getClass());
                
                if(list instanceof Klant){
                    
                    } 
               }
            
                
                //printKlantenLijst((List<Klant>) list);
            //else if (element instanceof Adres)
                //printAdressenLijst((List<Adres>) list);
            




    
    //static nu hier in de main
    public static void printKlantenLijst(List<Klant> lijst){
        System.out.println();
        System.out.println("Lijst met opgevraagde klanten");
        System.out.printf("%-10s%-10s%-18s%-15s%-25s%-15s%n","KlantId", "KlantNummer", "Voornaam", "Tussenvoegsel", "Achternaam", "Email");
        //System.out.println("KlantId\t\tVoornaam\t\tTussenvoegsel\t\tAchternaam\t\tEmail");
        if (!lijst.isEmpty() ){    
            System.out.println("lijst is niet leeg");
            for (Klant k: lijst){
            
                
                
                
        
        }
            
//                    System.out.printf("%-10d%-10s%-18s%-15s%-25s%-15s%n",
//                            (((List<Klant>) lijst).get(i)).getId(),(lijst.get(i)).getKlantNummer(),(lijst.get(i)).getVoornaam(),
//                            (lijst.get(i)).getTussenvoegsel(),(lijst.get(i)).getAchternaam(),
//                            (lijst.get(i)).getEmail());            
                  
        }
        else {
            System.out.println("lege lijst");
        }
    }
    
}
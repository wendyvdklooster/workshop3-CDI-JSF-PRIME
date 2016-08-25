/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Excen
 */
public class Adres {
    
    private int adresId;
    private String straatnaam;
    private String postcode;
    private String toevoeging;
    private int huisnummer;
    private String woonplaats;
    
    public Adres(AdresBuilder adresBuilder){
        this.adresId = adresBuilder.adresId;
        this.straatnaam = adresBuilder.straatnaam;
        this.postcode = adresBuilder.postcode;
        this.toevoeging = adresBuilder.toevoeging;
        this.huisnummer = adresBuilder.huisnummer;
        this.woonplaats = adresBuilder.woonplaats;
    }
    
    public Adres() {         
    }
    
    /*public static Adres getInstance(){
        return new Adres();
    }*/
    
    public int getAdresId(){
        return adresId;
    }
    
    public String getStraatnaam() {
        return straatnaam;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public int getHuisnummer() {
        return huisnummer;
    }
    
    public String getWoonplaats() {
        return woonplaats;
    }

    
    public static class AdresBuilder {
        private int adresId;
        private String straatnaam;
        private String postcode;
        private String toevoeging;
        private int huisnummer;
        private String woonplaats;       
    
        public AdresBuilder(){
            
        }
        
        public AdresBuilder adresId(int adresId){
            this.adresId = adresId;
                return this;
        }
        
        public AdresBuilder straatnaam(String straatnaam){
           this.straatnaam = straatnaam;
                return this; 
        }
        
        public AdresBuilder postcode (String postcode){
            this.postcode = postcode;
                return this;        
        }
        
        public AdresBuilder toevoeging (String toevoeging){
            this.toevoeging = toevoeging; 
                return this; 
        }
        
        public AdresBuilder huisnummer (int huisnummer){
            this.huisnummer = huisnummer;
                return this;
        }
        
        public AdresBuilder woonplaats (String woonplaats){
            this.woonplaats = woonplaats;
                return this;
        }
        
        public Adres build (){
            return new Adres (this);
        }       
        
        
    }  
  
    
}

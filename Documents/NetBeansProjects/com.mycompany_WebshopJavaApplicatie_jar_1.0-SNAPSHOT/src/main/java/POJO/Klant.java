package POJO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wendy
 */
public class Klant {
    private int klantId;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String email;    
    
    public Klant (KlantBuilder builder){
        this.klantId = builder.klantId;
        this.voornaam = builder.voornaam;
        this.achternaam = builder.achternaam; 
        this.tussenvoegsel = builder.tussenvoegsel;
        this.email = builder.email;       
    }
    
    public Klant(){
        
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Klant(int klantId, String voornaam, String achternaam, String tussenvoegsel, String email){   
        this.klantId = klantId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;
    }

   
    public int getKlantId() {
        return klantId;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getEmail() {
        return email;
    }
   
    public static class KlantBuilder {
        private int klantId;
        private String voornaam;
        private String achternaam;
        private String tussenvoegsel;
        private String email;       
    
        public KlantBuilder(){
        }
        
        public KlantBuilder klantId(int klantId){
            this.klantId = klantId;
                return this;
        }
    
        public KlantBuilder voornaam(String voornaam){
            this.voornaam = voornaam;
                return this;
        }
        
        public KlantBuilder achternaam(String achternaam){
            this.achternaam = achternaam;
                return this;
        }
        
        public KlantBuilder tussenvoegsel(String tussenvoegsel){
            this.tussenvoegsel = tussenvoegsel;
                return this;
        }
        
        public KlantBuilder email(String email){
            this.email = email;
                return this;
        }       
        
        public Klant build(){
            return new Klant(this);
        }
        
        
}
}
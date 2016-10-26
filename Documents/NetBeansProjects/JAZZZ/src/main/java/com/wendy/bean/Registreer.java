/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.bean;

import com.wendy.entities.Account;
import com.wendy.entities.Adres;
import com.wendy.entities.Klant;
import com.wendy.entities.Klantadres;
import com.wendy.controller.AccountFacade;
import com.wendy.controller.AdresFacade;
import com.wendy.controller.KlantFacade;
import com.wendy.controller.KlantadresFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wendy
 */
    @FacesValidator("passwordValidator")
    @Named (value="registreer")
    @FlowScoped(value="registreer")
    public class Registreer implements Serializable, Validator{

            @Inject
	    private Account account;    
            @Inject
            private Klant klant;  
            @Inject
	    private Adres adres;
            @Inject
            private Klantadres ka; 
           
            @Inject
            transient AccountFacade accountFacade;    
            @Inject
            transient AdresFacade adresFacade;           
            @Inject
            transient KlantadresFacade klantadresFacade;            
            @Inject
            transient KlantFacade klantFacade; 
            
            private String voornaam; 
            private String tussenvoegsel;
            private String achternaam; 
            private String email;
            
            private String password; 
            private String username; 
            
            private String straatnaam;
            private String toevoeging; 
            private int huisnummer; 
            private String postcode; 
            private String woonplaats; 
            private Integer adresType; 
            private String adresTypeString; 
    
    public Klant getKlant(){
        return klant; 
    }    
    public Adres getAdres(){
        return adres;
    }
    public Account getAccount(){
        return account;
    }
    public Klantadres getKlantadres(){
        return ka; 
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getAdresTypeString() {        
        return adresTypeString;
    }
    
    
    public void setAdresTypeString(String adresTypeString) {
        this.adresTypeString = adresTypeString;
    }
         
    
            
    public String save(){
        // klant opslaan
        klant = new Klant();
        klant.setEmail(email);
        klant.setAchternaam(achternaam);
        klant.setTussenvoegsel(tussenvoegsel);
        klant.setVoornaam(voornaam);
        //klant.setKlantNummer(klantNummer);         
        klantFacade.create(klant);        
        
        account= new Account();
        account.setCreatieDatum(new Date());
        account.setPassword(password);
        account.setUsername(username);
        account.setKlantId(klant);
        accountFacade.create(account);
        
        adres = new Adres();
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);
        adresType = adresFacade.convertAdresType(adresTypeString);
        adres.setAdresType(adresType);
        adresFacade.create(adres);  
        
        //<Iets met @postconstruct?>
        
        // klant met id ophalen. geeft error: "
//        Caused by: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.6.1.v20150605-31e8258): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column 'KLANT_ID' cannot be null
//Error Code: 1048
//Call: INSERT INTO klantadres (createdBy, createdDate, KLANT_ID, ADRES_ID) VALUES (?, ?, ?, ?)
//	bind => [4 parameters bound]
//Query: InsertObjectQuery(com.wendy.jazz.entities.Klantadres[ klantadresPK=null ])

//        ka = new Klantadres();
//        ka.setAdres(adres);
//        ka.setKlant(klant);
//        ka.setCreatedDate(new Date());     
//        
//        Collection<Klantadres> klantadresCollection = adres.getKlantadresCollection();   
//        klantadresCollection.add(ka);
//        adres.setKlantadresCollection(klantadresCollection);        
//        
//        klantadresCollection = klant.getKlantadresCollection();
//        klantadresCollection.add(ka);
//        klant.setKlantadresCollection(klantadresCollection);
//                
//        account = accountFacade.find(account.getAccountId());
//        klant.setAccount(account);
//        klantFacade.edit(klant);
//        adresFacade.edit(adres);

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
        requestMap.put("klant", klant);
        requestMap.put("account", account);
        requestMap.put("adres", adres);
        
        return "overzicht"; // id in flowscope om flow te sluiten. gaat naar pages/overzicht.xhtml
    }
    
        @Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {

	  password = value.toString();

	  UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
		.get("passwordCheck");
	  String passwordCheck = uiInputConfirmPassword.getSubmittedValue()
		.toString();

	  // Let required="true" do its job.
	  if (password == null || password.isEmpty() || passwordCheck == null
		|| passwordCheck.isEmpty()) {
              
              password = null; 
              passwordCheck = null;
			return;
	  }

	  if (!password.equals(passwordCheck)) {
              
                password = null;
                passwordCheck = null;
		uiInputConfirmPassword.setValid(false);
		throw new ValidatorException(new FacesMessage(
			"Password must match confirm password."));                
	  }
        }
    
    public String eind(){
        return "opslaan";
    }
          
    public String returnValue(){
        return "overzicht";
    }
    
    public String registeer(){
        return "/registreer/registeer";
    }
    
    }

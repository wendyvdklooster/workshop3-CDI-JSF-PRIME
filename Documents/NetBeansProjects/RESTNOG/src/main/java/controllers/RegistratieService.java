/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.wendy.restnog.Account;
import com.wendy.restnog.Adres;
import com.wendy.restnog.Klant;
import com.wendy.restnog.service.AccountFacadeREST;
import com.wendy.restnog.service.AdresFacadeREST;
import com.wendy.restnog.service.KlantFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Wendy
 */

/**
 * Gericht aan klant
 * Bedoeld voor registratie van klant. In- en uitloggen. 
 * Ophalen van gegevens van klant tbv account, persoons- en adresgegevens
 */

//account aanmaken, beheren, login >> alleen voor klant
@Path("/registratieKlant")  // of "/klant/registratieKlant" ??
public class RegistratieService {
    
    @EJB
    KlantFacadeREST klantFacade;
    @EJB
    AdresFacadeREST adresFacade; 
    @EJB
    AccountFacadeREST accountFacade; 
    
    // registreren
    // direct vanuit HTML
        // account aanmaken
            // dubbele invoer wachtwoord
            // hide wachtwoord
            // check of gebruikersnaam nog niet in gebruik is
    
            
        // klantgegeevens: klantNummer, klantId en email hier aanmaken
        // adres gegevens
        // afronding: alle gegevens tonen. bevestig = opslaan
        
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void registratie(
            @FormParam ("username") String username, 
            @FormParam ("email") String email, 
            @FormParam ("password") String password,
            @FormParam ("voornaam") String voornaam,
            @FormParam ("tussenvoegsel") String tussenvoegsel,
            @FormParam ("achternaam") String achternaam,
            @FormParam ("straatnaam") String straatnaam,
            @FormParam ("huisnummer") int huisnummer,
            @FormParam ("toevoeging") String toevoeging,
            @FormParam ("postcode") String postcode,
            @FormParam ("woonplaats") String woonplaats
//            , 
//            @FormParam ("adresType") enum adresType
            ){
        
        Account account = new Account();
        
        // check of email al bestaat
        List<Account> accounts = accountFacade.findAll();
        for (Account acc: accounts){
                if((!acc.getEmail().equals(email)) && (!acc.getUsername().equals(username))){
                   // return message to console;
                } 
                else{
                 account.setEmail(email);
                 account.setUsername(username);
                 account.setPassword(password);                 
                 accountFacade.create(account);                        
                }
//        Account a = accountFacade.find(this);
//        Long accountId = a.getAccountId();
        
        Klant klant = new Klant();
        
        //check of email al bestaat
        klant.setVoornaam(voornaam);
        if(tussenvoegsel!= null){
            klant.setTussenvoegsel(tussenvoegsel);
        }
        klant.setAchternaam(achternaam);
        klant.setEmail(email);
        klant.setKlantNummer(2016 + "/" + postcode + "/" + huisnummer);
        klantFacade.create(klant);
        
        
        // check combi huisnummer, toevoeging, postcode + later adresType
        Adres adres = new Adres();
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        if (toevoeging != null){
            adres.setToevoeging(toevoeging);
        }
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);
        adresFacade.create(adres);
//        adres.setKlantadresCollection(klantadresCollection);
//      List<Address> addressList = addressFacade.withNamedQuery("Address.findByNumberAndZipcode", new String[] {"number", "zipcode"}, new String[] {streetNumber, postalCode });
//        Address address = null;
//
//       // If the address does not excist, create new and persist
//        if (addressList.isEmpty()) {
//            address = new Address(street, streetNumber, postalCode, city);
//            address = addressFacade.create(address);
//            ArrayList<Address> tempAddressList = new ArrayList<>();
//            tempAddressList.add(address);
//            user.setAddressCollection(tempAddressList);
//        }
//        else { // add user to the address that already exists
//            address = addressList.get(0);
//            ArrayList<Address> tempAddressList = new ArrayList<>();
//            tempAddressList.add(address);
//            user.setAddressCollection(tempAddressList);
//        }
//
//        // Register the new user with the database and put the return object including user_id in address
//       userFacade.edit(user);

//        adres.setAdresType(adresType); 
//        klant.setAccountId(accountId);
    }
    
    
    
    
    // inloggen
        // met account gegevens
        // wachtwoord afgeschermd
    
    
    // na inloggen
        // inzien gegevens
        // wijzig gegevens
        // verwijder gegevens
        // wachtwoord op kunnen vragen
    
    }
}


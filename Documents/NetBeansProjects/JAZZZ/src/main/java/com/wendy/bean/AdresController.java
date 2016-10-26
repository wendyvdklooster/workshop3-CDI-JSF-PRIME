/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.bean;

import com.wendy.controller.AdresFacade;
import com.wendy.entities.Adres;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Wendy
 */

@SessionScoped
@ManagedBean(name = "adresController", eager = true)
public class AdresController implements Serializable{
    
    HttpSession session;
    @ManagedProperty (value = "#{adres}")
    Adres adres;
    @ManagedProperty (value = "#{adresFacade}")
    AdresFacade adresFacade; 
    private Long adresId;
    private String straatnaam;
    private String toevoeging; 
    private int huisnummer; 
    private String postcode; 
    private String woonplaats; 
    private Integer adresType; 
    private String adresTypeString; // code voor adresType toevoegen

   
    public String getStraatnaam() {
        return straatnaam;
    }
    public String getToevoeging() {
        return toevoeging;
    }
    public int getHuisnummer() {
        return huisnummer;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
    public Integer getAdresType() {
        return adresType;
    }   
    
    
    
    public Adres getAdres() {      
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
        requestMap.put("adres", adres);
        
    return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public AdresFacade getAdresFacade() {
        return adresFacade;
    }

    public void setAdresFacade(AdresFacade adresFacade) {
        this.adresFacade = adresFacade;
    } 
        
    
    public AdresFacade getFacade(){
        return adresFacade;
    }
    
    public String save (){
        session = SessionUtils.getSession();
        adres.setAdresType(adresType);
        adres.setHuisnummer(huisnummer);
        adres.setStraatnaam(straatnaam);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);
        adresFacade.create(adres);
        session.setAttribute("adres", adres);
        session.setAttribute("nieuw", true);
        // id ophalen via postconstruct>?
        // klantadres ding
    // TODO: gegevens ophalen en in db opslaan
        return "redirect:";
    }
    
    
    public String wijzigAdres(){
        // redirect to page to edit
        session = SessionUtils.getSession();
        adres = getAdres();
        
        session.setAttribute("adres", adres);
        session.setAttribute("adresWijzigen", true);
        
        return "/pages/overzicht2";
    }
    
    // stuur gegevens naar edit pagina. daar opslaan, via save
    public String edit (){
        // TODO: daadwerkelijke update en terug naar standaard pagina overzicht
        adres = (Adres)SessionUtils.getSessionObject("adres");
        
        adres.setAdresType(adresType);
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);
        adres.setAdresType(adresType);
        adresFacade.edit(adres);
        
        session = SessionUtils.getSession();
        session.setAttribute("adres", adres);
        
        return "/pages/overzicht2";
    }
    
    public String remove(long adresId){
        session = SessionUtils.getSession();
        adres = getAdres();
        Long Id = adres.getAdresId();        
        adresFacade.remove(adres);
        
        // loopje maken voor als wel of niet gelukt. maar entitymanager return type is void. 
        Adres a = getAdres();
        if (a == null){
            session.setAttribute("gelukt", true); 
        }
        
        else       
            session.setAttribute("gelukt", false);
        
        return "/crud/adres.Remove.xhtml";
    }
    
    
    
}

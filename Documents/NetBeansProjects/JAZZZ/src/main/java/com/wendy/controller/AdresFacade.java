/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Adres;
import com.wendy.controller.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wendy
 */
@Stateless
@Named
@ManagedBean (name = "adresfacade", eager = true)
public class AdresFacade extends AbstractFacade<Adres> {

    Adres adres;
    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdresFacade() {
        super(Adres.class);
    }
    
    public List<Adres> getAdressen(){
        return em.createNamedQuery("Adres.findAll").getResultList();
    }
    
    public Adres getAdres(long adresId){
         List<Adres> adressen = (List<Adres>)(Adres)em.createNamedQuery("Adres.findByAdresId").getResultList();
         adres = adressen.get(0);
       return adres;
    }
    
    public String adresPagina(){
        return"/crud/adres/adres";
        
    }
    
    // van db naar view
    public String convertAdresType(Integer adresType){
        String adresT = null; 
        if (null != adresType)
            switch (adresType) {
            case 4:
                adresT = "Postadres"; 
                break;
            case 3:
                adresT = "Factuuradres";  
                break;
            case 2:
                adresT = "Kadoadres";     
                break;
            case 1:
                adresT = "Hoofdadres";    
                break;
            default:
                adresT = "Not valid";
        }
        return adresT; 
    }    
    
    public Integer convertAdresType(String adresType){
        Integer adresT = null; 
        if (null != adresType)
            switch (adresType) {
            case "Postadres":
                adresT = 4 ; 
                break;
            case "Factuuradres":
                adresT = 3 ;  
                break;
            case "Kadoadres":
                adresT = 2;     
                break;
            case "Hoofdadres":
                adresT = 1;    
                break;
            default:
                adresT = 1;
        }
        return adresT; 
        
    }
}

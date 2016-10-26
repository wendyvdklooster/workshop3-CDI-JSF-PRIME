/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Klant;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wendy
 */

@Stateless
@Named
@ManagedBean (name = "klantfacade", eager = true)
public class KlantFacade extends AbstractFacade<Klant> {

    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KlantFacade() {
        super(Klant.class);
    }

    public List<Klant> getKlanten() {
        return em.createNamedQuery("Klant.findAll").getResultList();
    }
      
    
    public String registreer(){
        return "registreer/registreer";
    }
    
    
    public String informatie() {
        return "support/support";
    }

    public String contact() {
        return "support/support";
    }

    public String support() {
        return "support/support";
    }

    public String klantPagina(ActionEvent ae){
        getKlanten();
        
        return "/crud/klant/Create";
    }
    
//    public Klant find(Klant klant){
//       // ToDO
//    }
    
}

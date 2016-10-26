/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Bestelling;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Wendy
 */
@Stateless
@Named
@ManagedBean(name = "bestellingfacade", eager = true)
public class BestellingFacade extends AbstractFacade<Bestelling> {

    //Bestelling bestelling;
    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BestellingFacade() {
        super(Bestelling.class);
    }

    public List<Bestelling> getBestelling() {
        return em.createNamedQuery("Bestelling.findAll").getResultList();
    }

    public String bestellingPagina() {
        return "/crud/bestelling/bestelling";
    }

    public Bestelling getLaatsteBestelling() {
        try {
            List<Bestelling> lijst = getBestelling();
            int size = lijst.size();
            return lijst.get(size-1);
            
            
        } catch (NoResultException e) {
            return null;
        }

    }
    
    public Bestelling getBestellingById(long bestellingId){
        Bestelling bestelling = null; 
        List<Bestelling> lijst = getBestelling();
        for (Bestelling best: lijst){
            if(best.getBestellingId() == bestellingId){
                bestelling = best;
            }
            
    }
        return bestelling;
}
}

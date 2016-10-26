/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Betaling;
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
@ManagedBean (name = "betalingfacade", eager = true)
public class BetalingFacade extends AbstractFacade<Betaling> {

    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BetalingFacade() {
        super(Betaling.class);
    }

    public List<Betaling> getProducten() {
        return em.createNamedQuery("Betaling.findAll").getResultList();
    }
    
    public String betalingPagina() {
        return "/pages/betaling";
    }
    
    
}

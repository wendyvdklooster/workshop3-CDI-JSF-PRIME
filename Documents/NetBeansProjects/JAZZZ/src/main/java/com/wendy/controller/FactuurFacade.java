/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Factuur;
import java.util.Calendar;
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
@Named
@ManagedBean (name = "factuurfacade", eager = true)
@Stateless
public class FactuurFacade extends AbstractFacade<Factuur> {

    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactuurFacade() {
        super(Factuur.class);
    }
    
    public List<Factuur> getProducten() {
        return em.createNamedQuery("Factuur.findAll").getResultList();
    }

    public String factuurPagina() {
        return "pages/factuur";
    }
    
    int jaar = Calendar.getInstance().get(Calendar.YEAR);
    
}

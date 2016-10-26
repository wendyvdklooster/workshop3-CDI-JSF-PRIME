/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Bestellingartikel;
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
@ManagedBean (name = "bestellingartikelfacade", eager = true)
public class BestellingartikelFacade extends AbstractFacade<Bestellingartikel> {

    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BestellingartikelFacade() {
        super(Bestellingartikel.class);
    }
    
}

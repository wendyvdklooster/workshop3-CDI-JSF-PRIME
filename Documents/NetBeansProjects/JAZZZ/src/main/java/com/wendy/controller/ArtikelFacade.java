/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.entities.Artikel;
import java.util.List;
import java.util.Objects;
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
@ManagedBean (name = "artikelfacade", eager = true)
public class ArtikelFacade extends AbstractFacade<Artikel> {

    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;
    Artikel artikel; 

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtikelFacade() {
        super(Artikel.class);
    }

    public List<Artikel> getProducten() {
        return em.createNamedQuery("Artikel.findAll").getResultList();
    }
    
    public Artikel zoek(Long artikelId){
        
        try{
        List<Artikel> artikelen = getProducten();
        
        artikelen.stream().filter((a) -> (Objects.equals(a.getArtikelId(), artikelId))).forEach((a) -> {
                artikel = a;
            });

           
            
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            
        }
        return artikel;
    }
    
    public String nieuwArtikel(ActionEvent ea){
        return "/crud/artikel/Create.xhtml";
    }
    
}


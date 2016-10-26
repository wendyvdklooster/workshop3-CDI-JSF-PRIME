/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.rest;

import entities.Artikel;
import entities.service.ArtikelFacadeREST;
import entities.service.MedewerkerFacadeREST;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Wendy
 * 
 * Artikelbeheer, kan alleen uitgevoerd worden door medewerker. check uitvoeren
 * plaatsen en verwijderen van artikelen.
 * lijst inzien. product inzien. > beide met mogelijkheid voor update
 * 
 */
@Path("/artikelbeheer")
public class ArtikelBeheer {
    
    @EJB
    private ArtikelFacadeREST artikelFacade;
    @EJB
    private MedewerkerFacadeREST medewerkerFacade; 
    
    // bij login regelen dat er onderscheid is bij login voor mw en klant   
    
    
    @POST // create   
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Artikel artikel) {
        artikelFacade.create(artikel);
    }
    
    @PUT // update
    @Path("/update/{artikelId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("artikelId") Long id, Artikel artikel) {
//        Artikel a = artikelFacade.find(artikelId);
//        a = artikel; 
        artikelFacade.edit(artikel);
    }

    @DELETE
    @Path("/delete/{artikelId}")
    public void remove(@PathParam("artikelId") Long id) {
        artikelFacade.remove(artikelFacade.find(id));
    }
    
    @GET //findall
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Artikel> findAll() {
        return artikelFacade.findAll();
    }
    
    @GET // find one by Id
    @Path("/{artikelId}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Artikel find(@PathParam("artikelId") Long id) {
        return artikelFacade.find(id);
    }  
    
    @GET // find one by name
    @Path("/findByName/{artikelNaam}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Artikel findByName(@PathParam("artikelNaam") String artikelNaam) {
        return artikelFacade.findByName(artikelNaam);
    }
    
    // 
    @GET
    @Path("/{from}/{to}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Artikel> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return artikelFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(artikelFacade.count());
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.rest;


import entities.Klant;
import entities.Medewerker;
import entities.service.KlantFacadeREST;
import entities.service.MedewerkerFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Wendy
 */
@Path ("/login")
public class GebruikerBeheer {
    
    @EJB
    private KlantFacadeREST klantFacade;
    @EJB
    private MedewerkerFacadeREST medewerkerFacade; 
 
    
    // klanten  -- direct achter /login
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Klant> findAllKlanten(){
        // check of medewerker request doet
        return klantFacade.findAll();       
    }
    
    @GET
    @Path("/{klantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Klant findKlantById(@PathParam("klantId") Long klantId) {
    	return klantFacade.find(klantId);
    }

    
    
    // medewerkers -- /login/medewerker
    @GET
    @Path ("/medewerker")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Medewerker> findAllMw(){
        // check of medewerker request doet
        return medewerkerFacade.findAll();       
    }
    
    @GET
    @Path("medewerker/{medewerkerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Medewerker findMwById(@PathParam("medewerkerId") Long medewerkerId) {
    	// check of medewerker request doet
        return medewerkerFacade.find(medewerkerId);
    }
    
    
    
}

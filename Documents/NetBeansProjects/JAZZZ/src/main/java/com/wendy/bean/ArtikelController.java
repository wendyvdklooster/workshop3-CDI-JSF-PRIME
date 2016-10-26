/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.bean;

import com.wendy.controller.ArtikelFacade;
import com.wendy.entities.Artikel;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wendy
 */
@ManagedBean (name="artikelcontroller", eager = true)
@SessionScoped
public class ArtikelController {
    
        @EJB
	ArtikelFacade artikelFacade;
        //@ManagedProperty(value = "#{artikel}")
        private Artikel artikel;
        
	private String artikelnaam;
	private double artikelPrijs;
	private String omschrijving;
        private String artikelnr; 
        
	
	
        // crud handelingen/ methoden artikel
	public String naarArtikellijst(String type) {
		SessionUtils.addSessionObject("artikelOverzicht", artikelFacade.findAll());
		return "/crud/artikel/artikel?faces-redirect=true";
	}

	public String naarArtikelOverzicht(Artikel artikel) {
		SessionUtils.addSessionObject("artikel", artikel);
		return "/crud/artikel/artikelOverzicht";
	}

	public String naarWijzigArtikel() {
                // uitzoeken hoe dit verkrijgen
                SessionUtils.getSessionObject("artikel");
                
                // deze versturen naar wijzigpagina
                // daar de wijzigingen aanbrengen
                // opslaan met vervolgmethode 
                SessionUtils.addSessionObject("artikel",artikel);
		return "/crud/artikel/artikelWijzig";
	}
        
        public String wilArtikelWijzigen(){
            SessionUtils.addSessionObject("wijzigen", true);
            return "";
        }
        
        
        public void artikelWijzigen(){
            // ?? werkt dit
            SessionUtils.getSessionObject("artikel");
            artikelFacade.edit(artikel);
            naarArtikelOverzicht(artikel);
        }
        
        public List getArtikellijst() {
		List<Artikel> artikelen = artikelFacade.findAll();
		return artikelen;
	}

	public String artikelOpslaan() {
                //HttpSession session = SessionUtils.getSession();
                artikel = artikelAanmaken();
		artikelFacade.create(artikel);
                SessionUtils.addSessionObject("artikel", artikel);
		return "/crud/artikel/artikelOverzicht";
	}
	

	public Artikel artikelAanmaken() {
		artikel = new Artikel(artikelnaam, artikelPrijs, omschrijving);
                if (artikelnr != null){
                    artikel.setArtikelnr(artikelnr);
                }
		return artikel;
	}
        
        public boolean verwijderArtikel(){
            boolean verwijderd = false; 
            artikel = (Artikel)SessionUtils.getSessionObject("artikel");
            artikelFacade.remove(artikel);
            Artikel a = artikelFacade.find(artikel);
            if (a == null){
                verwijderd = true; 
            }
            return verwijderd;
        }
       
	 //Getters en Setters Artikel datafields

    public String getArtikelnaam() {
        return artikelnaam;
    }

    public void setArtikelnaam(String artikelnaam) {
        this.artikelnaam = artikelnaam;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getArtikelnr() {
        return artikelnr;
    }

    public void setArtikelnr(String artikelnr) {
        this.artikelnr = artikelnr;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }
	
    
    
    
}

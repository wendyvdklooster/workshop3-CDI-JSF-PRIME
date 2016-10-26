/*
 * 
TODO: 
Deze klasse communiceert in eerste instantie met de view. pas na bevestigen, 
zal de inhoud van de winkelwagen worden toegevoegd aan de db en gekoppeld aan de
klant, factuur, adres etc. 

Request hier nog in verwerken!!
Hoe klantId op te halen, gekoppeld met adres en account gegevens. 

 */
package com.wendy.bean;

import com.wendy.controller.ArtikelFacade;
import com.wendy.controller.BestellingFacade;
import com.wendy.entities.Artikel;
import com.wendy.entities.Bestelling;
import com.wendy.entities.Bestellingartikel;
import com.wendy.entities.Betaling;
import com.wendy.entities.Factuur;
import com.wendy.entities.Klant;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Wendy
 */
@SessionScoped
@ManagedBean (name = "winkelwagen", eager = true)
public class Winkelwagen implements Serializable{
    
    @EJB 
    BestellingFacade bestellingFacade;   
    @EJB 
    ArtikelFacade artikelFacade; 
    @ManagedProperty(value="#{bestelling}")
    Bestelling bestelling;
    @ManagedProperty (value="#{bestellingartikel}")
    Bestellingartikel ba; 
    @ManagedProperty (value="#{artikel}")
    Artikel artikel;
    @ManagedProperty (value="#{factuur}")
    Factuur factuur; 
    @ManagedProperty (value = "#{betaling}")
    Betaling betaling; 
    @ManagedProperty (value = "#{klant}")
            Klant klant;
    Date datum;
    long artikelId;
    int aantal; 
    private final String[] betaalWijzen = new String[]{"IDeal", "MoneyMaker","PayPal", "Creditcard", "GiftCard"};
    private String betaalwijze;

 
    // << GETTERS AND SETTERS >>
    public Collection getWinkelwagenItems(){
        return bestelling.getBestellingartikelCollection(); 
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Bestellingartikel getBa() {
        return ba;
    }

    public void setBa(Bestellingartikel ba) {
        this.ba = ba;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Factuur getFactuur() {
        return factuur;
    }

    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }

    public Betaling getBetaling() {
        return betaling;
    }

    public void setBetaling(Betaling betaling) {
        this.betaling = betaling;
    }
    
    
    
     /**
     * Called by the command button.
     * @param artikelId
     * @param aantal
     * @return outcome
     */
    // sla bestelling op
    public int year(){    
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        return now.get(Calendar.YEAR);
    }
    
    public String plaatsBestelling(){
        
                klant = (Klant) SessionUtils.getSessionObject("klant");
		factuur = new Factuur();                
		betaling = new Betaling();
		bestelling = new Bestelling();
                
		bestelling.setKlantId(klant);	
                datum = new Date();
                        bestelling.setBestellingDatum(datum);
                Collection<Factuur>factuurCollection = new LinkedHashSet<>();
                factuurCollection.add(factuur);
		bestelling.setFactuurCollection(factuurCollection);
		bestelling.setBestellingartikelCollection
                    ((LinkedHashSet<Bestellingartikel>) SessionUtils.getSessionObject("winkelmandje"));
		bestellingFacade.create(bestelling);
                bestelling = bestellingFacade.getLaatsteBestelling();
                
		
                for(Bestellingartikel best : bestelling.getBestellingartikelCollection()){
			best.setBestelling(bestelling);
		}
                
		factuurCollection = bestelling.getFactuurCollection();
		for (Factuur f: factuurCollection){
		factuur.setBestellingId(bestelling);
		//factuur.setBetalingCollection(betaling);
		factuur.setFactuurdatum(new Date(System.currentTimeMillis()));
		factuur.setFactuurnummer(klant.getKlantId()+ "/" + bestelling.getBestellingId()+ "/" + year());
		factuur.setKlantId(klant);
                }
		betaling.setFactuurId(factuur);
		betaling.setBetaalwijze(betaalwijze);
		betaling.setKlantId(klant);

		Collection <Factuur> facturen = klant.getFactuurCollection();
                facturen.add(factuur);
		Collection <Bestelling> bestellingen = klant.getBestellingCollection();
                bestellingen.add(bestelling);
                
		bestellingFacade.edit(bestelling);
                 
                SessionUtils.addSessionObject("winkelmandje", new LinkedHashSet<Bestellingartikel>());
                
                return ""; 
    }
    
    // creer bestelling met artikelen in bestelartikel
    public String voegArtikelToe(long artikelId, int artikelAantal){
        LinkedHashSet<Bestellingartikel> winkelmandje = ((LinkedHashSet<Bestellingartikel>) SessionUtils.getSessionObject("winkelmandje"));
	if (SessionUtils.getSessionObject("winkelmandje") == null) {
			SessionUtils.addSessionObject("winkelmandje", new LinkedHashSet<Bestellingartikel>());
                        winkelmandje = ((LinkedHashSet<Bestellingartikel>) SessionUtils.getSessionObject("winkelmandje"));
		}

        boolean gevonden = false;
                artikel = artikelFacade.find(artikelId);
		for (Bestellingartikel bestelartikel : winkelmandje) {
			if (Objects.equals(bestelartikel.getArtikel(), artikel)) {
				gevonden = true;
				bestelartikel.setArtikelAantal(artikelAantal + bestelartikel.getArtikelAantal());
			}
		}

		if (!gevonden) {
			((LinkedHashSet<Bestellingartikel>) SessionUtils.getSessionObject("winkelmandje")).add(new Bestellingartikel(artikel, artikelAantal));
		}
                
                // moet eerst een oude sessie verwijderd worden?
                //SessionUtils.removeSessionObject("artikel");
                //SessionUtils.getSessionObject("artikel");
                SessionUtils.addSessionObject("artikel", artikel);
                
        return "/winkelwagen/viewWinkelwagen";
    }
    
    public void wijzigBestelling(){
        // aanpassen van aantal, of verwijderen van artikel> dus ba
    }
    
    public void verwijderBestelling(){
        // gehele bestellig-winkelwagen weg
    }
    public String bevestigInhoudWinkelwagen(){
        return "winkelwagen/viewWinkelwagen";
        //knop bevestig, betaalopties, etc
    }
    
    
    public String toonInhoud(){
        
            return "/winkelwagen/viewWinkelwagen";
            }
    
    
    public double getTotaalbedrag(){
        
        return 0.0;
    }
}


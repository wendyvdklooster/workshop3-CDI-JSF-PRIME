/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package POJO;

import POJO.Klant;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */
public class KlantenLijst {
    private static final Logger _log = LoggerFactory.getLogger(KlantenLijst.class);

    private ArrayList <Klant> klantenLijst;
   
        
    public KlantenLijst(){
        klantenLijst = new ArrayList();
        
    }

    public void setKlantenLijst(ArrayList<Klant> klantenLijst) {
        this.klantenLijst = klantenLijst;
    }

    public ArrayList<Klant> getKlantenLijst() {
        return klantenLijst;
    }

    public void add(Klant klant){
       
        klantenLijst.add(klant);
    }
    
    public boolean remove(Klant klant){
        boolean removed = klantenLijst.remove(klant);
        return removed; 
    } 
    
    public int size(){
        return klantenLijst.size();
    }

    public Klant get(int i){
        Klant klant = klantenLijst.get(i);
        return klant;
    }
}

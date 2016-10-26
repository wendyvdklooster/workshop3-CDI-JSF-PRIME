package com.wendy.bean;

import com.wendy.entities.Klant;
import com.wendy.bean.util.PaginationHelper;
import com.wendy.controller.KlantFacade;
import com.wendy.entities.Account;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean(name = "klantController", eager = true)
public class KlantController implements Serializable {

    private Account account; 
    private Klant klant;
    private Klant current; 
    private DataModel items = null;
    @EJB
    private com.wendy.controller.KlantFacade klantFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    HttpSession session; 

    public KlantController() {
    }
    
    public String toonKlant(){
        SessionUtils.getSessionObject("klant");
        SessionUtils.addSessionObject("klant", klant);
        SessionUtils.getSessionObject("account");
        SessionUtils.addSessionObject("account", account);
        return "/pages/overzicht2";
    }    
    
    public String watWijzigen(){        
        session = SessionUtils.getSession();
        session.setAttribute("wijzigen", true);        
        return "pages/overzicht2";
    }
    
    public String wijzigKlantGegevens(){
        // TODO code om klantgegevens te wijzigen
        return "";
    }
    
   

}

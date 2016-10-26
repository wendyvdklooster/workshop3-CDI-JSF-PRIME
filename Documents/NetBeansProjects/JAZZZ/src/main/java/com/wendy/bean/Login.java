package com.wendy.bean;

import com.wendy.controller.AccountFacade;
import com.wendy.entities.Account;
import com.wendy.entities.Adres;
import com.wendy.entities.Klant;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wendy
 */
@FacesValidator("passwordValidator")
@SessionScoped
@ManagedBean(name = "login", eager = true)
public class Login implements Serializable, Validator {

    @EJB
    AccountFacade accountFacade;
    @ManagedProperty(value = "#{account}")
    Account account;
    @ManagedProperty(value = "#{klant}")
    Klant klant;
    @ManagedProperty(value = "#{adres}")
    Adres adres;
    private String password;
    private String message;
    private String username;

    public Account getAccount() {
        account = (Account) SessionUtils.getSessionObject("account");
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //validate login nu alleen op username >> als die werkt testen met beiden
    public String validateUsernamePassword() {
    
            boolean valid = accountFacade.validate(username, password);
            if (valid) {
            List<Account> temp = accountFacade.withNamedQuery("Account.findByUsernameAndPassword", new String[]{"username", "password"}, new String[]{username, password});

		if (temp.size() >= 1) {
		//SessionUtils.addSessionObject("account", temp.get(0));
		account = temp.get(0);	
			password = null;
		} else {
			account = null;  
		}
                HttpSession session = SessionUtils.getSession();
//                FacesContext context = FacesContext.getCurrentInstance();
//                Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
//                requestMap.put("klant", klant);
//                requestMap.put("account", account);
//                requestMap.put("adres", adres);
                Map<String, Object> sessionMap = SessionUtils.getSessionMap();
                sessionMap.put("account", account);
                session.setAttribute("gelukt", true);

                return "/login/login-success";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Foutief gebruikersnaam en wachtwoord",
                                "Voer uw juiste gebruikersnaam en wachtwoord in"));
                return "/login/login-failure";
            }        
    }
    
    @Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {

	  password = value.toString();

	  UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
		.get("passwordCheck");
	  String passwordCheck = uiInputConfirmPassword.getSubmittedValue()
		.toString();

	  // Let required="true" do its job.
	  if (password == null || password.isEmpty() || passwordCheck == null
		|| passwordCheck.isEmpty()) {
              
              password = null; 
              passwordCheck = null;
			return;
	  }

	  if (!password.equals(passwordCheck)) {
              
                password = null;
                passwordCheck = null;
		uiInputConfirmPassword.setValid(false);
		throw new ValidatorException(new FacesMessage(
			"Password must match confirm password."));  
	  }
	}
    
    //login event   - werkt vanuit link header2     
    public String login() {
        return "/login/login.xhtml";
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/index.xhtml";
    }

    public String wijzigInloggevens() {
        // TODO: aanpassen inlog/account gegevens. 
        return "";
    }

    public String medewerker() {
        //TODO: implementeren functionaliteiten medewerker
        return "";
    }

    
    public String wachtwoordVergeten(){
    
        return "/login/wachtwoord";
    }
}

package com.wendy.bean;

import com.wendy.entities.Account;
import com.wendy.controller.AccountFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "accountController", eager = true)
public class AccountController implements Serializable {

    private Account account;
    @EJB
    private AccountFacade accountFacade;
    

    public AccountController() {
    }

    // methoden bij account
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
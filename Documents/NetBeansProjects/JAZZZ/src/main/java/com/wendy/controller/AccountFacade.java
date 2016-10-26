

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.controller;

import com.wendy.bean.SessionUtils;
import com.wendy.entities.Account;
import java.util.List;
import java.util.Objects;
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
@ManagedBean (name = "accountfacade", eager = true)
public class AccountFacade extends AbstractFacade<Account> {

    private Account account;

    @PersistenceContext(unitName = "JAZZZPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    public List<Account> getProducten() {
        return em.createNamedQuery("Account.findAll").getResultList();
    }
    
    
    public boolean validate(String username, String password) {
        boolean validate = false;
        try {
            List<Account> lijst = getProducten();

            lijst.stream().filter((a) -> (Objects.equals(a.getUsername(), username) &&
                    Objects.equals(a.getPassword(), password))).forEach((a) -> {
                        account = a;
            });

//              lijst.stream().filter((a) -> (Objects.equals(a.getUsername(), username))).forEach((a) -> {
//                account = a;
//            });
//               lijst.stream().filter((a) -> (Objects.equals(a.getPassword(), password))).forEach((a) -> {
//                account = a;
//            });
          
            if (account != null) {
                validate = true;
            }
            
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            validate = false;
        }
        return validate;
    }

   
}

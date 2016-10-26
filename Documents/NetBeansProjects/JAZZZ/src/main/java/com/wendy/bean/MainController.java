/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Wendy
 */
@ManagedBean(name = "mainController", eager = true)
@SessionScoped
public class MainController {
    
    public String home(){
        return "/index.xhtml";
    }
    
    
}

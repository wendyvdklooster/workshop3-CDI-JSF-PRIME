/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wendy.bean;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author Wendy
 */

@Qualifier
@Retention (RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface LoggedIn {
    
}

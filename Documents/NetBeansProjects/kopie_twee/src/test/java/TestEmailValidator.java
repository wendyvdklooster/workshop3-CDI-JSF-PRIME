/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.KlantController;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anne
 */
public class TestEmailValidator {
    
    public TestEmailValidator() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testValidator() {
        
        String email = "niels@hogerson.com";
        KlantController instance = new KlantController();
        
        boolean expResult = true;
        boolean result = instance.isAdresGoed(email);
        assertEquals(expResult, result);
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    // assertEquals()
}

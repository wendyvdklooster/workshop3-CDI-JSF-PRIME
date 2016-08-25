/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

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
public class InsertReflectionTest {
    
    public InsertReflectionTest() {
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

    /**
     * Test of buildInsertStatementKlant method, of class InsertReflection.
     * @param object
     */
    
    @Test
    public void testBuildInsertStatementKlant() {
        System.out.println("buildInsertStatementKlant");
        
        Klant object = new Klant();
        object.setKlantId(10);
        object.setAchternaam("Klein");
        object.setVoornaam("Calimero");
        object.setEmail("calimero@klein.nl");
        
        InsertReflection instance = new InsertReflection();
        String expResult = "INSERT INTO klant (klant_id, voornaam, achternaam, tussenvoegsel, email) values (10, 'Calimero', 'Klein', '', calimero@klein.nl'))";
        String result = instance.buildInsertStatementKlant(object);
        
        assertEquals(expResult, result);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package POJO;

import POJO.Klant;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import java.lang.reflect.Field;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Wendy
 */
public class InsertReflection {
    private static Logger LOGGER = (Logger) LoggerFactory.getLogger(InsertReflection.class);
    
    static{
    LOGGER.setLevel(Level.DEBUG);
    }
    
    
    public String buildInsertStatementKlant(Klant object){
        
        int variableToInsert = 0;
        
        String sqlTableName = Klant.class.getSimpleName().toUpperCase();
        
        String sqlStatement = "INSERT INTO " + sqlTableName + "(";
        
        String valuesField = "values (";
        
        Field[] declaredFields = Klant.class.getDeclaredFields();
        
        for (int i =0; i < declaredFields.length; i++){
            try{
                declaredFields[i].setAccessible(true);
                if (declaredFields[i].get(object) != null){
                    if (!isPrimitiveZero(declaredFields[i].get(object))){
                        variableToInsert++;
                        if (variableToInsert > 1){
                            sqlStatement += ", ";
                            valuesField += ", ";
                        }
                        sqlStatement += declaredFields[i].getName();
                        
                        if (declaredFields[i].get(object) instanceof String){
                            valuesField += "\'";
                        }
                        valuesField += declaredFields[i].get(object);
                        if (declaredFields[i].get(object) instanceof String){
                            valuesField += "\'";
                        }
                    }
                }                
            }catch(IllegalArgumentException | IllegalAccessException | SecurityException ex){
                LOGGER.warn("Exception: ", ex);
                
            }
            
        }
        String compleet = sqlStatement + ") " + valuesField + ")";
        LOGGER.debug("sqlString builded is: " + compleet);
        return compleet;
        
    }
    
    private boolean isPrimitiveZero(Object object){
        
        boolean isPrimitiveZero = false; 
        
        if (object instanceof Long){
            if ((Long) object == 0){
                isPrimitiveZero = true;
            }
        }
        else if (object instanceof Integer){
            if ((Integer) object == 0)
                isPrimitiveZero = true;
        }
        else if (object instanceof Double){
            if ((Double) object == 0.0)
                isPrimitiveZero = true;
        }
        else if (object instanceof Float){
            if ((Float) object == 0.0)
                isPrimitiveZero = true;
        }
        
       return isPrimitiveZero; 
    }
}

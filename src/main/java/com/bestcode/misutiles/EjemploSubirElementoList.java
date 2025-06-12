package com.bestcode.misutiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jimmy
 */
public class EjemploSubirElementoList {

    public void ejemplo(){
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int indiceBuscado = -1;
        for(int i = 0; i < numeros.size(); i++){
            Integer obj = numeros.get(i);
            System.out.println("i = "+i +" " + numeros.get(i));
            if(obj.intValue() == 5){
                indiceBuscado = i;
                break;
            }
        }
         System.out.println("indiceBuscado = " + indiceBuscado);
         Collections.swap(numeros, indiceBuscado, 0);
        
         for(int i = 1; i < indiceBuscado; i++){
            Collections.swap(numeros, indiceBuscado, i);
         }
         
         
        for(Integer n : numeros){
            System.out.println("n = " + n);
        }
    }
    
}

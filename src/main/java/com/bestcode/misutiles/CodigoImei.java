/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

/**
 *
 * @author Jimmy Gutierrez
 */
public class CodigoImei {    
    
    public static boolean validarImeiSamsung(String imei){
        if(imei == null || imei.isEmpty()){//Si es null o vacio...false
            return false;
        }else{
            if(imei.trim().length() == 14 || imei.trim().length() == 15 ){ //https://es.wikipedia.org/wiki/IMEI
                String fac = imei.substring(6,8);//Final Assembly Code (FAC)
                if(fac.equals("06") || fac.equals("07") || fac.equals("08") || fac.equals("09") || fac.equals("10")){ //códigos de Samsung
                    return imei.matches("^[0-9]*$");
                }else{
                    return false;//Si no contiene solo números
                }
            }else{
                return false;//Si no tiene largo 14 o 15
            }
        }
    }
    
    public static boolean validarImei(String imei) {
        if (imei == null || imei.isEmpty()) {//Si es null o vacio...false
            return false;
        } else {
            if (imei.trim().length() == 14 || imei.trim().length() == 15) { //https://es.wikipedia.org/wiki/IMEI
                return true;
            } else {
                return false;//Si no tiene largo 14 o 15
            }
        }
    }

}

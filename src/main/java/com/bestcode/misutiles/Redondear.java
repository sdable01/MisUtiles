/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author jimmy
 */
public class Redondear {

    /**
     * Redondea hacia abajo, ejemplo:
     *  13,725579 lo redondea con 3 decimales a 13,725
     * @param valor
     * @param decimales
     * @return 
     */
    public static double redondearPorCantidadDecimales(double valor, int decimales) {
        int temp = (int) (valor * Math.pow(10, decimales));
        return ((double) temp) / Math.pow(10, decimales);
    }
    
    /**
     * Redondea hacia donde corresponda, ejemplo:
     *  13,725579 lo redondea con 3 decimales a 13,726
     * @param valor
     * @param decimales
     * @return 
     */
    public static double redondearPorCantidadDecimales2(double valor, int decimales) {
        BigDecimal a = BigDecimal.valueOf(valor);
        a = a.setScale(decimales, RoundingMode.HALF_DOWN);
        return a.doubleValue();
    }
    
//    /**
//     * Redondea hacia donde corresponda, ejemplo:
//     *  13,725579 lo redondea con 3 decimales a 13,726
//     * @param valor
//     * @param decimales
//     * @return 
//     */
//    public static double redondearPorCantidadDecimales3(double valor, int decimales) {
//        BigDecimal a = BigDecimal.valueOf(valor);
//        a = a.setScale(decimales, RoundingMode.HALF_UP);
//        return a.doubleValue();
//    }
    
    /**
     *  Retorna un entero con la cantidad de decimales que posee un número double
     *  Ej: 1.23456 retorna 5
     * @param num
     * @return 
     */
    public static int cantidadDecimales(double num){
        if(num != 0){
            String str = String.valueOf(num);
            String decimales = str.substring(str.indexOf(".")+1, str.length() );            
            if(decimales.equals("0")){
                return 0;
            }else{
                return decimales.length();
            }                        
        }else{
            return 0;
        }
    }
}

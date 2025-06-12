/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

/**
 *
 * @author jimmy
 */
public class UtilesNumeros {
    
    public static Integer[] getCoordenadasHorizontalesDiagrama(int elementos, int centro){
        if(elementos == 1){
            //return new Integer[]{24};
            return new Integer[]{centro};
        }else if(elementos == 2){
            //return new Integer[]{18,30};
            return new Integer[]{(centro-6),(centro+6)};
        }else if(elementos == 3){
            //return new Integer[]{12,24,36};
            return new Integer[]{(centro-12),centro,(centro+12)};
        }else if(elementos == 4){
            return new Integer[]{(centro-18),(centro-6),(centro+6),(centro+18)};
        }else if(elementos == 5){
            return new Integer[]{(centro-24),(centro-12),centro,(centro+12),(centro+24)};
        }else if(elementos == 6){
            return new Integer[]{(centro-26),(centro-16),(centro-6),(centro+6),(centro+16),(centro+26)};
        }else if(elementos == 7){
            //return new Integer[]{0,9,18,27,36,45,54};
            return new Integer[]{(centro-24),(centro-15),(centro-6),(centro+3),(centro+12),(centro+21),(centro+30)};
        }else if(elementos == 8){
            //return new Integer[]{0,7,14,21,28,35,42,49};
            return new Integer[]{0,7,14,(centro-3),28,35,42,49};
        }else if(elementos == 9){
            return new Integer[]{0,6,12,18,24,30,36,42,48};
        }else if(elementos == 10){
            return new Integer[]{0,5,10,15,20,25,30,35,40,45};
        }else{
            Integer[] x = new Integer[elementos];
            for(int i = 0; i < elementos; i ++){
                x[i] = i * 3;
            }
            return x;
        }
    }
}

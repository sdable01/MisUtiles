/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bestcode.misutiles;

import java.time.LocalTime;

/**
 *
 * @author jimmy
 */
public class VariablesEstaticas {

    public static final short IDEMPRESA = 1;
    public static final String COD_EMPRESA_ERP = "01";

    public static final short SI = 1;
    public static final short NO = 0;

    public static final short ERROR = -1;
    
    public static final int IDUSUARIO_APP = 2; //Usuario robot
    public static final int IDUSUARIO_DESARROLLADOR = 1; //Usuario desarrollador    

    public static String getSaludo() {
        LocalTime time = LocalTime.now();
        if (time.getHour() >= 5 && time.getHour() <= 12) {
            return "Buenos días";
        } else if (time.getHour() >= 13 && time.getHour() <= 20) {
            return "Buenas tardes";
        } else {
            return "Buenas noches";
        }
    }

    
}

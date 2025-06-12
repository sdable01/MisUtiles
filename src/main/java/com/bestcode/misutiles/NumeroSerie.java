/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.util.regex.Pattern;

/**
 *
 * @author Jimmy Gutierrez
 */
public class NumeroSerie {

    public static boolean validarNumeroSerieSamsung(String serie) {
        if (serie == null || serie.isEmpty()) {//Si es null o vacio...false
            return false;
        } else {

            Pattern p = Pattern.compile("[0-9]");
            boolean contieneNumeros = p.matcher(serie).find();
            p = Pattern.compile("[a-z]");
            boolean contieneLetras = p.matcher(serie.toLowerCase()).find();

            if (contieneLetras && contieneNumeros && serie.length() > 0) {
                return true;
            } else {
                return false;
            }

        }
    }
}

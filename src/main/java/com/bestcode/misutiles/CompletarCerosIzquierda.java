package com.bestcode.misutiles;

/**
 *
 * @author Jimmy
 */
public class CompletarCerosIzquierda {

    /**
     *
     * @param largoCadena: Largo final de la cadena que se retornará.
     * @param cadena: Cadena de texto que se completará con ceros a la izquierda.
     * @return
     */
    public static String completarCerosIzquierda(int largoCadena, String cadena){
        if(largoCadena > cadena.length()){
            for(int i = cadena.length(); i < largoCadena; i++){
                cadena="0"+cadena;
            }
        }
        return cadena;
    }

}

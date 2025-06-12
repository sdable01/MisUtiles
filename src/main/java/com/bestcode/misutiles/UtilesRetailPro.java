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
public class UtilesRetailPro {
    
    public static final int TIPO_BOLETA_MANUAL = 1;
    public static final int TIPO_FACTURA_MANUAL = 2;
    public static final int TIPO_NOTA_CRED_MANUAL = 3;
    public static final int TIPO_BOLETA_ELECTRONICA = 4;
    public static final int TIPO_FACTURA_ELECTRONICA = 5;
    public static final int TIPO_NOTA_CRED_ELECTRONICA = 6;
    public static final int TIPO_GUIA_DES_MANUAL = 7;
    public static final int TIPO_GUIA_DES_ELECTRONICA = 8;
    
    public static String getNombreTipoDocumentoRetailPro(int tipoRp){
        if (tipoRp == TIPO_BOLETA_ELECTRONICA) {
            return "BOLETA ELECT";
        } else if (tipoRp == TIPO_BOLETA_MANUAL) {
            return "BOLETA MANUAL";
        } else if (tipoRp == TIPO_FACTURA_ELECTRONICA) {
            return "FACTURA ELECT";
        } else if (tipoRp == TIPO_FACTURA_MANUAL) {
            return "FACTURA MANUAL";
        } else if (tipoRp == TIPO_NOTA_CRED_ELECTRONICA) {
            return "NOTA CREDITO ELECT";
        } else if (tipoRp == TIPO_NOTA_CRED_MANUAL) {
            return "NOTA CREDITO MANUAL";
        } else if (tipoRp == TIPO_GUIA_DES_ELECTRONICA) {
            return "GUIA ELECT";
        } else if (tipoRp == TIPO_GUIA_DES_MANUAL) {
            return "GUIA MANUAL";
        }else{
            return "TIPO DOCUMENTO DESCONOCIDO";
        }
    }
}

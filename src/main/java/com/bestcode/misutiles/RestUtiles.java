/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Date;

/**
 *
 * @author jimmy
 */
public class RestUtiles {

    public static final String RESPONSE_OK = "{\"mensaje\": \"OK\"}";
    public static final String RESPONSE_ERROR_LLAVE = "{\"mensaje\": \"ERROR_KEY\"}";
    public static final String RESPONSE_ERROR = "{\"mensaje\": \"ERROR\"}";
    public static final String RESPONSE_OBJETO_NULL = "{\"mensaje\": \"NULL\"}";
    public static final String RESPONSE_PRODUCTO_NULL = "{\"mensaje\": \"PRODUCTO NULL\"}";
    public static final String RESPONSE_UBICACION_NULL = "{\"mensaje\": \"UBICACION NULL\"}";
    public static final String RESPONSE_REGISTRO_EXISTE = "{\"mensaje\": \"EXISTE\"}";
    public static final String RESPONSE_GENERIC = "{\"mensaje\": \"MSJ\"}"; 
    public static final String  LLAVE2 = "tufazo";
    
    
    public static final int CODE_OK = 200;
    public static final int CODE_ERROR_LLAVE = 401;
    public static final int CODE_ERROR_OBJETO_NULL = 404;
    

    public static String getLlave(String privateKey) {
        String fecha = Fechas.formatearFecha5(new Date());
        String llave1 = Encriptar.cryptMD5(fecha);
        final String llaveFinal = llave1 + Encriptar.cryptMD5(privateKey);
        return llaveFinal;
    }
    
    public static final String getValorPorCampo(String json, String campo){
        try {
            JsonElement jsonElement = new JsonParser().parse(json);
            String retorno = null;
            if (jsonElement.isJsonNull() == false) {
                JsonObject jo = jsonElement.getAsJsonObject();
                if (jo.has(campo)) {
                    retorno = jo.get(campo).getAsString();
                }
            }
            return retorno;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

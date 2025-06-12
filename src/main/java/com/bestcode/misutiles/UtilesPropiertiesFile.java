/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bestcode.misutiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.faces.context.ExternalContext;

/**
 *
 * @author jimmy
 */
public class UtilesPropiertiesFile {
    
    public static final String RUTA_JASPER = "ruta.jasper";
    public static final String RUTA_REPOSITORIO = "ruta.repositorios";
    public static final String RUTA_TEMP = "ruta.temp";
    public static final String URL_REPOSITORIO = "url.repositorio";
    public static final String URL_WS = "url.ws";
    public static final String ZONA_HORARIA = "zona.horaria";
    
    public static String getRutaRepo(String mode){
        return getString(RUTA_REPOSITORIO, mode);
    }
    
    public static String getRutaTemp(String mode){
        return getString(RUTA_TEMP, mode);
    }
    
    public static String getUrlRepo(String mode){
        return getString(URL_REPOSITORIO, mode);
    }
    
    public static String getUrlWs(String mode){
        return getString(URL_WS, mode);
    }
    
    public static String getZonaHoraria(String mode){
        return getString(ZONA_HORARIA, mode);
    }
    
    public static String getRutaJasper(String mode){
        return getString(RUTA_JASPER, mode);
    }
    
    
    public static String getString(String param, String mode) {
        try (InputStream input = getPropertiesInputStream(mode)) {
            if (input == null) {
                throw new IllegalArgumentException("No se pudo cargar el archivo de propiedades.");
            }
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(param);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setCronProperty(String param, String newValue, ExternalContext ec) {
        Properties prop = new Properties();
        try {
            prop.load(ec.getResourceAsStream("/resources/cron.properties"));
            prop.setProperty(param, newValue);
            prop.store(new FileOutputStream("/resources/cron.properties"), null);        
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
    
    private static InputStream getPropertiesInputStream(String mode) {        
        String resourcePath;

        switch (mode) {
            case "PROD":
                resourcePath = "/properties/prod.properties";
                break;
            case "DEV":
                resourcePath = "/properties/dev.properties";
                break;
            case "URGENCIA":
                resourcePath = "/properties/urgencia.properties";
                break;
            default:
                throw new IllegalArgumentException("Modo no soportado: " + mode);
        }

        // Cargar desde el classpath
        return UtilesPropiertiesFile.class.getResourceAsStream(resourcePath);
    }
}

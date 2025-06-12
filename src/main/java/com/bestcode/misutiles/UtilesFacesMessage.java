/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.io.Serializable;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jimmy
 */
public class UtilesFacesMessage implements Serializable{
    
    public FacesMessage crearErrorConDetalle(String titulo, String detalle){
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle);        
    }
    
    public FacesMessage crearErrorSinDetalle(String titulo){
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, null);        
    }
    
    public FacesMessage crearFatalConDetalle(String titulo, String detalle){
        return new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, detalle);        
    }
    
    public FacesMessage crearFatalSinDetalle(String titulo){
        return new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, null);        
    }
    
    public FacesMessage crearInfoConDetalle(String titulo, String detalle){
        return new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalle);        
    }
    
    public FacesMessage crearInfoSinDetalle(String titulo){
        return new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, null);        
    }
    
    public FacesMessage crearWarmConDetalle(String titulo, String detalle){
        return new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, detalle);        
    }
    
    public FacesMessage crearWarmSinDetalle(String titulo){
        return new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, null);        
    }
    
}

package com.bestcode.exceptions;

/**
 *
 * @author jimmy
 */
public class CodigoErroneoException extends Exception{
    private int largoMaximoCodigo;

    public CodigoErroneoException(int largoMaximoCodigo) {
        super();
        this.largoMaximoCodigo=largoMaximoCodigo;
    }
    
    @Override
    public String getMessage() {        
        return "El código no puede tener más de "+largoMaximoCodigo+" dígitos.";
    }

    
    
    
}

package com.bestcode.misutiles;

import com.google.gson.annotations.Expose;

/**
 *
 * @author jimmy
 */
public class Codigo {
    
     @Expose
        public String codigo;
     @Expose
     public String descripcion;
     @Expose
     public String fecha;

        public Codigo() {
        }

        public Codigo(String codigo) {
            this.codigo = codigo;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
        
        
}

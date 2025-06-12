package com.bestcode.misutiles;

/**
 *
 * @author jimmy
 */
public class Filtro {
    public static final String MODO_EXACT = "exact";
    public static final String MODO_CONTAINS = "contains";
    public static final String MODO_START = "startsWith";
    public static final String MODO_END = "endsWith";
    public static final String MODO_BETWEEN = "between";
    public static final String MODO_LT = "lt";
    public static final String MODO_LTE = "lte";
    public static final String MODO_GT = "gt";
    public static final String MODO_GTE = "gte";
    public static final String MODO_EQUALS = "equals";
    public static final String MODO_IN = "in";
    
    public static final String TIPO_INT = "INT";
    public static final String TIPO_SHORT = "SHORT";
    public static final String TIPO_DOUBLE = "DOUBLE";
    public static final String TIPO_DATE = "DATE";
    public static final String TIPO_CHAR = "CHAR";
    public static final String TIPO_LONG = "LONG";
    public static final String TIPO_BOOLEAN = "BOOLEAN";
    public static final String TIPO_RANGO = "RANGO";
    
    

    private String campo;
    private String valor;
    private String modo;
    private String tipo;

    public Filtro() {
    }

    public Filtro(String campo, String valor, String modo) {
        this.campo = campo;
        this.valor = valor;
        this.modo = modo;
    }
    
    public String print(){
        StringBuilder sb = new StringBuilder("");
        sb.append("Filter by Campo: ");
        sb.append(campo);
        sb.append("\t");
        sb.append("valor: ");
        sb.append(valor);
        sb.append("\t");
        sb.append("modo: ");
        sb.append(modo);
        sb.append("\t");
        sb.append("tipo: ");
        sb.append(tipo);
        sb.append("\t");
        return sb.toString();
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

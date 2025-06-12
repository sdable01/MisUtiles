/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bestcode.misutiles;

/**
 *
 * @author jimmy
 */
public class Orden {

    public static final String MODO_ASC = "ASCENDING";
    public static final String MODO_DESC = "DESCENDING";

    private String campo;
    private String modo;
    private int prioridad;

    public Orden(String campo, String modo, int prioridad) {
        this.campo = campo;
        this.modo = modo;
        this.prioridad = prioridad;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String print() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Sort by Campo: ");
        sb.append(campo);
        sb.append("\t");
        sb.append("modo: ");
        sb.append(modo);
        sb.append("\t");
        return sb.toString();
    }
}

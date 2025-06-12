/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jimmy Gutierrez
 */
public class CodigoBarraEAN {
    private String codigoBarra;

    public CodigoBarraEAN(String codigoBarra){
        this.codigoBarra = codigoBarra;
    }

    public CodigoBarraEAN(){
    }

    public String getCodigoBarra(){
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra){
        this.codigoBarra = codigoBarra;
    }

    //Métodos de verificação e validação do codigo de barras.    
    public static String validar(CodigoBarraEAN codigoBarra){
        String valido;

        if (validarEAN(codigoBarra.getCodigoBarra())){
            int digitoVerificador = obterDigitoVerificador(codigoBarra.getCodigoBarra());                                    
            valido = (calcularDigitoVerificador(removerDigitoVerificador(codigoBarra.getCodigoBarra())) == digitoVerificador) ? "OK" : "Inválido";
        }
        else
            valido = "Inválido";

        return valido;
    }

    public static List<Integer> obterNumeroPosicao(String codigoBarra, String imparOuPar){        
        List<Integer> numeros = new ArrayList<>();

        for (int i = 0, posicao = 1; i < codigoBarra.length() - 1; i++){
            if ((posicao % 2 != 0))                        
                numeros.add(Integer.parseInt(String.valueOf(codigoBarra.charAt(imparOuPar.equals("impar") ? posicao - 1 : posicao))));

            posicao++;
        }

        return numeros;
    }

    public static int somarNumeros(List<Integer> numeros){
        return numeros.stream().reduce(0, Integer::sum);
    }

    public static String removerDigitoVerificador(String codigoBarra){
        return codigoBarra.substring(0, codigoBarra.length() -1);
    }

    public static int obterDigitoVerificador(String codigoBarra){
        return Integer.parseInt(String.valueOf(codigoBarra.charAt(codigoBarra.length() - 1)));
    }

    public static  boolean validarEAN(String codigoBarra){
        return (codigoBarra.length() == 13);
    }

    public static int calcularDigitoVerificador(String codigoBarra){
        int somaPar = somarNumeros(obterNumeroPosicao(codigoBarra, "par")),
            somaImpar = somarNumeros(obterNumeroPosicao(codigoBarra, "impar"));        
        int multiplicaPar = somaPar * 3;        
        int resultado = somaImpar + multiplicaPar;
        int digitoVerificador = 0;
        int auxiliar = 0;        

        while ((resultado % 10) != 0){                        
            digitoVerificador++;
            resultado += digitoVerificador - auxiliar;
            auxiliar = digitoVerificador;
        }

        return digitoVerificador;
    }
}

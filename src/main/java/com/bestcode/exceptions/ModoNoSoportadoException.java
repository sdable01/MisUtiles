package com.bestcode.exceptions;

/**
 *
 * @author jimmy
 */
public class ModoNoSoportadoException  extends Exception{

    public ModoNoSoportadoException() {
        super("Modo no soportado. Las valores permitidos son PROD y DEV");
    }

}

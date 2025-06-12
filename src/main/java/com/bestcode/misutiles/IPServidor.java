/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimmy
 */
public class IPServidor {

    public static String getIpServidor() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            byte[] bIPAddress = address.getAddress();
            
            // IP en formato String
            String sIPAddress = "";
            
            for (int i = 0; i < bIPAddress.length; i++) {
                // A todos los numeros les anteponemos
                // un punto menos al primero
                sIPAddress += ".";
                
                // Jugamos con los bytes y cambiamos el bit del signo
                sIPAddress += bIPAddress[i] & 255;
            }
            return sIPAddress.substring(1, sIPAddress.length());
        } catch (UnknownHostException ex) {
            return "ERROR obteniendo IP servidor";
        }
    }

}

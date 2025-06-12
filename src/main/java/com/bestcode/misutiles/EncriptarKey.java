package com.bestcode.misutiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author dmort
 */
public class EncriptarKey {

    private static final String claveEncritacion = "V0I+XTbRw";

    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
 //       String contrasena = "10.20.2028";
 //       String resultado = encriptarKey(contrasena);
 //       System.out.println(resultado);
 //       String resultado2 = desencriptarKey(resultado);
 //       System.out.println(resultado2);

    }

    private static SecretKey generarKey(String claveMaestra) throws NoSuchAlgorithmException {
        // Usar SHA-256 como función hash
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] claveMaestraBytes = claveMaestra.getBytes();
        // Aplicar la función hash a la clave maestra
        byte[] claveHash = digest.digest(claveMaestraBytes);
        // Truncar o ajustar la clave hash según sea necesario
        int longitudClave = 128; // Longitud deseada de la clave en bits
        claveHash = Arrays.copyOf(claveHash, longitudClave / 8);
        // Crear una clave secreta a partir de la clave derivada
        return new SecretKeySpec(claveHash, "AES");
    }

    public static String encriptarKey(String encriptar) throws Exception {
        SecretKey id = generarKey(claveEncritacion);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, id);
        byte[] textoEncriptado = cipher.doFinal(encriptar.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    public static String desencriptarKey(String encriptar) throws Exception {
        SecretKey id = generarKey(claveEncritacion);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, id);
        byte[] textoDesencriptado = cipher.doFinal(Base64.getDecoder().decode(encriptar));
        return new String(textoDesencriptado, "UTF-8");
    }

}
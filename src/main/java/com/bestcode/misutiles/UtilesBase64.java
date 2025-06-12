/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jimmy
 */
public class UtilesBase64 {
    private static Logger log = LoggerFactory.getLogger(UtilesBase64.class);


    public static void decode(String filename, String text) {
        FileOutputStream fos = null;
        File file = new File(filename);
        try {
            byte[] decode = Base64.getDecoder().decode(text);
            byte[] decode2 = javax.xml.bind.DatatypeConverter.parseBase64Binary(text);
            fos = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            fos.write(decode);
            fos.flush();
            
        } catch (FileNotFoundException ex) {
            log.error(UtilesString.getDetalleException(ex));
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                log.error(UtilesString.getDetalleException(ex));
            }
        }
    }
}

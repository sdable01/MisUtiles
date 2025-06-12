/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bestcode.misutiles;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

/**
 *
 * @author jgutierrez
 */
public class ResourceUtil {


    public static String getResourceText(String bundleName, String key,Object... params) {        
        try {
            FacesContext context =
            FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            ResourceBundle bundle = app.getResourceBundle(context, bundleName);
            return MessageFormat.format(bundle.getString(key),
            params);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return "???" + key + "¡¡¡";
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getResourceText(String bundleName, String key, FacesContext context,Object... params) {
        try {
            
            Application app = context.getApplication();
            ResourceBundle bundle = app.getResourceBundle(context, bundleName);
            return MessageFormat.format(bundle.getString(key),
            params);
        } catch (MissingResourceException e) {
            return "???" + key + "¡¡¡";
        }
    }
}

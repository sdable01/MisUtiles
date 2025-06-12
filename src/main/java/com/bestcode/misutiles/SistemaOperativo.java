/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

public class SistemaOperativo {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String OSArch = System.getProperty("os.arch").toLowerCase();
    private static String OSVersion = System.getProperty("os.version").toLowerCase();

    public static String OS() {
        if (isWindows()) {
            return "WINDOWS";
        } else if (isMac()) {            
            return "MAC";
        } else if (isUnix()) {
            return "LINUX";
        } else if (isSolaris()) {
            return "SOLARIS";            
        } else {
            return "OTRO";
        }
    }

    public static String version() {
        return OSVersion;
    }

    public static String arquitectura() {
        return OSArch;
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

}

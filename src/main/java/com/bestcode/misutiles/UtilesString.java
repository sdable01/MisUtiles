/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;

/**
 *
 * @author jimmy
 */
public class UtilesString {

    public static void main(String[] args) {
//        System.out.println(rutAKoen("6102030-6"));

    }

    public static final String COLOR_TEXTO_NORMAL = "#777777";
    public static final String COLOR_TEXTO_DISABLED = "#D8D8D8";
    public static final String COLOR_TEXTO_NEGATIVO = "darkred";
    public static final String COLOR_TEXTO_POSITIVO = "darkgreen";

    public static boolean stringNullOVacio(String st) {
        if (st == null || st.trim().isEmpty() || st.equals("null")) {
            return true;
        } else {
            return false;
        }
    }

    public static String dosDigitos(int numero) {
        if (String.valueOf(numero).length() == 1) {
            return "0" + String.valueOf(numero);
        } else if (String.valueOf(numero).length() == 2) {
            return String.valueOf(numero);
        } else {
            return "Error";
        }

    }

    /**
     * Retorna el número de cadenas de texto separadas por espacios
     * pertenecientes a una cadena entregada por parámetro.
     *
     * @param cadena
     * @return
     */
    public static int contarPalabras(String cadena) {
        if (cadena != null) {
            StringTokenizer tok = new StringTokenizer(cadena);
            return tok.countTokens();
        } else {
            return 0;
        }
    }

    /**
     * Recibe un String con cadenas separadas por espacios y los guarda en un
     * ArrayList
     *
     * @param cadena
     * @return - ArrayList con las distintas cadenas del String incial.
     * @throws NullPointerException
     */
    public static ArrayList<String> stringToArraylist(String cadena) throws NullPointerException {
        ArrayList palabras = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(cadena);
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken();
            palabras.add(palabra);

        }
        return palabras;

    }

    /**
     * Recibe un String con cadenas separadas por espacios y los guarda en un
     * ArrayList<Integer>
     *
     * @param cadena
     * @return - ArrayList con las distintas cadenas del String incial.
     * @throws NullPointerException
     */
    public static ArrayList<Integer> stringToArraylist2(String cadena) throws NullPointerException {
        ArrayList palabras = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(cadena);
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken();
            palabras.add(palabra);

        }
        return palabras;

    }

    /**
     * Recibe un String con cadenas separadas por el caracter del segundo
     * parámetro y los guarda en un ArrayList
     *
     * @param cadena
     * @return - ArrayList con las distintas cadenas del String incial.
     * @throws NullPointerException
     */
    public static ArrayList<String> stringToArraylist(String cadena, String separador) throws NullPointerException {
        ArrayList palabras = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(cadena, separador);
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken();
            palabras.add(palabra);

        }
        return palabras;

    }

    /**
     * Recibe un String con cadenas separadas por espacios y los guarda en un
     * Arreglo de String
     *
     * @param cadena
     * @return - Arreglo de String con las distintas cadenas del String incial.
     * @throws NullPointerException
     */
    public static String[] stringToStringArray(String cadena) throws NullPointerException {
        StringTokenizer tokens = new StringTokenizer(cadena);
        int cantidadCadenas = tokens.countTokens();
        String[] cadenas = new String[cantidadCadenas];

        int contador = 0;
        while (tokens.hasMoreTokens()) {
            cadenas[contador] = tokens.nextToken();
            contador++;
        }
        return cadenas;

    }

    /**
     * Recibe arreglo de string y lo transforma a un solo string con todo el
     * contenido del arreglo separados por caracter del parámetro.
     *
     * @param cadenas - Arrglo de string.
     * @param separador - separador en cadena retornada.
     * @return - Cadena de texto con todo el contenido del arreglo en una ,isma
     * línea, separadas por un guión.
     */
    public static String stringArrayToString(String[] cadenas, char separador) {
        if (cadenas != null) {
            StringBuilder cadena = new StringBuilder("");
            int largo = cadenas.length;
            for (int i = 0; i < largo; i++) {
                cadena.append(cadenas[i]);
                if (i < (largo - 1)) {//Esto es para no concatenar el separador después del último elemento
                    cadena.append(separador);
                }
            }
            return cadena.toString();
        } else {
            return "**PARAMETRO ES NULO**";
        }

    }

    public static String listaStringToString(List<String> lista, char separador) {
        if (lista != null) {
            StringBuilder cadena = new StringBuilder("");
            for (int i = 0; i < lista.size(); i++) {
                cadena.append(lista.get(i));
                if (i < (lista.size() - 1)) {//Esto es para no concatenar el separador después del último elemento
                    cadena.append(separador);
                }
            }
            return cadena.toString();
        } else {
            return "**PARAMETRO ES NULO**";
        }

    }

    /**
     * Recibe arreglo de short y lo transforma a un solo string con todo el
     * contenido del arreglo separados por caracter del parámetro.
     *
     * @param array - Arrglo de string.
     * @param separador - separador en cadena retornada.
     * @return - Cadena de texto con todo el contenido del arreglo en una ,isma
     * línea, separadas por un guión.
     */
    public static String shortArrayToString(Short[] array, char separador) {
        if (array != null) {
            StringBuilder cadena = new StringBuilder("");
            int largo = array.length;
            for (int i = 0; i < largo; i++) {
                cadena.append(array[i]);
                if (i < (largo - 1)) {//Esto es para no concatenar el separador después del último elemento
                    cadena.append(separador);
                }
            }
            return cadena.toString();
        } else {
            return "**PARAMETRO ES NULO**";
        }

    }

    /**
     * Recibe arreglo de short y lo transforma a un solo string con todo el
     * contenido del arreglo separados por caracter del parámetro.
     *
     * @param array - Arrglo de string.
     * @param separador - separador en cadena retornada.
     * @return - Cadena de texto con todo el contenido del arreglo en una ,isma
     * línea, separadas por un guión.
     */
    public static String intArrayToString(Integer[] array, char separador) {
        if (array != null) {
            StringBuilder cadena = new StringBuilder("");
            int largo = array.length;
            for (int i = 0; i < largo; i++) {
                cadena.append(array[i]);
                if (i < (largo - 1)) {//Esto es para no concatenar el separador después del último elemento
                    cadena.append(separador);
                }
            }
            return cadena.toString();
        } else {
            return "**PARAMETRO ES NULO**";
        }

    }

    /**
     * Retorna arreglo de String a partir de los elementos de la lista recibida
     * por parámetro.
     *
     * @param lista
     * @return
     */
    public static String[] listToStringArray(List<String> lista) {
        if (lista != null) {
            String[] array = new String[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                array[i] = lista.get(i);
            }
            return array;
        } else {
            return null;
        }
    }

    /**
     * Retorna arreglo de String a partir de los elementos de un set por
     * parámetro.
     *
     * @param set
     * @return
     */
    public static String[] setToStringArray(Set<String> set) {
        if (set != null) {
            String[] array = set.toArray(new String[set.size()]);
            return array;
        } else {
            return null;
        }
    }

    /**
     * Retorna una lista de String a partir de los elementos de un array por
     * parámetro.
     *
     * @param lista
     * @return
     */
    public static List<String> stringArrayToArray(String[] array) {
        if (array != null && array.length > 0) {
            List<String> lista = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                lista.add(array[i]);
            }
            return lista;
        } else {
            return null;
        }
    }

    /**
     * Valida el contenido de una cadena de texto como una dirección IP válida,
     * osea que contenga puntos y numeros, etc.
     *
     * @param ip
     * @return
     */
    public static boolean validarIP(String ip) {
        String octetos[] = ip.split("\\.");
        boolean isValido = true;
        try {
            int uno = Integer.parseInt(octetos[0]);
            int dos = Integer.parseInt(octetos[1]);
            int tres = Integer.parseInt(octetos[2]);
            int cuatro = Integer.parseInt(octetos[3]);

            if (!ip.equals(String.valueOf(uno) + "." + String.valueOf(dos) + "." + String.valueOf(tres) + "." + String.valueOf(cuatro))) {
                isValido = false;
            }

            if (String.valueOf(uno).length() < 1) {
                isValido = false;
            }

            if (String.valueOf(dos).length() < 1) {
                isValido = false;
            }

            if (String.valueOf(tres).length() < 1) {
                isValido = false;
            }

            if (String.valueOf(cuatro).length() < 1) {
                isValido = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isValido = false;
        }

        return isValido;
    }

    public static Boolean validaEmail(String email) {
        if (email == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    /**
     * Método que convierte un tipo float a String en formato ###.###.##0
     *
     * @param numero
     * @return Número con los puntos de mil y de millón correspondientes. Ej:
     * 2.345.715
     */
    public static String fNum(float numero) {

        double d = (double) numero;
        Double amount = new Double(d);
        NumberFormat numberFormatter;
        String amountOut;

        numberFormatter = NumberFormat.getNumberInstance(new Locale("es"));
        numberFormatter.setMaximumFractionDigits(1);
        amountOut = numberFormatter.format(amount);
        return amountOut;
    }

    /**
     * Método que convierte un tipo float a String en formato ###.###.##0,#0
     *
     * @param numero - Número a transformar
     * @param numDecimales - Cantidad máxima de decimales visibles
     * @return - Número formateado.
     */
    public static String fNum(float numero, int numDecimales) {
        double d = (double) numero;
        Double amount = new Double(d);
        NumberFormat numberFormatter;
        String amountOut;

        numberFormatter = NumberFormat.getNumberInstance(new Locale("es"));
        numberFormatter.setMaximumFractionDigits(numDecimales);
        amountOut = numberFormatter.format(amount);
        return amountOut;
    }

    /**
     * Completa ceros a la izquierda hasta alcanzar el largo total que es el del
     * parametro cantidadMaximaDigitos
     *
     * @param numero
     * @param cantidadMaximaDigitos
     * @return
     */
    public static String llenarConCerosIzquierda(int numero, int cantidadMaximaDigitos) {

        String numeroSt = String.valueOf(numero);

        int ceros = (cantidadMaximaDigitos - numeroSt.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ceros; i++) {
            sb.append("0");
        }
        sb.append(numero);
        return sb.toString();
    }

    /**
     * Completa ceros a la izquierda hasta alcanzar el largo total que es el del
     * parametro cantidadMaximaDigitos
     *
     * @param numero
     * @param cantidadMaximaDigitos
     * @return
     */
    public static String llenarConCerosIzquierda(long numero, int cantidadMaximaDigitos) {

        String numeroSt = String.valueOf(numero);

        int ceros = (cantidadMaximaDigitos - numeroSt.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ceros; i++) {
            sb.append("0");
        }
        sb.append(numero);
        return sb.toString();
    }

    public static List<String> getListParametro(String cadena) {
        int inicio = 0;
        int fin = 0;
        String paso = "";
        List<String> lista = new ArrayList<String>();
        for (int i = 0; i < cadena.length() - 1; i++) {
            if (cadena.charAt(i) == ';') {
                fin = i;
                paso = cadena.substring(inicio, fin);
                inicio = i + 1;
                lista.add(paso);
            }
        }

        for (String p : lista) {
            System.out.println(p);
        }
        return lista;
    }

    public static String cryptMD5(String textoPlano) {
        try {
            final char[] HEXADECIMALES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest msgdgt = MessageDigest.getInstance("MD5");
            byte[] bytes = msgdgt.digest(textoPlano.getBytes());
            StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int low = (int) (bytes[i] & 0x0f);
                int high = (int) ((bytes[i] & 0xf0) >> 4);
                strCryptMD5.append(HEXADECIMALES[high]);
                strCryptMD5.append(HEXADECIMALES[low]);
            }

            return strCryptMD5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String getExtensionArchivo(String fileName) {
        int punto = 0;
        for (int x = 0; x < fileName.length(); x++) {
            if (fileName.charAt(x) == '.') {
                punto = x + 1;
            }
        }
        if (punto == 0) {
            return "";
        } else {
            return fileName.substring(punto, fileName.length());
        }
    }

    /**
     * Recibe arreglo de string y lo transforma a un solo string con todo el
     * contenido del arreglo separados por guión.
     *
     * @param cadenas - Arrglo de string.
     * @return - Cadena de texto con todo el contenido del arreglo en una ,isma
     * línea, separadas por un guión.
     */
    public static String desfragmentarCadenaTexto(String[] cadenas) {
        if (cadenas != null) {
            StringBuilder cadena = new StringBuilder("");
            for (String str : cadenas) {
                cadena.append(str);
                cadena.append("-");
            }
            return cadena.toString();
        } else {
            return "**PARAMETRO ES NULO**";
        }

    }

    /**
     * Recibe arreglo de string y lo transforma a un solo string con todo el
     * contenido del arreglo separados por guión bajo.
     *
     * @param cadenas - Arrglo de string.
     * @return - Cadena de texto con todo el contenido del arreglo en una ,isma
     * línea, separadas por un guión bajo.
     */
    public static String desfragmentarCadenaTexto2(String[] cadenas) {
        if (cadenas != null) {
            StringBuilder cadena = new StringBuilder("");
            for (String str : cadenas) {
                cadena.append(str);
                cadena.append("_");
            }
            return cadena.toString();
        } else {
            return "**PARAMETRO ES NULO**";
        }

    }

    public static String limpiarUrl(String url) {
        return url.replace("-EsPaCiO", " ").
                replace("-GaTo", "#").
                replace("-PoRcEnTaJe", "%").
                replace("-AsTeRiScO", "*").
                replace("-LlAvEiZq", "{").
                replace("-LlAvEdEr", "}").
                replace("-BaCkSlAsH", "\\").
                replace("-SlAsH", "/").
                replace("-MeNoR", "<").
                replace("-MaYoR", ">").
                replace("-CoMa", ",").
                replace("-PuNtOcOmA", ";").
                replace("-ExClA1", "¡").
                replace("-ExClA2", "!").
                replace("-PrEgUnTa1", "¿").
                replace("-SuMa", "+").
                replace("-CoMiLlA0", "'").
                replace("-CoMiLlA1", "´").
                replace("-DiErEsIs", "¨").
                replace("-FlEcHaArRiBa", "^").
                replace("-Or", "|").
                replace("-GrAdO", "°").
                replace("-CoRcHeTe1", "[").
                replace("-CoRcHeTe2", "]").
                replace("-CoMiLlA2", "`").
                replace("-PaReNtEsIs1", "(").
                replace("-PaReNtEsIs2", ")").
                replace("-MoNeDa", "$");
    }

    public static String quitarGuionYDigitoVerificador(String rut) {
        if (rut != null) {
            String nRut = null;
            if (rut.contains("-")) {
                nRut = rut.substring(0, rut.indexOf("-"));
            } else {
                nRut = rut;
            }
            if (nRut.length() == 9) {
                nRut = nRut.substring(0, 8);
            }
            if (nRut.contains(".")) {
                nRut = nRut.replace(".", "");
            }
            return nRut;
        } else {
            System.out.println("***PARÁMETRO RUT ES NULL");
            return null;
        }
    }

    public static String formatoNumericoChile(int numero) {
        Locale locale = new Locale("es_CL");
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        return nf.format(numero).replace(",", ".");
    }

    public static String passTemporal(int largo) {
        //declaro las variables del rango numérico que corresponde al alfabeto en Ascii
        //si quisieramos que generase letras mayúsculas tendríamos que usar el rango 65-90
        int num1 = 97;
        int num2 = 122;
        //a la variable de caracter le di un valor inicial de cero
        String retorno = "";
        //establezco una secuencia de diez números aleatorios
        //podemos establecer la secuencia que queramos, siempre y cuando recordemos que el alfabeto en acssi tiene 26 letras, aí que ese es el máximo de la secuencia
        for (int i = 1; i <= largo; i++) {
            int numAleatorio = (int) Math.floor(Math.random() * (num2 - num1) + num1);
            //para transformar los números en letras según ACSII
            if (numAleatorio % 2 == 0) {//Si es par...concateno un número
                retorno = retorno + numAleatorio;
            } else {//Si es impar...concateno una letra
                retorno = retorno + (char) numAleatorio;
            }

        }
        return retorno;
    }

    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validacion;
    }

    public static Long numerosDeUnaCadena(String cadena) {
        String str = "";
        if (cadena != null) {
            for (int i = 0; i < cadena.length(); i++) {
                if (Character.isDigit(cadena.charAt(i))) {
                    str = str + cadena.charAt(i);
                }
            }
            return Long.parseLong(str);

        } else {
            return null;
        }
    }

    /**
     * Convierte en número quinatndo primero el prefijo. Prefijo debe ser de
     * máximo 3 caractéres
     *
     * @param nudo
     * @return
     */
    public static int getNumeroNudo(String nudo) {
        return Integer.parseInt(nudo.substring(3, 10));
    }
    
    public static int getNumeroNudo(String nudo, int largoPrefijo) {
        return Integer.parseInt(nudo.substring(largoPrefijo, 10));
    }

    /**
     * Concatena el prefijo con el número rellenando con ceros entremedio y
     * logrando un largo de 10 * Prefijo debe ser de máximo 3 caractéres
     *
     * @param prefijo
     * @param numero
     * @return
     */
    public static String getNudo(String prefijo, int numero) {
        return prefijo + llenarConCerosIzquierda(numero, (10 - prefijo.length()));
    }

    /**
     * Ubicaciòn con formato" 1. 2. 5b"
     *
     * @param ubicacion
     * @return
     */
    public static int getRack(String ubicacion) {
        try {
            String rack = ubicacion.substring(0, 2);
            return Integer.parseInt(rack.trim());
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getSlot(String ubicacion) {
        try {
            String rack = ubicacion.substring(3, 5);
            return Integer.parseInt(rack.trim());
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Retorna una cadena de texto para ser incluida en una cláusula "in" de
     * sql. Cada cadena va envuelta en comillas simples
     *
     * @param datos
     * @return
     */
    public static String getCadenaParaInTextoSql(String[] datos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datos.length; i++) {
            String s = datos[i];
            sb.append("'");
            sb.append(s);
            if (i == (datos.length - 1)) {//Si es el último...
                sb.append("'");//No lleva coma al final ya que es el último
            } else {
                sb.append("',");
            }
        }

        return sb.toString();
    }

    /**
     * Retorna una cadena de texto para ser incluida en una cláusula "in" de
     * sql.
     *
     * @param datos
     * @return
     */
    public static String getCadenaParaInIntegerSql(Integer[] datos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datos.length; i++) {
            Integer s = datos[i];
            sb.append(s);
            if (i == (datos.length - 1)) {//Si es el último...
                //Es el último
            } else {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static String getCadenaParaInIntegerSql(List<Integer> datos) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Integer s : datos) {
            sb.append(s);
            if (i == (datos.size() - 1)) {//Si es el último...
                //Es el último
            } else {
                sb.append(",");
            }
            i++;
        }

        return sb.toString();
    }

    /**
     * Retorna una cadena de texto para ser incluida en una cláusula "in" de
     * sql.
     *
     * @param datos
     * @return
     */
    public static String getCadenaParaInShortSql(Short[] datos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datos.length; i++) {
            Short s = datos[i];
            sb.append(s);
            if (i == (datos.length - 1)) {//Si es el último...
                //Es el último
            } else {
                sb.append(",");
            }
        }

        return sb.toString();
    }

//    public static String primeraLetraMayuscula(String texto) {
//        if (texto != null) {
//            char[] arr = texto.toCharArray();
//            arr[0] = Character.toUpperCase(arr[0]);
//            return new String(arr);
//        } else {
//            System.out.println("ERROR: primeraLetraMayuscula(String texto) recibió un parámetro null");
//            return null;
//        }
//    }
    public static String primeraLetraMayuscula(String texto) {
        if (texto != null) {
            String[] words = stringToStringArray(texto);
            StringBuilder sb = new StringBuilder("");

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (i == 0) {
                    word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
                } else {
                    word = word.isEmpty() ? word : word.toLowerCase();
                }

                sb.append(word);
                sb.append(" ");
            }

            return sb.toString();
        } else {
            System.out.println("ERROR: primeraLetraMayuscula(String texto) recibió un parámetro null");
            return null;
        }
    }

    /**
     * Retorna solo la primera palabra del string con la primera letra
     * mayúscula, todas las demás en minúsculas. Si encuentra la marca, todas
     * las palabras que comprenden la marca se mostrarán en mayúscula
     *
     * @param texto
     * @param marca
     * @return
     */
    public static String primeraLetraMayuscula(String texto, String marca) {
        if (texto != null) {
            String[] words = stringToStringArray(texto);
            StringBuilder sb = new StringBuilder("");

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (i == 0) {
                    word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
                } else {
                    word = word.isEmpty() ? word : word.toLowerCase();
                }

                sb.append(word);
                sb.append(" ");
            }

            String retorno = sb.toString();
            if (marca != null && retorno.toLowerCase().contains(marca.toLowerCase())) {
                //retorno = StringUtils.replace(retorno, marca, primerasLetrasMayusculas(marca));//Solo primera letra en mayúscula
                retorno = StringUtils.replace(retorno, marca, primerasLetrasMayusculas(marca));
            }
            return retorno;
        } else {
            System.out.println("ERROR: primeraLetraMayuscula(String texto) recibió un parámetro null");
            return null;
        }
    }

    /**
     * Retorna TODAS las palabras del string con la primera letra en mayúscula,
     * excepto: de, del, y, con, la, el, las, los
     *
     * @param texto
     * @return
     */
    public static String primerasLetrasMayusculas(String texto) {
        if (texto != null) {
            String[] words = stringToStringArray(texto);
            StringBuilder sb = new StringBuilder("");

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (i == 0) {//La primera palabra siempre con la primera letra en mayúscula, sea cual sea
                    word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
                } else {
                    if (word.toLowerCase().equals("de")
                            || word.toLowerCase().equals("del")
                            || word.toLowerCase().equals("y")
                            || word.toLowerCase().equals("con")
                            || word.toLowerCase().equals("la")
                            || word.toLowerCase().equals("el")
                            || word.toLowerCase().equals("las")
                            || word.toLowerCase().equals("los")) {
                        //Estas palabras siempre en minúsculas
                    } else {
                        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
                    }
                }

                sb.append(word);
                sb.append(" ");
            }
            return sb.toString().trim();
        } else {
            System.out.println("ERROR: primeraLetraMayuscula(String texto) recibió un parámetro null");
            return null;
        }
    }

//    public static String primerasLetraMayusculaProductoRincon(String texto, String marca) {
//        if (texto != null) {
//            if(marca == null){
//                marca = "";
//            }
//            StringTokenizer st = new StringTokenizer(texto.toLowerCase());
//            int indice = 0;
//            StringBuilder sb = new StringBuilder("");
//            
//            while (st.hasMoreTokens()) {
//                String word = st.nextToken();
//                //System.out.println("st.nextToken() = " + st.nextToken());
//                if (word.toLowerCase().equals(marca.toLowerCase())) {
//                    sb.append(word.toUpperCase());
//                } else {
//                    if (indice == 0) {
//                        sb.append(primeraLetraMayuscula(word) );
//                    } else {
//                        sb.append(word);
//                    }
//                }
//
//                sb.append(" ");
//                indice++;
//            }
//
//            return sb.toString().trim();
//        } else {
//            System.out.println("ERROR: primeraLetraMayuscula(String texto) recibió un parámetro null");
//            return null;
//        }
//    }
    public static String concatenar(String... texto) {
        if (texto != null && texto.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String txt : texto) {
                sb.append(txt);
            }
            return sb.toString();

        } else {
            return null;
        }
    }

    public static String rutAKoen(String rut) {
        if (rut != null) {
            boolean rutValido = validarRut(rut.trim());
            if (!rutValido) {
                return null;
            }

            String koen = UtilesString.quitarGuionYDigitoVerificador(rut);
            if (koen.length() == 7) {
                koen = concatenar("0", koen);
            }
            return koen;
        } else {
            return null;
        }
    }

    public static String getDetalleException(Exception ex) {
        try {
            StringBuilder sb = new StringBuilder(ex.toString());
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                StackTraceElement ste = ex.getStackTrace()[i];
                sb.append("\t");
                sb.append(ste.toString());
                sb.append("\n");
            }

            return sb.toString();
        } catch (Exception e) {
            System.out.println("Exception intentando mostrar Exception. Traza original:");
            ex.printStackTrace();
            System.out.println("Exception: ");
            e.printStackTrace();
            return null;
        }
    }

    public static String imprimirObjeto(Object obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static String imprimirObjetoJson(Object obj, Logger log) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return "Exception imprimienddo json de " + obj.toString();
        }

    }

    public static void printObject(Object obj, Logger log) {
        if (obj == null) {
            System.out.println("El objeto es null");
            return;
        }

        Class<?> clazz = obj.getClass();

        Field[] fields = clazz.getDeclaredFields();
        String data = null;
        for (Field field : fields) {
            field.setAccessible(true); // Permite acceder a atributos privados
            try {
                Object value = field.get(obj);
                data = concatenar(field.getName(), ": ", (value != null ? value.toString() : "null"), " - ");
                if (log == null) {
                    System.out.print(data);
                } else {
                    log.info(data);
                }
            } catch (IllegalAccessException e) {
                System.out.println(field.getName() + ": No se puede acceder");
            }
        }
    }

    public static String quitarTildesYEñes(String input) throws NullPointerException {
        return input.replace("Á", "A")
                .replace("É", "E")
                .replace("í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("Ñ", "N")
                .replace("ñ", "n");
    }
}

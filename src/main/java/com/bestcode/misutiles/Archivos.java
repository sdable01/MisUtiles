package com.bestcode.misutiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase orientada a ser usada mediante métodos estáticos que trabajen con la
 * creación, modificacion y borrado de archivos y carpetas.
 *
 * @author Jimmy Gutiérrez B.
 * @version 1.0
 */
public class Archivos {

    private static Logger log = LoggerFactory.getLogger(Archivos.class);

    /**
     * Este mètod estático cumple la función de borrar la carpeta recibida como
     * parámetro de entrada, junto con todo su contenido, tanto archivos como
     * subcarpetas.
     *
     * @param direccion - carpeta que se desea borrar, ej: "C:/El Volcan/"
     */
    public static void borrardirectoriosrecursivamente(String direccion) {
        File directorio = new File(direccion);

        if (directorio.listFiles() != null) {
            File[] contenido = directorio.listFiles();

            for (int x = 0; x < contenido.length; x++) {
                if (contenido[x].isDirectory()) {
                    borrardirectoriosrecursivamente(contenido[x].getPath());
                }
                contenido[x].delete();
                directorio.delete();
            }
        } else {
            directorio.delete();
            log.warn("La carpeta no existe o está mal escrita");
        }
    }

    /**
     * Este mètod estático cumple la función de borrar el contenido la carpeta
     * recibida como parámetro de entrada, tanto archivos como subcarpetas.
     *
     * @param direccion - carpeta que se desea borrar, ej: "C:/El Volcan/"
     */
    public static void limpiarDirectoriosRecursivamente(String direccion) {
        File directorio = new File(direccion);

        if (directorio.listFiles() != null) {
            File[] contenido = directorio.listFiles();

            for (int x = 0; x < contenido.length; x++) {
                if (contenido[x].isDirectory()) {
                    borrardirectoriosrecursivamente(contenido[x].getPath());
                }
                contenido[x].delete();
            }
        } else {
            log.warn("La carpeta no existe o está mal escrita");
        }
    }

    /**
     * Mètodo que crea una carpeta a partir de la dirección recibida como
     * parámetro de entrada. Los resultados de la operación son mostrados en
     * log.
     *
     * @param direccion - carpeta que se desea crear, ej: "C:/El Volcan/"
     */
    public static void creardirectorio(String direccion) {
        File directorio = new File(direccion);
        boolean resultado = directorio.mkdir();
        if (resultado == true) {
            log.info(direccion + " creada con éxito");
        } else {
            log.warn(direccion + " no es una dirección válida, no se pudo crear");
        }

    }

    /**
     * Método capaz de crear un archivo en la ruta recibida por parámetro, y
     * también de escribir en dicho archivo el texto recibido en el otro
     * parámetro. Está orientado principalmente a creación de archivos de texto
     * plano ej: txt, sql, etc.
     *
     * @param uri - Ubicación del archivo ej:"c:/El Volcan/hola.txt"
     * @param data - Texto para escribir en el archivo.
     * @throws FileNotFoundException
     */
    public static void crearArchivo(String uri, String data) throws FileNotFoundException {
        try {
            File unArchivo = new File(uri);
            FileOutputStream out = new FileOutputStream(unArchivo);
            out.write(data.getBytes());
            out.close();
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
        }
    }

    public static String leerArchivoTexto(String nombreArchivo) {
        File f = new File(nombreArchivo);
        BufferedReader entrada;
        String texto = null;
        try {
            entrada = new BufferedReader(new FileReader(f));

            if (f.exists()) {

                texto = entrada.readLine();

                System.out.println(texto);
            }

        } catch (FileNotFoundException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }
        return texto;
    }

    public static void recorrerRamaDOM(Node nodo) {
        log.info("Nombre :" + nodo.getNodeName());
        log.info("Valor :" + nodo.getNodeValue());

        NodeList hijos = nodo.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node nodoNieto = hijos.item(i);
            recorrerRamaDOM(nodoNieto);
        }
    }

    public static void leerArchivoDomXML(String nombreArchivo) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File f = new File(nombreArchivo);
            Document doc = builder.parse(f);
            recorrerRamaDOM(doc);
        } catch (Exception ex) {
            log.error(UtilesString.getDetalleException(ex));
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

    public static String quitarExtensionArchivo(String fileName) {
        int punto = 0;
        for (int x = 0; x < fileName.length(); x++) {
            if (fileName.charAt(x) == '.') {
                punto = x + 1;
            }
        }
        if (punto == 0) {
            return "";
        } else {
            return fileName.replace(fileName.substring(punto, fileName.length()), "");
        }
    }

    public static String getExtensionArchivoUrl(String url) {
        if (url.contains(".jpg")) {
            return ".jpg";
        } else if (url.contains(".jpej")) {
            return ".jpeg";
        } else if (url.contains(".png")) {
            return ".png";
        } else if (url.contains(".gif")) {
            return ".gif";
        } else if (url.contains(".webp")) {
            return ".webp";
        } else if (url.contains(".pdf")) {
            return ".pdf";
        } else if (url.contains(".xls")) {
            return ".xls";
        } else if (url.contains(".xlsx")) {
            return ".xlsx";
        } else if (url.contains(".txt")) {
            return ".txt";
        } else if (url.contains(".json")) {
            return ".json";
        } else if (url.contains(".ppt")) {
            return ".ppt";
        } else if (url.contains(".pptx")) {
            return ".pptx";
        } else if (url.contains(".mp4")) {
            return ".mp4";
        } else if (url.contains(".mkv")) {
            return ".mkv";
        } else {
            return "???";
        }
    }

    /**
     * ¡¡¡¡¡¡¡¡¡¡¡¡¡¡NO USAR!!!!!!!!!!!!!!!!!!
     *
     * @param arc
     */
    public void MoverArchivo(File arc) {
        JFileChooser fileChooser = new JFileChooser();

        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {

            File objFile = fileChooser.getSelectedFile();

            log.info(objFile.getAbsolutePath());
            FileOutputStream archivoSalida = null;
            try {
                objFile.renameTo(new File("C:/plop.pdf"));

                FileOutputStream aaa = new FileOutputStream(objFile);

                aaa.close();

            } catch (FileNotFoundException ex) {
                log.error(UtilesString.getDetalleException(ex));
            } catch (IOException ex) {
                log.error(UtilesString.getDetalleException(ex));
            }

        } else if (seleccion == JFileChooser.CANCEL_OPTION) {
            fileChooser.hide();
        }
    }

    public static String getRutaProyecto() {
        File f = new File(".");
        try {
            return f.getCanonicalPath() + "/";
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return "";
        }
    }

    public void InputStreamAFile(InputStream entrada) {
        try {

            File f = new File("Archivo.txt");//Aqui le dan el nombre y/o con la ruta del archivo salida
            System.out.println(f.getName());
            System.out.println(f.getAbsoluteFile());
            System.out.println(f.getAbsolutePath());
            OutputStream salida = new FileOutputStream(f);
            byte[] buf = new byte[1024];//Actualizado me olvide del 1024
            int len;
            while ((len = entrada.read(buf)) > 0) {
                salida.write(buf, 0, len);
            }
            salida.close();
            entrada.close();
            log.info("Se realizo la conversion con exito");
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
        }
    }

    /**
     * Busca caracteres no permitidos para nombres de archivos y los reemplaza
     * por espacios
     *
     * @param nombreArchivo
     * @param agregarId - Agrega un ID numérico al archivo, utilizar en archivos
     * que podrían ser abiertos por más de un usuario al mismo tiempo.
     * @param extension - xls, pdf, etc. OJO no poner punto, el punto lo pone el
     * método. Si no se desea concatenar la extensión
     * @return
     */
    public static String revisionNombreArchivo(String nombreArchivo, boolean agregarId, String extension) {
        if (nombreArchivo.contains("/")) {
            nombreArchivo = nombreArchivo.replace("/", " ");
        }
        if (nombreArchivo.contains("\\")) {
            nombreArchivo = nombreArchivo.replace("\\", " ");
        }
        if (nombreArchivo.contains(":")) {
            nombreArchivo = nombreArchivo.replace(":", " ");
        }
        if (nombreArchivo.contains("*")) {
            nombreArchivo = nombreArchivo.replace("*", " ");
        }
        if (nombreArchivo.contains("?")) {
            nombreArchivo = nombreArchivo.replace("?", " ");
        }
        if (nombreArchivo.contains("\"")) {
            nombreArchivo = nombreArchivo.replace("\"", " ");
        }
        if (nombreArchivo.contains("<")) {
            nombreArchivo = nombreArchivo.replace("<", " ");
        }
        if (nombreArchivo.contains(">")) {
            nombreArchivo = nombreArchivo.replace(">", " ");
        }
        if (nombreArchivo.contains("|")) {
            nombreArchivo = nombreArchivo.replace("|", " ");
        }

        if (agregarId) {
            nombreArchivo += " (ID_" + Math.round(Math.random() * 10000) + ")";
        }

        if (extension != null) {
            if (extension.contains(".")) {
                nombreArchivo += extension;
            } else {
                nombreArchivo += "." + extension;
            }

        }

        return nombreArchivo;
    }

    public static String limpiarFilenameUrl(String filename) {
        if (filename == null) {
            log.error("ERROR: filename null");
            return null;
        }
        return filename.toLowerCase().replace(" ", "_").
                replace("á", "a").
                replace("é", "e").
                replace("í", "i").
                replace("ó", "o").
                replace("ú", "u").
                replace("ñ", "n");
    }

    public static boolean fileMove(String rutaNombreExtensionOrigen, String rutaNombreExtensionDestino) {
        log.info("Desde: " + rutaNombreExtensionOrigen);
        log.info("Hacia: " + rutaNombreExtensionDestino);

        try {
            File inFile = new File(rutaNombreExtensionOrigen);
            File outFile = new File(rutaNombreExtensionDestino);

            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();

            File file = new File(rutaNombreExtensionOrigen);
            if (file.exists()) {
                file.delete();
            }

            return true;
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return false;
        }
    }

    public static String limpiarNombrearchivo(String filename) {
        return filename.replace("á", "a").
                replace("é", "e").
                replace("í", "i").
                replace("ó", "o").
                replace("ú", "u").
                replace("Á", "A").
                replace("É", "e").
                replace("Í", "i").
                replace("Ó", "o").
                replace("Ú", "U").
                replace("ñ", "n").
                replace("Ñ", "n").
                replace(" ", "_").
                replace("?", "_").
                replace("-", "_");
    }

    public static int borrarArchivosPorCantidadDias(String directorio, int cantidadDias) {
        try {
            File dir = new File(directorio);
            File[] listFiles = dir.listFiles();
            int borrados = 0;
            if (listFiles != null) {
                for (File f : listFiles) {
                    // Obtener la ruta del archivo
                    Path filePath = f.toPath();

                    // Obtener los atributos básicos del archivo
                    BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);

                    // Obtener la fecha de creación
                    FileTime creationTime = attrs.creationTime();
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(creationTime.toMillis());
                    Date fecha = c.getTime();
                    int diasDeDiferencia = Fechas.diasDeDiferencia(fecha, new Date());
                    if (diasDeDiferencia >= cantidadDias) {
                        f.delete();
                        borrados++;
                    }
                }
            }
            return borrados;
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return 0;
        }
    }
    
    public static void openFile(File file, FacesContext context) {
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {

            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), 10240);

            // Init servlet response.
            response.reset();
            // lire un fichier pdf
            response.setHeader("Content-type", "application/pdf");
            response.setContentLength((int) file.length());
            response.setHeader("Content-disposition", "inline; filename=" + file.getName());
            response.setHeader("pragma", "public");
            output = new BufferedOutputStream(response.getOutputStream(), 10240);
            // Write file contents to response.
            byte[] buffer = new byte[10240];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // Finalize task.
            output.flush();output.close();input.close();
            context.responseComplete();
        } catch (Exception ex) {
            log.error(UtilesString.getDetalleException(ex));
        } finally {
            
        }
    }
}

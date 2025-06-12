package com.bestcode.jasper;

import com.bestcode.misutiles.Conexion;
import com.bestcode.misutiles.UtilesPropiertiesFile;
import com.bestcode.misutiles.UtilesString;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizationHelper;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jimmy
 */
public class ReportExcel {
    
    private static Logger log = LoggerFactory.getLogger(ReportExcel.class);
    
    public static String xlsxResponseFromDatabase(Map<String, Object> parametros, String jasperFileName, String outputFileName, SessionFactory sf, FacesContext context, boolean virtualizar)
            throws JRException, Exception {
        Connection connection = null;
        ServletOutputStream stream = null;
        try {
            String mode = (String) parametros.get("mode");            
            StringBuilder sbJasper = new StringBuilder("/jasperfiles/");
            sbJasper.append(jasperFileName + ".jasper");
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "inline; filename=\"" + outputFileName+".xlsx" + "\"");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");            
            stream = response.getOutputStream();
            
            connection = Conexion.getConexion(sf);
            outputFileName = outputFileName+".xlsx";
            log.info("archivoPdfName = " + outputFileName);

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(sbJasper.toString()));
            //File jasper = cargarArchivoJasper(sbJasper.toString());

            if (virtualizar) {
                String repositorioTemp = UtilesPropiertiesFile.getString(UtilesPropiertiesFile.RUTA_TEMP, mode);
                log.info("Virtualizando en "+repositorioTemp);
                JRVirtualizer virtualizer = new JRSwapFileVirtualizer(1000, new JRSwapFile(repositorioTemp, 2048, 1024), true);
                JRVirtualizationHelper.setThreadVirtualizer(virtualizer);
                parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, connection);

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));

            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setDetectCellType(true);
            config.setCollapseRowSpan(false);
            config.setRemoveEmptySpaceBetweenRows(true);
            config.setWhitePageBackground(false);
            exporter.setConfiguration(config);

            exporter.exportReport();
            
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
            response.setStatus(401);            
            return outputFileName;
        } catch (JRException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }finally{
            connection.close();
        }
    }
    
    public static String xlsxResponseFromJSON(Map<String, Object> parametros, String jasperFileName, String outputFileName, File jsonFile, FacesContext context, boolean virtualizar)
            throws JRException, Exception {
        ServletOutputStream stream = null;
        try {
            String mode = (String) parametros.get("mode");            
            StringBuilder sbJasper = new StringBuilder("/jasperfiles/");
            sbJasper.append(jasperFileName + ".jasper");
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "inline; filename=\"" + outputFileName+".xlsx" + "\"");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");            
            stream = response.getOutputStream();
            
            outputFileName = outputFileName+".xlsx";
            log.info("archivoPdfName = " + outputFileName);

            //InputStream isjson = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
            InputStream isjson = new FileInputStream(jsonFile);
            parametros.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, isjson);
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(sbJasper.toString()));
            //File jasper = cargarArchivoJasper(sbJasper.toString());

            if (virtualizar) {
                String repositorioTemp = UtilesPropiertiesFile.getString(UtilesPropiertiesFile.RUTA_TEMP, mode);
                log.info("Virtualizando en "+repositorioTemp);
                JRVirtualizer virtualizer = new JRSwapFileVirtualizer(1000, new JRSwapFile(repositorioTemp, 2048, 1024), true);
                JRVirtualizationHelper.setThreadVirtualizer(virtualizer);
                parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros);

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));

            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setDetectCellType(true);
            config.setCollapseRowSpan(false);
            config.setRemoveEmptySpaceBetweenRows(true);
            config.setWhitePageBackground(false);
            exporter.setConfiguration(config);

            exporter.exportReport();
            
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
            response.setStatus(401);            
            return outputFileName;
        } catch (JRException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }
    }
    
    /**
     * Siempre debe existir una carpeta files/temp.
     * @param parametros parámetros del reporte.
     * @param jasperFileName solo el nombre del archivo jasper, sin ubicación ni extensión.
     * @param outputFileName Ruta completa, ej: C:\bestcode\files\temp\boleta.pdf
     * @param sf SessionFactory para la consulta a la BD.
     * @param virtualizar Verdadero para construir el archivo por partes (para archivos muy pesados).
     * @return el mismo outputFileName que recibió
     * @throws JRException
     * @throws Exception 
     */
    public static String saveXlsxFromJSON(Map<String, Object> parametros, String jasperFileName, String outputFileName, String json, boolean virtualizar)
            throws JRException, Exception {
        try {
            String mode = (String) parametros.get("mode");
            StringBuilder sbJasper = new StringBuilder("/jasperfiles/");
            sbJasper.append(jasperFileName + ".jasper");

            InputStream isjson = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
            parametros.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, isjson);
            
            File jasper = cargarArchivoJasper(sbJasper.toString());

            if (virtualizar) {                
                String repositorioTemp = UtilesPropiertiesFile.getString(UtilesPropiertiesFile.RUTA_REPOSITORIO, mode)+"temp"+File.separator;
                log.info("Virtualizando en "+repositorioTemp);
                JRVirtualizer virtualizer = new JRSwapFileVirtualizer(1000, new JRSwapFile(repositorioTemp, 2048, 1024), true);
                JRVirtualizationHelper.setThreadVirtualizer(virtualizer);
                parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros);

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFileName));

            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setDetectCellType(true);
            config.setCollapseRowSpan(false);
            config.setRemoveEmptySpaceBetweenRows(true);
            config.setWhitePageBackground(false);
            exporter.setConfiguration(config);

            exporter.exportReport();
            
            log.info("XLSX creado con éxito");
            return outputFileName;
        } catch (JRException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        } catch (Exception ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }
    }
    
    /**
     * Siempre debe existir una carpeta files/temp.
     * @param parametros parámetros del reporte. Siempre debe poseer con la clave "mode" los valores DEV o PROD.
     * @param jasperFileName solo el nombre del archivo jasper, sin ubicación ni extensión.
     * @param outputFileName Ruta completa, ej: C:\bestcode\files\temp\boleta.pdf
     * @param sf SessionFactory para la consulta a la BD.
     * @param virtualizar Verdadero para construir el archivo por partes (para archivos muy pesados).
     * @return el mismo outputFileName que recibió
     * @throws JRException
     * @throws Exception 
     */
    public static String saveXlsxFromDatabase(Map<String, Object> parametros, String jasperFileName, String outputFileName, SessionFactory sf,boolean virtualizar)
            throws JRException, Exception {
        try {
            String mode = (String) parametros.get("mode");
            StringBuilder sbJasper = new StringBuilder("/jasperfiles/");
            sbJasper.append(jasperFileName + ".jasper");

            Connection connection = Conexion.getConexion(sf);
            File jasper = cargarArchivoJasper(sbJasper.toString());

            if (virtualizar) {
                String repositorioTemp = UtilesPropiertiesFile.getString(UtilesPropiertiesFile.RUTA_REPOSITORIO, mode)+"temp"+File.separator;
                log.info("Virtualizando en "+repositorioTemp);
                JRVirtualizer virtualizer = new JRSwapFileVirtualizer(1000, new JRSwapFile(repositorioTemp, 2048, 1024), true);
                JRVirtualizationHelper.setThreadVirtualizer(virtualizer);
                parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, connection);

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFileName));

            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setDetectCellType(true);
            config.setCollapseRowSpan(false);
            config.setRemoveEmptySpaceBetweenRows(true);
            config.setWhitePageBackground(false);
            exporter.setConfiguration(config);

            exporter.exportReport();
            
            connection.close();
            log.info("XLSX creado con éxito");
            return outputFileName;
        } catch (JRException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        } catch (Exception ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }
    }
    
    public static File cargarArchivoJasper(String jasperFileName){
        InputStream jasperInputStream = ReportExcel.class.getResourceAsStream(jasperFileName.toString());

        if (jasperInputStream == null) {
            throw new IllegalArgumentException("No se encontró el archivo Jasper en la ruta: " + jasperFileName.toString());
        }

        // Crear un archivo temporal si es necesario para JasperReports.
        File jasper = null;
        try {
            jasper = File.createTempFile(jasperFileName.replace(".jasper", ""), ".jasper");
        } catch (IOException ex) {
            log.error(UtilesString.getDetalleException(ex));
        }
        try (OutputStream outputStream = new FileOutputStream(jasper)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = jasperInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch(Exception ex){
            log.error(UtilesString.getDetalleException(ex));
        }
        return jasper;
    }
}

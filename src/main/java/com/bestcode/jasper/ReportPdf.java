package com.bestcode.jasper;

import static com.bestcode.jasper.ReportExcel.cargarArchivoJasper;
import com.bestcode.misutiles.Archivos;
import com.bestcode.misutiles.Conexion;
import com.bestcode.misutiles.UtilesPropiertiesFile;
import com.bestcode.misutiles.UtilesString;
import java.io.File;
import java.sql.Connection;
import java.util.Map;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizationHelper;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jimmy
 */
public class ReportPdf {
    
    private static Logger log = LoggerFactory.getLogger(ReportPdf.class);
    
    public static String xlsxResponseFromDatabase(Map<String, Object> parametros, String jasperFileName, String outputFileName, SessionFactory sf, FacesContext context, boolean abrirOtraPestaña, boolean virtualizar)
            throws JRException, Exception {
        try {
            String mode = (String) parametros.get("mode");            
            StringBuilder sbJasper = new StringBuilder("/jasperfiles/");
            sbJasper.append(jasperFileName + ".jasper");

            Connection connection = Conexion.getConexion(sf);
            //File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(sbJasper.toString()));
            File jasper = cargarArchivoJasper(sbJasper.toString());

            if (virtualizar) {
                String repositorioTemp = UtilesPropiertiesFile.getString(UtilesPropiertiesFile.RUTA_REPOSITORIO, mode)+"temp"+File.separator;
                log.info("Virtualizando en "+repositorioTemp);
                JRVirtualizer virtualizer = new JRSwapFileVirtualizer(1000, new JRSwapFile(repositorioTemp, 2048, 1024), true);
                JRVirtualizationHelper.setThreadVirtualizer(virtualizer);
                parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            }
            
            log.info("jasper.getPath() = " + jasper.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, connection);

            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

            if (abrirOtraPestaña) {
                Archivos.openFile(new File(outputFileName), context);
            }
            connection.close();
            return outputFileName;
        } catch (JRException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
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
    public static String savePdfFromDatabase(Map<String, Object> parametros, String jasperFileName, String outputFileName, SessionFactory sf,boolean virtualizar, String mode)
            throws JRException, Exception {
        try {
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

            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName+".pdf");
            connection.close();
            log.info("PDF creado con éxito");
            return outputFileName;
        } catch (JRException ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        } catch (Exception ex) {
            log.error(UtilesString.getDetalleException(ex));
            return null;
        }
    }
}

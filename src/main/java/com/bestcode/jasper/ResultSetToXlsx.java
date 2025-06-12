package com.bestcode.jasper;

import com.bestcode.misutiles.Conexion;
import com.bestcode.misutiles.UtilesString;
import com.google.common.io.CountingOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;

public class ResultSetToXlsx {

    private SXSSFWorkbook workbook;
    private Sheet sheet;
    private Font boldFont;
    private DataFormat format;
    private ResultSet resultSet;
    private FormatType[] formatTypes;
    private CellStyle cellStyle;
    private CellStyle headerStyle;
    private Map<String, CellStyle> mapEstilos;

    public static final FormatType FORMAT_INTEGER = FormatType.INTEGER;
    public static final FormatType FORMAT_FLOAT = FormatType.FLOAT;
    public static final FormatType FORMAT_TEXT = FormatType.TEXT;
    public static final FormatType FORMAT_DATE = FormatType.DATE;
    public static final FormatType FORMAT_MONEY = FormatType.MONEY;
    public static final FormatType FORMAT_PORCENTAJE = FormatType.PERCENTAGE;

    public ResultSetToXlsx(ResultSet resultSet, FormatType[] formatTypes, String sheetName) {
        this.workbook = new SXSSFWorkbook();
        this.resultSet = resultSet;
        this.sheet = workbook.createSheet(sheetName);
        this.boldFont = workbook.createFont();
        boldFont.setBold(true);
        this.format = workbook.createDataFormat();
        this.formatTypes = formatTypes;
        
        crearEstilos(workbook);
    }

    public ResultSetToXlsx(ResultSet resultSet, String sheetName) {
        this(resultSet, null, sheetName);
    }

    private FormatType getFormatType(Class<?> clazz) {
        if (clazz == Integer.class || clazz == Long.class) {
            return FormatType.INTEGER;
        } else if (clazz == Float.class || clazz == Double.class) {
            return FormatType.FLOAT;
        } else if (clazz == Timestamp.class || clazz == java.sql.Date.class) {
            return FormatType.DATE;
        } else {
            return FormatType.TEXT;
        }
    }

    public void generate(OutputStream outputStream) throws Exception {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            if (formatTypes != null && formatTypes.length != resultSetMetaData.getColumnCount()) {
                throw new IllegalStateException("Number of types is not identical to number of resultset columns.");
            }
            int currentRow = 0;
            Row row = sheet.createRow(currentRow);
            int numCols = resultSetMetaData.getColumnCount();
            boolean isAutoDecideFormatTypes = (formatTypes == null);
            if (isAutoDecideFormatTypes) {
                formatTypes = new FormatType[numCols];
            }
            for (int i = 0; i < numCols; i++) {
                String title = resultSetMetaData.getColumnName(i + 1);
                writeCell(row, i, title, FormatType.TEXT, boldFont, true);
                if (isAutoDecideFormatTypes) {
                    Class<?> clazz = Class.forName(resultSetMetaData.getColumnClassName(i + 1));
                    formatTypes[i] = getFormatType(clazz);
                }
            }
            currentRow++;
            while (resultSet.next()) {
                row = sheet.createRow(currentRow++);
                for (int i = 0; i < numCols; i++) {
                    Object value = resultSet.getObject(i + 1);
                    writeCell(row, i, value, formatTypes[i], false);
                }
            }
            workbook.write(outputStream);
        } finally {
            outputStream.close();
            workbook.dispose();
        }
    }

    public static void construir(SessionFactory sf, String query, String fileName, ResultSetToXlsx.FormatType[] formatTypes, FacesContext fc, Logger log) {
        try {
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName+".xlsx" + "\"");
            
            //Session sessionBD = com.rincon.coemds.controlador.HibernateUtil.getSessionFactory1().openSession();
            Connection con = Conexion.getConexion(sf);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ResultSetToXlsx rste = new ResultSetToXlsx(rs,
                    formatTypes,
                    fileName);
            try ( OutputStream output = fc.getExternalContext().getResponseOutputStream()) {
                rste.generate(output);

                try ( CountingOutputStream co = new CountingOutputStream(output)) {
                    fc.getExternalContext().setResponseContentLength((int) co.getCount());
                    co.close();
                } catch (IOException ex) {
                    log.error("Error al obtener el tamaño del stream " + ex.getMessage());
                    log.error(UtilesString.getDetalleException(ex));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            fc.responseComplete();
        }
    }

    public void generate(File file) throws Exception {
        generate(new FileOutputStream(file));
    }

    private void writeCell(Row row, int col, Object value, FormatType formatType, boolean isHeader) {
        writeCell(row, col, value, formatType, null, null, isHeader);
    }

    private void writeCell(Row row, int col, Object value, FormatType formatType, Font font, boolean isHeader) {
        writeCell(row, col, value, formatType, null, font, isHeader);
    }

    private void writeCell(Row row, int col, Object value, FormatType formatType, Short bgColor, Font font, boolean isHeader) {
        Cell cell = row.createCell(col);        
        cellStyle = mapEstilos.get(formatType.name());        
//
//        if (font != null) {
//            cellStyle.setFont(font);
//        }

        if (value == null) {
            return;
        }

        switch (formatType) {
            case TEXT:
                cell.setCellValue(value.toString());
                break;
            case INTEGER:
                cell.setCellValue(((Number) value).intValue());
                break;
            case FLOAT:
                cell.setCellValue(((Number) value).doubleValue());
                break;
            case DATE:
                cell.setCellValue((Timestamp) value);
                break;
            case MONEY:
                cell.setCellValue(((Number) value).doubleValue());
                break;
            case PERCENTAGE:
                cell.setCellValue(((Number) value).doubleValue());
                break;
        }

//        if (bgColor != null) {
//            cellStyle.setFillForegroundColor(bgColor);
//            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        }

        if (isHeader) {
            cell.setCellStyle(headerStyle);  // Solo para cabecera
        } else {        
            cell.setCellStyle(cellStyle);  // Para celdas normales
        }
    }

    private void crearEstilos(SXSSFWorkbook wb){
        
        mapEstilos = new HashMap();
        
        headerStyle = wb.createCellStyle();
        headerStyle.setDataFormat(format.getFormat("General"));        
        headerStyle.setFont(boldFont);
        
        // Establecer color de fondo
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        CellStyle c1 = wb.createCellStyle();
        c1.setDataFormat(format.getFormat("General"));
        mapEstilos.put("TEXT", c1);
        
        CellStyle c2 = wb.createCellStyle();
        c2.setDataFormat(format.getFormat("#,##0"));
        mapEstilos.put("INTEGER", c2);
        
        CellStyle c3 = wb.createCellStyle();
        c3.setDataFormat(format.getFormat("#,##0.00"));
        mapEstilos.put("FLOAT", c3);
        
        CellStyle c4 = wb.createCellStyle();
        c4.setDataFormat(format.getFormat("m/d/yy"));
        mapEstilos.put("DATE", c4);
        
        CellStyle c5 = wb.createCellStyle();
        c5.setDataFormat(format.getFormat("$#,##0.00"));
        mapEstilos.put("MONEY", c5);
        
        CellStyle c6 = wb.createCellStyle();
        c6.setDataFormat(format.getFormat("0.00%"));
        mapEstilos.put("PERCENTAGE", c6);        
    }
    
    public enum FormatType {
        TEXT,
        INTEGER,
        FLOAT,
        DATE,
        MONEY,
        PERCENTAGE
    }
}

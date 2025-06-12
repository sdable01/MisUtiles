package com.bestcode.misutiles;

import org.apache.poi.hssf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellUtil;

public class ResultSetToExcel {

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFFont boldFont;
    private HSSFDataFormat format;
    private ResultSet resultSet;
    private FormatType[] formatTypes;

    public static final FormatType FORMAT_INTEGER = FormatType.INTEGER;
    public static final FormatType FORMAT_FLOAT = FormatType.FLOAT;
    public static final FormatType FORMAT_TEXT = FormatType.TEXT;
    public static final FormatType FORMAT_DATE = FormatType.DATE;
    public static final FormatType FORMAT_MONEY = FormatType.MONEY;
    public static final FormatType FORMAT_PORCENTAJE = FormatType.PERCENTAGE;

    public ResultSetToExcel(ResultSet resultSet, FormatType[] formatTypes, String sheetName) {
        this.workbook = new HSSFWorkbook();
        this.resultSet = resultSet;
        sheet = workbook.createSheet(sheetName);
        boldFont = workbook.createFont();
        boldFont.setBold(true);
        format = workbook.createDataFormat();
        this.formatTypes = formatTypes;
    }

    public ResultSetToExcel(ResultSet resultSet, String sheetName) {
        this(resultSet, null, sheetName);
    }

    private FormatType getFormatType(Class _class) {
        if (_class == Integer.class || _class == Long.class) {
            return FormatType.INTEGER;
        } else if (_class == Float.class || _class == Double.class) {
            return FormatType.FLOAT;
        } else if (_class == Timestamp.class || _class == java.sql.Date.class) {
            return FormatType.DATE;
        } else {
            return FormatType.TEXT;
        }
    }

    public void generate(OutputStream outputStream) throws Exception {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            if (formatTypes != null && formatTypes.length != resultSetMetaData.getColumnCount()) {
                throw new IllegalStateException("Number of types is not identical to number of resultset columns. "
                        + "Number of types: " + formatTypes.length + ". Number of columns: " + resultSetMetaData.getColumnCount());
            }
            int currentRow = 0;
            HSSFRow row = sheet.createRow(currentRow);
            int numCols = resultSetMetaData.getColumnCount();
            boolean isAutoDecideFormatTypes;
            if (isAutoDecideFormatTypes = (formatTypes == null)) {
                formatTypes = new FormatType[numCols];
            }
            for (int i = 0; i < numCols; i++) {
                String title = resultSetMetaData.getColumnName(i + 1);
                writeCell(row, i, title, FormatType.TEXT, boldFont);
                if (isAutoDecideFormatTypes) {
                    Class _class = Class.forName(resultSetMetaData.getColumnClassName(i + 1));
                    formatTypes[i] = getFormatType(_class);
                }
            }
            currentRow++;
            // Write report rows
            while (resultSet.next()) {
                row = sheet.createRow(currentRow++);
                for (int i = 0; i < numCols; i++) {
                    Object value = resultSet.getObject(i + 1);
                    writeCell(row, i, value, formatTypes[i]);
                }
            }
            // Autosize columns
            for (int i = 0; i < numCols; i++) {
                sheet.autoSizeColumn((short) i);
            }
            workbook.write(outputStream);
        } finally {
            outputStream.close();
        }
    }

    public void generate(File file) throws Exception {
        generate(new FileOutputStream(file));
    }

    private void writeCell(HSSFRow row, int col, Object value, FormatType formatType)  {
        writeCell(row, col, value, formatType, null, null);
    }

    private void writeCell(HSSFRow row, int col, Object value, FormatType formatType, HSSFFont font)  {
        writeCell(row, col, value, formatType, null, font);
    }

    private void writeCell(HSSFRow row, int col, Object value, FormatType formatType,
            Short bgColor, HSSFFont font){
        HSSFCell cell = row.createCell(col);
        CellStyle cellStyle = cell.getCellStyle();
        DataFormat format = workbook.createDataFormat();
        if (value == null) {
            return;
        }
        if (font != null) {
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            cell.setCellStyle(style);
        }

        switch (formatType) {
            case TEXT:
                cell.setCellValue(value.toString());
                cell.setCellStyle(cellStyle);
                break;
            case INTEGER:
                cell.setCellValue(((Number) value).intValue());
                cellStyle.setDataFormat(format.getFormat("#,##0"));
                cell.setCellStyle(cellStyle);
//                HSSFCellUtil.setCellStyleProperty(cell, workbook, CellUtil.DATA_FORMAT,
//                        HSSFDataFormat.getBuiltinFormat(("#,##0")));
                break;
            case FLOAT:
                cell.setCellValue(((Number) value).doubleValue());
                cellStyle.setDataFormat(format.getFormat("#,##0.00"));
                cell.setCellStyle(cellStyle);
//                CellUtil.setCellStyleProperty(cell, workbook, CellUtil.DATA_FORMAT,
//                        HSSFDataFormat.getBuiltinFormat(("#,##0.00")));
                break;
            case DATE:
                cell.setCellValue((Timestamp) value);
                cellStyle.setDataFormat(format.getFormat("m/d/yy"));
                cell.setCellStyle(cellStyle);
//                HSSFCellUtil.setCellStyleProperty(cell, workbook, CellUtil.DATA_FORMAT,
//                        HSSFDataFormat.getBuiltinFormat(("m/d/yy")));
                break;
            case MONEY:
                cell.setCellValue(((Number) value).intValue());
                cellStyle.setDataFormat(format.getFormat("$#,##0.00;-$#,##0.00"));
                cell.setCellStyle(cellStyle);
//                HSSFCellUtil.setCellStyleProperty(cell, workbook,
//                        CellUtil.DATA_FORMAT, format.getFormat("$#,##0.00;-$#,##0.00"));
                if (((Number) value).intValue() < 0) {
//                    HSSFCellUtil.setCellStyleProperty(cell, workbook,
//                            CellUtil.FILL_FOREGROUND_COLOR, IndexedColors.RED.getIndex());
                }
                break;
            case PERCENTAGE:
                cell.setCellValue(((Number) value).doubleValue());
                cellStyle.setDataFormat(format.getFormat("0.00%"));
                cell.setCellStyle(cellStyle);
//                HSSFCellUtil.setCellStyleProperty(cell, workbook,
//                        CellUtil.DATA_FORMAT, HSSFDataFormat.getBuiltinFormat("0.00%"));
        }
        if (bgColor != null) {
            // Establece el color de fondo
            cellStyle.setFillForegroundColor(bgColor);
            // Establece el patrón de relleno
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Aplica el estilo modificado a la celda
            cell.setCellStyle(cellStyle);
//            HSSFCellUtil.setCellStyleProperty(cell, workbook, CellUtil.FILL_FOREGROUND_COLOR, bgColor);
//            HSSFCellUtil.setCellStyleProperty(cell, workbook, CellUtil.FILL_PATTERN, HSSFCellStyle.SOLID_FOREGROUND);
        }
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

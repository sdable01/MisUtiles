package com.bestcode.misutiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author giftsam
 */
public class LeerArchivoExcel {

        
    /**
     * This method is used to read the data's from an excel file.
     *
     * @param fileName - Name of the excel file.
     */
    public static ArrayList getDatos(File fileName) {

        ArrayList columnas = new ArrayList();

        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStream = new FileInputStream(fileName);

            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();

            int clave = 1;
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
////            columnas.put("columnas", hssfRow.getPhysicalNumberOfCells());//Agrego el número de celdas de cada fila (columnas) bajo la llave "cantidad".

                ArrayList celdas = new ArrayList();
                int contadorColumnas = 1;
                HashMap fila = new HashMap();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    fila.put(contadorColumnas, hssfCell.toString());
                    contadorColumnas++;
                    //celdas.add(hssfCell.toString());
                }

                columnas.add(fila);
//            columnas.put(clave, fila);
                clave++;

            }
//        columnas.put("filas", hssfSheet.getPhysicalNumberOfRows());;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnas;
    }

    /**
     * datos= ArrayList y cantidadColumnas = int
     *
     * @param inputStream
     * @return
     */
    public static Map getDatos2(InputStream inputStream) throws OfficeXmlFileException {
        ArrayList filas = new ArrayList();
        short maxColIndex = 0;
        Map resultado = new HashMap();

        try {
            // Create a new instance for POIFSFileSystem class
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(inputStream);

            //Create a new instance for HSSFWorkBook Class
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            HSSFRow cabecera = hssfSheet.getRow(0);
            String[] arrayCabeceras = new String[cabecera.getLastCellNum()];
            for (int c = 0; c < cabecera.getLastCellNum(); c++) {
                arrayCabeceras[c] = cabecera.getCell(c).getStringCellValue();
            }

            Iterator rowIterator = hssfSheet.rowIterator();
            int clave = 1;
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                if (row.getRowNum() < 65000) {//En las filas superiores a 65000 tengo los valores de las listas
                    short minColIndex = row.getFirstCellNum();
//                    System.out.println("row " + row.getRowNum());
                    if (row.getRowNum() == 0) {//Solo si el la primera fila (cabecera), obtengo el número de columnas
                        maxColIndex = row.getLastCellNum();
                    }
                    Map celdas = new HashMap();
                    for (short colIndex = minColIndex; colIndex < maxColIndex; colIndex++) {

                        HSSFCell cell = row.getCell(colIndex);

                        if (cell == null) {
                            //blank/undefined cell
//                            System.out.println("nulo " + colIndex);
                            celdas.put(colIndex, " ");
                        } else {
                            DataFormatter dataFormatter = new DataFormatter();
                            String cellValue = dataFormatter.formatCellValue(cell);
                            celdas.put(cell.getColumnIndex(), cellValue);
                            //defined cell which still could be of type HSSFCell.CELL_TYPE_BLANK or contain an empty string
                        }
                    }
                    filas.add(celdas);
                }

            }
            resultado.put("datos", filas);
            resultado.put("cantidadColumnas", maxColIndex);
            resultado.put("cabeceras", arrayCabeceras);
            //        columnas.put("filas", hssfSheet.getPhysicalNumberOfRows());;
        } catch (OfficeXmlFileException ex) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Con cell.setCellType(Cell.CELL_TYPE_STRING) lo que afecta a campos de
     * fecha
     *
     * @param inputStream
     * @return
     * @throws OfficeXmlFileException
     */
    public static Map getDatos3(InputStream inputStream) throws OfficeXmlFileException {
        ArrayList filas = new ArrayList();
        short maxColIndex = 0;
        Map resultado = new HashMap();

        try {
            // Create a new instance for POIFSFileSystem class
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(inputStream);

            //Create a new instance for HSSFWorkBook Class
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            HSSFRow cabecera = hssfSheet.getRow(0);
            String[] arrayCabeceras = new String[cabecera.getLastCellNum()];
            for (int c = 0; c < cabecera.getLastCellNum(); c++) {
                arrayCabeceras[c] = cabecera.getCell(c).getStringCellValue();
            }

            Iterator rowIterator = hssfSheet.rowIterator();
            int clave = 1;
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                if (row.getRowNum() < 65000) {//En las filas superiores a 65000 tengo los valores de las listas
                    short minColIndex = row.getFirstCellNum();
//                    System.out.println("row " + row.getRowNum());
                    if (row.getRowNum() == 0) {//Solo si el la primera fila (cabecera), obtengo el número de columnas
                        maxColIndex = row.getLastCellNum();
                    }
                    Map celdas = new HashMap();
                    for (short colIndex = minColIndex; colIndex < maxColIndex; colIndex++) {

                        HSSFCell cell = row.getCell(colIndex);

                        if (cell == null) {
                            //blank/undefined cell
//                            System.out.println("nulo " + colIndex);
                            celdas.put(colIndex, " ");
                        } else {
                            cell.setCellType(STRING);
//                            System.out.println(cell.toString() + " " + colIndex + "\t");
                            celdas.put(cell.getColumnIndex(), cell.toString());
                            //defined cell which still could be of type HSSFCell.CELL_TYPE_BLANK or contain an empty string
                        }
                    }

                    filas.add(celdas);
                }

            }
            resultado.put("datos", filas);
            resultado.put("cantidadColumnas", maxColIndex);
            resultado.put("cabeceras", arrayCabeceras);
            //        columnas.put("filas", hssfSheet.getPhysicalNumberOfRows());;
        } catch (OfficeXmlFileException ex) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static ArrayList getDatos4(File fileName) {

        ArrayList columnas = new ArrayList();

        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStream = new FileInputStream(fileName);

            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fileInputStream);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            HSSFRow cabecera = hssfSheet.getRow(0);
            String[] arrayCabeceras = new String[cabecera.getLastCellNum()];
            for (int c = 0; c < cabecera.getLastCellNum(); c++) {
                arrayCabeceras[c] = cabecera.getCell(c).getStringCellValue();
            }

            Iterator rowIterator = hssfSheet.rowIterator();

            int clave = 1;
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
////            columnas.put("columnas", hssfRow.getPhysicalNumberOfCells());//Agrego el número de celdas de cada fila (columnas) bajo la llave "cantidad".

                ArrayList celdas = new ArrayList();
                int contadorColumnas = 1;
                HashMap fila = new HashMap();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    fila.put(contadorColumnas, hssfCell.toString());
                    contadorColumnas++;
                    //celdas.add(hssfCell.toString());
                }

                columnas.add(fila);
//            columnas.put(clave, fila);
                clave++;

            }
//        columnas.put("filas", hssfSheet.getPhysicalNumberOfRows());;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnas;
    }

    /**
     * datos= ArrayList y cantidadColumnas = int
     *
     * @param inputStream
     * @return
     */
    public static Map getDatos5(InputStream inputStream, Logger log, boolean debugmode) throws OfficeXmlFileException {
        ArrayList filas = new ArrayList();
        short maxColIndex = 0;
        Map resultado = new HashMap();

        try {
            // Create a new instance for POIFSFileSystem class
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(inputStream);

            //Create a new instance for HSSFWorkBook Class
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            
            HSSFRow cabecera = hssfSheet.getRow(0);
            String[] arrayCabeceras = new String[cabecera.getLastCellNum()];
            for (int c = 0; c < cabecera.getLastCellNum(); c++) {
                arrayCabeceras[c] = cabecera.getCell(c).getStringCellValue();
            }

            Iterator rowIterator = hssfSheet.rowIterator();
            int clave = 1;
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                if (row.getRowNum() < 65000) {//En las filas superiores a 65000 tengo los valores de las listas
                    short minColIndex = row.getFirstCellNum();
//                    System.out.println("row " + row.getRowNum());
                    if (row.getRowNum() == 0) {//Solo si el la primera fila (cabecera), obtengo el número de columnas
                        maxColIndex = row.getLastCellNum();
                    }
                    Map celdas = new HashMap();
                    for (int colIndex = minColIndex; colIndex < maxColIndex; colIndex++) {

                        HSSFCell cell = row.getCell(colIndex);

                        if (cell == null) {
                            celdas.put(colIndex, " ");
                        } else {
                            HSSFCellStyle st = cell.getCellStyle();
                            DataFormatter dataFormatter = new DataFormatter();
                            Object cellValue = null;

                            switch (st.getDataFormatString()) {
                                case "General":
                                        cellValue = dataFormatter.formatCellValue(cell);
                                    break;
                                case "m/d/yy":
                                    try{
                                        cellValue = cell.getDateCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "d-mmm-yy":
                                    try{
                                        cellValue = cell.getDateCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "d-mmm":
                                    try{
                                        cellValue = cell.getDateCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "mmm-yy":
                                    try{
                                        cellValue = cell.getDateCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "m/d/yy h:mm":
                                    try{
                                        cellValue = cell.getDateCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "0":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "0.00":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "#,##0":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "#,##0.00":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "\"$\"#,##0_);(\"$\"#,##0)":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "\"$\"#,##0_);Red":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "\"$\"#,##0.00_);(\"$\"#,##0.00)":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "\"$\"#,##0.00_);Red":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "0%":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                case "0.00%":
                                    try{
                                        cellValue = cell.getNumericCellValue();
                                    }catch(IllegalStateException ex){
                                        cellValue = cell.getStringCellValue();
                                    }
                                    break;
                                default:
                                    cellValue = dataFormatter.formatCellValue(cell);
                                    break;
                            }
                            celdas.put(cell.getColumnIndex(), cellValue);
                            if(debugmode){
                                log.info(UtilesString.concatenar("row:",String.valueOf(row.getRowNum()+1)," col:",String.valueOf(colIndex+1)," - ",st.getDataFormatString(),"(style) - ",cellValue.getClass().getSimpleName(),"(class) - ",cellValue.toString(),"(toString)"));
                            }
                            //defined cell which still could be of type HSSFCell.CELL_TYPE_BLANK or contain an empty string
                        }
                    }
                    filas.add(celdas);
                }

            }
            resultado.put("datos", filas);
            resultado.put("cantidadColumnas", maxColIndex);
            resultado.put("cabeceras", arrayCabeceras);
            //        columnas.put("filas", hssfSheet.getPhysicalNumberOfRows());;
        } catch (OfficeXmlFileException ex) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static Map<String, Object> getDatos6(InputStream inputStream, Logger log, boolean debugmode) throws OfficeXmlFileException {
        ArrayList<Map<Integer, Object>> filas = new ArrayList<>();
        short maxColIndex = 0;
        Map<String, Object> resultado = new HashMap<>();

        try {
            // Create a new instance for POIFSFileSystem class
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(inputStream);

            // Create a new instance for HSSFWorkbook class
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            HSSFRow cabecera = hssfSheet.getRow(0);
            String[] arrayCabeceras = new String[cabecera.getLastCellNum()];
            for (int c = 0; c < cabecera.getLastCellNum(); c++) {
                arrayCabeceras[c] = cabecera.getCell(c).getStringCellValue();
            }

            Iterator<?> rowIterator = hssfSheet.rowIterator();
            int clave = 1;
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                if (row.getRowNum() < 65000) { // En las filas superiores a 65000 tengo los valores de las listas
                    short minColIndex = row.getFirstCellNum();
                    if (row.getRowNum() == 0) { // Solo si es la primera fila (cabecera), obtengo el número de columnas
                        maxColIndex = row.getLastCellNum();
                    }
                    Map<Integer, Object> celdas = new HashMap<>();
                    for (int colIndex = minColIndex; colIndex < maxColIndex; colIndex++) {

                        HSSFCell cell = row.getCell(colIndex);

                        if (cell == null) {
                            celdas.put(colIndex, " ");
                        } else {
                            DataFormatter dataFormatter = new DataFormatter();
                            Object cellValue;

                            switch (cell.getCellType()) {
                                case STRING:
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        cellValue = cell.getDateCellValue();
                                    } else {
                                        cellValue = cell.getNumericCellValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    cellValue = cell.getBooleanCellValue();
                                    break;
                                case FORMULA:
                                    cellValue = dataFormatter.formatCellValue(cell);
                                    break;
                                case BLANK:
                                    cellValue = "";
                                    break;
                                default:
                                    cellValue = dataFormatter.formatCellValue(cell);
                                    break;
                            }
                            if(debugmode){
                                log.info(UtilesString.concatenar("row:",String.valueOf(row.getRowNum()+1)," col:",String.valueOf(colIndex+1)," - ",cell.getCellStyle().getDataFormatString(),"(style) - ",cellValue.getClass().getSimpleName(),"(class) - ",cellValue.toString(),"(toString)"));
                            }
                            celdas.put(cell.getColumnIndex(), cellValue);
                        }
                    }
                    filas.add(celdas);
                }
            }
            resultado.put("datos", filas);
            resultado.put("cantidadColumnas", maxColIndex);
            resultado.put("cabeceras", arrayCabeceras);

        } catch (OfficeXmlFileException ex) {
            // Manejo específico de OfficeXmlFileException
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public static int getCantidadColumnas(InputStream inputStream) {
        try {
            // Create a new instance for POIFSFileSystem class
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(inputStream);

            //Create a new instance for HSSFWorkBook Class
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();
            short maxColIndex = 0;
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                maxColIndex = row.getLastCellNum();

            }
            return maxColIndex;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void imprimirEnConsola(File archivo) {
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStream = new FileInputStream(archivo);

            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();

            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();

                short minColIndex = row.getFirstCellNum();
                short maxColIndex = row.getLastCellNum();
                for (short colIndex = minColIndex; colIndex < maxColIndex; colIndex++) {
                    HSSFCell cell = row.getCell(colIndex);

                    if (cell == null) {
                        //blank/undefined cell
                        System.out.println("nulo");
                    } else {
                        System.out.println(cell.toString() + "\t");
                        //defined cell which still could be of type HSSFCell.CELL_TYPE_BLANK or contain an empty string
                    }
//                Iterator iterator = hssfRow.cellIterator();
//                List cellTempList = new ArrayList();
//                while (iterator.hasNext()) {
//                    HSSFCell hssfCell = (HSSFCell) iterator.next();
//                    System.out.println("hssfCell = " + hssfCell.toString()+"\t");
//                    cellTempList.add(hssfCell);
//                }
//                cellDataList.add(cellTempList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < cellDataList.size(); i++) {
//            List cellTempList = (List) cellDataList.get(i);
//            for (int j = 0; j < cellTempList.size(); j++) {
//                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
////                System.out.println(hssfCell.getColumnIndex() + "\t");
//                int cellType = hssfCell.getCellType();
//                String stringCellValue = null;
//                if (cellType == HSSFCell.CELL_TYPE_BLANK) {
//                    stringCellValue = "null";
//                } else {
//                    stringCellValue = hssfCell.toString();
//                }
//
////                System.out.print(getCellValue(hssfCell) + "\t");
//            }
//            System.out.println();
//        }
    }

    public static void imprimirEnConsola2(InputStream inputStream) {

        List cellDataList = new ArrayList();
        try {

            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(inputStream);


            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();

            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            for (int j = 0; j < cellTempList.size(); j++) {
                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
                String stringCellValue = hssfCell.toString();
                System.out.print(stringCellValue + "\t");
            }
            System.out.println();
        }
    }

//    public static void main(String[] args){
//        imprimirEnConsola(new File("C:/recepcionTarjetaExcel.xls"));
//
//    }
}

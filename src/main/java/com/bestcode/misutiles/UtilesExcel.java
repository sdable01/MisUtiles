package com.bestcode.misutiles;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.util.XMLHelper;
import org.slf4j.Logger;
import org.xml.sax.Attributes;

/**
 *
 * @author jimmy
 */
public class UtilesExcel {

    public static Map<String, Object> getDatosXlsxStreaming(InputStream inputStream, Logger log, boolean debugmode) throws NumberFormatException, Exception {
        Map<String, Object> resultado = new HashMap<>();
        ArrayList<Map<Integer, Object>> filas = new ArrayList<>();
        String[] cabeceras = null;
        short maxColIndex = 0;

        try ( OPCPackage pkg = OPCPackage.open(inputStream)) {
            XSSFReader reader = new XSSFReader(pkg);
            SharedStringsTable sst = (SharedStringsTable) reader.getSharedStringsTable();
            StylesTable styles = reader.getStylesTable();

            XMLReader parser = XMLReaderFactory.createXMLReader();
            SheetHandler handler = new SheetHandler(sst, styles, filas, cabeceras, debugmode, log);
            parser.setContentHandler(handler);

            try ( InputStream sheet = reader.getSheetsData().next()) {
                parser.parse(new InputSource(sheet));
            }

            maxColIndex = (short) handler.getColumnCount();
        }

        resultado.put("datos", filas);
        resultado.put("cantidadColumnas", maxColIndex);

        cabeceras = new String[maxColIndex];
        Map<Integer, Object> fila = filas.get(0);
        Set<Integer> keySet = fila.keySet();
        for (Integer key : keySet) {
            cabeceras[(key)] = (String) fila.get(key);
        }

        resultado.put("cabeceras", cabeceras);

        return resultado;
    }

    static class SheetHandler extends DefaultHandler {

        private SharedStringsTable sst;
        private DataFormatter formatter = new DataFormatter();
        private List<Map<Integer, Object>> filas;
        private String[] cabeceras;
        private boolean debugmode;
        private Logger log;

        private Map<Integer, Object> currentRow;
        private int currentCol = -1;
        private String currentValue;
        private boolean isString;
        private int maxColumns = 0;

        public SheetHandler(SharedStringsTable sst, StylesTable styles, List<Map<Integer, Object>> filas, String[] cabeceras, boolean debugmode, Logger log) {
            this.sst = sst;
            this.filas = filas;
            this.cabeceras = cabeceras;
            this.debugmode = debugmode;
            this.log = log;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("row")) {
                currentRow = new HashMap<>();
                currentCol = -1;
            } else if (qName.equals("c")) {
                isString = "s".equals(attributes.getValue("t"));
                String cellRef = attributes.getValue("r");
                currentCol = getColumnIndex(cellRef);
                currentValue = null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (currentValue == null) {
                currentValue = "";
            }
            currentValue += new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("v")) {
                try {
                    Object value;
                    if (isString) {
                        value = sst.getItemAt(Integer.parseInt(currentValue)).getString();
                    } else {
                        // Verificar si el valor de la celda es una fórmula antes de procesarlo
                        if (currentValue.matches(".*[=+\\-*/()].*")) {
                            value = "CELDA CON FÓRMULA: " + currentValue;
                        } else {
                            value = formatter.formatRawCellContents(Double.parseDouble(currentValue), -1, "");
                        }
                    }
                    currentRow.put(currentCol, value);
                } catch (NumberFormatException e) {
                    log.error("Error de formato en la celda {}: {}", currentCol, currentValue, e);
                    currentRow.put(currentCol, "ERROR: CELDA NO PROCESADA");
                }
            } else if (qName.equals("c") && !currentRow.containsKey(currentCol)) {
                currentRow.put(currentCol, null);
            } else if (qName.equals("row")) {
                if (filas.isEmpty()) {
                    maxColumns = currentRow.size();
                }
                Map<Integer, Object> completeRow = new HashMap<>();
                for (int i = 0; i < maxColumns; i++) {
                    completeRow.put(i, currentRow.getOrDefault(i, null));
                }
                filas.add(completeRow);
            }
        }

        public int getColumnCount() {
            return maxColumns;
        }

        private int getColumnIndex(String cellRef) {
            int col = -1;
            for (int i = 0; i < cellRef.length(); i++) {
                if (Character.isDigit(cellRef.charAt(i))) {
                    break;
                }
                col = (col + 1) * 26 + (cellRef.charAt(i) - 'A');
            }
            return col;
        }
    }
}

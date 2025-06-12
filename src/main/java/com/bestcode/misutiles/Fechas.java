package com.bestcode.misutiles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Clase que contiene métodos útiles para el manejo de fechas, como diferencia
 * entre fechas, transformación de objetos Date a String en distintos formatos.
 *
 * @author Jimmy Gutiérrez B.
 * @version 1.0
 */
public class Fechas {

    public static int horasEntreFechas(Date fechaDesde, Date fechaHasta, boolean contarSabadoDomingo, List<Date> listaFechasNoLaborables) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(fechaDesde);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fechaHasta);
        int dif = fecha1.compareTo(fecha2);

        int feriadosIncluidos = 0;
        int sabadoDomingoIncluidos = 0;

        if (listaFechasNoLaborables != null) {//Parámetro podría venir null, de ser así, excluyo
            for (Date f : listaFechasNoLaborables) {
                if (f.after(fechaDesde) && f.before(fechaHasta)) {
                    feriadosIncluidos++;
                }
            }
        }

        if (contarSabadoDomingo == false) {//Si no se cuentan, debo identificarlos y restarlos (excluirlos) de la cantidad de días retornada
            while (fecha1.before(fecha2) || fecha1.equals(fecha2)) {
                if (fecha1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fecha1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {//Si es sábado o domingo...
                    sabadoDomingoIncluidos++;
                }
                //se suma 1 dia para hacer la validacion del siguiente dia.
                fecha1.add(Calendar.DATE, 1);
            }
        }

        long diff = fechaHasta.getTime() - fechaDesde.getTime();
        double horas = Math.floor(diff / 3600000L); // 3600 * 24 * 1000

        return ((int) horas - (feriadosIncluidos * 24) - (sabadoDomingoIncluidos * 24));
    }

    public static int diasEntreFechas(Date fechaDesde, Date fechaHasta, boolean contarSabadoDomingo, List<Date> listaFechasNoLaborables) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(fechaDesde);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fechaHasta);
        int dif = fecha1.compareTo(fecha2);

        long diff = fechaHasta.getTime() - fechaDesde.getTime();
        double dias2 = Math.floor(diff / 86400000L); // 3600 * 24 * 1000

        int feriadosIncluidos = 0;
        int sabadoDomingoIncluidos = 0;

        if (listaFechasNoLaborables != null) {//Parámetro podría venir null, de ser así, excluyo
            for (Date f : listaFechasNoLaborables) {
                if (f.after(fechaDesde) && f.before(fechaHasta)) {
                    feriadosIncluidos++;
                }
            }
        }

        if (contarSabadoDomingo == false) {//Si no se cuentan, debo identificarlos y restarlos (excluirlos) de la cantidad de días retornada
            while (fecha1.before(fecha2) || fecha1.equals(fecha2)) {
                System.out.println("Recorriendo el día " + fecha1.getTime().toString() + " - dýa de la semana: " + fecha1.get(Calendar.DAY_OF_WEEK));
                if (fecha1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || fecha1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {//Si es sábado o domingo...
                    sabadoDomingoIncluidos++;
                }
                //se suma 1 dia para hacer la validacion del siguiente dia.
                fecha1.add(Calendar.DATE, 1);
            }
        }
        return ((int) (dias2 - feriadosIncluidos - sabadoDomingoIncluidos));
    }

    /**
     * Método que obtiene la diferencia en días de las dos fechas recibidas como
     * parámetro de entrada.
     *
     * @param fechaInicial - Objeto Date de inicio.
     * @param fechaFinal - Objeto Date al final.
     * @return - Un número entero que representa la cantidad de días de
     * diferencia entre ambas fechas.
     */
    public static int diasDeDiferencia(Date fechaInicial, Date fechaFinal) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(fechaInicial);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fechaFinal);

        int dif = fecha1.compareTo(fecha2);

        long diff = fechaFinal.getTime() - fechaInicial.getTime();
        double dias2 = Math.floor(diff / 86400000L); // 3600 * 24 * 1000
        return ((int) dias2);
    }

    public static int mesesDeDiferencia(Date fechaInicial, Date fechaFinal) {
        Calendar inicio = new GregorianCalendar();
        Calendar fin = new GregorianCalendar();
        inicio.setTime(fechaInicial);
        fin.setTime(fechaFinal);
        int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        int difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        return difM;
    }

    public static int aniosDeDiferencia(Date fechaInicial, Date fechaFinal) {
        Calendar inicio = new GregorianCalendar();
        Calendar fin = new GregorianCalendar();
        inicio.setTime(fechaInicial);
        fin.setTime(fechaFinal);
        int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);

        inicio.set(Calendar.YEAR, fin.get(Calendar.YEAR));
//        System.out.println("nuevo inicio "+Fechas.formatearFecha5(inicio.getTime()));
        if (inicio.compareTo(fin) > 0) {
            difA--;
        }
//        System.out.println("inicio.compareTo(fin) = " + inicio.compareTo(fin));

        return difA;
    }

    /**
     * Método que obtiene la diferencia en HORAS de las dos fechas recibidas
     * como parámetro de entrada.
     *
     * @param fechaInicial - Objeto Date de inicio.
     * @param fechaFinal - Objeto Date al final.
     * @return - Un número entero que representa la cantidad de horas de
     * diferencia entre ambas fechas.
     */
    public static int horasDeDiferencia(Date fechaInicial, Date fechaFinal) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(fechaInicial);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fechaFinal);
        int dif = fecha1.compareTo(fecha2);

        long diff = fechaFinal.getTime() - fechaInicial.getTime();
        double horas = Math.floor(diff / 3600000L); // 3600 * 24 * 1000
        return ((int) horas);
    }

    /**
     * Método que obtiene la diferencia en MINUTOS de las dos fechas recibidas
     * como parámetro de entrada.
     *
     * @param fechaInicial - Objeto Date de inicio.
     * @param fechaFinal - Objeto Date al final.
     * @return - Un número entero que representa la cantidad de minutos de
     * diferencia entre ambas fechas.
     */
    public static int minutosDeDiferenciaEntreFechas(Date fechaInicial, Date fechaFinal) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(fechaInicial);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fechaFinal);
        int dif = fecha1.compareTo(fecha2);

        long diff = fechaFinal.getTime() - fechaInicial.getTime();
        double minutos = Math.floor((diff / 60000)); // 3600 * 24 * 1000
        return ((int) minutos);
    }

    /**
     * Recibe un Date y lo transforma a String en formato "EEEEEEEEE dd 'de'
     * MMMMM 'de' yyyy"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha1(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'de' yyyy");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha1");
            return "El objeto fecha recibido es nulo";
        }

    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd/MM/yyyy"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha2(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha2");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd MMM yyyy"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha3(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha3");
            return "El objeto fecha recibido es nulo";
        }

    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd/MMM/yy hh:mm"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha4(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yy HH:mm");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha4");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd-MM-yyyy"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha5(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha5");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd-MM-yyyy HH:mm:ss"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha6(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha6");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "yyyy/MM/dd"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha7(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha7");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd-MMM"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha8(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MMM");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha8");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "yyyyMMdd"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha9(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha9");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "yyyy-MM-dd"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha10(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha10");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Formato yyyy-MM-dd HH:mm:ss
     *
     * @param fecha
     * @return
     */
    public static String formatearFecha11(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha11");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Formato yyyy/MM/dd HH:mm
     *
     * @param fecha
     * @return
     */
    public static String formatearFecha12(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha12");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Recibe un Date y lo transforma a String en formato "dd 'de' MMMMM 'de'
     * yyyy"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha13(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha13");
            return "El objeto fecha recibido es nulo";
        }

    }

    /**
     * Recibe un Date y lo transforma a String en formato "yyyy-MM-ddTHH:mm:ss"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha14(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formato.format(fecha).replace(" ", "T");
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha14");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Formato yyyyMMddHHmmss
     *
     * @param fecha
     * @return
     */
    public static String formatearFecha15(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha15");
            return "El objeto fecha recibido es nulo";
        }
    }

    /**
     * Formato yyyy-MM
     *
     * @param fecha
     * @return
     */
    public static String formatearFecha16(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo en formatearFecha16");
            return "El objeto fecha recibido es nulo";
        }
    }

      /**
     * Recibe un Date y lo transforma a String en formato "MM-dd-yyyy"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha17(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha17");
            return "El objeto fecha recibido es nulo";
        }
    }

      /**
     * Recibe un Date y lo transforma a String en formato "HH:mm"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha18(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha18");
            return "El objeto fecha recibido es nulo";
        }
    }
    
    /**
     * Recibe un Date y lo transforma a String en formato "HH:mm:ss"
     *
     * @param fecha - Date del paqueta java.util.Date
     * @return - Cadena de texto con la fecha formateada.
     */
    public static String formatearFecha19(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
            return formato.format(fecha);
        } else {
            System.out.println("El objeto fecha recibido es nulo formatearFecha19");
            return "El objeto fecha recibido es nulo";
        }
    }
    
    /**
     * El formato de la cadena de texto debe ser 03-12-2010 ó 03/12/2010
     *
     * @param fecha
     * @return
     */
    public static Date StringToDate(String fecha) {
        try {
            if (fecha.length() == 10) {
                Date date = new Date(Integer.parseInt(fecha.substring(6, 10)) - 1900, Integer.parseInt(fecha.substring(3, 5)) - 1, Integer.parseInt(fecha.substring(0, 2)));
                return date;
            } else if (fecha.length() == 11) {
//                System.out.println("intentando convertir en numero el valor "+fecha.substring(3, 6));
                Date date = new Date(Integer.parseInt(fecha.substring(7, 11)) - 1900, numeroMes(fecha.substring(3, 6), fecha), Integer.parseInt(fecha.substring(0, 2)));
                return date;
            } else {
                System.out.println("Error inesperado obteniendo objeto Date en Fecha.StringToDate(String): " + fecha);
                return null;
            }

        } catch (NumberFormatException ex) {
            System.out.println("Error en la fecha, posiblemente la cadena posee letras o símbolos no admitidos: " + fecha);
            return null;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("El largo de la cadena de texto no es válido, debe ser de 10 caracteres ej: 04-12-2010");
            return null;
        } catch (NullPointerException e) {
//            System.out.println("La cadena de texto recibida es nula");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * El formato de fecha debe ser 20170528 (yyyyMMdd). Si además tiene a la
     * derecha hora, minutos y segundo, serán ignorados
     *
     * @param fecha
     * @return
     */
    public static Date StringToDate3(String fecha) {
        try {
            if (fecha.length() >= 7) {
                Date date = new Date(Integer.parseInt(fecha.substring(0, 4)) - 1900, Integer.parseInt(fecha.substring(4, 6)) - 1, Integer.parseInt(fecha.substring(6, 8)));
                return date;
            } else {
                System.out.println("Error inesperado obteniendo objeto Date en Fecha.StringToDate(String)");
                return null;
            }

        } catch (NumberFormatException ex) {
            System.out.println("Error en la fecha, posiblemente la cadena posee letras o símbolos no admitidos: " + fecha);
            return null;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("El largo de la cadena de texto no es válido, debe ser de 10 caracteres ej: 04-12-2010");
            return null;
        } catch (NullPointerException e) {
//            System.out.println("La cadena de texto recibida es nula");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * El formato de fecha debe ser 20170528 (yyyy-MM-dd). Si además tiene a la
     * derecha hora, minutos y segundo, serán ignorados
     *
     * @param fecha
     * @return
     */
    public static Date stringToDate2(String fecha) {
        try {
            if (fecha.length() >= 9) {
                Date date = new Date(Integer.parseInt(fecha.substring(0, 4)) - 1900, Integer.parseInt(fecha.substring(5, 7)) - 1, Integer.parseInt(fecha.substring(8, 10)));
                return date;
            } else {
                System.out.println("Error inesperado obteniendo objeto Date en Fecha.StringToDate(String)");
                return null;
            }

        } catch (NumberFormatException ex) {
            System.out.println("Error en la fecha, posiblemente la cadena posee letras o símbolos no admitidos: " + fecha);
            return null;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("El largo de la cadena de texto no es válido, debe ser de 10 caracteres ej: 04-12-2010");
            return null;
        } catch (NullPointerException e) {
//            System.out.println("La cadena de texto recibida es nula");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Convierte a Date un String con formato dd-MMM-yyyy
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date stringToDate3(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date fecha = formatter.parse(strDate);
        return fecha;
    }

    /**
     * Convierte a Date un String con formato yyyy-MM-dd
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date stringToDate4(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formatter.parse(strDate);
        return fecha;
    }

    /**
     * Convierte a Date un String con formato yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date stringToDate5(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = formatter.parse(strDate);
        return fecha;
    }
    
    /**
     * Convierte a Date un String con formato yyyy-MM-ddTHH:mm:ss
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date stringToDate6(String strDate) throws ParseException {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fecha = formatter.parse(strDate.replace("T", " "));
            return fecha;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date sumarHoras(Date fecha, int horas) {
        if (fecha != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.HOUR, horas);
            fecha = calendar.getTime();
            return fecha;
        } else {
            return null;
        }
    }

    public static Date sumarMinutos(Date fecha, int minutos) {
        if (fecha != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.MINUTE, minutos);
            fecha = calendar.getTime();
            return fecha;
        } else {
            return null;
        }
    }

    public static Date sumarSegundos(Date fecha, int segundos) {
        if (fecha != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.SECOND, segundos);
            fecha = calendar.getTime();
            return fecha;
        } else {
            return null;
        }
    }

    public static Date sumarDias(Date fecha, int dias) {
        if (fecha != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.DATE, dias);
            fecha = calendar.getTime();
            return fecha;
        } else {
            return null;
        }
    }

    public static Date sumarMeses(Date fecha, int meses) {
        if (fecha != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.MONTH, meses);
            fecha = calendar.getTime();
            return fecha;
        } else {
            return null;
        }
    }

    public static Date sumarAnios(Date fecha, int anios) {
        if (fecha != null) {

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.YEAR, anios);
            fecha = calendar.getTime();
            return fecha;
        } else {
            return null;
        }
    }

    /**
     * Retorna el nombre del mes en base al número de mes que se le pase por
     * parametro, los números de los meses es entre 1 y 12
     *
     * @param mes - Número entre 1 y 12
     * @return Nombre del mes Ej: Septiembre.
     */
    public static String getNombreMes(int mes) {
        String nombreMes = null;

        switch (mes) {
            case 1:
                nombreMes = "Enero";
                break;
            case 2:
                nombreMes = "Febrero";
                break;
            case 3:
                nombreMes = "Marzo";
                break;
            case 4:
                nombreMes = "Abril";
                break;
            case 5:
                nombreMes = "Mayo";
                break;
            case 6:
                nombreMes = "Junio";
                break;
            case 7:
                nombreMes = "Julio";
                break;
            case 8:
                nombreMes = "Agosto";
                break;
            case 9:
                nombreMes = "Septiembre";
                break;
            case 10:
                nombreMes = "Octubre";
                break;
            case 11:
                nombreMes = "Noviembre";
                break;
            case 12:
                nombreMes = "Diciembre";
                break;
            default:
                nombreMes = "ERROR: Mes fuera de rango";
        }
        return nombreMes;
    }

    public static int numeroMes(String mes, String fecha) {
        if (mes.toLowerCase().contains("ene") || mes.toLowerCase().contains("jan")) {
            return 0;
        } else if (mes.toLowerCase().contains("feb")) {
            return 1;
        } else if (mes.toLowerCase().contains("mar")) {
            return 2;
        } else if (mes.toLowerCase().contains("abr") || mes.toLowerCase().contains("apr")) {
            return 3;
        } else if (mes.toLowerCase().contains("may")) {
            return 4;
        } else if (mes.toLowerCase().contains("jun")) {
            return 5;
        } else if (mes.toLowerCase().contains("jul")) {
            return 6;
        } else if (mes.toLowerCase().contains("ago") || mes.toLowerCase().contains("aug")) {
            return 7;
        } else if (mes.toLowerCase().contains("sep")) {
            return 8;
        } else if (mes.toLowerCase().contains("oct")) {
            return 9;
        } else if (mes.toLowerCase().contains("nov")) {
            return 10;
        } else if (mes.toLowerCase().contains("dic") || mes.toLowerCase().contains("dec")) {
            return 11;
        } else {
            System.out.println(UtilesString.concatenar("No se pudo obtener el valor del mes en Fechas.numeroMes(", mes, ")  ", fecha));
            return -1;
        }
    }

    public static int getAño(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMes(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        int mes = calendar.get(Calendar.MONTH) + 1;
        return mes;
    }

    public static int getDiaMes(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        int dias = calendar.get(Calendar.DAY_OF_MONTH);
        return dias;
    }

    /**
     * Método que retorna un número entre 1 y 7 que representa día de la semana
     * de la fecha recibida como parámetro, en donde 1 es lunes y 7 es domingo
     *
     * @param fecha
     * @return día de la semana en entero.
     */
    public static int getDiaDeLaSemana(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dia) {
            case 1:
                return 7;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            default:
                return 0;
        }
    }

    public static Date getPrimerDiaDelMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    public static Date getUltimoDiaDelMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int dia = getMaxDiaMes(fecha);
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                dia,
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

    public static int getMaxDiaMes(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        int mes = calendar.get(Calendar.MONTH) + 1;

        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return 31;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else if (mes == 2) {
            int año = getAño(fecha);
            if (año == 2016 || año == 2020 || año == 2024 || año == 2028 || año == 2032 || año == 2036) {//años bisiestos
                return 29;
            } else {
                return 28;
            }
        } else {
            return -1;
        }
    }

    /**
     * Recibe una objeto Date y lo retorna sin hora, minutos, segundos Ejemplo:
     * 2016-03-02 00:00:00.000
     *
     * @param fecha
     * @return
     */
    public static Date getFechaSinHora(Date fecha) throws NullPointerException {
        Calendar calendar0 = GregorianCalendar.getInstance();
        calendar0.setTime(fecha);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.MONTH, calendar0.get(Calendar.MONTH));
        calendar.set(Calendar.YEAR, calendar0.get(Calendar.YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * La idea de restar una cantidad dinamica de meses recibidos por
     * parámetros, fue probada, pero me complique mucho.
     *
     * @param fecha
     * @return
     */
    public static Date restarUnMes(Date fecha) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String nFecha = f.format(fecha);
        int dia = Integer.parseInt(nFecha.substring(0, 2));
        int mes = Integer.valueOf(nFecha.substring(3, 5));
        int año = Integer.valueOf(nFecha.substring(6, 10));

        mes--; //Lo transformo a indice..

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(fecha);
        int anio = cal.get(Calendar.YEAR);

        //Asigno el dia de fin de mes que corresponda.        
        if (dia == 30 && (mes == 3 || mes == 5 || mes == 8 || mes == 10)) {
            dia = 31;
        } else if ((dia == 31 || dia == 30 || dia == 29) && mes == 2) {
            if (anio == 2008 || anio == 2012 || anio == 2016 || anio == 2020 || anio == 2024 || anio == 2028 || anio == 2032 || anio == 2036) {//año bisiesto            
                dia = 29;

            } else {
                dia = 28;

            }
        } else if (dia == 31 && (mes == 4 || mes == 6 || mes == 7 || mes == 9 || mes == 11)) {
            dia = 30;
        } else if ((dia == 28 || dia == 29) && mes == 1) {
            dia = 31;
        }

        if (mes > 0) {
            mes = mes - 1;

        } else {// Si el mes es enero...lo dejo en diciembre.
            mes = 11;
            año--;
        }

        GregorianCalendar calendar = new GregorianCalendar(año, mes, dia);
        return calendar.getTime();
    }

    /**
     * Se agrega la hora a un objeto Date. Se espera que la hora venga en
     * formato de 0 a 23 horas
     *
     * @param fecha
     * @param hora En formato HH:MM
     * @return
     */
    public static Date agregarHoraAFecha(Date fecha, String hora) {
        if (fecha != null && hora != null) {
            String soloHora = hora.substring(0, hora.indexOf(":"));
            String soloMinuto = hora.substring(hora.indexOf(":") + 1, hora.length());

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(soloHora));
            calendar.set(Calendar.MINUTE, Integer.parseInt(soloMinuto));

            return calendar.getTime();
        } else {
            System.out.println("***NO SE PUDO ASIGNAR HORA, UNIO DE LOS PARÁMETROS ES NULO");
            return fecha;
        }
    }

    /**
     * Se agrega la hora a un objeto Date. La hora se espera por ej: 523 (05:23)
     * o 4 (sería 00:04). Especial para usar con campo docTime de SAP.
     *
     * @param fecha
     * @param hora En formato HHMM o HMM o MM o M.
     * @return
     */
    public static Date agregarHoraAFecha2(Date fecha, int docTime) {
        if (fecha != null) {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            if (docTime < 1000) {//Menor a 10:00, es decir, con 3 dígitos
                if (docTime < 100) {//Si es menos de una hora
                    String hora = "0";
                    String min = String.valueOf(docTime);

                    calendar.set(Calendar.HOUR, Integer.parseInt(hora));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(min));
                } else {//Si es más de una hora
                    String hora = String.valueOf(docTime).substring(0, 1);
                    String min = String.valueOf(docTime).substring(1, 3);

                    calendar.set(Calendar.HOUR, Integer.parseInt(hora));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(min));
                }
            } else {
                String hora = String.valueOf(docTime).substring(0, 2);
                String min = String.valueOf(docTime).substring(2, 4);
                calendar.set(Calendar.HOUR, Integer.parseInt(hora));
                calendar.set(Calendar.MINUTE, Integer.parseInt(min));
            }

            return calendar.getTime();
        } else {
            System.out.println("***NO SE PUDO ASIGNAR HORA, UNIO DE LOS PARÁMETROS ES NULO");
            return fecha;
        }
    }

    /**
     * Se agrega la hora a un objeto Date. Se espera que la hora venga en
     * formato de 0 a 23 horas
     *
     * @param fecha
     * @param hora En formato HH:MM:ss
     * @return
     */
    public static Date agregarHoraAFecha3(Date fecha, String hora) {
        if (fecha != null && hora != null) {
            String soloHora = hora.substring(0, hora.indexOf(":"));
            String soloMinuto = hora.substring(hora.indexOf(":") + 1, hora.lastIndexOf(":"));
            String soloSegundos = hora.substring(hora.lastIndexOf(":") + 1, hora.length());

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(soloHora));
            calendar.set(Calendar.MINUTE, Integer.parseInt(soloMinuto));
            calendar.set(Calendar.SECOND, Integer.parseInt(soloSegundos));

            return calendar.getTime();
        } else {
            System.out.println("***NO SE PUDO ASIGNAR HORA, UNIO DE LOS PARÁMETROS ES NULO");
            return fecha;
        }
    }

    /**
     * Especial para campo HORAGRAB RANDOM
     *
     * @param fecha
     * @param segundos
     * @return
     */
    public static final Date agregarSegundosAFecha(Date fecha, long segundos) {
        try {
            int day = (int) TimeUnit.SECONDS.toDays(segundos);
            long hours = TimeUnit.SECONDS.toHours(segundos) - (day * 24);
            long minute = TimeUnit.SECONDS.toMinutes(segundos) - (TimeUnit.SECONDS.toHours(segundos) * 60);
            long second = TimeUnit.SECONDS.toSeconds(segundos) - (TimeUnit.SECONDS.toMinutes(segundos) * 60);

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, (int) hours);
            calendar.set(Calendar.MINUTE, (int) minute);
            calendar.set(Calendar.SECOND, (int) second);
            return calendar.getTime();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Especial para generar el campo HORAGRAB de random
     *
     * @param fecha
     * @return
     */
    public static int dateToSegundos(Date fecha) {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fecha);
            int horas = calendar.get(Calendar.HOUR_OF_DAY);
            int minutos = calendar.get(Calendar.MINUTE);
            int segundos = calendar.get(Calendar.SECOND);

            int totalSegundos = segundos + ((minutos + (horas / 60)) / 60);
            return totalSegundos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int getHoragrab(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        int horas = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        int segundos = calendar.get(Calendar.SECOND);

        int totalSegundos = segundos + ((minutos + (horas * 60)) * 60);
        return totalSegundos;
    }

    public static int mesesTributariosDiferencia(Date fechaInicio, Date fechaFin) {
        int mes1 = Fechas.getMes(fechaInicio);
        int año1 = Fechas.getAño(fechaInicio);

        int mes2 = Fechas.getMes(fechaFin);
        int año2 = Fechas.getAño(fechaFin);

        if (año1 == año2) {

        } else if (año1 < año2) {
            int difAños = año2 - año1;
            mes2 = mes2 + difAños * 12;
        }

        return mes2 - mes1;

    }

    public static final String diasHorasMinutosSegundosDeDiferncia(Date inicio, Date fin) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(inicio);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fin);
        int dif = fecha1.compareTo(fecha2);

        long diff = fin.getTime() - inicio.getTime();
        double segundos = Math.floor((diff / 1000)); // 3600 * 24 * 1000

        return segundosAHoraMinutosSegundos((long) segundos);
    }

    /**
     * Especial para campo HORAGRAB RANDOM
     *
     * @param segundos
     * @return
     */
    public static final String segundosAHoraMinutosSegundos(long segundos) {
        int day = (int) TimeUnit.SECONDS.toDays(segundos);
        long hours = TimeUnit.SECONDS.toHours(segundos) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(segundos) - (TimeUnit.SECONDS.toHours(segundos) * 60);
        long second = TimeUnit.SECONDS.toSeconds(segundos) - (TimeUnit.SECONDS.toMinutes(segundos) * 60);
        return UtilesString.llenarConCerosIzquierda((int) hours, 2) + ":" + UtilesString.llenarConCerosIzquierda((int) minute, 2) + ":" + UtilesString.llenarConCerosIzquierda((int) second, 2);
    }

    public static final String horasMinutosDeDiferncia(Date inicio, Date fin) {
        Calendar fecha1 = GregorianCalendar.getInstance();
        fecha1.setTime(inicio);
        Calendar fecha2 = GregorianCalendar.getInstance();
        fecha2.setTime(fin);
        int dif = fecha1.compareTo(fecha2);

        long diff = fin.getTime() - inicio.getTime();
        double segundos = Math.floor((diff / 1000)); // 3600 * 24 * 1000

        return segundosAHoraMinutos((long) segundos);
    }

    public static final String segundosAHoraMinutos(long segundos) {
        int day = (int) TimeUnit.SECONDS.toDays(segundos);
        long hours = TimeUnit.SECONDS.toHours(segundos) - (day * 24);
        if (hours < 0) {
            hours = hours * -1;
        }
        long minute = TimeUnit.SECONDS.toMinutes(segundos) - (TimeUnit.SECONDS.toHours(segundos) * 60);
        if (minute < 0) {
            minute = minute * -1;
        }
        return UtilesString.llenarConCerosIzquierda((int) hours, 2) + ":" + UtilesString.llenarConCerosIzquierda((int) minute, 2);
    }

    public static Date getAhora(String zonaHoraria) {
        LocalDateTime ld = LocalDateTime.now(ZoneId.of(zonaHoraria));
        return java.sql.Timestamp.valueOf(ld);
    }

    public static Date getFecha(String zonaHoraria, Date fecha) {
        LocalDateTime ld = LocalDateTime.parse(formatearFecha14(fecha));
        return java.sql.Timestamp.valueOf(ld);
    }

    /**
     * Si es reciente muestra tiempo en minutos u horas, sino en días (cantidad
     * descriipción. Ej: 34 minutos)
     *
     * @param desde
     * @param hasta
     * @return
     */
    public static String getTiempoActivo(Date desde, Date hasta) {
        if (desde != null) {
            int horasDeDiferencia = Fechas.horasDeDiferencia(desde, hasta);

            if (horasDeDiferencia > 168) {/* > 7 días */
                return formatearFecha5(hasta);
            }else if (horasDeDiferencia > 48) {
                int diasDeDiferencia = Fechas.diasDeDiferencia(desde, hasta);
                return diasDeDiferencia + " días";
            } else if (horasDeDiferencia == 0) {
                int minutosDeDiferenciaEntreFechas = Fechas.minutosDeDiferenciaEntreFechas(desde, hasta);
                return minutosDeDiferenciaEntreFechas + " min";
            } else {
                return horasDeDiferencia + " horas";
            }
        } else {
            return "";
        }
    }

    public static Date sumarDiasHabiles(Date desde, int dias) {
        LocalDateTime ld1 = LocalDateTime.parse(formatearFecha14(desde));

        if (dias < 1) {
            return desde;
        }

        LocalDateTime result = LocalDateTime.parse(formatearFecha14(desde));;
        int addedDays = 0;
        while (addedDays < dias) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY
                    || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }

        return java.sql.Timestamp.valueOf(result);
    }

    public static LocalDateTime dateToLocalDateTime(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date localDateTimeToDate(LocalDateTime ldt) {
        return java.util.Date
                .from(ldt.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static Date agregarMinutosAFecha(Date fecha, int minutos) {
        LocalDateTime ldt = dateToLocalDateTime(fecha);
        ldt = ldt.plusMinutes(minutos);
        return localDateTimeToDate(ldt);
    }

    public static String aniosMesesdiasHorasDeDiferncia(Date d1, Date d2) {

        if (d1 == null || d2 == null) {
            return "";
        }
        if (getFechaSinHora(d1).toString().equals(getFechaSinHora(d2).toString())) {
            return "Hoy";
        } else {
            Period period = Period.between(getLocalDateFrommDate(d1), getLocalDateFrommDate(d2));
            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();

            if (years == 0 && months == 0) {
                return days + " días";
            } else if (years == 0 && months > 0) {
                if (days == 0) {
                    return months + " meses";
                } else {
                    return UtilesString.concatenar(String.valueOf(months), " meses y ", String.valueOf(days), " días");
                }
            } else {
                return UtilesString.concatenar(String.valueOf(years), " años, ", String.valueOf(months), " meses y ", String.valueOf(days), " días");
            }
        }
    }

    public static LocalDate getLocalDateFrommDate(Date fecha) {
        return LocalDate.of(getAño(fecha), getMes(fecha), getDiaMes(fecha));
    }



    public static Date setTimeToDate(Date date, LocalTime time) {
        if (date == null || time == null) {
            throw new IllegalArgumentException("date y time no pueden ser nulos");
        }

        // Convertir Date a LocalDateTime
        LocalDateTime originalDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .atTime(time); // Se combina la fecha del Date con la hora del LocalTime

        // Convertir de vuelta a Date
        return Date.from(originalDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}

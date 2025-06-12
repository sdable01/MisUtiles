package com.bestcode.misutiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jimmy
 */
public class CuotasUtiles {

    public List<Double> distribuirMontoEnCuotas(double monto, int cuotas) throws DistribucionCuotasException {
        if (monto > 0 && cuotas > 0) {
            List<Double> listaCuotas = new ArrayList();

            double acumulado = 0;
            for (int i = 1; i <= cuotas; i++) {
                double c = Math.ceil(monto / cuotas);

                if (i == cuotas) {//Si estoy pasando por la última cuota...       
                    c = monto - acumulado;
                }
                listaCuotas.add(c);
                acumulado += c;
            }
            return listaCuotas;
        } else {
            throw new DistribucionCuotasException("Monto y cantidad de cuotas deben ser mayores a 0");
        }
    }

    public  List<Date> distribuirPlazoEnCuotas(Date fechaFinal, int cuotas) throws DistribucionCuotasException {
        if (fechaFinal != null && cuotas > 0) {
            List<Date> fechas = new ArrayList();
            Date hoy = new Date();
            int plazoTotal = Fechas.diasDeDiferencia(hoy, fechaFinal);

            int acumulado = 0;
            for (int i = 1; i <= cuotas; i++) {
                int c = (int) Math.ceil(plazoTotal / cuotas);

                if (i == cuotas) {//Si estoy pasando por la última cuota...       
                    c = plazoTotal - acumulado;
                }
                acumulado += c;
                Date fecha = Fechas.sumarDias(hoy, acumulado);
                int diaDeLaSemana = Fechas.getDiaDeLaSemana(fecha);
                if (diaDeLaSemana == 6) {//Si es sábado
                    fecha = Fechas.sumarDias(fecha, 2);//Para que se el lunes
                } else if (diaDeLaSemana == 7) {//Si es domingo
                    fecha = Fechas.sumarDias(fecha, 1);//Para que se el lunes
                }

                if (i == cuotas) {//Si estoy pasando por la última cuota...       
                    int diaDeLaSemana3 = Fechas.getDiaDeLaSemana(fecha);
                    if (!Fechas.formatearFecha5(fecha).equals(Fechas.formatearFecha5(fechaFinal)) && diaDeLaSemana3 < 5) {
                        fecha = fechaFinal;
                    }
                }
                fechas.add(fecha);
            }
            return fechas;
        } else {
            throw new DistribucionCuotasException("FechaFinal no debe ser null y cuotas debe ser mayor a 0");
        }
    }

    public class DistribucionCuotasException extends Exception {

        public DistribucionCuotasException(String mensaje) {
            super(mensaje);
        }

    }
}

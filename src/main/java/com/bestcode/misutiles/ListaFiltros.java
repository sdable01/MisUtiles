package com.bestcode.misutiles;

import com.bestcode.misutiles.Filtro;
import org.slf4j.Logger;

/**
 *
 * @author jimmy
 */
public class ListaFiltros extends java.util.ArrayList<Filtro> {

    public ListaFiltros() {
        super();
    }

    public void asignarTipo(String campo, String tipo, Logger log) {
        try {
            Filtro f = this.stream()
                    .filter(filtro -> filtro.getCampo().equals(campo))
                    .findFirst()
                    .orElse(null);
            if (f != null) {
                f.setTipo(tipo);
            }
        } catch (Exception ex) {
            log.error(UtilesString.getDetalleException(ex));
        }
    }
}

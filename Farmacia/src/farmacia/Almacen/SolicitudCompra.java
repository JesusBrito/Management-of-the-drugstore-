/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Almacen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jesus
 */
public class SolicitudCompra {
    private StringProperty Rfc;
    private StringProperty Codigo;
    private StringProperty Cantidad;

    public SolicitudCompra(String Rfc, String Codigo, String Cantidad) {
        this.Rfc = new SimpleStringProperty(Rfc);
        this.Codigo = new SimpleStringProperty(Codigo);
        this.Cantidad = new SimpleStringProperty(Cantidad);
    }
     
    public SimpleStringProperty getRfc() {
        return(SimpleStringProperty) this.Rfc;
    }

    public SimpleStringProperty getCodigo() {
        return (SimpleStringProperty) this.Codigo;
    }

    public SimpleStringProperty getCantidad() {
        return (SimpleStringProperty) this.Cantidad;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Venta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jesus
 */
public class Detalle_Ventas {
    private StringProperty No_nota;
    private StringProperty Fecha;
    private StringProperty CodigoProducto;
    private StringProperty Cantidad;
    private StringProperty Subtotal;
    private StringProperty PrecioC;

    public Detalle_Ventas(String No_nota, String Fecha, String CodigoProducto, 
            String Cantidad, String PrecioC, String Subtotal) {
        this.No_nota = new SimpleStringProperty(No_nota);
        this.Fecha = new SimpleStringProperty(Fecha);
        this.CodigoProducto = new SimpleStringProperty(CodigoProducto);
        this.Cantidad = new SimpleStringProperty(Cantidad);
        this.Subtotal = new SimpleStringProperty(Subtotal);
        this.PrecioC = new SimpleStringProperty(PrecioC);
    }

    public SimpleStringProperty getNo_nota() {
        return(SimpleStringProperty) this.No_nota;
    }

    public SimpleStringProperty getFecha() {
        return (SimpleStringProperty)this.Fecha;
    }

    public SimpleStringProperty getCodigoProducto() {
        return (SimpleStringProperty)this.CodigoProducto;
    }

    public SimpleStringProperty getCantidad() {
        return (SimpleStringProperty) this.Cantidad;
    }

    public SimpleStringProperty getSubtotal() {
        return(SimpleStringProperty) this.Subtotal;
    }

    public SimpleStringProperty getPrecioC() {
        return(SimpleStringProperty) this.PrecioC;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Almacen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Detalle_Compra {
    private StringProperty No_factura;
    private StringProperty Fecha;
    private StringProperty Cantidad;
    private StringProperty Subtotal;
    private StringProperty PrecioC;
    private StringProperty CodigoProducto;

    public Detalle_Compra(String No_factura, String Fecha, String Cantidad, String Subtotal, 
            String PrecioC, String CodigoProducto) {
        this.No_factura = new SimpleStringProperty(No_factura);
        this.Fecha = new SimpleStringProperty(Fecha);
        this.Cantidad = new SimpleStringProperty(Cantidad);
        this.Subtotal = new SimpleStringProperty(Subtotal);
        this.PrecioC = new SimpleStringProperty(PrecioC);
        this.CodigoProducto = new SimpleStringProperty(CodigoProducto);
    }
    
    public SimpleStringProperty getNo_factura() {
        return(SimpleStringProperty) No_factura;
    }

    public SimpleStringProperty getFecha() {
        return (SimpleStringProperty)Fecha;
    }

    public SimpleStringProperty getCantidad() {
        return (SimpleStringProperty)Cantidad;
    }

    public SimpleStringProperty getSubtotal() {
        return(SimpleStringProperty) Subtotal;
    }

    public SimpleStringProperty getPrecioC() {
        return(SimpleStringProperty) PrecioC;
    }

    public SimpleStringProperty getCodigoProducto() {
        return(SimpleStringProperty) CodigoProducto;
    }
    
    
}

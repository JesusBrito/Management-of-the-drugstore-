/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Reporte;

/**
 *
 * @author jesus
 */
public class DetalleVentaReporter {
    private String cantidad;
    private String unidad;
    private String nombre;
    private String precio;
    private String subtotal;

    public DetalleVentaReporter(String cantidad, String unidad, String nombre, String precio, String subtotal) {
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.nombre = nombre;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}

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
public class AlmacenReporter {
    private String codigo;
    private String nombre;
    private String precio;
    private String existencia;
    private String unidad;
    
    public AlmacenReporter(String Codigo, String Nombre, String Precio, String Existencia, String Unidad) {
        this.codigo = Codigo;
        this.nombre = Nombre;
        this.precio = Precio;
        this.existencia = Existencia;
        this.unidad = Unidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getExistencia() {
        return existencia;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setCodigo(String Codigo) {
        this.codigo = Codigo;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setPrecio(String Precio) {
        this.precio = Precio;
    }

    public void setExistencia(String Existencia) {
        this.existencia = Existencia;
    }

    public void setUnidad(String Unidad) {
        this.unidad = Unidad;
    }
}

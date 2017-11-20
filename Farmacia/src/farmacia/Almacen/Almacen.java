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
public class Almacen {
    private StringProperty Codigo;
    private StringProperty Nombre;
    private StringProperty Precio;
    private StringProperty Existencia;
    private StringProperty Unidad;
    private StringProperty Rfc;
    private StringProperty Proveedor;    
    public Almacen(){}
    public Almacen(String Codigo, String Nombre, String Precio, String Existencia, String Unidad, String Rfc, String Proveedor) {
        this.Codigo = new SimpleStringProperty(Codigo);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Precio = new SimpleStringProperty(Precio);
        this.Existencia = new SimpleStringProperty (Existencia);
        this.Unidad = new SimpleStringProperty (Unidad);
        this.Rfc = new SimpleStringProperty(Rfc);
        this.Proveedor = new SimpleStringProperty(Proveedor);
    }

    public SimpleStringProperty getCodigo() {
        return (SimpleStringProperty) this.Codigo;
    }

    public SimpleStringProperty getNombre() {
        return (SimpleStringProperty) this.Nombre;
    }

    public SimpleStringProperty getPrecio() {
        return (SimpleStringProperty) this.Precio;
    }

    public StringProperty getExistencia() {
        return (SimpleStringProperty) this.Existencia;
    }

    public SimpleStringProperty getUnidad() {
        return (SimpleStringProperty) this.Unidad;
    }

    public SimpleStringProperty getRfc() {
        return (SimpleStringProperty) this.Rfc;
    }

    public SimpleStringProperty getProveedor() {
        return (SimpleStringProperty) this.Proveedor;
    }
    @Override
    public String toString(){
        return this.Nombre.get();
    }   
}

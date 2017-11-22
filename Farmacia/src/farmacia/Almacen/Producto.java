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
public class Producto {
    private StringProperty Codigo;
    private StringProperty Nombre;
    private StringProperty Unidad;

    public Producto(String Codigo, String Nombre, String Unidad) {
        this.Codigo = new SimpleStringProperty(Codigo);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Unidad = new SimpleStringProperty(Unidad);
    }
     
    public SimpleStringProperty getCodigo() {
        return(SimpleStringProperty) Codigo;
    }

    public SimpleStringProperty getFecha() {
        return (SimpleStringProperty)Nombre;
    }

    public SimpleStringProperty getCantidad() {
        return (SimpleStringProperty)Unidad;
    }
    
    @Override
    public String toString(){
        return this.Codigo.get()+"-"+this.Nombre.get();
    } 
}

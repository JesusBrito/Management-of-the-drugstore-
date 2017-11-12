/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Administrador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jesus
 */
public class Ventas {
    StringProperty NoNota;
    StringProperty Fecha;
    StringProperty Rfc;
    StringProperty Nombre;
    StringProperty ApPaterno;
    StringProperty ApMaterno;
    StringProperty Total;
    
    public Ventas(){}
    
    public Ventas(String noNota,String fecha,String rfc,String nombre,String paterno,String materno,String total){
        this.NoNota=new SimpleStringProperty(noNota);
        this.Fecha=new SimpleStringProperty(fecha);
        this.Rfc=new SimpleStringProperty(rfc);
        this.Nombre=new SimpleStringProperty(nombre);
        this.ApPaterno=new SimpleStringProperty(paterno);
        this.ApMaterno=new SimpleStringProperty(materno);
        this.Total=new SimpleStringProperty(total);
    }
    public SimpleStringProperty getNoNota(){    
        return (SimpleStringProperty) this.NoNota;
    }
    public SimpleStringProperty getFecha(){
        return (SimpleStringProperty) this.Fecha;
    }
    public SimpleStringProperty getRfc(){    
        return (SimpleStringProperty) this.Rfc;
    }
    public SimpleStringProperty getNombre(){
        return (SimpleStringProperty) this.Nombre;
    }
    public SimpleStringProperty getApPaterno(){
        return (SimpleStringProperty) this.ApPaterno;
    }
    public SimpleStringProperty getApMaterno(){
        return (SimpleStringProperty) this.ApMaterno;
    }public SimpleStringProperty getTotal(){
        return (SimpleStringProperty) this.Total;
    }   
}

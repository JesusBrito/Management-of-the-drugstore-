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
    private StringProperty noNota;
    private StringProperty fecha;
    private StringProperty rfc;
    private StringProperty nombre;
    private StringProperty apPaterno;
    private StringProperty apMaterno;
    private StringProperty total;
    
    public Ventas(){}
    
    public Ventas(String noNota,String fecha,String rfc,String nombre,String paterno,String materno,String total){
        this.noNota=new SimpleStringProperty(noNota);
        this.fecha=new SimpleStringProperty(fecha);
        this.rfc=new SimpleStringProperty(rfc);
        this.nombre=new SimpleStringProperty(nombre);
        this.apPaterno=new SimpleStringProperty(paterno);
        this.apMaterno=new SimpleStringProperty(materno);
        this.total=new SimpleStringProperty(total);
    }

    public void setNoNota(StringProperty noNota) {
        this.noNota = noNota;
    }

    public void setFecha(StringProperty fecha) {
        this.fecha = fecha;
    }

    public void setRfc(StringProperty rfc) {
        this.rfc = rfc;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public void setApPaterno(StringProperty apPaterno) {
        this.apPaterno = apPaterno;
    }

    public void setApMaterno(StringProperty apMaterno) {
        this.apMaterno = apMaterno;
    }

    public void setTotal(StringProperty total) {
        this.total = total;
    }
    
    
    public SimpleStringProperty getNoNota(){    
        return (SimpleStringProperty) this.noNota;
    }
    public SimpleStringProperty getFecha(){
        return (SimpleStringProperty) this.fecha;
    }
    public SimpleStringProperty getRfc(){    
        return (SimpleStringProperty) this.rfc;
    }
    public SimpleStringProperty getNombre(){
        return (SimpleStringProperty) this.nombre;
    }
    public SimpleStringProperty getApPaterno(){
        return (SimpleStringProperty) this.apPaterno;
    }
    public SimpleStringProperty getApMaterno(){
        return (SimpleStringProperty) this.apMaterno;
    }public SimpleStringProperty getTotal(){
        return (SimpleStringProperty) this.total;
    }   
}

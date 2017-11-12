package farmacia.Administrador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Proveedores extends farmacia.Utilidades.Persona {
    StringProperty Telefono;
    StringProperty NombreEmpresa;

public Proveedores(){}
public Proveedores(String rfc,String nombre, String apPaterno, String apMaterno, String calle, String colonia , String numero,
            String ciudad, String delegacion, String cp, String telefono, String nombreEmpresa){
        this.RFC= new SimpleStringProperty(rfc);
        this.Nombre=new SimpleStringProperty(nombre);
        this.ApPaterno= new SimpleStringProperty(apPaterno);
        this.ApMaterno=new SimpleStringProperty(apMaterno);
        this.Calle=new SimpleStringProperty(calle);
        this.Colonia=new SimpleStringProperty(colonia);
        this.Numero=new SimpleStringProperty(numero);
        this.Ciudad=new SimpleStringProperty(ciudad);
        this.Delegacion=new SimpleStringProperty(delegacion);
        this.Cp= new SimpleStringProperty(cp);
        this.NombreEmpresa=new SimpleStringProperty(nombreEmpresa);
        this.Telefono=new SimpleStringProperty(telefono);
    }
    
    public SimpleStringProperty rfc(){
        return (SimpleStringProperty) this.RFC;
    }
    public SimpleStringProperty nombre(){
        return (SimpleStringProperty) this.Nombre;
    }
    public SimpleStringProperty apPaterno(){
        return (SimpleStringProperty) this.ApPaterno;
    }
    public SimpleStringProperty apMaterno(){
        return (SimpleStringProperty) this.ApMaterno;
    }
    public SimpleStringProperty Calle(){
        return (SimpleStringProperty) this.Calle;
    }
    public SimpleStringProperty Colonia(){
        return (SimpleStringProperty) this.Colonia;
    }
    public StringProperty Numero(){
        return (StringProperty) this.Numero;
    }
    public SimpleStringProperty Ciudad() {
        return (SimpleStringProperty) this.Ciudad;
    }
    public SimpleStringProperty Delegacion() {
        return  (SimpleStringProperty)this.Delegacion;
    }
    public SimpleStringProperty Cp() {
        return (SimpleStringProperty) this.Cp;
    }
    public SimpleStringProperty nombreEmpresa() {
        return (SimpleStringProperty) this.NombreEmpresa;
    }
    public SimpleStringProperty Telefono() {
        return (SimpleStringProperty) this.Telefono;
    }
}
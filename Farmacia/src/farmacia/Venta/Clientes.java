
package farmacia.Venta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Clientes extends farmacia.Utilidades.Persona {
    private StringProperty Correo;
    
    
    public Clientes(){}
    public Clientes(String rfc,String nombre, String apPaterno, String apMaterno, String calle, String colonia , String numero,
            String ciudad, String delegacion, String cp, String correo){
        this.RFC=new SimpleStringProperty(rfc);
        this.Nombre=new SimpleStringProperty(nombre);
        this.ApPaterno=new SimpleStringProperty(apPaterno);
        this.ApMaterno=new SimpleStringProperty(apMaterno);
        this.Calle=new SimpleStringProperty(calle);
        this.Colonia=new SimpleStringProperty(colonia);
        this.Numero=new SimpleStringProperty(numero);
        this.Ciudad=new SimpleStringProperty(ciudad);
        this.Delegacion=new SimpleStringProperty(delegacion);
        this.Cp=new SimpleStringProperty(cp);
        this.Correo=new SimpleStringProperty(correo);
    }
    
    public SimpleStringProperty getRfc(){
        return(SimpleStringProperty) this.RFC;
    }
    public SimpleStringProperty getNombre(){
        return(SimpleStringProperty) this.Nombre;
    }
    public SimpleStringProperty getApPaterno(){
        return (SimpleStringProperty)this.ApPaterno;
    }
    public SimpleStringProperty getApMaterno(){
        return(SimpleStringProperty) this.ApMaterno;
    }
    public SimpleStringProperty getCalle(){
        return(SimpleStringProperty) this.Calle;
    }
    public SimpleStringProperty getColonia(){
        return(SimpleStringProperty) this.Colonia;
    }
    public SimpleStringProperty getNumero(){
        return(SimpleStringProperty) this.Numero;
    }
    public SimpleStringProperty getCiudad() {
        return(SimpleStringProperty) this.Ciudad;
    }
    public SimpleStringProperty getDelegacion() {
        return(SimpleStringProperty) this.Delegacion;
    }
    public SimpleStringProperty getCp() {
        return(SimpleStringProperty) this.Cp;
    }
    public SimpleStringProperty getCorreo() {
        return (SimpleStringProperty)this.Correo;
    }
    @Override
    public String toString(){
        return this.RFC.get();
    } 
}




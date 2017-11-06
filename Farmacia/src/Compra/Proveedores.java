package Compra;



public class Proveedores extends Utilidades.Persona {
    String Telefono;
    String NombreEmpresa;

public Proveedores(){}
public Proveedores(String rfc,String nombre, String apPaterno, String apMaterno, String calle, String colonia , int numero,
            String ciudad, String delegacion, int cp, String telefono, String nombreEmpresa){
        this.RFC=rfc;
        this.Nombre=nombre;
        this.ApPaterno=apPaterno;
        this.ApMaterno=apMaterno;
        this.Calle=calle;
        this.Colonia=colonia;
        this.Numero=numero;
        this.Ciudad=ciudad;
        this.Delegacion=delegacion;
        this.Cp=cp;
        this.NombreEmpresa=nombreEmpresa;
        this.Telefono=telefono;
    }
    
    public String rfc(){
        return this.RFC;
    }
    public String nombre(){
        return this.Nombre;
    }
    public String apPaterno(){
        return this.ApPaterno;
    }
    public String apMaterno(){
        return this.ApMaterno;
    }
    public String Calle(){
        return this.Calle;
    }
    public String Colonia(){
        return this.Colonia;
    }
    public int Numero(){
        return this.Numero;
    }
    public String Ciudad() {
        return this.Ciudad;
    }
    public String Delegacion() {
        return this.Delegacion;
    }
    public int Cp() {
        return this.Cp;
    }
    public String nombreEmpresa() {
        return this.NombreEmpresa;
    }
    public String Telefono() {
        return this.Telefono;
    }
}
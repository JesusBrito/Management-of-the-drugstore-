/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Venta;

/**
 *
 * @author jesus
 */
public class Factura {
    private String noNota;
    private String fecha;
    private String hora;
    private String rfc;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String total;

    public Factura(String noNota, String fecha, String hora, String rfc, String nombre, String apPaterno, String apMaterno, String total) {
        this.noNota = noNota;
        this.fecha = fecha;
        this.hora = hora;
        this.rfc = rfc;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.total = total;
    }

    public String getNoNota() {
        return noNota;
    }

    public String getFecha() {
        return fecha;
    }

    public String getRfc() {
        return rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public String getTotal() {
        return total;
    }

    public String getHora() {
        return hora;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

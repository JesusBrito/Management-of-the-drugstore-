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
public class SolicitudCompraReporter {
    private String rfc;
    private String codigo;
    private String cantidad;

    public SolicitudCompraReporter(String Rfc, String Codigo, String Cantidad) {
        this.rfc = Rfc;
        this.codigo = Codigo;
        this.cantidad = Cantidad;
    }

    public String getRfc() {
        return rfc;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setRfc(String Rfc) {
        this.rfc = Rfc;
    }

    public void setCodigo(String Codigo) {
        this.codigo = Codigo;
    }

    public void setCantidad(String Cantidad) {
        this.cantidad = Cantidad;
    }   
}

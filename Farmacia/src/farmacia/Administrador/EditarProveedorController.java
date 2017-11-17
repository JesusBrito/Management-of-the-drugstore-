/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Administrador;

import com.jfoenix.controls.JFXTextField;
import farmacia.Utilidades.Bd;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class EditarProveedorController implements Initializable {
    String Usuario;
    String Contrasenia;
    String Query;
    /**
     * Initializes the controller class.
     */
    Proveedores proveedor;
    String rfc="null", empresa="null",paterno="null",nombre="null",colonia="null",
            calle="null",materno="null",telefono="null",delegacion="null",ciudad="null";
    int numero=0,cp=0;
    @FXML public JFXTextField txtRfc;
    @FXML public JFXTextField txtEmpresa;
    @FXML public JFXTextField txtPaterno;
    @FXML public JFXTextField txtNombre;
    @FXML public JFXTextField txtColonia;
    @FXML public JFXTextField txtNumero;
    @FXML public JFXTextField txtCalle;
    @FXML public JFXTextField txtMaterno;
    @FXML public JFXTextField txtCp;
    @FXML public JFXTextField txtTelefono;
    @FXML public JFXTextField txtDelegacion;
    @FXML public JFXTextField txtCiudad;   
    @FXML public Button btnCancelar;
     
    public void btnActualizarClicked() throws SQLException{
        rfc=txtRfc.getText();
        empresa=txtEmpresa.getText();
        nombre=txtNombre.getText();
        paterno=(txtPaterno.getText().length()>0) ? txtPaterno.getText():"null";
        materno=(txtMaterno.getText().length()>0) ? txtMaterno.getText():"null";
        calle=txtCalle.getText();
        colonia=txtColonia.getText();
        numero=Integer.parseInt(txtNumero.getText());
        ciudad=txtCiudad.getText();
        delegacion=txtDelegacion.getText();
        cp=Integer.parseInt(txtCp.getText());
        telefono=(txtTelefono.getText().length()>0)? txtTelefono.getText() :"null";
                
        if(empresa.equals("")|| nombre.equals("") || calle.equals("")||
                colonia.equals("")|| ciudad.equals("")|| telefono.equals("")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Campos llenados incorrectamente");
            alert2.setContentText("Todos los campos a excepcion de los apellidos y el telefono son obligatorios");
            alert2.showAndWait();
            
        }else{
            Query="UPDATE ELENA.PROVEEDORES SET NOMBRE_PROVEEDOR='"+empresa+"', \n" +
            "AP_PATERNO='"+paterno+"', AP_MATERNO='"+materno+"', "
            + "CALLE='"+calle+"', COLONIA='"+colonia+"', NUMERO="+numero+", "
            + "CIUDAD='"+ciudad+"', DELEGACION='"+delegacion+"',"
            + " CP="+cp+", TELEFONO_CONTACTO='"+telefono+"', NOMBRE_CONTACTO='"+nombre+"'"
            + " where RFC='"+rfc+"'";
            Actualizar(Query);  
        }                
    }
    
    private void Actualizar(String Query) throws SQLException{
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
     
        if(db.Actualizar(Query)==true){
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Actualizacion exitosa")           
            .text("El proveedor "+empresa+" se ha actualizado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showInformation();
        }else{
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Error al actualizar el proveedor")           
            .text("Hubo un error al realizar la actualización")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();
        }
        db.cerrarConexion();
    }
    public void btnCerrarClicked(){
        Cerrar();
    }
    
    public void setProveedor(Proveedores provSelected, String usr, String pass){
        this.proveedor=provSelected;
        this.Usuario=usr;
        this.Contrasenia=pass;
        txtRfc.setText(this.proveedor.rfc().getValue());
        txtEmpresa.setText(this.proveedor.nombreEmpresa().getValue());
        txtNombre.setText(this.proveedor.nombre().getValue());
        txtPaterno.setText(this.proveedor.apPaterno().getValue());
        txtMaterno.setText(this.proveedor.apMaterno().getValue());
        txtCalle.setText(this.proveedor.Calle().getValue());
        txtColonia.setText(this.proveedor.Colonia().getValue()); 
        txtNumero.setText(this.proveedor.Numero().getValue());
        txtCiudad.setText(this.proveedor.Ciudad().getValue()); 
        txtDelegacion.setText(this.proveedor.Delegacion().getValue());
        txtCp.setText(this.proveedor.Cp().getValue());
        txtTelefono.setText(this.proveedor.Telefono().getValue());
    }

    private void Cerrar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}

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
import javafx.event.ActionEvent;
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
public class AgregarProveedorController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    
    public void btnRegistrarClicked(ActionEvent event) throws SQLException{
        String Query="";
        rfc=txtRfc.getText();
        empresa=txtEmpresa.getText();
        paterno=(txtPaterno.getText().length()>0) ? txtPaterno.getText():"null";
        materno=(txtMaterno.getText().length()>0) ? txtMaterno.getText():"null";
        calle=txtCalle.getText();
        colonia=txtColonia.getText();
        numero=Integer.parseInt(txtNumero.getText());
        ciudad=txtCiudad.getText();
        delegacion=txtDelegacion.getText();
        cp=Integer.parseInt(txtCp.getText());
        telefono=(txtTelefono.getText().length()>0)? txtTelefono.getText() :"null";
        nombre=txtNombre.getText();
        
        if(rfc.equals("")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Campos llenados incorrectamente");
            alert2.setContentText("El RFC es un campo obligatorio");
            alert2.showAndWait();
        }else if(empresa.equals("")|| nombre.equals("") || calle.equals("")||
                colonia.equals("")|| ciudad.equals("")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Campos llenados incorrectamente");
            alert2.setContentText("Todos los campos a excepcion de los apellidos y el telefono son obligatorios");
            alert2.showAndWait();
            
        }else{
            Query="Insert into ELENA.PROVEEDORES (RFC,NOMBRE_PROVEEDOR,AP_PATERNO,AP_MATERNO,CALLE,COLONIA,NUMERO,CIUDAD,DELEGACION,CP,TELEFONO_CONTACTO,NOMBRE_CONTACTO) "
           +"values ('"+rfc+"','"+empresa+"','"+paterno+"','"+materno+"','"+calle+"','"+colonia+"',"+numero+",'"+ciudad+"','"+delegacion+"',"+cp+",'"+telefono+"','"+nombre+"')";
           registrarBd(Query);   
        }
    }
    
    
    public void btnCancelarClicked(ActionEvent event){
        Cerrar();
    }
    private void registrarBd(String Query) throws SQLException{
        
        Bd db = new Bd();
        db.Conectar("administrador1", "12345");
        if(db.InsertarClientes(Query)==true){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Registro exitoso");
           alert.setHeaderText("Error en las fechas");
           alert.setContentText("El proveedor "+empresa+" se ha registrado correctamente");
           alert.showAndWait();        
        }else{
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Error al registrar el proveedor")           
            .text("Hubo un error al realizar el registro")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();
        }        
        txtRfc.setText("");
        txtEmpresa.setText("");
        txtPaterno.setText("");
        txtNombre.setText("");
        txtColonia.setText("");
        txtNumero.setText("");
        txtCalle.setText("");
        txtMaterno.setText("");
        txtCp.setText("");
        txtTelefono.setText("");
        txtDelegacion.setText("");
        txtCiudad.setText(""); 
        db.cerrarConexion();
    }
    
    
    private void Cerrar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

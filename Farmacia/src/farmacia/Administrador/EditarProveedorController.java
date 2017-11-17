/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Administrador;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class EditarProveedorController implements Initializable {

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
     
    public void btnActualizarClicked(){
        
    }
    
    public void btnCerrarClicked(){
        Cerrar();
    }
    
    public void setProveedor(Proveedores provSelected){
        this.proveedor=provSelected;
        System.out.println("entra bien");
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

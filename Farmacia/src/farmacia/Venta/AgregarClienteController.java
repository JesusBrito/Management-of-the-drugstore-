/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Venta;

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
public class AgregarClienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
        String rfc="null", empresa="null",paterno="null",nombre="null",colonia="null",
            calle="null",materno="null",correo="null",delegacion="null",ciudad="null";
    int numero=0,cp=0;
    @FXML public JFXTextField txtRfc;
    @FXML public JFXTextField txtPaterno;
    @FXML public JFXTextField txtNombre;
    @FXML public JFXTextField txtColonia;
    @FXML public JFXTextField txtNumero;
    @FXML public JFXTextField txtCalle;
    @FXML public JFXTextField txtMaterno;
    @FXML public JFXTextField txtCp;
    @FXML public JFXTextField txtCorreo;
    @FXML public JFXTextField txtDelegacion;
    @FXML public JFXTextField txtCiudad;   
    
 
    @FXML public Button btnCancelar;
    
    public void btnRegistrarClicked(ActionEvent event) throws SQLException{
        String Query="";
        if(!isNumeric(txtNumero.getText())||!isNumeric(txtCp.getText())){
            System.out.println(isNumeric(txtNumero.getText())+"-"+isNumeric(txtCp.getText()));
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Error");
            alert1.setHeaderText("Campos llenados incorrectamente");
            alert1.setContentText("Verifique que el Numero y código postal\n sean tipo numerico");
            alert1.showAndWait();
        }else  if(txtNombre.getText().equals("") ||txtPaterno.getText().equals("") ||
                txtCalle.getText().equals("")||txtMaterno.getText().equals("") ||
                txtColonia.getText().equals("")|| txtCiudad.getText().equals("")||
                txtDelegacion.getText().equals("")|| Integer.parseInt(txtNumero.getText())==0||
                Integer.parseInt(txtCp.getText())==0||correo.equals("")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Campos llenados incorrectamente");
            alert2.setContentText("Todos los campos son obligatorios");
            alert2.showAndWait();
        }else if(rfc.equals("")){
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Error");
            alert3.setHeaderText("Campos llenados incorrectamente");
            alert3.setContentText("El RFC es un campo obligatorio");
            alert3.showAndWait();
        }else if(txtNombre.getText().equals("") ||txtPaterno.getText().equals("") ||
                txtCalle.getText().equals("")||txtMaterno.getText().equals("") ||
                txtColonia.getText().equals("")|| txtCiudad.getText().equals("")||
                txtDelegacion.getText().equals("")|| Integer.parseInt(txtNumero.getText())==0||
                Integer.parseInt(txtCp.getText())==0||correo.equals("")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Campos llenados incorrectamente");
            alert2.setContentText("Todos los campos son obligatorios");
            alert2.showAndWait();
            
        }else{
            rfc=txtRfc.getText();
            paterno=(txtPaterno.getText().length()>0) ? txtPaterno.getText():"null";
            materno=(txtMaterno.getText().length()>0) ? txtMaterno.getText():"null";
            calle=txtCalle.getText();
            colonia=txtColonia.getText();
            numero=Integer.parseInt(txtNumero.getText());
            ciudad=txtCiudad.getText();
            delegacion=txtDelegacion.getText();
            cp=Integer.parseInt(txtCp.getText());
            correo=(txtCorreo.getText().length()>0)? txtCorreo.getText() :"null";
            nombre=txtNombre.getText();
            Query="Insert into ELENA.CLIENTES (RFC,NOMBRE,AP_PATERNO,AP_MATERNO,CALLE,COLONIA,NUMERO,CIUDAD,DELEGACION,CP,CORREO_ELECTRONICO) "
                    + "values ('"+rfc+"','"+nombre+"','"+paterno+"','"+materno+"','"+calle+"','"+colonia+"',"+numero+",'"+ciudad+"','"+delegacion+"',"+cp+",'"+correo+"')";
           registrarBd(Query);   
        }
    }
    
    
    public void btnCancelarClicked(ActionEvent event){
        Cerrar();
    }
    private void registrarBd(String Query) throws SQLException{
        
        Bd db = new Bd();
        db.Conectar("vendedor1", "12345");
        if(db.InsertarClientes(Query)==true){
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Registro exitoso")           
            .text("El cliente "+nombre+" se ha registrado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showInformation();            
        }else{
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Error al registrar el proveedor")           
            .text("Hubo un error al realizar el registro")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();
        }        
        txtRfc.setText("");
        txtPaterno.setText("");
        txtNombre.setText("");
        txtColonia.setText("");
        txtNumero.setText("");
        txtCalle.setText("");
        txtMaterno.setText("");
        txtCp.setText("");
        txtCorreo.setText("");
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
        private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
}

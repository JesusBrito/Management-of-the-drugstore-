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
public class EditarClienteController implements Initializable {

    String Usuario;
    String Contrasenia;
    String Query;
    Clientes cliente;
    
    String rfc="null",paterno="null",nombre="null",colonia="null",
            calle="null",materno="null",correo="null",delegacion="null",ciudad="null";
    int numero=0,cp=0;
    @FXML public JFXTextField txtRfc;
    @FXML public JFXTextField txtNombre;
    @FXML public JFXTextField txtPaterno;
    @FXML public JFXTextField txtMaterno;
    @FXML public JFXTextField txtCalle;
    @FXML public JFXTextField txtColonia;
    @FXML public JFXTextField txtNumero;
    @FXML public JFXTextField txtCiudad; 
    @FXML public JFXTextField txtDelegacion; 
    @FXML public JFXTextField txtCp;
    @FXML public JFXTextField txtCorreo; 
    @FXML public Button btnCancelar;
    
    @FXML
    public void btnCerrarClicked(){
        Cerrar();
    }
    @FXML
    public void btnActualizarClicked() throws SQLException{
        
        if(!isNumeric(txtNumero.getText())||!isNumeric(txtCp.getText())){
            System.out.println(isNumeric(txtNumero.getText())+"-"+isNumeric(txtCp.getText()));
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Error");
            alert1.setHeaderText("Campos llenados incorrectamente");
            alert1.setContentText("Verifique que el Numero y código postal\n sean tipo numerico");
            alert1.showAndWait();
        }
        else  if(txtNombre.getText().equals("") ||txtPaterno.getText().equals("") ||
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
            nombre=txtNombre.getText();
            paterno=(txtPaterno.getText().length()>0) ? txtPaterno.getText():"null";
            materno=(txtMaterno.getText().length()>0) ? txtMaterno.getText():"null";
            calle=txtCalle.getText();
            colonia=txtColonia.getText();
            numero=Integer.parseInt(txtNumero.getText());
            ciudad=txtCiudad.getText();
            delegacion=txtDelegacion.getText();
            cp=Integer.parseInt(txtCp.getText());
            correo=(txtCorreo.getText().length()>0)? txtCorreo.getText() :"null";
            Query="UPDATE ELENA.CLIENTES Set NOMBRE='"+nombre+"',AP_PATERNO='"+paterno+"',"
            + "AP_MATERNO='"+materno+"',CALLE='"+calle+"',COLONIA='"+colonia+"',NUMERO="+numero+","
            + "CIUDAD='"+ciudad+"',DELEGACION='"+delegacion+"',CP="+cp+",CORREO_ELECTRONICO='"+correo+"' "
            + "where RFC='"+rfc+"'";
            Actualizar(Query);  

        }                
    }
    public void setCliente(Clientes clientSelected, String usr, String pass){
        this.cliente=clientSelected;
        this.Usuario=usr;
        this.Contrasenia=pass;
        txtRfc.setText(this.cliente.getRfc().getValue());
        txtNombre.setText(this.cliente.getNombre().getValue());
        txtPaterno.setText(this.cliente.getApPaterno().getValue());
        txtMaterno.setText(this.cliente.getApMaterno().getValue());
        txtCalle.setText(this.cliente.getCalle().getValue());
        txtColonia.setText(this.cliente.getColonia().getValue()); 
        txtNumero.setText(this.cliente.getNumero().getValue());
        txtCiudad.setText(this.cliente.getCiudad().getValue()); 
        txtDelegacion.setText(this.cliente.getDelegacion().getValue());
        txtCp.setText(this.cliente.getCp().getValue());
        txtCorreo.setText(this.cliente.getCorreo() .getValue());
    }
    private void Actualizar(String Query) throws SQLException{
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
     
        if(db.Actualizar(Query)==true){
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Actualizacion exitosa")           
            .text("El cliente con rfc "+rfc+" se ha actualizado correctamente")
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

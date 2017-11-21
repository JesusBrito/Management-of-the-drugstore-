/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Almacen;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import farmacia.Utilidades.Bd;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class AgregarProductoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String codigo;
    String nombre;
    String unidad;
    String codigoAlmacen;
    String precio;
    String Query;
    @FXML private JFXTextField txtCodigoA;
    @FXML private JFXTextField txtNombreA;
    @FXML private JFXTextField txtUnidadA;
    @FXML private JFXTextField txtCodigoAlmacen;
    @FXML private JFXTextField txtPrecioVenta;
    @FXML private JFXButton btnCancelar;
    
    @FXML
    public void btnCerrarClicked(ActionEvent event) throws IOException, SQLException{
        Cerrar();
    }
    
    @FXML
    public void btnAgregarProductoClicked(ActionEvent event) throws IOException, SQLException{
        codigo= txtCodigoA.getText();
        nombre= txtNombreA.getText();
        unidad= txtUnidadA.getText();
        codigoAlmacen=txtCodigoAlmacen.getText();
        precio= txtPrecioVenta.getText();
        if(codigo.equals("")|| nombre.equals("") || unidad.equals("")|| codigoAlmacen.equals("")|| precio.equals("")){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Campos llenados incorrectamente");
            alert2.setContentText("Todos los campos son obligarorios");
            alert2.showAndWait();
            
        }else{
            Query ="Insert into ELENA.PRODUCTOS (CODIGO_PRODUCTO,NOMBRE_MEDICAMENTO,UNIDAD) "
                + "values ('"+codigo+"','"+nombre+"','"+unidad+"')"; 
            registrarBd(Query); 
            Query ="Insert into ELENA.FARMACIA (CODIGO,PRECIO,EXISTENCIA,CODIGO_PRODUCTO) values ('"+codigoAlmacen+"',"+precio+",0,'"+codigo+"')"; 
            registrarBd(Query); 
            
        }
    }
    private void registrarBd(String Query) throws SQLException{
        Bd db = new Bd();
        db.Conectar("almacen1", "12345");
        if(db.InsertarClientes(Query)==true){
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Registro exitoso")               
            .text("El producto "+nombre+" se ha registrado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();                  
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error al registrar el producto");
           alert.setContentText("Hubo un error al realizar el registro");
           alert.showAndWait();
        }        
        txtCodigoA.setText("");
        txtNombreA.setText("");
        txtUnidadA.setText("");
        txtCodigoAlmacen.setText("");
        txtPrecioVenta.setText("");
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

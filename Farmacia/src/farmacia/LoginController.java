package farmacia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;
import farmacia.Utilidades.Bd;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import farmacia.Administrador.Menu_AdminController;
import farmacia.Almacen.Menu_AlmacenController;
import farmacia.Utilidades.Usuario;
import farmacia.Venta.Menu_VendedorController;
import java.io.IOException;
import java.text.ParseException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author jesus
 */
public class LoginController implements Initializable {
    String Usuario="";
    String Contrasenia="";
    int contadorInicio=1;
    
    private Stage stagePrincipal = new Stage();;
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtContrasenia;
    @FXML
    private JFXButton btnIniciar;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException, ParseException {
       Bd base = new Bd();
       String tipoUsuario;
       Usuario=txtUsuario.getText();
       Contrasenia=txtContrasenia.getText();
       Connection conector =base.Conectar(Usuario, Contrasenia);       
       Usuario usuarioSelected= new Usuario(Usuario,Contrasenia);
       if(conector!=null){
           tipoUsuario=base.ConsultarUsuario();                    
           base.cerrarConexion();
           switch(tipoUsuario){
                case "VENDEDOR":
                    System.out.println("Exitoso");
                    Notifications notificationsBuilderVended = Notifications.create()
                     .title("Bienvenido "+Usuario)           
                     .text("Que tenga un excelente día :)")
                     .hideAfter(Duration.seconds(4))
                     .position(Pos.TOP_RIGHT);
                    notificationsBuilderVended.showConfirm();
                    
                    FXMLLoader loaderV = new FXMLLoader(LoginController.class.getResource("Menu_Vendedor.fxml"));
                    rootPane=(AnchorPane) loaderV.load();
                    Menu_VendedorController controllerV = loaderV.getController();
                    controllerV.setCredenciales(usuarioSelected);
                    Stage stageV = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stageV.setTitle("Bienvenido "+ Usuario);
                    stageV.setScene(new Scene(rootPane));
                    stageV.show();
                    
                    break;
                case "ADMINISTRADOR":
                    System.out.println("Exitoso");
                    Notifications notificationsBuilderAdmin = Notifications.create()
                     .title("Bienvenido "+Usuario)           
                     .text("Que tenga un excelente día :)")
                     .hideAfter(Duration.seconds(4))
                     .position(Pos.TOP_RIGHT);
                    notificationsBuilderAdmin.showConfirm();
                    
                    FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("Menu_Admin.fxml"));
                    rootPane=(AnchorPane) loader.load();
                    Menu_AdminController controller = loader.getController();
                    controller.setCredenciales(usuarioSelected);
                    Stage stageA = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stageA.setTitle("Bienvenido "+ Usuario);
                    stageA.setScene(new Scene(rootPane));
                    stageA.show();
                    break;
                case "ALMACEN":
                    System.out.println("Exitoso");
                    Notifications notificationsBuilderAlmacen = Notifications.create()
                     .title("Bienvenido "+Usuario)           
                     .text("Que tenga un excelente día :)")
                     .hideAfter(Duration.seconds(4))
                     .position(Pos.TOP_RIGHT);
                    notificationsBuilderAlmacen.showConfirm();
                    
                    

                    FXMLLoader loaderAlm = new FXMLLoader(LoginController.class.getResource("Menu_Almacen.fxml"));
                    rootPane=(AnchorPane) loaderAlm.load();
                    Menu_AlmacenController controllerAlm = loaderAlm.getController();
                    controllerAlm.setCredenciales(usuarioSelected);
                    Stage stageAlm = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stageAlm.setTitle("Bienvenido "+ Usuario);
                    stageAlm.setScene(new Scene(rootPane));
                    stageAlm.show();
                    
                    break;
                default:
                    System.out.println("Exitoso");
                    Notifications notificationsBuilderDefault = Notifications.create()
                     .title("¡Error!")           
                     .text("Hubo un error, el usuario no esta registrado favor de consultar a su administrador :)")
                     .hideAfter(Duration.seconds(4))
                     .position(Pos.TOP_RIGHT);
                    notificationsBuilderDefault.showError();
                    break;
           }            
       }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al Iniciar sesión");
            alert.setContentText("Usuario y/o contraseña incorrectos");
            alert.showAndWait();
            contadorInicio++;
            if (contadorInicio>3) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Error");
                alert2.setHeaderText("Limite de intentos superado");
                alert2.setContentText("El número de intentos para iniciar sesión ha sido superado,\n por favor contacta con el administrador del sistema :)");
                alert2.showAndWait();
                txtUsuario.setDisable(true);
                txtContrasenia.setDisable(true);
                btnIniciar.setDisable(true);
            }
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

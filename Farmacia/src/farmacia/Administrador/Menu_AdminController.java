package farmacia.Administrador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import farmacia.Utilidades.Bd;
import farmacia.Utilidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author jesus
 */

 //AnchorPane Eventos


public class Menu_AdminController implements Initializable {
    String Query="";
    String fechaInicio="";
    String fechaFin="";
    String pattern="";
    
    String Usuario;
    String Contrasenia;
    
    private Stage stagePrincipal = new Stage();;
    private AnchorPane rootPane;
    //==============TABLA DE PROVEEDORES==============    
    @FXML private TableView<Proveedores> tvProveedores;
    @FXML private TableColumn<Proveedores, String> colRFC;
    @FXML private TableColumn<Proveedores, String> colProv;
    @FXML private TableColumn<Proveedores, String> colapPaterno;
    @FXML private TableColumn<Proveedores, String> colapMaterno;
    @FXML private TableColumn<Proveedores, String> colCalle;
    @FXML private TableColumn<Proveedores, String> colColonia;
    @FXML private TableColumn<Proveedores, String> colNum;
    @FXML private TableColumn<Proveedores, String> colCiudad;
    @FXML private TableColumn<Proveedores, String> colDeleg;
    @FXML private TableColumn<Proveedores, String> colCp;
    @FXML private TableColumn<Proveedores, String> colTelefono;
    @FXML private TableColumn<Proveedores, String> colNombreCon;
    @FXML private JFXTextField txtRrfProv;
    
    ObservableList<Proveedores> dataProveedores = FXCollections.observableArrayList();
    ArrayList<String[]> proveedor = new ArrayList<>();
    
    //==============TABLA DE REPORTE DE VENTAS==============
    @FXML private TableView<Ventas> tvVentas;
    @FXML private TableColumn<Ventas, String> colRfc;
    @FXML private TableColumn<Ventas, String> colNota;
    @FXML private TableColumn<Ventas, String> colFecha;
    @FXML private TableColumn<Ventas, String> colNombre;
    @FXML private TableColumn<Ventas, String> colPaterno;
    @FXML private TableColumn<Ventas, String> colMaterno;
    @FXML private TableColumn<Ventas, String> colTotal;
    
    ObservableList<Ventas> dataVentas = FXCollections.observableArrayList();
    ArrayList<String[]> venta = new ArrayList<>();
    
    //==============REPORTE DE VENTAS==============
    @FXML private JFXDatePicker dateIni;
    @FXML private JFXDatePicker dateFin;

    
    @FXML private Label lblNombreUsuario;

    //==============REGISTRO DE VENDEDORES==============
    @FXML private ComboBox<Usuario> cmbProveedores;
    @FXML private TableView<Usuario> tvUsuarios;
    @FXML private JFXTextField txtNombreUsuario;
    @FXML private JFXTextField txtNombre;
    @FXML private JFXPasswordField txtContrasenia;
    @FXML private JFXComboBox cmbTipoUsuario;
    ObservableList<Usuario> dataUsuarios = FXCollections.observableArrayList();
    ArrayList<String[]> usuario = new ArrayList<>();
    
    
    //==============TABLA DE REPORTE DE VENDEDORES==============
    @FXML private TableColumn<Usuario, String> colNomb;
    @FXML private TableColumn<Usuario, String> colTipo;
    
    @FXML
    public void btnCerrarSesionClicked(ActionEvent event) throws IOException{
        Parent Alm = FXMLLoader.load(getClass().getResource("../Login.fxml"));
        Stage stageAlm = (Stage)((Node)event.getSource()).getScene().getWindow();
        stageAlm.setTitle("Bienvenido");
        stageAlm.setScene(new Scene(Alm));    
    }
    @FXML
    public void btnGenerarReporteClicked(ActionEvent event) throws IOException, SQLException, ParseException {      

        LocalDate fechaI = dateIni.getValue();
        LocalDate fechaF = dateFin.getValue();
        LocalDate hoy = LocalDate.now();
        if (fechaI==null||fechaF==null) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error en las fechas");
           alert.setContentText("Debe elegir la fecha de inicio\n y la fecha de fin");
           alert.showAndWait();        
        }else{
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
            fechaInicio  = fechaI.format(formatters);
            fechaFin  = fechaF.format(formatters);

            Query="SELECT ELENA.VENTA_MEDICAMENTOS.NO_NOTA, ELENA.VENTA_MEDICAMENTOS.FECHA, ELENA.VENTA_MEDICAMENTOS.RFC, "
                    + "ELENA.CLIENTES.NOMBRE, ELENA.CLIENTES.AP_PATERNO, ELENA.CLIENTES.AP_MATERNO, ELENA.VENTA_MEDICAMENTOS.TOTAL_VENTA "
                    + "FROM ELENA.VENTA_MEDICAMENTOS JOIN ELENA.CLIENTES ON ELENA.CLIENTES.RFC= ELENA.VENTA_MEDICAMENTOS.RFC "
                    + "WHERE ELENA.VENTA_MEDICAMENTOS.FECHA Between TO_DATE('"+fechaInicio+"') and TO_DATE('"+fechaFin+"')";
            ObtenerDatosVentas(Query);
        }
    }
    
    public void btnAgregarClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AgregarProveedor.fxml"));      
        Stage stage = new Stage();
        stage.setTitle("Agregar nuevo proveedor");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.show(); 
    }
    
    public void btnModificarClicked(ActionEvent event) throws IOException{
        
        String rfcSelected="";
        try{
            rfcSelected =tvProveedores.getSelectionModel().getSelectedItem().rfc().getValue();
                  
            String empresaSelected=tvProveedores.getSelectionModel().getSelectedItem().nombre().getValue();
            String paternoSelected=tvProveedores.getSelectionModel().getSelectedItem().apPaterno().getValue();
            String maternoSelected=tvProveedores.getSelectionModel().getSelectedItem().apMaterno().getValue();
            String calleSelected=tvProveedores.getSelectionModel().getSelectedItem().Calle().getValue();
            String coloniaSelected=tvProveedores.getSelectionModel().getSelectedItem().Colonia().getValue();
            String numeroSelected=tvProveedores.getSelectionModel().getSelectedItem().Numero().getValue();
            String ciudadSelected=tvProveedores.getSelectionModel().getSelectedItem().Ciudad().getValue();
            String delegacionSelected=tvProveedores.getSelectionModel().getSelectedItem().Delegacion().getValue();
            String cpSelected= tvProveedores.getSelectionModel().getSelectedItem().Cp().getValue();
            String telefonoSelected=tvProveedores.getSelectionModel().getSelectedItem().Telefono().getValue();
            String nombreSelected=tvProveedores.getSelectionModel().getSelectedItem().nombreEmpresa().getValue();
            
            Proveedores perSelected = new Proveedores(rfcSelected, nombreSelected,paternoSelected,
            maternoSelected,calleSelected,coloniaSelected,numeroSelected,ciudadSelected,delegacionSelected,
            cpSelected,telefonoSelected,empresaSelected);
            
            
            FXMLLoader loader = new FXMLLoader(Menu_AdminController.class.getResource("EditarProveedor.fxml"));
            rootPane=(AnchorPane) loader.load();
            EditarProveedorController controller = loader.getController();
            controller.setProveedor(perSelected, this.Usuario,this.Contrasenia);
     
            Stage stage = new Stage();
            stage.setTitle("Agregar nuevo proveedor");
            stage.setScene(new Scene(rootPane));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch(IOException ex){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Error al modificar");
            alert2.setContentText("Debe seleccionar un proveedor");
            alert2.showAndWait();
        }        
    }
    
    //==============BUSQUEDA DE PROVEEDORES==============
    public void btnBuscarProveedorClicked(ActionEvent event) throws IOException, SQLException, ParseException { 
        String RFC= txtRrfProv.getText();
        Query=("SELECT * FROM ELENA.PROVEEDORES WHERE RFC='"+RFC+"'");
            ObtenerDatosProveedores(Query);
    }
    
    //==============ACTUALIZAR PROVEEDORES==============
    public void btnActualizarClicked(ActionEvent event) throws SQLException{
       try {
            Query="SELECT * FROM ELENA.PROVEEDORES";
            ObtenerDatosProveedores(Query);
         } catch (SQLException | ParseException | IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    //================ELIMINAR PROVEEDOR=========================
    public void btnEliminarClicked(ActionEvent event) throws SQLException, ParseException, IOException{
        Query="";    
        String rfcSelected =tvProveedores.getSelectionModel().getSelectedItem().rfc().getValue();
        Query="DELETE FROM ELENA.PROVEEDORES WHERE RFC='"+rfcSelected+"'"; 
        eliminarProveedor(Query);
    } 
    
    //==============BUSQUEDA DE USUARIOS==============
    public void btnBuscarUsuarioClicked(ActionEvent event) throws IOException, SQLException, ParseException { 
        String Nombre= txtNombreUsuario.getText().toUpperCase();
        Query=("SELECT GRANTEE, GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE='"+Nombre+"'");
        ObtenerDatosUsuario(Query);
    }
    //==============AGREGAR USUARIOS===============

    public void btnAgregarUsuarioClicked(ActionEvent event) throws SQLException{
        Bd bd = new Bd();
        if(cmbTipoUsuario.getValue()!=null && txtNombre.getText()!=null&& txtContrasenia.getText()!=null){
            String cmbSeleccion = cmbTipoUsuario.getValue().toString();
            Usuario nuevoUsuario = new Usuario(txtNombre.getText(), txtContrasenia.getText());
            bd.Conectar(this.Usuario, this.Contrasenia);
            if ( bd.CrearUsuario(nuevoUsuario, cmbSeleccion)) {
                bd.cerrarConexion();
                
                Notifications notificationsBuilderAlmacen = Notifications.create()
                .title("Registro exitoso")           
                .text("El usuario: "+txtNombre.getText()+" se he registrado correctamente")
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT);
                notificationsBuilderAlmacen.showConfirm();
                bd.Conectar(this.Usuario, this.Contrasenia);
                Query="SELECT GRANTEE, GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTED_ROLE=UPPER('vendedor') OR GRANTED_ROLE=UPPER('almacen') ORDER BY GRANTEE";
                ObtenerDatosUsuario(Query);
                txtNombre.setText("");
                txtContrasenia.setText("");
            }
        }else{
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Error en los campos ");
            alert2.setContentText("Debe seleccionar todos los campos");
            alert2.showAndWait();
        }        
    }
    //==============ELIMINAR USUARIOS===============
    public void btnEliminarrUsuarioClicked(ActionEvent event) throws SQLException, ParseException, IOException{
        Query="";    
        String usuarioSelected = tvUsuarios.getSelectionModel().getSelectedItem().getUsuario().getValue();
        Query="DROP USER "+usuarioSelected+""; 
        eliminarUsuario(Query);
    }
    
    public void LlenarTablaProveedores(){
        //Se actualizan los datos de la tabla, en base a la colección "data".
        colRFC.setCellValueFactory(cellData -> cellData.getValue().rfc());
        colProv.setCellValueFactory(cellData -> cellData.getValue().nombreEmpresa());
        colapPaterno.setCellValueFactory(cellData -> cellData.getValue().apPaterno());
        colapMaterno.setCellValueFactory(cellData -> cellData.getValue().apMaterno());
        colCalle.setCellValueFactory(cellData -> cellData.getValue().Calle());
        colColonia.setCellValueFactory(cellData -> cellData.getValue().Colonia());
        colNum.setCellValueFactory(cellData -> cellData.getValue().Numero());
        colCiudad.setCellValueFactory(cellData -> cellData.getValue().Ciudad());
        colDeleg.setCellValueFactory(cellData -> cellData.getValue().Delegacion());
        colCp.setCellValueFactory(cellData -> cellData.getValue().Cp());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().Telefono());
        colNombreCon.setCellValueFactory(cellData -> cellData.getValue().nombre());
        tvProveedores.setItems(dataProveedores);
    }
    
    public void ObtenerDatosProveedores(String Query) throws SQLException, ParseException, IOException{
        
        //Se llena la colección "dataProveedores" en base a la base de datos.
        dataProveedores.clear();
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
        proveedor = db.Seleccionar(Query);        
        for (int i=0; i<=proveedor.size()-1; i++){
            dataProveedores.addAll( new Proveedores(
                    proveedor.get(i)[0], //id
                    proveedor.get(i)[1],
                    proveedor.get(i)[2],//correo
                    proveedor.get(i)[3],//telefono
                    proveedor.get(i)[4],//tratamiento
                    proveedor.get(i)[5],//empresa
                    proveedor.get(i)[6],//país
                    proveedor.get(i)[7],
                    proveedor.get(i)[8],
                    proveedor.get(i)[9],
                    proveedor.get(i)[10],//direccio
                    proveedor.get(i)[11]//direccio
            ));
        }
        db.cerrarConexion();
        LlenarTablaProveedores();
    }
    
    public void ObtenerDatosUsuario(String Query) throws SQLException{
        dataUsuarios.clear();
        Bd db = new Bd();
        db.Conectar("Elena", "brito");
        usuario= db.Seleccionar(Query);
       
        for (int i = 0; i <=usuario.size()-1; i++) {
            dataUsuarios.addAll( new Usuario(
                    usuario.get(i)[0],
                    usuario.get(i)[1]
            ));
        }
        db.cerrarConexion();
        LlenarTablaUsuario();
    }
    public void LlenarTablaUsuario(){
        colNomb.setCellValueFactory(cellData -> cellData.getValue().getUsuario());
        colTipo.setCellValueFactory(cellData -> cellData.getValue().getPassword());
        tvUsuarios.setItems(dataUsuarios);
    }
    public void LlenarTablaVentas(){
        
        colRfc.setCellValueFactory(cellData -> cellData.getValue().Rfc);
        colNota.setCellValueFactory(cellData -> cellData.getValue().NoNota);
        colNombre.setCellValueFactory(cellData -> cellData.getValue().Nombre);
        colPaterno.setCellValueFactory(cellData -> cellData.getValue().ApPaterno);
        colMaterno.setCellValueFactory(cellData -> cellData.getValue().ApMaterno);
        colFecha.setCellValueFactory(cellData -> cellData.getValue().Fecha);
        colTotal.setCellValueFactory(cellData -> cellData.getValue().Total);
        tvVentas.setItems(dataVentas);
    }
    
    public void ObtenerDatosVentas(String Query) throws SQLException, ParseException, IOException{
        //Se llena la colección "dataVentas" en base a la base de datos.
        dataVentas.clear();
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
        venta = db.Seleccionar(Query);        
        for (int i=0; i<=venta.size()-1; i++){
            dataVentas.addAll( new Ventas(
                    venta.get(i)[0],
                    venta.get(i)[1],
                    venta.get(i)[2],
                    venta.get(i)[3],
                    venta.get(i)[4],
                    venta.get(i)[5],
                    venta.get(i)[6]
            ));
        }
        db.cerrarConexion();
        LlenarTablaVentas();
    }
    
    public void eliminarProveedor(String Query) throws SQLException, ParseException, IOException{
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);  
        if (db.Eliminar(Query)) {
            db.Eliminar(Query);                
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Borrado exitoso")           
            .text("El proveedor se ha eliminado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();
            Query=("SELECT * FROM ELENA.PROVEEDORES");
            ObtenerDatosProveedores(Query);
        }else{
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setContentText("No se pudo eliminar el usuario");
            alert2.showAndWait();
        }      
    }
    public void eliminarUsuario(String Query) throws SQLException, ParseException, IOException{
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
        if (db.Eliminar(Query)) {
            db.cerrarConexion();                
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Borrado exitoso")           
            .text("El usuario se ha eliminado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();
            Query="SELECT GRANTEE, GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTED_ROLE=UPPER('vendedor') OR GRANTED_ROLE=UPPER('almacen') ORDER BY GRANTEE";
            ObtenerDatosUsuario(Query);
        }else{
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setContentText("No se pudo eliminar el usuario");
            alert2.showAndWait();
        }        
    }
    public void setCredenciales(Usuario usrSelected){
        
        this.Usuario=(usrSelected.getUsuario().getValue());
        this.Contrasenia=(usrSelected.getPassword().getValue());
        System.out.println(this.Usuario+" Set 3"+this.Contrasenia);
        try {
            Query="SELECT * FROM ELENA.PROVEEDORES";
            ObtenerDatosProveedores(Query);
            Query= "SELECT ELENA.VENTA_MEDICAMENTOS.NO_NOTA, ELENA.VENTA_MEDICAMENTOS.FECHA, ELENA.VENTA_MEDICAMENTOS.RFC, \n" +
                               "ELENA.CLIENTES.NOMBRE, ELENA.CLIENTES.AP_PATERNO, ELENA.CLIENTES.AP_MATERNO, ELENA.VENTA_MEDICAMENTOS.TOTAL_VENTA \n" +
                               "FROM ELENA.VENTA_MEDICAMENTOS JOIN ELENA.CLIENTES ON ELENA.CLIENTES.RFC= ELENA.VENTA_MEDICAMENTOS.RFC";
            ObtenerDatosVentas(Query);
            Query="SELECT GRANTEE, GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTED_ROLE=UPPER('vendedor') OR GRANTED_ROLE=UPPER('almacen') ORDER BY GRANTEE";
            ObtenerDatosUsuario(Query);
         } catch (SQLException | ParseException | IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
        lblNombreUsuario.setText(this.Usuario);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTipoUsuario.getItems().addAll(
            "VENDEDOR",
            "ALMACEN"
        ); 
    }      
}

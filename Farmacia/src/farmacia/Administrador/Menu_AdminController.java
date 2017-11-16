package farmacia.Administrador;

import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jesus
 */

 //AnchorPane Eventos


public class Menu_AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String Query="";
    String fechaInicio="";
    String fechaFin="";
    String pattern="";
    
    Usuario datosUsuario= new Usuario();
    String UsuarioBD= datosUsuario.getUsuario();
    String PasswordBD= datosUsuario.getPassword();
        
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
    
    @FXML
    public void btnGenerarReporteClicked(ActionEvent event) throws IOException, SQLException, ParseException {      

        LocalDate fechaI = dateIni.getValue();
        LocalDate fechaF = dateFin.getValue();
        LocalDate hoy = LocalDate.now();
        if (fechaI==null||fechaF==null) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error en las fechas");
           alert.setContentText("Debe elejir la fecha de inicio\n y la fecha de fin");
           alert.showAndWait();        
        }else{
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
            String fechaInicio  = fechaI.format(formatters);
            String fechaFin  = fechaF.format(formatters);

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
         } catch (SQLException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParseException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    
    public void btnEliminarClicked(ActionEvent event) throws SQLException, ParseException, IOException{
        String Query="";
        ObservableList<Proveedores> proveedorSelected;
        String rfcSelected =tvProveedores.getSelectionModel().getSelectedItem().rfc().getValue().toString();
        Query="DELETE FROM ELENA.PROVEEDORES WHERE RFC='"+rfcSelected+"'"; 
        eliminarProveedor(Query);
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
        db.Conectar("administrador1", "12345");
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
        db.Conectar("administrador1", "12345");
        venta = db.Seleccionar(Query);        
        for (int i=0; i<=venta.size()-1; i++){
            dataVentas.addAll( new Ventas(
                    venta.get(i)[0], //id
                    venta.get(i)[1],
                    venta.get(i)[2],//correo
                    venta.get(i)[3],//telefono
                    venta.get(i)[4],//tratamiento
                    venta.get(i)[5],//empresa
                    venta.get(i)[6]//país
            ));
        }
        db.cerrarConexion();
        LlenarTablaVentas();
    }
    
    public void eliminarProveedor(String Query) throws SQLException, ParseException, IOException{
        Bd db = new Bd();
        db.Conectar("administrador1", "12345");
        
        db.Eliminar(Query);
        
        Query=("SELECT * FROM ELENA.PROVEEDORES");
        ObtenerDatosProveedores(Query);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(datosUsuario.getUsuario()+"--"+datosUsuario.getPassword());
        try {
            Query="SELECT * FROM ELENA.PROVEEDORES";
            ObtenerDatosProveedores(Query);
            Query= "SELECT ELENA.VENTA_MEDICAMENTOS.NO_NOTA, ELENA.VENTA_MEDICAMENTOS.FECHA, ELENA.VENTA_MEDICAMENTOS.RFC, \n" +
                               "ELENA.CLIENTES.NOMBRE, ELENA.CLIENTES.AP_PATERNO, ELENA.CLIENTES.AP_MATERNO, ELENA.VENTA_MEDICAMENTOS.TOTAL_VENTA \n" +
                               "FROM ELENA.VENTA_MEDICAMENTOS JOIN ELENA.CLIENTES ON ELENA.CLIENTES.RFC= ELENA.VENTA_MEDICAMENTOS.RFC";
            ObtenerDatosVentas(Query);
         } catch (SQLException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParseException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }   
    }      
}

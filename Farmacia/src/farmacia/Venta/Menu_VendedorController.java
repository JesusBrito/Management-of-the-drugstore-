package farmacia.Venta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import farmacia.Administrador.Menu_AdminController;
import farmacia.Almacen.Almacen;
import farmacia.Almacen.Detalle_Compra;
import farmacia.Utilidades.Bd;
import farmacia.Utilidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
public class Menu_VendedorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stagePrincipal = new Stage();;
    private AnchorPane rootPane;
    String Query;
    int contador=0;
    @FXML private Label lblNombreUsuario;
    @FXML private JFXComboBox cmbRfc;
    @FXML private TableView<Detalle_Ventas> tvDetallesVenta;
    @FXML private TableColumn<Detalle_Ventas, String> colNota;
    @FXML private TableColumn<Detalle_Ventas, String> colFecha;
    @FXML private TableColumn<Detalle_Ventas, String> colCodigo;
    @FXML private TableColumn<Detalle_Ventas, String> colCantidad;
    @FXML private TableColumn<Detalle_Ventas, String> colPrecio;
    @FXML private TableColumn<Detalle_Ventas, String> colSubtotal;
    ObservableList<Detalle_Ventas> dataDetalles = FXCollections.observableArrayList();
    ArrayList<String[]> detalle = new ArrayList<>();
    @FXML private Label lblTotal;
     
    @FXML private TableView<Almacen> tvAlmacen;
    @FXML private TableColumn<Almacen, String> colCodigoAlm;
    @FXML private TableColumn<Almacen, String> colNombreAlm;
    @FXML private TableColumn<Almacen, String> colPrecioAlm;
    @FXML private TableColumn<Almacen, String> colExistenciaAlm;
    @FXML private TableColumn<Almacen, String> colUnidadAlm;
    @FXML private JFXTextField txtCveProducto;
    @FXML private JFXTextField txtNombreMed;
    @FXML private JFXTextField txtCantidad;
    ObservableList<Almacen> dataAlmacen = FXCollections.observableArrayList();
    ArrayList<String[]> almacen = new ArrayList<>();
    //==============TABLA DE PROVEEDORES==============    
    @FXML private TableView<Clientes> tvClientes;
    @FXML private TableColumn<Clientes, String> colRfcCli;
    @FXML private TableColumn<Clientes, String> colNombreCli;
    @FXML private TableColumn<Clientes, String> colPaternoCli;
    @FXML private TableColumn<Clientes, String> colMaternoCli;
    @FXML private TableColumn<Clientes, String> colCalleCli;
    @FXML private TableColumn<Clientes, String> colColoniaCli;
    @FXML private TableColumn<Clientes, String> colNumeroCli;
    @FXML private TableColumn<Clientes, String> colCiudadCli;
    @FXML private TableColumn<Clientes, String> colDelegCli;
    @FXML private TableColumn<Clientes, String> colCpCli;
    @FXML private TableColumn<Clientes, String> colCorreo;

    @FXML private JFXTextField txtRfcCliente;
    @FXML private JFXButton btnModificarCliente;
    @FXML private JFXButton btnEliminarCliente;
    ObservableList<Clientes> dataClientes = FXCollections.observableArrayList();
    ArrayList<String[]> cliente = new ArrayList<>();
    
    String Usuario;
    String Contrasenia;
    String noFactura;
    String fechaFact;
    String horaFact;
    String Rfc;
    String Total;
    String Cantidad;
    String Subtotal;
    String PrecioV;
    String Codigo;
    
    @FXML
     public void btnActualizarClientesClicked(ActionEvent event) throws SQLException{
       try {
            Query="SELECT * FROM ELENA.CLIENTES";
            ObtenerDatosClientes(Query);
         } catch (SQLException | ParseException | IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    @FXML
    public void btnModificarClicked(ActionEvent event) throws IOException{
        
        String rfcSelected="";
        try{
            rfcSelected =tvClientes.getSelectionModel().getSelectedItem().getRfc().getValue();
                  
            String nombreSelected=tvClientes.getSelectionModel().getSelectedItem().getNombre().getValue();
            String paternoSelected=tvClientes.getSelectionModel().getSelectedItem().getApPaterno().getValue();
            String maternoSelected=tvClientes.getSelectionModel().getSelectedItem().getApMaterno().getValue();
            String calleSelected=tvClientes.getSelectionModel().getSelectedItem().getCalle().getValue();
            String coloniaSelected=tvClientes.getSelectionModel().getSelectedItem().getColonia().getValue();
            String numeroSelected=tvClientes.getSelectionModel().getSelectedItem().getNumero().getValue();
            String ciudadSelected=tvClientes.getSelectionModel().getSelectedItem().getCiudad().getValue();
            String delegacionSelected=tvClientes.getSelectionModel().getSelectedItem().getDelegacion().getValue();
            String cpSelected= tvClientes.getSelectionModel().getSelectedItem().getCp().getValue();
            String correoSelected=tvClientes.getSelectionModel().getSelectedItem().getCorreo().getValue();

            
            Clientes perSelected = new Clientes(rfcSelected, nombreSelected,paternoSelected,
            maternoSelected,calleSelected,coloniaSelected,numeroSelected,ciudadSelected,delegacionSelected,
            cpSelected,correoSelected);
            
            
            FXMLLoader loader = new FXMLLoader(Menu_VendedorController.class.getResource("EditarCliente.fxml"));
            rootPane=(AnchorPane) loader.load();
            EditarClienteController controller = loader.getController();
            controller.setCliente(perSelected, this.Usuario,this.Contrasenia);
            
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
    @FXML
    public void btnFinalizarClicked(ActionEvent event) throws SQLException{
        Date ahora = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
        horaFact=formater.format(ahora);
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
        System.out.println(noFactura+"--"+fechaFact+"--"+Rfc+"--"+Total+"--"+horaFact);
        Query="Insert into ELENA.VENTA_MEDICAMENTOS (NO_NOTA,FECHA,RFC,TOTAL_VENTA,HORA) "
                + "values ('"+noFactura+"',to_date('"+fechaFact+"','DD/MM/RR'),'"+Rfc+"',"+Total+",to_date('"+horaFact+"','hh24:mi:ss'))";
        if(db.InsertarClientes(Query)==true){
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Registro exitoso")               
            .text("La compra con No: "+noFactura+" se ha registrado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();                  
        }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Error al registrar la compra");
           alert.setContentText("Hubo un error al realizar el registro");
           alert.showAndWait();
        } 
        for (int i = 0; i <=contador-1; i++) {
            Codigo= dataDetalles.get(i).getCodigoProducto().get();
            Cantidad= dataDetalles.get(i).getCantidad().get();
            PrecioV= dataDetalles.get(i).getPrecioC().get();
            Subtotal= dataDetalles.get(i).getSubtotal().get();
            
            
            Query="Insert into ELENA.DETALLE_VENTAS (NO_NOTA,FECHA,CANTIDAD,SUBTOTAL,PRECIO_VENTA,CODIGO) \n" +
    "                                values ('"+noFactura+"',to_date('"+fechaFact+"','DD/MM/RR'),"+Cantidad+","+Subtotal+","+PrecioV+",'"+Codigo+"')";
                if(db.InsertarClientes(Query)==true){
                    Notifications notificationsBuilderAlmacen = Notifications.create()
                    .title("Registro exitoso")               
                    .text("EL detalle de venta se ha registrado correctamente")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT);
                    notificationsBuilderAlmacen.showConfirm();                  
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Error al registrar el detalle");
                   alert.setContentText("Hubo un error al realizar el registro");
                   alert.showAndWait();
                }   
                          
            Query="Select EXISTENCIA FROM ELENA.FARMACIA WHERE CODIGO_PRODUCTO= '"+Codigo+"'";
            int existencia= db.SeleccionarExist(Query);
            System.out.println("Existencia"+existencia);
            int nuevaExistencia=existencia-Integer.parseInt(Cantidad);
            System.out.println("Existencia nueva"+nuevaExistencia);
                Query="UPDATE ELENA.FARMACIA SET EXISTENCIA= "+nuevaExistencia+" WHERE CODIGO_PRODUCTO ='"+Codigo+"'";
                if(db.Actualizar(Query)){
                    Notifications notificationsBuilderAlmacen = Notifications.create()
                    .title("Actualizacion exitosa")               
                    .text("El almacen se ha actualizado correctamente")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT);
                    notificationsBuilderAlmacen.showConfirm(); 

                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Error al actualizar el almacen");
                   alert.setContentText("Hubo un error al realizar  la actualización");
                   alert.showAndWait();
                }
            }
        
                //limpiar todos los valores

        dataDetalles.clear();
        lblTotal.setText("");
        txtCantidad.setText("");
        noFactura="";
        fechaFact="";
        Rfc="";
        Total="";
        Cantidad="";
        Subtotal="";
        PrecioV="";
        Codigo="";
        txtCveProducto.setText("");
        txtNombreMed.setText("");
        txtCantidad.setText("");
        contador=0;
        cmbRfc.setDisable(false);
    }
    @FXML
    public void btnEliminarClienteClicked(ActionEvent event) throws SQLException, ParseException, IOException{
        Query="";    
        String rfcSelected =tvClientes.getSelectionModel().getSelectedItem().getRfc().getValue();
        Query="DELETE FROM ELENA.CLIENTES WHERE RFC='"+rfcSelected+"'"; 
        eliminarCliente(Query);
    }
    @FXML
    public void btnBuscarProveedorClicked(ActionEvent event) throws IOException, SQLException, ParseException { 
        String RFC= txtRfcCliente.getText();
        Query=("SELECT * FROM ELENA.CLIENTES WHERE RFC='"+RFC+"'");
            ObtenerDatosClientes(Query);
    }
    @FXML
    public void btnAgregarClienteClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AgregarCliente.fxml"));      
        Stage stage = new Stage();
        stage.setTitle("Agregar nuevo cliente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.show(); 
    }
    
    @FXML
    public void btnBuscarProductoClicked(ActionEvent event) throws IOException, SQLException, ParseException {
        String codigo= txtCveProducto.getText();
        String nombre= txtNombreMed.getText();
        if(codigo.equals("")){
           Query="SELECT ELENA.FARMACIA.CODIGO, ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO, ELENA.FARMACIA.PRECIO, ELENA.FARMACIA.EXISTENCIA,ELENA.PRODUCTOS.UNIDAD" +
 "                FROM ELENA.FARMACIA JOIN ELENA.PRODUCTOS ON ELENA.FARMACIA.CODIGO_PRODUCTO=ELENA.PRODUCTOS.CODIGO_PRODUCTO \n" +
 "                WHERE  ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO LIKE '"+nombre+"%'";
             ObtenerDatosAlmacen(Query); 
        }else if(nombre.equals("")){
             Query="SELECT ELENA.FARMACIA.CODIGO, ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO, ELENA.FARMACIA.PRECIO, ELENA.FARMACIA.EXISTENCIA,ELENA.PRODUCTOS.UNIDAD "
                     + "FROM ELENA.FARMACIA JOIN ELENA.PRODUCTOS ON ELENA.FARMACIA.CODIGO_PRODUCTO=ELENA.PRODUCTOS.CODIGO_PRODUCTO WHERE ELENA.FARMACIA.CODIGO ='"+codigo+"'";
             ObtenerDatosAlmacen(Query);
        }else{
             Alert alert2 = new Alert(Alert.AlertType.WARNING);
             alert2.setTitle("Error");
             alert2.setHeaderText("Error en los campos de busqueda ");
             alert2.setContentText("Debe insetar sólo uno de los dos campos");
             alert2.showAndWait();    
        }
    }
    public void btnAgregarClicked(ActionEvent event) throws SQLException{
        ObtenerDatosProducto();
    }
    private void llenarTablaAlmacen(){
        colCodigoAlm.setCellValueFactory(cellData -> cellData.getValue().getCodigo());
        colNombreAlm.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        colPrecioAlm.setCellValueFactory(cellData -> cellData.getValue().getPrecio());
        colExistenciaAlm.setCellValueFactory(cellData -> cellData.getValue().getExistencia());
        colUnidadAlm.setCellValueFactory(cellData -> cellData.getValue().getUnidad());
        tvAlmacen.setItems(dataAlmacen);        
    }
    
    private void ObtenerDatosAlmacen(String Query) throws SQLException{
        dataAlmacen.clear();
        Bd db = new Bd();
        db.Conectar(this.Usuario,this.Contrasenia);
        almacen = db.Seleccionar(Query);       
        for (int i = 0; i <=almacen.size()-1; i++) {
            dataAlmacen.addAll( new Almacen(
                    almacen.get(i)[0],
                    almacen.get(i)[1],
                    almacen.get(i)[2],
                    almacen.get(i)[3],
                    almacen.get(i)[4]
            ));
        }
        db.cerrarConexion();
        llenarTablaAlmacen();
    }
    private void ObtenerDatosProducto() throws SQLException{
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int noNota=0;
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
        Query="SELECT NO_NOTA FROM ELENA.VENTA_MEDICAMENTOS "
                + "WHERE No_Nota = (SELECT MAX(No_Nota) FROM ELENA.VENTA_MEDICAMENTOS)"
                + "AND  FECHA = (SELECT MAX(FECHA) FROM ELENA.VENTA_MEDICAMENTOS)";
        noNota= db.SeleccionarNota(Query);
        noFactura=String.valueOf(noNota+1);
        Cantidad=txtCantidad.getText();
        PrecioV= tvAlmacen.getSelectionModel().getSelectedItem().getPrecio().getValue();
        
        float subtotal= Float.parseFloat(PrecioV)* Integer.parseInt(txtCantidad.getText());
        
        Subtotal= String.valueOf(subtotal);
        fechaFact  = formateador.format(fecha);
        Rfc=cmbRfc.getValue().toString();
        if(Integer.parseInt(Cantidad)>Integer.parseInt(tvAlmacen.getSelectionModel().getSelectedItem().getExistencia().getValue())){
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Existencias insuficientes");
            alert2.setContentText("La cantidad solicitada es mayor al stock");
            alert2.showAndWait();
        }else{
            Codigo=tvAlmacen.getSelectionModel().getSelectedItem().getCodigo().getValue();
            dataDetalles.addAll( new Detalle_Ventas(
                        noFactura, 
                        fechaFact,
                        Codigo,
                        Cantidad,
                        PrecioV,
                        Float.toString(subtotal)

                ));
            cmbRfc.setDisable(true);
            llenarTablaDetalles();
            double total = 0;
            for (int i = 0; i <=contador; i++) {
             total+=Double.parseDouble(dataDetalles.get(i).getSubtotal().get());     
            }
            contador++;
            Total=String.valueOf(total);
            lblTotal.setText(Total);
        }
    }
    
    private void llenarTablaDetalles(){    
        colNota.setCellValueFactory(cellData -> cellData.getValue().getNo_nota());
        colFecha.setCellValueFactory(cellData -> cellData.getValue().getFecha());
        colCodigo.setCellValueFactory(cellData -> cellData.getValue().getCodigoProducto());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().getPrecioC());
        colSubtotal.setCellValueFactory(cellData -> cellData.getValue().getSubtotal());
        tvDetallesVenta.setItems(dataDetalles);        
    }
    public void LlenarTablaClientes(){
        //Se actualizan los datos de la tabla, en base a la colección "data".
        colRfcCli.setCellValueFactory(cellData -> cellData.getValue().getRfc());
        colNombreCli.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        colPaternoCli.setCellValueFactory(cellData -> cellData.getValue().getApPaterno());
        colMaternoCli.setCellValueFactory(cellData -> cellData.getValue().getApMaterno());
        colCalleCli.setCellValueFactory(cellData -> cellData.getValue().getCalle());
        colColoniaCli.setCellValueFactory(cellData -> cellData.getValue().getColonia());
        colNumeroCli.setCellValueFactory(cellData -> cellData.getValue().getNumero());
        colCiudadCli.setCellValueFactory(cellData -> cellData.getValue().getCiudad());
        colDelegCli.setCellValueFactory(cellData -> cellData.getValue().getDelegacion());
        colCpCli.setCellValueFactory(cellData -> cellData.getValue().getCp());
        colCorreo.setCellValueFactory(cellData -> cellData.getValue().getCorreo());
        tvClientes.setItems(dataClientes);
        cmbRfc.setItems(dataClientes);
        gestionarEventos();
    }
    public void eliminarCliente(String Query) throws SQLException, ParseException, IOException{
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);  
        if (db.Eliminar(Query)) {
            db.Eliminar(Query);                
            Notifications notificationsBuilderAlmacen = Notifications.create()
            .title("Borrado exitoso")           
            .text("El cliente se ha eliminado correctamente")
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);
            notificationsBuilderAlmacen.showConfirm();
            Query=("SELECT * FROM ELENA.CLIENTES");
            ObtenerDatosClientes(Query);
        }else{
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setContentText("No se pudo eliminar el usuario");
            alert2.showAndWait();
        }      
    }
    public void ObtenerDatosClientes(String Query) throws SQLException, ParseException, IOException{
        
        //Se llena la colección "dataProveedores" en base a la base de datos.
        dataClientes.clear();
        Bd db = new Bd();
        db.Conectar(this.Usuario, this.Contrasenia);
        cliente = db.Seleccionar(Query);        
        for (int i=0; i<=cliente.size()-1; i++){
            dataClientes.addAll( new Clientes(
                    cliente.get(i)[0], //id
                    cliente.get(i)[1],
                    cliente.get(i)[2],//correo
                    cliente.get(i)[3],//telefono
                    cliente.get(i)[4],//tratamiento
                    cliente.get(i)[5],//empresa
                    cliente.get(i)[6],//país
                    cliente.get(i)[7],
                    cliente.get(i)[8],
                    cliente.get(i)[9],
                    cliente.get(i)[10]
            ));
        }
        db.cerrarConexion();
        LlenarTablaClientes();
    }
    
    public void setCredenciales(Usuario usrSelected) throws ParseException, IOException, SQLException{
        
        this.Usuario=(usrSelected.getUsuario().getValue());
        this.Contrasenia=(usrSelected.getPassword().getValue());
        System.out.println(this.Usuario+" Set 3 "+this.Contrasenia);
              
        
        Query="SELECT * FROM ELENA.CLIENTES";
        ObtenerDatosClientes(Query);
        lblNombreUsuario.setText(this.Usuario);
        
    }
        private void gestionarEventos() {
        tvClientes.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Clientes> observable, Clientes oldValue, Clientes newValue) -> {
            btnEliminarCliente.setDisable(false);
            btnModificarCliente.setDisable(false);
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}

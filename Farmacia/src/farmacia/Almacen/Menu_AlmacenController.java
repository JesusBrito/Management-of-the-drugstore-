/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Almacen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import farmacia.Administrador.Menu_AdminController;
import farmacia.Administrador.Proveedores;
import farmacia.Utilidades.Bd;
import farmacia.Utilidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
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
public class Menu_AlmacenController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    String Usuario;
    String Contrasenia;
    String Query;
    //===============
    @FXML private Label lblNombreUsuario;
    @FXML private JFXTextField txtCveProducto;
    @FXML private JFXTextField txtNombreMed;
    @FXML private JFXComboBox cmbRfc;
    @FXML private JFXComboBox cmbProducto;
    @FXML private JFXButton btnAgregarStock;
    //===============TABLA DE ALMACÉN=================
    @FXML private TableView<Almacen> tvAlmacen;
    @FXML private TableColumn<Almacen, String> colCodigo;
    @FXML private TableColumn<Almacen, String> colNombre;
    @FXML private TableColumn<Almacen, String> colPrecio;
    @FXML private TableColumn<Almacen, String> colExistencia;
    @FXML private TableColumn<Almacen, String> colUnidad;
    ObservableList<Almacen> dataAlmacen = FXCollections.observableArrayList();
    ArrayList<String[]> almacen = new ArrayList<>();
    //==============TABLA NOTA DE COMPRA
    @FXML private TableView<SolicitudCompra> tvNotaCompra;
    @FXML private TableColumn<SolicitudCompra, String> colRfc;
    @FXML private TableColumn<SolicitudCompra, String> colCodigoSol;
    @FXML private TableColumn<SolicitudCompra, String> colCantidad;
    @FXML private JFXTextField txtCantidad;
    @FXML private JFXButton btnEliminar;
    ObservableList<SolicitudCompra> dataSolicitudes = FXCollections.observableArrayList();
    ArrayList<String[]> solicitud = new ArrayList<>();
    String RfcSoli;
    String CodigSoli;
    String CantidadSol;
    //================CMb Proveedores
    ObservableList<Proveedores> dataProveedores = FXCollections.observableArrayList();
    ArrayList<String[]> proveedor = new ArrayList<>();
    
    ObservableList<Producto> dataProductos = FXCollections.observableArrayList();
    ArrayList<String[]> producto = new ArrayList<>();
    @FXML
    public void btnBuscarClicked(ActionEvent event) throws IOException, SQLException{
       String codigo= txtCveProducto.getText();
       String nombre= txtNombreMed.getText();
       if(codigo.equals("")){
          Query="SELECT ELENA.FARMACIA.CODIGO, ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO, ELENA.FARMACIA.PRECIO, ELENA.FARMACIA.EXISTENCIA,ELENA.PRODUCTOS.UNIDAD" +
"                FROM ELENA.FARMACIA JOIN ELENA.PRODUCTOS ON ELENA.FARMACIA.CODIGO_PRODUCTO=ELENA.PRODUCTOS.CODIGO_PRODUCTO \n" +
"                WHERE  ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO LIKE '"+nombre+"%'";
            obtenerDatosAlmacen(Query); 
       }else if(nombre.equals("")){
            Query="SELECT ELENA.FARMACIA.CODIGO, ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO, ELENA.FARMACIA.PRECIO, ELENA.FARMACIA.EXISTENCIA,ELENA.PRODUCTOS.UNIDAD "
                    + "FROM ELENA.FARMACIA JOIN ELENA.PRODUCTOS ON ELENA.FARMACIA.CODIGO_PRODUCTO=ELENA.PRODUCTOS.CODIGO_PRODUCTO WHERE ELENA.FARMACIA.CODIGO ='"+codigo+"'";
            obtenerDatosAlmacen(Query);
       }else{
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Error");
            alert2.setHeaderText("Error en los campos de busqueda ");
            alert2.setContentText("Debe insetar sólo uno de los dos campos");
            alert2.showAndWait();    
       }
    }
    @FXML
    public void btnActualizarClicked(ActionEvent event) throws IOException, SQLException{
            Query="SELECT ELENA.FARMACIA.CODIGO , ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO, ELENA.FARMACIA.PRECIO, ELENA.FARMACIA.EXISTENCIA,ELENA.PRODUCTOS.UNIDAD "
                    + "FROM ELENA.FARMACIA JOIN ELENA.PRODUCTOS ON ELENA.FARMACIA.CODIGO_PRODUCTO=ELENA.PRODUCTOS.CODIGO_PRODUCTO";
            obtenerDatosAlmacen(Query);
    }
    @FXML
    public void btnAgregarProductoClicked(ActionEvent event) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("AgregarProducto.fxml"));      
        Stage stage = new Stage();
        stage.setTitle("Agregar nuevo producto");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.show();   
    }
    @FXML
    public void btnAgregarStockClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("RegistrarCompra.fxml"));      
        Stage stage = new Stage();
        stage.setTitle("Registrar Compra");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    @FXML
    public void btnImprimirReporteClicked(ActionEvent event) throws IOException{
    
    }
    @FXML
    public void btnAgregarSolicitudClicked(ActionEvent event) throws IOException, SQLException{
        obtenerDatosSolicitud();
    }
    @FXML
    public void btnEliminarSolicitudClicked(ActionEvent event) throws SQLException, ParseException, IOException{
        int selectedIndex = tvNotaCompra.getSelectionModel().getSelectedIndex();
        tvNotaCompra.getItems().remove(selectedIndex);
        
    }  
    private void obtenerDatosAlmacen(String Query) throws SQLException{
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
    
    public void ObtenerDatosProductos(String Query) throws SQLException, ParseException, IOException{
        //Se llena la colección "dataProveedores" en base a la base de datos.
        dataProductos.clear();
        Bd db = new Bd();
        db.Conectar(this.Usuario,this.Contrasenia);
        producto = db.Seleccionar(Query);        
        for (int i=0; i<=producto.size()-1; i++){
            dataProductos.addAll( new Producto(
                    producto.get(i)[0], //id
                    producto.get(i)[1],
                    producto.get(i)[2]//correo
            ));
        }
        db.cerrarConexion();
        LlenarComboProductos();  
    }
    
    private void llenarTablaAlmacen(){
        colCodigo.setCellValueFactory(cellData -> cellData.getValue().getCodigo());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().getPrecio());
        colExistencia.setCellValueFactory(cellData -> cellData.getValue().getExistencia());
        colUnidad.setCellValueFactory(cellData -> cellData.getValue().getUnidad());
        tvAlmacen.setItems(dataAlmacen);        
    }  
    
    public void LlenarComboProductos(){
        //Se actualizan los datos de la tabla, en base a la colección "data".
        cmbProducto.setItems(dataProductos);
        
    }
    public void LlenarComboProveedores(){
        //Se actualizan los datos de la tabla, en base a la colección "data".
        cmbRfc.setItems(dataProveedores);
        
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
        LlenarComboProveedores();
    }
    

    private void llenarTablaSolicitud(){
        colRfc.setCellValueFactory(cellData -> cellData.getValue().getRfc());
        colCodigoSol.setCellValueFactory(cellData -> cellData.getValue().getCodigo());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
        tvNotaCompra.setItems(dataSolicitudes);
        gestionarEventos();
    }
    private void obtenerDatosSolicitud() throws SQLException{
        RfcSoli=cmbRfc.getValue().toString();
        CodigSoli=cmbProducto.getValue().toString();
        CantidadSol=txtCantidad.getText();
        dataSolicitudes.addAll( new SolicitudCompra(
                    RfcSoli, 
                    CodigSoli,
                    CantidadSol
            ));
        llenarTablaSolicitud();
    }
    public void setCredenciales(Usuario usrSelected) throws ParseException, IOException{
        
        this.Usuario=(usrSelected.getUsuario().getValue());
        this.Contrasenia=(usrSelected.getPassword().getValue());
        System.out.println(this.Usuario+" Set 3 "+this.Contrasenia);
        lblNombreUsuario.setText(this.Usuario);
        try {
            Query="SELECT ELENA.FARMACIA.CODIGO, ELENA.PRODUCTOS.NOMBRE_MEDICAMENTO, ELENA.FARMACIA.PRECIO, ELENA.FARMACIA.EXISTENCIA,ELENA.PRODUCTOS.UNIDAD "
                    + "FROM ELENA.FARMACIA JOIN ELENA.PRODUCTOS ON ELENA.FARMACIA.CODIGO_PRODUCTO=ELENA.PRODUCTOS.CODIGO_PRODUCTO";
            obtenerDatosAlmacen(Query);
            
            Query="SELECT * FROM ELENA.PROVEEDORES";
            ObtenerDatosProveedores(Query);
            Query="SELECT * FROM ELENA.PRODUCTOS";
            ObtenerDatosProductos(Query);
         } catch (SQLException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    private void gestionarEventos() {
        tvNotaCompra.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<SolicitudCompra>() {

            @Override
            public void changed(ObservableValue<? extends SolicitudCompra> observable, SolicitudCompra oldValue, SolicitudCompra newValue) {
                btnEliminar.setDisable(false);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
}

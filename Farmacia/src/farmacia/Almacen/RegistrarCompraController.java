/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Almacen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import farmacia.Administrador.Proveedores;
import farmacia.Utilidades.Bd;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author jesus
 */
public class RegistrarCompraController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private JFXTextField txtNoFactura;
    @FXML private DatePicker dtFecha;
    @FXML private JFXComboBox cmbRFC;
    @FXML private TableColumn<Detalle_Compra, String> colNoFactura;
    @FXML private TableColumn<Detalle_Compra, String> colFechaFact;
    @FXML private TableColumn<Detalle_Compra, String> colCodigo;
    @FXML private TableColumn<Detalle_Compra, String> colCantidad;
    @FXML private TableColumn<Detalle_Compra, String> colPrecio;
    @FXML private TableColumn<Detalle_Compra, String> colSubtotal;
    @FXML private TableView<Detalle_Compra> tvFactura;
    @FXML private JFXComboBox cmbCodigo;
    @FXML private JFXTextField txtCantidad;
    @FXML private JFXTextField txtPrecio;
    @FXML private Label lblTotal;
    int contador=0;
    String Query;
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
    ObservableList<Detalle_Compra> dataDetalle = FXCollections.observableArrayList();
    ArrayList<String[]> detalle = new ArrayList<>();
    ObservableList<Proveedores> dataProveedores = FXCollections.observableArrayList();
    ArrayList<String[]> proveedor = new ArrayList<>();
    ObservableList<Producto> dataProductos = FXCollections.observableArrayList();
    ArrayList<String[]> producto = new ArrayList<>();
    @FXML private JFXButton btnCancelar;
    
    String noFactura;
    String fechaFact;
    String Rfc;
    String Total;
    String Cantidad;
    String Subtotal;
    String PrecioC;
    String Codigo;
    
    
    public void btnFinalizarClicked(ActionEvent event) throws SQLException{
        Bd db = new Bd();
        db.Conectar("almacen1", "12345");
        System.out.println(noFactura+"--"+fechaFact+"--"+Rfc+"--"+Total);
        Query="Insert into ELENA.COMPRA_MEDICAMENTOS (NO_FACTURA,FECHA,RFC,TOTAL_COMPRA) "
                + "values ('"+noFactura+"',to_date('"+fechaFact+"','DD/MM/RR'),'"+Rfc+"',"+Total+")";
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
            Cantidad= dataDetalle.get(i).getCantidad().get();
            Subtotal= dataDetalle.get(i).getSubtotal().get();
            PrecioC= dataDetalle.get(i).getPrecioC().get();
            Codigo= dataDetalle.get(i).getCodigoProducto().get();
            Query="Insert into ELENA.DETALLE_COMPRAS (NO_FACTURA,FECHA,CANTIDAD,SUBTOTAL,PRECIO_COMPRA,CODIGO_PRODUCTO) \n" +
"                                values ('"+noFactura+"',to_date('"+fechaFact+"','DD/MM/RR'),"+Cantidad+","+Subtotal+","+PrecioC+",'"+Codigo+"')";
            if(db.InsertarClientes(Query)==true){
                Notifications notificationsBuilderAlmacen = Notifications.create()
                .title("Registro exitoso")               
                .text("EL detalle de compra se ha registrado correctamente")
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
        }
        contador=0;
    }
    public void btnAgregarClicked(ActionEvent event) throws SQLException{
        obtenerDatosProducto();
    }
    public void btnCancelarClicked(ActionEvent event) throws SQLException{
        Cerrar();
    } 
    
    private void llenarTablaDetalles(){
        colNoFactura.setCellValueFactory(cellData -> cellData.getValue().getNo_factura());
        colFechaFact.setCellValueFactory(cellData -> cellData.getValue().getFecha());
        colCodigo.setCellValueFactory(cellData -> cellData.getValue().getCodigoProducto());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().getCantidad());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().getPrecioC());
        colSubtotal.setCellValueFactory(cellData -> cellData.getValue().getSubtotal());
        tvFactura.setItems(dataDetalle);        
    }
    private void obtenerDatosProducto() throws SQLException{
        noFactura=txtNoFactura.getText();
        Cantidad=txtCantidad.getText();
        float subtotal= Float.parseFloat(txtPrecio.getText())* Integer.parseInt(txtCantidad.getText());
        Subtotal= String.valueOf(subtotal);
        PrecioC=txtPrecio.getText();
        LocalDate fecha = dtFecha.getValue();
        fechaFact  = fecha.format(formatters);
        Rfc=cmbRFC.getValue().toString();
        Codigo=cmbCodigo.getValue().toString();
        dataDetalle.addAll( new Detalle_Compra(
                    noFactura, 
                    fechaFact,
                    Cantidad,
                    Float.toString(subtotal),
                    PrecioC,
                    Codigo
            ));
        llenarTablaDetalles();
        txtNoFactura.setEditable(false);
        cmbRFC.setEditable(false);
        dtFecha.setEditable(false);
        txtCantidad.setText("");
        txtPrecio.setText("");
        double total = 0;
        for (int i = 0; i <=contador; i++) {
         total+=Double.parseDouble(dataDetalle.get(i).getSubtotal().get());     
        }
        contador++;
        Total=String.valueOf(total);
        lblTotal.setText(Total);
    }
    
    public void LlenarComboProveedores(){
        //Se actualizan los datos de la tabla, en base a la colección "data".
        cmbCodigo.setItems(dataProductos);
        
    }
    public void ObtenerDatosProveedores(String Query) throws SQLException, ParseException, IOException{
        //Se llena la colección "dataProveedores" en base a la base de datos.
        dataProveedores.clear();
        Bd db = new Bd();
        db.Conectar("almacen1", "12345");
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
        
    public void LlenarComboProductos(){
        //Se actualizan los datos de la tabla, en base a la colección "data".
        cmbRFC.setItems(dataProveedores);
        
    }
    public void ObtenerDatosProductos(String Query) throws SQLException, ParseException, IOException{
        //Se llena la colección "dataProveedores" en base a la base de datos.
        dataProductos.clear();
        Bd db = new Bd();
        db.Conectar("almacen1", "12345");
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
    
    private void Cerrar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        try {
            Query="SELECT * FROM ELENA.PROVEEDORES";
            ObtenerDatosProveedores(Query);
            Query="SELECT * FROM ELENA.PRODUCTOS";
            ObtenerDatosProductos(Query);
        } catch (SQLException | ParseException | IOException ex) {
            Logger.getLogger(RegistrarCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

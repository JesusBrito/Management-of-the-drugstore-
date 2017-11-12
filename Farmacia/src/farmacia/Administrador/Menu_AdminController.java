/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Administrador;

import com.jfoenix.controls.JFXTextField;
import farmacia.Utilidades.Bd;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    String UsuarioBD="asd", PasswordBD="asd";
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
    
    
    
    public void setCredenciales(String usr, String pass){
       this.UsuarioBD=usr;
       this.PasswordBD=pass;
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
    
    public void ObtenerDatosProveedores() throws SQLException, ParseException, IOException{
        //Se llena la colección "dataClientes" en base a la base de datos.
        dataProveedores.clear();
        Bd db = new Bd();
        db.Conectar("administrador1", "12345");
        proveedor = db.Seleccionar("SELECT * FROM ELENA.PROVEEDORES");        
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
    
    public void ObtenerDatosVentas() throws SQLException, ParseException, IOException{
        //Se llena la colección "dataClientes" en base a la base de datos.
        dataVentas.clear();
        Bd db = new Bd();
        db.Conectar("administrador1", "12345");
        venta = db.Seleccionar("SELECT ELENA.VENTA_MEDICAMENTOS.NO_NOTA, ELENA.VENTA_MEDICAMENTOS.FECHA, ELENA.VENTA_MEDICAMENTOS.RFC, \n" +
                               "ELENA.CLIENTES.NOMBRE, ELENA.CLIENTES.AP_PATERNO, ELENA.CLIENTES.AP_MATERNO, ELENA.VENTA_MEDICAMENTOS.TOTAL_VENTA \n" +
                               "FROM ELENA.VENTA_MEDICAMENTOS JOIN ELENA.CLIENTES ON ELENA.CLIENTES.RFC= ELENA.VENTA_MEDICAMENTOS.RFC");        
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             ObtenerDatosProveedores();
             ObtenerDatosVentas();
         } catch (SQLException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParseException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }   
    }      
}

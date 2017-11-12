/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Administrador;

import com.jfoenix.controls.JFXTextField;
import farmacia.Utilidades.Bd;
import java.awt.image.BufferedImage;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    

    public void setCredenciales(String usr, String pass){
       this.UsuarioBD=usr;
       this.PasswordBD=pass;
    }

    public void actualizarTablaProveedores(){
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
    
    public void actualizarBDProveedores() throws SQLException, ParseException, IOException{
        //Se llena la colección "dataClientes" en base a la base de datos.
        dataProveedores.clear();
        Bd db = new Bd();
        db.Conectar("administrador1", "12345");
        proveedor = db.Seleccionar("SELECT * FROM ELENA.PROVEEDORES");
        System.out.println(proveedor.size());
        
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
        actualizarTablaProveedores();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(this.UsuarioBD+","+this.PasswordBD);
        try {
             actualizarBDProveedores();
         } catch (SQLException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParseException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Menu_AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }   
    }    
    
}

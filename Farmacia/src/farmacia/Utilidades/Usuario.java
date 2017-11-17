/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Utilidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jesus
 */
public class Usuario {
    private  StringProperty Usuario; 
    private  StringProperty Password;
    private  StringProperty Rol;
    
    public Usuario(){}
    public Usuario(String usr, String pass){
        this.Usuario= new SimpleStringProperty(usr);
        this.Password= new SimpleStringProperty(pass);
    }    
    public SimpleStringProperty getUsuario(){
        return (SimpleStringProperty)Usuario;
    }
    public SimpleStringProperty getPassword(){
        return(SimpleStringProperty) Password;
    }
}

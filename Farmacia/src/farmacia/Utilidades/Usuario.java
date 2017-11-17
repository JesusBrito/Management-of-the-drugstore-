/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.Utilidades;

/**
 *
 * @author jesus
 */
public class Usuario {
    private  String Usuario =""; 
    private  String Password =""; 
    
    public Usuario(){}
    public Usuario(String usr, String pass){
        this.Usuario=usr;
        this.Password=pass;
    }
    
    public String getUsuario(){
        return Usuario;
    }
    public String getPassword(){
        return Password;
    }
    public void setUsuario(String usr){
        this.Usuario=usr;
    }
    public void setPassword(String pass){
        this.Password=pass;
    }
}

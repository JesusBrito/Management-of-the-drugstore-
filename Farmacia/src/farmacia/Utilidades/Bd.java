package farmacia.Utilidades;

import java.sql.*;
import java.util.ArrayList;

public class Bd {
    private Connection conexion=null;
    public Connection Conectar(String usuario, String contrasenia){
        try
        {
            //Se carga el driver JDBC
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
            //nombre del servidor
            String nombre_servidor = "localhost";
            //numero del puerto
            String numero_puerto = "1521";
            //SID
            String sid = "xe";
            //URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
            String url = "jdbc:oracle:thin:@" + nombre_servidor + ":" + numero_puerto + ":" + sid;
 
            //Nombre usuario y password
            String user = usuario;
            String password = contrasenia;
 
            //Obtiene la conexion
            conexion = DriverManager.getConnection( url, user, password );
        }catch( SQLException e ){
            e.printStackTrace();
        }
        return conexion;
    }
    public String ConsultarUsuario() throws SQLException{
        String usuario="";
        String query = "SELECT username, granted_role from user_role_privs";         
        try {
            PreparedStatement stmt = conexion.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
               usuario=rs.getString("granted_role"); 
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error finding department in DAO", se);
        }
        return usuario;
    }
  
    public ArrayList<String[]> Seleccionar(String query){

        ArrayList<String[]> elementos = new ArrayList<>();
        try{
            Statement cmd = conexion.createStatement();
            ResultSet res = cmd.executeQuery(query);
            
            int clm = res.getMetaData().getColumnCount();
            
            while(res.next()){               
                String[] data = new String[res.getMetaData().getColumnCount()];  
                
                for (int i = 0; i<=clm-1; i++){   
                    String dato = res.getString(res.getMetaData().getColumnName(i+1));
                    data[i] = dato;
                }
                elementos.add(data);
            }
            res.close();
            cmd.close();
            
        }catch (SQLException ex){
            System.err.println(ex);
            //ErrorMessage("MySQL Error", "Ha ocurrido un error en la Base de Datos", "No se pudo seleccionar información desde la Base de Datos.");
        }
        return elementos;
    }
    
     public void cerrarConexion() throws SQLException{
        conexion.close();
    }
    
}

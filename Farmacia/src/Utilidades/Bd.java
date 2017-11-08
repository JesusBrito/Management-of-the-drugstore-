package Utilidades;

import java.sql.*;

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
    
    public void ConsultarUsuario() throws SQLException{
         String query = "SELECT username, granted_role from user_role_privs;";     
            
        try {
            PreparedStatement stmt = conexion.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("Salida: "+ rs.getString("username")+" "+ rs.getString("granted_role")); 
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error finding department in DAO", se);
        } 
    }
    public void cerrarConexion() throws SQLException{
        conexion.close();
    }
}

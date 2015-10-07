/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NProt.accesoDatos;

/**
 *
 * @author Oswaldo
 */
import java.sql.*;

public class accesoDatos {

    String url, usuario, password;
    Connection conexion;
    Statement instruccion;
    ResultSet tabla;

    public accesoDatos() {

        
    }

    public Connection conectar() {
            String driver = "org.postgresql.Driver"; // el nombre de nuestro driver Postgres.
         //Crear el objeto de conexion a la base de datos
            String connectString = "jdbc:postgresql://localhost:5432/relationship_cysteine_evolutionary_distances/"; // llamamos nuestra bd            System.out.println("Conexion Abierta");
            String user = "postgres"; // usuario postgres
            String password ="cobain"; // no tiene password nuestra bd.
            
            try {
                Class.forName(driver);
                //Hacemos la coneccion.
                Connection conn = DriverManager.getConnection(connectString, user, password);
                //Si la conexion fue realizada con exito, muestra el sgte mensaje.
                System.out.println("Conexion a la base de datos Ejemplo realizada con exito! ");
                //Cerramos la conexion
                return conn;
            }catch(Exception e){
                
                System.out.println("Conexion a la base de datos Ejemplo realizada Fallo");
                return null;
            }
             
             

    } 
    public void cerrarConexion(Connection c) {
        try {
            c.close();
        } catch (Exception e) {
            System.out.println("No se pudo cerrar.");
        }
    }
}//end class

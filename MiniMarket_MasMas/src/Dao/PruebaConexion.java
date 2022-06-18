package Dao;
// libreria
import java.sql.*;
import Formatos.Mensajes;

public class PruebaConexion implements Parametros {
    
    public static void main(String[] args) {
        
        try {
            
            Class.forName(driver);
            Connection con = DriverManager.getConnection(ruta, usuario, clave);
            Mensajes.m1("Conexión a Base de datos exitosa");
            
        } catch (Exception e) {
            Mensajes.m1("Error no se puede conectar la BD" + e);
        }
        
    }
    
}

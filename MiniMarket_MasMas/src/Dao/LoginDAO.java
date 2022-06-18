
package Dao;

import Modelo.Login;
import java.sql.SQLException;

public class LoginDAO extends ConectarBD{

    public LoginDAO() {
    }
    
    
    
    public Login log(String dni, String pass){
        Login l = new Login();
        try {
            rs= st.executeQuery("SELECT * FROM empleado WHERE Dni = '"+ dni + "' AND contrasena = '"+pass+"'");
            if (rs.next()) {
                l.setDni(rs.getString("Dni"));
                l.setPass(rs.getString("contrasena"));
                l.setRol(rs.getInt("idRol"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
}


package Dao;
// librerias
import Modelo.*;
import java.util.*;
import Formatos.*;
//import javax.swing.JComboBox;


public class EmpleadoDAO extends ConectarBD implements crudEmpleados {
    
    @Override
    public ArrayList<Empleado> SincronizarListaEmpleado() {
        ArrayList<Empleado> Lista = new ArrayList();
        
        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT e.IdEmpleado, e.Dni, e.Nombres, e.Telefono, r.nombre, e.contrasena, e.Apellidos\n" +
"FROM empleado e INNER JOIN roles r ON (e.idRol = r.id);");
            // recorremos los registros de la consulta
            while(rs.next()) { // recupera registros por registro
                Empleado emplea = new Empleado();
                emplea.setIdemp(rs.getInt(1));
                emplea.setDni(rs.getString(2));
                emplea.setNombres(rs.getString(3));
                emplea.setTelefono(rs.getInt(4));
                emplea.setNombreCargo(rs.getString(5));
                emplea.setContrasena(rs.getString(6));
                emplea.setApellidos(rs.getString(7));
                Lista.add(emplea);
            }
            
            rs.close(); // cerramos la conexion para liberar espacio
            
        } catch (Exception e){ 
            Mensajes.m1("Error no se puede recuperar los datos" + e);
        }
        return Lista;
    }
    // -- Insertando la lista de ROLES PARA EL COMBOBOX
    @Override
    public ArrayList<Rol> SincronizarListaRolesEnEmpleado() {
        ArrayList<Rol> listaroles = new ArrayList<>();
        
        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT * FROM roles");
            
            while(rs.next()) {
                Rol roles = new Rol();
                roles.setCodRol(rs.getInt("id"));
                roles.setNomRol(rs.getString("nombre"));
                listaroles.add(roles);
            }
            
        } catch (Exception e) {
            Mensajes.m1("Error no se puede llamar a la lista de roles" + e);
        }
        return listaroles;
    }
    // -- Insertando la lista de ROLES PARA EL COMBOBOX

    @Override
    public void InsertarEmpleado(Empleado empleado) {
        try {
            // definimos la consulta SQL con interrogantes
            ps = conexion.prepareStatement("INSERT INTO empleado (Dni,Nombres,Telefono,idRol,contrasena,Apellidos)VALUES(?,?,?,?,?,?)");
            // actualizar los parametros
            //ps.setInt(1, 0);
            ps.setString(1, empleado.getDni());
            ps.setString(2, empleado.getNombres());
            ps.setInt(3, empleado.getTelefono());
            ps.setInt(4, empleado.getIdcargo());
            ps.setString(5, empleado.getContrasena());
            ps.setString(6, empleado.getApellidos());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
            
            
        } catch (Exception e) {
             Mensajes.m1("Error no se puede insertar el registro" + e);
        }
    }

    @Override
    public Empleado BuscarEmpleadoPorCodigo(String codempleado) {
        Empleado emplea = null; // creamos el objeto inicializamsoe en vacio
        try {
            rs = st.executeQuery("SELECT e.Dni, e.Nombres, e.Telefono, r.nombre, e.contrasena, e.Apellidos, r.id FROM empleado e INNER JOIN roles r ON (e.idRol = r.id) WHERE Dni = '" + codempleado + "'");
                    
            if(rs.next()) {
                emplea = new Empleado();
                emplea.setDni(rs.getString(1));
                emplea.setNombres(rs.getString(2));
                emplea.setTelefono(rs.getInt(3));
                emplea.setNombreCargo(rs.getString(4));
                emplea.setContrasena(rs.getString(5));
                emplea.setApellidos(rs.getString(6));
                emplea.setIdcargo(rs.getInt(7));
                
            }
            rs.close();
            
        } catch (Exception e) {
            Mensajes.m1("Error no se puede buscar el DNI..." + e);
        }
        return emplea;
    }

    @Override
    public void ActualizarEmpleado(Empleado nuevoempleado) {
        try {
            ps = conexion.prepareStatement("UPDATE empleado SET Nombres=?,Telefono = ?, idRol = ? ,contrasena = ?,Apellidos = ? WHERE Dni = ?");
            // actualizar los parametros 
            ps.setString(1, nuevoempleado.getNombres());
            ps.setInt(2, nuevoempleado.getTelefono());
            ps.setInt(3, nuevoempleado.getIdcargo());
            ps.setString(4, nuevoempleado.getContrasena());
            ps.setString(5, nuevoempleado.getDni());
            ps.setString(6, nuevoempleado.getApellidos());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
         
        } catch (Exception e) {
            Mensajes.m1("Error no se puede actualizar el empleado..." + e);
        }
    }

    @Override
    public void EliminarEmpleado(String codempleado) {
        try {
            ps = conexion.prepareStatement("DELETE FROM empleado WHERE Dni = ?");
            ps.setString(1, codempleado);
            ps.executeUpdate();
            ps.close();    
            
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar el empleado..." + e);
        }   
    }
}

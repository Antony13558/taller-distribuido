
package Dao;
// librerias
import Modelo.*;
import java.util.*;
import Formatos.*;

public class RolesDAO extends ConectarBD implements crudRoles {
    
    @Override
    public ArrayList<Rol> SincronizarListaRol() {
        ArrayList<Rol> Lista = new ArrayList();
        
        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT id, nombre FROM roles");
            // recorremos los registros de la consulta
            while(rs.next()) { // recupera registros por registro
                Rol ro = new Rol();
                ro.setCodRol(rs.getInt(1));
                ro.setNomRol(rs.getString(2));
                Lista.add(ro);
            }
            
            rs.close(); // cerramos la conexion para liberar espacio
            
        } catch (Exception e){ 
            Mensajes.m1("Error no se puede recuperar los datos" + e);
        }
        return Lista;
    }

    @Override
    public void InsertarRol(Rol rol) {
        try {
            // definimos la consulta SQL con interrogantes
            ps = conexion.prepareStatement("INSERT INTO roles(nombre) VALUES(?)");
            // actualizar los parametros 
            //ps.setInt(1, 0);
            ps.setString(1, rol.getNomRol());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
            
            
        } catch (Exception e) {
            //Mensajes.m1("Error no se puede insertar el registro" + e);
            Mensajes.m1("Error no se puede insertar el registro con el mismo ID" + e);
        }
    }

    @Override
    public Rol BuscarRolPorNombre(String nombrol) {
        Rol ro = null;
        
        try {
            rs = st.executeQuery("SELECT id, nombre FROM roles WHERE nombre = '" + nombrol + "'");
            if(rs.next()) {
                ro = new Rol();
                ro.setCodRol(rs.getInt(1));
                ro.setNomRol(rs.getString(2));
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("Error no se puede buscar el nombre..." + e);
        }
        return ro;
    }

    @Override
    public void ActualizarRol(Rol nuevorol) {
        try {
            
            ps = conexion.prepareStatement("UPDATE roles SET nombre = ? WHERE id = ?");
            // actualizar los parametros 
            ps.setString(1, nuevorol.getNomRol());
            ps.setInt(2, nuevorol.getCodRol());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
         
        } catch (Exception e) {
            Mensajes.m1("Error no se puede actualizar el rol..." + e);
        }
    }

    @Override
    public void EliminarRol(String codrol) {
        try {
            ps = conexion.prepareStatement("DELETE FROM roles WHERE id = ?");
            ps.setString(1, codrol);
            ps.executeUpdate();
            ps.close();    
            
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar el rol..." + e);
        }
    }    
}

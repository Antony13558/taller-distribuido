
package Dao;

import Modelo.Categoria;
import java.util.ArrayList;
import Formatos.*;

public class CategoriaDAO extends ConectarBD implements crudCategoria {
    
    @Override
    public ArrayList<Categoria> SincronizarListaCategoria() {
        ArrayList<Categoria> Lista = new ArrayList();
        
        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT IdCategoria, nombre, descripcion FROM categoria");
            // recorremos los registros de la consulta
            while(rs.next()) {
                Categoria cat = new Categoria();
                cat.setCodCat(rs.getInt(1));
                cat.setNomCat(rs.getString(2));
                cat.setDescripcion(rs.getString(3));
                Lista.add(cat);
            }
            
            rs.close(); // cerramos la conexion para liberar espacio
            
        } catch (Exception e){ 
            Mensajes.m1("Error no se puede recuperar los datos" + e);
        }
        return Lista;
    }

    @Override
    public void InsertarCategoria(Categoria cat) {
        try {
            // definimos la consulta SQL con interrogantes
            ps = conexion.prepareStatement("INSERT INTO categoria(nombre, descripcion) VALUES(?, ?)");
            // actualizar los parametros 
            //ps.setInt(1, 0);
            ps.setString(1, cat.getNomCat());
            ps.setString(2, cat.getDescripcion());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
            
            
        } catch (Exception e) {
            //Mensajes.m1("Error no se puede insertar el registro" + e);
            Mensajes.m1("Error no se puede insertar el registro con el mismo ID" + e);
        }
    }

    @Override
    public Categoria BuscarCategoriaPorNombre(String nombcat) {
        Categoria cat = null;
        
        try {
            rs = st.executeQuery("SELECT IdCategoria, nombre, descripcion FROM categoria WHERE nombre = '" + nombcat + "'");
            if(rs.next()) {
                cat = new Categoria();
                cat.setCodCat(rs.getInt(1));
                cat.setNomCat(rs.getString(2));
                cat.setDescripcion(rs.getString(3));
            }
            rs.close();
        } catch (Exception e) {
            Mensajes.m1("Error no se puede buscar el nombre..." + e);
        }
        return cat;
    }

    @Override
    public void ActualizarCategoria(Categoria nuevocat) {
        try {
            
            ps = conexion.prepareStatement("UPDATE categoria SET nombre = ?, descripcion = ? WHERE IdCategoria = ?");
            // actualizar los parametros 
            ps.setString(1, nuevocat.getNomCat());
            ps.setString(2, nuevocat.getDescripcion());
            ps.setInt(3, nuevocat.getCodCat());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
         
        } catch (Exception e) {
            Mensajes.m1("Error no se puede actualizar el categoria..." + e);
        }
    }

    @Override
    public void EliminarCategoria(String codcat) {
        try {
            ps = conexion.prepareStatement("DELETE FROM categoria WHERE IdCategoria = ?");
            ps.setString(1, codcat);
            ps.executeUpdate();
            ps.close();    
            
        } catch (Exception e) {
            Mensajes.m1("ERROR no se puede eliminar la categoria..." + e);
        }
    }
}

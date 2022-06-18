package Dao;

// librerias
import Modelo.Inventario;
import java.util.*;
import Formatos.*;
import Modelo.Categoria;

public class InventarioDAO extends ConectarBD implements crudInventario {
    
    @Override
    public ArrayList<Inventario> SincronizarListaProductoEnInventario() {
        ArrayList<Inventario> Lista = new ArrayList();
        
        try {
            
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT codProducto, nombre, descripcion, categoria, stock FROM producto");
            // recorremos los registros de la consulta
            while(rs.next()) { // recupera registros por registro
                Inventario prodInv = new Inventario();
                prodInv.setCodProd(rs.getString(1));
                prodInv.setNomProd(rs.getString(2));
                prodInv.setDescripcion(rs.getString(3));
                prodInv.setCategoria(rs.getInt(4));
                prodInv.setStock(rs.getInt(5));
                Lista.add(prodInv);
            }
            
            rs.close(); // cerramos la conexion para liberar espacio
            
        } catch (Exception e) {
            Mensajes.m1("Error no se puede recuperar los datos" + e);
        }
        return Lista;
    }
    

    @Override
    public Inventario BuscarProductoEnInventarioPorCodigo(String codprodinvent) {
        Inventario prodInv = null; // creamos el objeto inicializamsoe en vacio
        try {
            rs = st.executeQuery("SELECT codProducto, nombre, descripcion, categoria, stock FROM producto WHERE codProducto='"+ codprodinvent +"'");
            if(rs.next()) {
                prodInv = new Inventario();
                prodInv.setCodProd(rs.getString(1));
                prodInv.setNomProd(rs.getString(2));
                prodInv.setDescripcion(rs.getString(3));
                prodInv.setCategoria(rs.getInt(4));
                prodInv.setStock(rs.getInt(5));
            }
            rs.close();
            
        } catch (Exception e) {
            Mensajes.m1("Error no se puede buscar el codigo..." + e);
        }
        return prodInv;
    }

    @Override
    public void ActualizarProductoEnInventario(Inventario nuevoprodinvent) { 
        try {
            ps = conexion.prepareStatement("UPDATE producto SET nombre=?, descripcion = ?,categoria = ?, stock = ? WHERE codProducto = ?");
            // actualizar los parametros
            ps.setString(1, nuevoprodinvent.getNomProd());
            ps.setString(2, nuevoprodinvent.getDescripcion());
            ps.setInt(3, nuevoprodinvent.getCategoria());
            ps.setInt(4, nuevoprodinvent.getStock());
            ps.setString(5, nuevoprodinvent.getCodProd());
            ps.executeUpdate(); // actualiza y ejecuta  la consulta SQL
            ps.close();
        } catch (Exception e) {
            Mensajes.m1("Error no se puede actualizar el producto..." + e);
        }
    }

    @Override
    public ArrayList<Categoria> SincronizarListaCategoriasEnInventario() {
        ArrayList<Categoria> listacat = new ArrayList<>();

        try {
            // ejecutamos la consulta
            rs = st.executeQuery("SELECT IdCategoria, nombre FROM categoria");

            while (rs.next()) {
                Categoria categ = new Categoria();
                categ.setCodCat(rs.getInt("IdCategoria"));
                categ.setNomCat(rs.getString("nombre"));
                listacat.add(categ);
            }

        } catch (Exception e) {
            Mensajes.m1("Error no se puede llamar a la lista de categorias" + e);
        }
        return listacat;
    }
} // fin de la clase DAO

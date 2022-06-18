
package Dao;
// libreria
import Modelo.Categoria;
import Modelo.Producto;
import java.util.ArrayList;


public interface crudProducto {
    public void InsertarProducto(Producto prod);
    
    public Producto BuscarProductoPorCodigo(String codproducto);
    public Producto BuscarProductoPorCodigoAPI(String codproducto);
    
    public void ActualizarProducto(Producto nuevoprod);
    
    public void EliminarProducto(String codproducto);
    
    public ArrayList<Producto> SincronizarListaProducto();
    
    public ArrayList<Categoria> SincronizarListaCategoriasEnProducto();
}

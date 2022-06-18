package Dao;
// libreria
import Modelo.Categoria;
import java.util.ArrayList;
import Modelo.Inventario;

public interface crudInventario {
    public Inventario BuscarProductoEnInventarioPorCodigo(String codprodinvent);
    
    public void ActualizarProductoEnInventario(Inventario nuevoprodinvent);
    
    public ArrayList<Categoria> SincronizarListaCategoriasEnInventario();
    
    public ArrayList<Inventario> SincronizarListaProductoEnInventario();
}

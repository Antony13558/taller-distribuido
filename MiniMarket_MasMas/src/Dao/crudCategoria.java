
package Dao;
import java.util.ArrayList;
import Modelo.Categoria;

public interface crudCategoria {
    public void InsertarCategoria(Categoria cat);
    
    public Categoria BuscarCategoriaPorNombre(String nombcat);
    
    public void ActualizarCategoria(Categoria nuevocat);
    
    public void EliminarCategoria(String codcat);
    
    public ArrayList<Categoria> SincronizarListaCategoria();
}

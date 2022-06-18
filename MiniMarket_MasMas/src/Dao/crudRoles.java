
package Dao;
// libreria
import java.util.ArrayList;
import Modelo.Rol;

public interface crudRoles {
    public void InsertarRol(Rol rol);
    
    public Rol BuscarRolPorNombre(String nombrol);
    
    public void ActualizarRol(Rol nuevorol);
    
    public void EliminarRol(String codrol);
    
    public ArrayList<Rol> SincronizarListaRol();
}

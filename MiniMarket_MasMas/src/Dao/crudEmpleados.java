package Dao;
// libreria
import java.util.ArrayList;
import Modelo.Empleado;
import Modelo.Rol;

public interface crudEmpleados {
    public void InsertarEmpleado(Empleado empleado);
    
    public Empleado BuscarEmpleadoPorCodigo(String codempleado);
    
    public void ActualizarEmpleado(Empleado nuevoempleado);
    
    public void EliminarEmpleado(String codempleado);
    
    public ArrayList<Empleado> SincronizarListaEmpleado();
    
    public ArrayList<Rol> SincronizarListaRolesEnEmpleado();
}

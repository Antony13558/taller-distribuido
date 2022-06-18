package Modelo;
// importando librerias
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListaEmpleados implements TablaEmpleados {
    
    // atributos
    public ArrayList<Empleado> ListaEmplead;
    
    //constructor
    public ListaEmpleados() {
        ListaEmplead = new ArrayList();
    }
    
    // Métodos que manipulan los objetos de la coleccion
    public void AgregarEmpleado(Empleado emple) { ListaEmplead.add(emple); }
    
    public Empleado RecuperarEmpleado(int posicion) { return ListaEmplead.get(posicion); }
    
    public void EliminarEmpleado(int posicion) { ListaEmplead.remove(posicion); }
    
    // método que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion , Empleado empleactual) { ListaEmplead.set(posicion, empleactual); }
    
    // metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de Empleado
    public int BuscarEmpleado(String codbuscado) {
        for(int i = 0; i < ListaEmplead.size(); i++) { 
            if(codbuscado.equals(ListaEmplead.get(i).getDni())) return i; // si existe retorna la posicion
        }
        return -1; // retorno -1 si no existe el codigo en la coleccion
    }
    

    @Override
    public void MostrarEnTabla(JTable tabla) {
         DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
        for(int i = 0; i < ListaEmplead.size(); i++) {
            mt.addRow(ListaEmplead.get(i).RegistroEmpleados(i + 1)); // porque i inicia en 0
        }
    }
    
}

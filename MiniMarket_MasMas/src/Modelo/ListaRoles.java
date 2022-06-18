package Modelo;
// importando librerias
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListaRoles implements TablaRoles {
    // atributos
    public ArrayList<Rol> ListaRol;
    
    //constructor
    public ListaRoles() {
        ListaRol = new ArrayList();
    }

    // Métodos que manipulan los objetos de la coleccion
    public void AgregarRol(Rol ro) { ListaRol.add(ro); }
    
    public Rol RecuperarRol(int posicion) { return ListaRol.get(posicion); }
    
    public void EliminarRol(int posicion) { ListaRol.remove(posicion); }
    
    // método que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion , Rol rolactual) { ListaRol.set(posicion, rolactual); }
    
    // metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de producto
    public int BuscarRol(String codbuscado) {
        for(int i = 0; i < ListaRol.size(); i++) { 
            if(codbuscado.equals(ListaRol.get(i).getCodRol())) return i; // si existe retorna la posicion
        }
        return -1; // retorno -1 si no existe el codigo en la coleccion
    }
    
    @Override
    public void MostrarEnTabla(JTable tabla) {
        DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
        for(int i = 0; i < ListaRol.size(); i++) {
            mt.addRow(ListaRol.get(i).RegistroRoles(i + 1)); // porque i inicia en 0
        }
    }
}

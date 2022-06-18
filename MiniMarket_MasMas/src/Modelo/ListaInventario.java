package Modelo;
// importando librerias
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListaInventario implements TablaInventario {
    
    // atributos
    public ArrayList<Inventario> ListaInventa;
    
    //constructor
    public ListaInventario() {
        ListaInventa = new ArrayList();
    }
    
    // Métodos que manipulan los objetos de la coleccion
    //public void AgregarProducto(Producto prod) { ListaProduct.add(prod); }
    
    public Inventario RecuperarInventario(int posicion) { return ListaInventa.get(posicion); }
    
    // método que actualiza un objeto en base a su posicion en la coleccion (set)
    public void ActualizarInventario(int posicion , Inventario prodactual) { ListaInventa.set(posicion, prodactual); }
    
    // metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de producto
    public int BuscarProductoenInventario(String codbuscado) {
        for(int i = 0; i < ListaInventa.size(); i++) { 
            if(codbuscado.equals(ListaInventa.get(i).getCodProd())) return i; // si existe retorna la posicion
        }
        return -1; // retorno -1 si no existe el codigo en la coleccion
    }

    @Override
    public void MostrarEnTabla(JTable tabla) {
        DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
        for(int i = 0; i < ListaInventa.size(); i++) {
            mt.addRow(ListaInventa.get(i).RegistroInventario(i + 1)); // porque i inicia en 0
        }
    }
    
    // talvez se puede crear un metodo para ver como resumen la cantidad total de productos
    
}

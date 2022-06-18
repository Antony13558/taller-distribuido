package Modelo;
// importando librerias
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
//import javax.swing.JTextArea;

public class ListaProductos implements TablaProductos {
    
    // atributos
    public ArrayList<Producto> ListaProduct;
    
    //constructor
    public ListaProductos() {
        ListaProduct = new ArrayList();
    }

    // Métodos que manipulan los objetos de la coleccion
    public void AgregarProducto(Producto prod) { ListaProduct.add(prod); }
    
    public Producto RecuperarProducto(int posicion) { return ListaProduct.get(posicion); }
    
    public void EliminarProducto(int posicion) { ListaProduct.remove(posicion); }
    
    // método que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion , Producto prodactual) { ListaProduct.set(posicion, prodactual); }
    
    // metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de producto
    public int BuscarProducto(String codbuscado) {
        for(int i = 0; i < ListaProduct.size(); i++) { 
            if(codbuscado.equals(ListaProduct.get(i).getCodProd())) return i; // si existe retorna la posicion
        }
        return -1; // retorno -1 si no existe el codigo en la coleccion
    }
    
    @Override
    public void MostrarEnTabla(JTable tabla) {
        DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
        for(int i = 0; i < ListaProduct.size(); i++) {
            mt.addRow(ListaProduct.get(i).RegistroProducto(i + 1)); // porque i inicia en 0
        }
    }
    
    // talvez se puede crear un metodo para ver como resumen la cantidad total de productos
    
}

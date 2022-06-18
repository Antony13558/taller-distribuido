package Modelo;
// importando librerias
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListaCategorias implements TablaCategorias {
    
    // atributos
    public ArrayList<Categoria> ListaCategoria;
    
    //constructor
    public ListaCategorias() {
        ListaCategoria = new ArrayList();
    }

    // Métodos que manipulan los objetos de la coleccion
    public void AgregarCategoria(Categoria cat) { ListaCategoria.add(cat); }
    
    public Categoria RecuperarCategoria(int posicion) { return ListaCategoria.get(posicion); }
    
    public void EliminarCategoria(int posicion) { ListaCategoria.remove(posicion); }
    
    // método que actualiza un objeto en base a su posicion en la coleccion (set)
    public void Actualizar(int posicion , Categoria catactual) { ListaCategoria.set(posicion, catactual); }
    
    // metodo que busca un objeto en la coleccion y retorna su posicion. La busqueda se realizará por el codigo de producto
    public int BuscarCategoria(String codbuscado) {
        for(int i = 0; i < ListaCategoria.size(); i++) { 
            if(codbuscado.equals(ListaCategoria.get(i).getCodCat())) return i; // si existe retorna la posicion
        }
        return -1; // retorno -1 si no existe el codigo en la coleccion
    }

    @Override
    public void MostrarEnTabla(JTable tabla) {
        DefaultTableModel mt = new DefaultTableModel(null, Encabezado);
        tabla.setModel(mt);
        for(int i = 0; i < ListaCategoria.size(); i++) {
            mt.addRow(ListaCategoria.get(i).RegistroCategorias(i + 1)); // porque i inicia en 0
        }
    }
    
}

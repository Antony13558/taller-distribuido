package Modelo;
// libreria
import javax.swing.JTable;

public interface TablaInventario {
    public String[] Encabezado = {"Num", "Codigo", "Nombre", "Descripcion", "Categoria", "Stock"};
    public void MostrarEnTabla(JTable tabla);
}

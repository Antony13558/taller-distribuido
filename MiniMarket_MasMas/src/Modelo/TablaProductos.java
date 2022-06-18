package Modelo;
// libreria
import javax.swing.JTable;

public interface TablaProductos {
    public String[] Encabezado = {"Num", "Codigo", "Nombre", "Descripcion", "Categoria", "Stock",
        "Precio Coste", "P.venta (Sin IGV)", "P.venta (Con IGV)"};
    public void MostrarEnTabla(JTable tabla);
}

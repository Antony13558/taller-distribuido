
package Modelo;
//libreria
import javax.swing.JTable;

public interface TablaVenta {
    public String[] Encabezado= {"ID","Codigo","Nombre","Cant.","Precio","Total"};
    public void MostrarEnTabla(JTable tabla);
}

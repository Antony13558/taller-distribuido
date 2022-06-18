
package Modelo;
// libreria
import javax.swing.JTable;

public interface TablaEmpleados {
    public String[] Encabezado = {"Num", "Dni", "Nombres", "Apellidos", "Teléfono", "Cargo", "Contraseña"};
    public void MostrarEnTabla(JTable tabla);
}

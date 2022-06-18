
package Modelo;

import javax.swing.JTable;

public interface TablaCategorias {
    public String[] Encabezado = {"Num", "Nombre", "Descripción"};
    public void MostrarEnTabla(JTable tabla);
}

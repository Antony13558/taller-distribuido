
package Formatos;
// libreria
import javax.swing.JOptionPane;

public class Mensajes {
    public static void m1(String texto) {
        JOptionPane.showMessageDialog(null, texto);
    }
    
    public static String m2(String texto) {
        String dato = JOptionPane.showInputDialog(texto);
        return dato;
    }
    
    public static int m3(String titulo, String texto) {
        int respuesta = JOptionPane.showConfirmDialog(null, titulo, texto, JOptionPane.OK_CANCEL_OPTION);
        return respuesta;
    }
}

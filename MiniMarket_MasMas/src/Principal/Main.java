package Principal;

// librerias
import Modelo.*;
import Vista.*;
import Controlador.*;

public class Main {
    
    // variables globales para EL LOGIN - La vista principal
    public static FormLogin fFormLogin;
    public static ControladorLogin controlLogin;
    public static ListaProductos ListaProd;
    public static ListaInventario ListaInvent;
    public static ListaRoles ListaRole;
    public static ListaEmpleados ListaEmp;
    public static ListaCategorias ListaCat;
    public static ListaVenta ListaVenta;
    
    public static void main(String[] args) {
        
        ListaProd = new ListaProductos();
        ListaInvent = new ListaInventario();
        ListaRole = new ListaRoles();
        ListaEmp = new ListaEmpleados();
        ListaCat = new ListaCategorias();
        ListaVenta = new ListaVenta();
                
        fFormLogin = new FormLogin();
        fFormLogin.setVisible(true);
        fFormLogin.logo.setVisible(false);
        fFormLogin.nameWelcome.setVisible(false);

        fFormLogin.setTitle("Bienvenido a Minimarket Mas Mas");
        fFormLogin.setLocationRelativeTo(null);
        
        // creamos el controlador y le enviamos el formulario que va a manipular (fFormInvent)
        controlLogin = new ControladorLogin(fFormLogin); 
    }
}

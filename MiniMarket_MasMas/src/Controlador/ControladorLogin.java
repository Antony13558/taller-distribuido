package Controlador;

import Dao.LoginDAO;
import Formatos.Mensajes;
import Modelo.Empleado;
import Modelo.Login;
import Modelo.Rol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// importando las vistas
import Vista.*;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

    // atributos
    FormLogin vista;
    ControladorMenu controlMenu;
    Empleado emplead;
    Rol rol;
    Login lg;
    LoginDAO loging = new LoginDAO();

    // constructor
    public ControladorLogin(FormLogin formLogin) {
        this.vista = formLogin;
        this.vista.jbtnIngresar.addActionListener(this);
        this.vista.logo.setVisible(false);
        this.vista.nameWelcome.setVisible(false);
    }

    public void validar() {
        String dni = this.vista.jTextFieldUsuario.getText();
        String pass = String.valueOf(this.vista.jPasswordField.getPassword());

        System.out.println(dni);
        System.out.println(pass);

        if (!"".equals(dni) || !"".equals(pass)) {

            lg = loging.log(dni, pass);

            if (lg.getDni() != null && lg.getPass() != null) {
                FormMenu fmenu = new FormMenu();

                fmenu.setVisible(true);
                fmenu.setTitle("Menú principal - Minimarket Mas Mas");
                fmenu.setLocationRelativeTo(null);
                vista.dispose();
                ControladorMenu controladorMenu = new ControladorMenu(fmenu, lg);

            } else {
                JOptionPane.showMessageDialog(null, "Correo o la Contraseña incorrecta");
            }
        } else {
            Mensajes.m1("Completar campos");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // creamos la accion del boton ingresar
        if (e.getSource() == vista.jbtnIngresar) {
            validar();
        }
    }

}

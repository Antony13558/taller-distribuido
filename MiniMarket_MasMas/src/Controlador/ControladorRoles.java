package Controlador;
// libreria

import Principal.Main;
import Vista.*;
import Modelo.*;

// librerias de las clases que manipulan eventos del formulario
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Formatos.*;

import Dao.*;

public class ControladorRoles implements ActionListener {

    // atributos
    FormRoles vista;
    int posicion;
    Rol rol;
    Login login = new Login();

    public ControladorRoles(FormRoles frol, Login lg) {
        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());

        this.vista = frol;
        // recopilando los eventos de los botones
        this.vista.jbtnGuardarRol.addActionListener(this);
        this.vista.jbtnEliminarRol.addActionListener(this);
        this.vista.jbtnConsultarRol.addActionListener(this);
        this.vista.jbtnActualizarRol.addActionListener(this);
        this.vista.jbtnLimpiarCampos.addActionListener(this);
        this.vista.jRregresar.addActionListener(this);
        // deshabilitando los campos para que no se modifique
        this.vista.jtxtIdRol.setEnabled(false);
        ActualizarFormulario();

    }

    // metodo que limpia las entradas
    void LimpiarEntradas() {
        this.vista.jtxtIdRol.setText("");
        this.vista.jtxtNombreRol.setText("");
        this.vista.jtxtBusquedaRoles.setText("");
        this.vista.jtxtNombreRol.requestFocus();
    }

    // metodo que crea un objeto del rol
    void LeerRoles() {
        rol = new Rol();
        rol.setCodRol(Integer.parseInt(this.vista.jtxtIdRol.getText()));
        rol.setNomRol(this.vista.jtxtNombreRol.getText());
    }

    // implementando el metodo de interface que define los eventos sobre el formulario
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnGuardarRol) {
            if (this.vista.jtxtNombreRol.getText().equals("")) {
                Mensajes.m1("Completar campos de roles");
            } else {
                rol = new Rol();
                rol.setNomRol(this.vista.jtxtNombreRol.getText());
                RolesDAO roles = new RolesDAO();
                roles.InsertarRol(rol);
                Main.ListaRole.ListaRol = roles.SincronizarListaRol();
                ActualizarFormulario();
                Mensajes.m1("Rol registrado correctamente!!!");
            }
        }

        if (e.getSource() == vista.jbtnConsultarRol) {
            String nombrebuscado = this.vista.jtxtBusquedaRoles.getText();
            RolesDAO dao = new RolesDAO();
            rol = dao.BuscarRolPorNombre(nombrebuscado);

            if (rol == null) {
                Mensajes.m1(nombrebuscado + " no existe en la tabla de roles");
                vista.jtxtBusquedaRoles.requestFocus();
            } else {
                // actualizamos el formulario con el objeto recuperado
                this.vista.jtxtIdRol.setText(rol.getCodRol() + "");
                this.vista.jtxtNombreRol.setText(rol.getNomRol());
            }
        }

        if (e.getSource() == vista.jbtnActualizarRol) {
            LeerRoles();
            RolesDAO dao = new RolesDAO();
            dao.ActualizarRol(rol);
            ActualizarFormulario();
            Mensajes.m1("Datos del rol actualizado");
        }

        if (e.getSource() == vista.jbtnEliminarRol) {
            int respuesta = Mensajes.m3("¿Desea eliminar el rol?", "Confirmación");
            if (respuesta == 0) { // 0= Yes
                RolesDAO dao = new RolesDAO();
                dao.EliminarRol(vista.jtxtIdRol.getText());
                ActualizarFormulario();
                Mensajes.m1("Registro del rol ha sido eliminado...");
            }
        }

        if (e.getSource() == vista.jbtnLimpiarCampos) {
            LimpiarEntradas();
        }

        if (e.getSource() == vista.jRregresar) {
            FormMenu fmenu = new FormMenu();
            fmenu.setVisible(true);
            fmenu.setTitle("Menú principal - Minimarket Mas Mas");
            fmenu.setLocationRelativeTo(null);
            vista.dispose();
            ControladorMenu controladorMenu = new ControladorMenu(fmenu, login);
        }
    }

    void ActualizarFormulario() {
        RolesDAO roles = new RolesDAO();
        Main.ListaRole.ListaRol = roles.SincronizarListaRol();
        Main.ListaRole.MostrarEnTabla(vista.jTableRoles);

        LimpiarEntradas();
    }

}

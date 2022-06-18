package Controlador;
// libreria

import Principal.Main;
import Vista.*;
import Modelo.*;

// librerias de las clases que manipulan eventos del formulario
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JOptionPane; // ventanas de dialogo // esto ya no se usa para usar el Formatos

import Formatos.*;

// libreria adicional
import Dao.*;
import java.util.ArrayList;

public class ControladorEmpleado implements ActionListener {

    // atributos de la clase
    FormEmpleados vista;
    int posicion;
    Empleado emplead;
    Rol rol;
    Login login = new Login();

    // constructor
    public ControladorEmpleado(FormEmpleados femple, Login lg) {
        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());
        this.vista = femple;
        // recopilando los eventos de los botones
        this.vista.jbtnGuardarEmple.addActionListener(this);
        this.vista.jbtnActualizarEmple.addActionListener(this);
        this.vista.jbtnConsultarEmple.addActionListener(this);
        this.vista.jbtnEliminarEmple.addActionListener(this);
        this.vista.jbtnLimpiarCampos.addActionListener(this);
        this.vista.jEbtnRegresar.addActionListener(this);

        ActualizarFormulario();
    }

    // metodo que limpia las entradas
    void LimpiarEntradas() {
        this.vista.jtxtDni.setText("");
        this.vista.jtxtNomEmple.setText("");
        this.vista.jtxtTelefonoEmple.setText("");
        this.vista.jtxtApellidosEmple.setText("");
        this.vista.jcbxCargo.setSelectedIndex(0);
        this.vista.jPassword.setText("");
        this.vista.jtxtDni.requestFocus();
    }

    // metodo que crea un objeto del emplead
    void LeerEmpleado() {
        emplead = new Empleado();
        emplead.setDni(this.vista.jtxtDni.getText());
        emplead.setNombres(this.vista.jtxtNomEmple.getText());
        emplead.setApellidos(this.vista.jtxtApellidosEmple.getText());
        emplead.setTelefono(Integer.parseInt(this.vista.jtxtTelefonoEmple.getText()));
        emplead.setIdcargo(Integer.parseInt(this.vista.jcbxCargo.getSelectedItem().toString()));
        emplead.setContrasena(this.vista.jPassword.getPassword().toString());
    }

    void GuardaryActualizarEmpleadoConRoles() {
        int idRol = this.vista.jcbxCargo.getItemAt(vista.jcbxCargo.getSelectedIndex()).getCodRol();
        emplead = new Empleado();
        emplead.setDni(this.vista.jtxtDni.getText());
        emplead.setNombres(this.vista.jtxtNomEmple.getText());
        emplead.setApellidos(this.vista.jtxtApellidosEmple.getText());
        emplead.setTelefono(Integer.parseInt(this.vista.jtxtTelefonoEmple.getText()));
        emplead.setIdcargo(idRol);
        emplead.setContrasena(this.vista.jPassword.getPassword().toString());
    }
    // metodo a experimentar

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnGuardarEmple) {

            GuardaryActualizarEmpleadoConRoles();
            EmpleadoDAO empleado = new EmpleadoDAO();
            empleado.InsertarEmpleado(emplead);
            Main.ListaEmp.ListaEmplead = empleado.SincronizarListaEmpleado();

            ActualizarFormulario();
            Mensajes.m1("Empleado registrado correctamente!!!");
        }

        if (e.getSource() == vista.jbtnConsultarEmple) {
            String codbuscado = Mensajes.m2("Ingrese el código del empleado a buscar :");
            EmpleadoDAO dao = new EmpleadoDAO();
            emplead = dao.BuscarEmpleadoPorCodigo(codbuscado);

            if (emplead == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla de empleados");
                this.vista.jtxtDni.requestFocus();
            } else {
                // actualizamos el formulario con el objeto recuperado
                this.vista.jtxtDni.setText(emplead.getDni() + "");
                this.vista.jtxtNomEmple.setText(emplead.getNombres());
                this.vista.jtxtApellidosEmple.setText(emplead.getApellidos());
                this.vista.jtxtTelefonoEmple.setText(emplead.getTelefono() + "");
                this.vista.jcbxCargo.setSelectedIndex(emplead.getIdcargo() - 1);
                this.vista.jPassword.setText(emplead.getContrasena());
            }
        }

        if (e.getSource() == vista.jbtnActualizarEmple) {
            if (this.vista.jtxtDni.getText().isEmpty()
                    || this.vista.jtxtNomEmple.getText().isEmpty()
                    || this.vista.jtxtApellidosEmple.getText().isEmpty()
                    || this.vista.jtxtTelefonoEmple.getText().isEmpty()
                    //|| this.vista.jtxtContra.getText().isEmpty()
                    || this.vista.jPassword.getPassword().equals("")) {
                Mensajes.m1("Busque DNI de empleado");
            } else {
                GuardaryActualizarEmpleadoConRoles();
                EmpleadoDAO dao = new EmpleadoDAO();
                dao.ActualizarEmpleado(emplead);
                ActualizarFormulario();
                Mensajes.m1("Datos del producto actualizado");
            }

        }
        if (e.getSource() == vista.jbtnEliminarEmple) {

            int respuesta = Mensajes.m3("¿Desea eliminar el empleado?", "Confirmación");
            if (respuesta == 0) { // 0= Yes
                EmpleadoDAO dao = new EmpleadoDAO();
                dao.EliminarEmpleado(vista.jtxtDni.getText());
                ActualizarFormulario();
                Mensajes.m1("Registro del empleado ha sido eliminado...");
            }
        }

        if (e.getSource() == vista.jbtnLimpiarCampos) {
            LimpiarEntradas();
        }

        if (e.getSource() == vista.jEbtnRegresar) {
            FormMenu fmenu = new FormMenu();
            fmenu.setVisible(true);
            fmenu.setTitle("Menú principal - Minimarket Mas Mas");
            fmenu.setLocationRelativeTo(null);
            vista.dispose();
            ControladorMenu controladorMenu = new ControladorMenu(fmenu, login);
        }

    }

    void ActualizarFormulario() {
        EmpleadoDAO empleado = new EmpleadoDAO();

        // codigo para listar el combo
        ArrayList<Rol> listaroles = empleado.SincronizarListaRolesEnEmpleado();
        vista.jcbxCargo.removeAllItems();

        for (int i = 0; i < listaroles.size(); i++) {
            vista.jcbxCargo.addItem(new Rol(listaroles.get(i).getCodRol(), listaroles.get(i).getNomRol()));
        }
        // codigo para listar el combo

        Main.ListaEmp.ListaEmplead = empleado.SincronizarListaEmpleado();
        Main.ListaEmp.MostrarEnTabla(vista.jTableEmpleados); // lo mostramos en tabla
        LimpiarEntradas();
    }
}

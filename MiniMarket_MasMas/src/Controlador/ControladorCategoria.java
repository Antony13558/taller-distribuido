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

public class ControladorCategoria implements ActionListener {

    // atributos
    FormCategorias vista;
    int posicion;
    Categoria cate;
    Login login = new Login();

    public ControladorCategoria(FormCategorias fcat, Login lg) {
        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());
        this.vista = fcat;
        // recopilando los eventos de los botones
        this.vista.jbtnGuardarCategoria.addActionListener(this);
        this.vista.jbtnEliminarCategoria.addActionListener(this);
        this.vista.jbtnConsultarCategoria.addActionListener(this);
        this.vista.jbtnActualizarCategoria.addActionListener(this);
        this.vista.jbtnLimpiarCampos.addActionListener(this);
        this.vista.jRregresar.addActionListener(this);
        // deshabilitando los campos para que no se modifique
        this.vista.jtxtIdCat.setEnabled(false);
        ActualizarFormulario();
    }

    // metodo que limpia las entradas
    void LimpiarEntradas() {
        this.vista.jtxtIdCat.setText("");
        this.vista.jtxtNombreCat.setText("");
        this.vista.jtxtBusquedaCat.setText("");
        this.vista.jtxtDescCategoria.setText("");
        this.vista.jtxtNombreCat.requestFocus();
    }

    // metodo que crea un objeto del cate
    void LeerCategorias() {
        cate = new Categoria();
        cate.setCodCat(Integer.parseInt(this.vista.jtxtIdCat.getText()));
        cate.setNomCat(this.vista.jtxtNombreCat.getText());
        cate.setDescripcion(this.vista.jtxtDescCategoria.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.jbtnGuardarCategoria) {
            if (this.vista.jtxtNombreCat.getText().equals("")
                    || this.vista.jtxtDescCategoria.getText().equals("")) {
                Mensajes.m1("Completar campos de Categoria");
            } else {
                cate = new Categoria();

                cate.setNomCat(this.vista.jtxtNombreCat.getText());
                cate.setDescripcion(this.vista.jtxtDescCategoria.getText());

                CategoriaDAO categorias = new CategoriaDAO();
                categorias.InsertarCategoria(cate);
                Main.ListaCat.ListaCategoria = categorias.SincronizarListaCategoria();
                ActualizarFormulario();
                Mensajes.m1("Categoria registrado correctamente!!!");
            }

        }

        if (e.getSource() == vista.jbtnConsultarCategoria) {
            String nombrebuscado = this.vista.jtxtBusquedaCat.getText();
            CategoriaDAO dao = new CategoriaDAO();
            cate = dao.BuscarCategoriaPorNombre(nombrebuscado);

            if (cate == null) {
                Mensajes.m1("El nombre "+ nombrebuscado + " no existe en la lista de categorias");
                vista.jtxtDescCategoria.requestFocus();
            } else {
                // actualizamos el formulario con el objeto recuperado
                this.vista.jtxtIdCat.setText(cate.getCodCat() + "");
                this.vista.jtxtNombreCat.setText(cate.getNomCat());
                this.vista.jtxtDescCategoria.setText(cate.getDescripcion());
            }
        }

        if (e.getSource() == vista.jbtnActualizarCategoria) {
            if (this.vista.jtxtIdCat.getText().equals("")
                    || this.vista.jtxtNombreCat.getText().equals("")
                    || this.vista.jtxtDescCategoria.getText().equals("")) {
                Mensajes.m1("Ingrese el nombre de la categoria");
            } else {
                LeerCategorias();
                CategoriaDAO dao = new CategoriaDAO();
                dao.ActualizarCategoria(cate);
                ActualizarFormulario();
                Mensajes.m1("Datos de la categoría actualizado");
            }

        }

        if (e.getSource() == vista.jbtnEliminarCategoria) {
            int respuesta = Mensajes.m3("¿Desea eliminar esta categoria?", "Confirmación");
            if (respuesta == 0) { // 0= Yes
                CategoriaDAO dao = new CategoriaDAO();
                dao.EliminarCategoria(vista.jtxtIdCat.getText());
                ActualizarFormulario();
                Mensajes.m1("Registro de la categoría ha sido eliminado...");
            }
        }

        if (e.getSource() == vista.jRregresar) {
            FormMenu fmenu = new FormMenu();
            fmenu.setVisible(true);
            fmenu.setTitle("Menú principal - Minimarket Mas Mas");
            fmenu.setLocationRelativeTo(null);
            vista.dispose();
            ControladorMenu controladorMenu = new ControladorMenu(fmenu, login);
        }

        if (e.getSource() == vista.jbtnLimpiarCampos) {
            LimpiarEntradas();
        }
    }

    void ActualizarFormulario() {
        CategoriaDAO categorias = new CategoriaDAO();
        Main.ListaCat.ListaCategoria = categorias.SincronizarListaCategoria();
        Main.ListaCat.MostrarEnTabla(vista.jTableCategorias);
        LimpiarEntradas();
    }
}

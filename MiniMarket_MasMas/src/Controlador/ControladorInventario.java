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
import java.awt.Color;
import java.util.ArrayList;
//import java.util.ArrayList;

public class ControladorInventario implements ActionListener {

    // atributos de la clase
    FormInventario vista;
    int posicion;
    Inventario inventa;
    Login login = new Login();
    Categoria categ;

    public ControladorInventario(FormInventario finvent, Login lg) {
        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());
        this.vista = finvent;
        // recopilando los eventos de los botones
        this.vista.jbtnConsultarInventario.addActionListener(this);
        this.vista.jbtnActualizarInventario.addActionListener(this);
        this.vista.jIRegresar.addActionListener(this);
        this.vista.jbtnLimpiarCampos.addActionListener(this);
        // deshabilitando los campos para que no se modifique
        this.vista.jtxtCodProdInv.setEnabled(false);
        this.vista.jtxtNombreProdInv.setEnabled(false);
        this.vista.jcbxCategoriaInv.setEnabled(false);
        this.vista.jtxtDescripcionProdInv.setEnabled(false);
        
        

        ActualizarFormulario();

    } // fin del constructor

    // metodo que limpia las entradas
    void LimpiarEntradas() {
        this.vista.jtxtCodProdInv.setText("");
        this.vista.jtxtNombreProdInv.setText("");
        this.vista.jtxtDescripcionProdInv.setText("");
        this.vista.jspnStockProInventario.setValue(0);
        //this.vista.jcbxCategoriaInv.setSelectedIndex(0);

        this.vista.jspnStockProInventario.requestFocus();

    }

    // metodo que crea un objeto del prod
    void LeerInventario() {
        inventa = new Inventario();
        inventa.setCodProd(this.vista.jtxtCodProdInv.getText());
        inventa.setNomProd(this.vista.jtxtNombreProdInv.getText());
        inventa.setDescripcion(this.vista.jtxtDescripcionProdInv.getText());
        inventa.setCategoria(Integer.parseInt(this.vista.jcbxCategoriaInv.getSelectedItem().toString()));
        inventa.setStock(Integer.parseInt(this.vista.jspnStockProInventario.getValue().toString()));
    }

    // implementando el metodo de interface que define los eventos sobre el formulario
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnConsultarInventario) {
            String codbuscado = Mensajes.m2("Ingrese el código del producto a buscar :");
            InventarioDAO dao = new InventarioDAO();
            inventa = dao.BuscarProductoEnInventarioPorCodigo(codbuscado);

            if (inventa == null) {
                Mensajes.m1("El codigo" + codbuscado + "no existe en la tabla de productos");
                vista.jspnStockProInventario.requestFocus();
            } else {
                // actualizamos el formulario con el objeto recuperado
                this.vista.jtxtCodProdInv.setText(inventa.getCodProd());
                this.vista.jtxtNombreProdInv.setText(inventa.getNomProd());
                this.vista.jtxtDescripcionProdInv.setText(inventa.getDescripcion());
                this.vista.jspnStockProInventario.setValue(inventa.getStock());
                this.vista.jcbxCategoriaInv.setSelectedIndex(inventa.getCategoria()-1);
            }
        }

        if (e.getSource() == vista.jbtnActualizarInventario) {
            LeerInventario();
            InventarioDAO dao = new InventarioDAO();
            dao.ActualizarProductoEnInventario(inventa);
            ActualizarFormulario();
            Mensajes.m1("Datos del producto actualizado");
        }

        if (e.getSource() == vista.jbtnLimpiarCampos) {
            LimpiarEntradas();
        }

        if (e.getSource() == vista.jIRegresar) {
            FormMenu fmenu = new FormMenu();
            fmenu.setVisible(true);
            fmenu.setTitle("Menú principal - Minimarket Mas Mas");
            fmenu.setLocationRelativeTo(null);
            vista.dispose();
            ControladorMenu controladorMenu = new ControladorMenu(fmenu, login);
        }
    }

    void ActualizarFormulario() {
        InventarioDAO inventar = new InventarioDAO();
        
        // codigo para listar el combo
        ArrayList<Categoria> listacategor = inventar.SincronizarListaCategoriasEnInventario();
        vista.jcbxCategoriaInv.removeAllItems();

        System.out.println(listacategor.get(0).getCodCat());
        System.out.println(listacategor.get(0).getNomCat());
        
        for (int i = 0; i < listacategor.size(); i++) {
            vista.jcbxCategoriaInv.addItem(listacategor.get(i).getNomCat());
        }
        
        // End codigo para listar el combo
        
        Main.ListaInvent.ListaInventa = inventar.SincronizarListaProductoEnInventario();
        Main.ListaInvent.MostrarEnTabla(vista.jTableInventarioProductos); // lo mostramos en tabla
        LimpiarEntradas();
    }

}

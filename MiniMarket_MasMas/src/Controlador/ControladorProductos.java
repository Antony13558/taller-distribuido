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

public class ControladorProductos implements ActionListener {

    // atributos de la clase
    FormProductos vista;
    int posicion;
    Producto product;
    Categoria categ;
    Login login = new Login();

    public ControladorProductos(FormProductos fprod, Login lg) {
        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());

        this.vista = fprod;
        // recopilando los eventos de los botones
        this.vista.jbtnGuardarProd.addActionListener(this);
        this.vista.jbtnEliminarProd.addActionListener(this);
        this.vista.jbtnConsultarProd.addActionListener(this);
        this.vista.jbtnActualizarProd.addActionListener(this);
        this.vista.jbtnLimpiarCampos.addActionListener(this);
        this.vista.jPregresar.addActionListener(this);
        
        ActualizarFormulario();
    }

    // metodo que limpia las entradas
    void LimpiarEntradas() {
        this.vista.jtxtCodProd.setText("");
        this.vista.jtxtNombreProd.setText("");
        this.vista.jtxtDescripcionProd.setText("");
        this.vista.jspnStockProducto.setValue(0);
        this.vista.jtxtPCoste.setText("");
        this.vista.jtxtPVenta.setText("");
        this.vista.jcbxCategoria.setSelectedIndex(0);
        /* faltaria ver el dato fecha, pero como no es null entonces seguimos */
        this.vista.jtxtNombreProd.requestFocus();
    }

    // metodo que crea un objeto del prod
    void LeerProducto() {
        product = new Producto();
        product.setCodProd(this.vista.jtxtCodProd.getText());
        product.setNomProd(this.vista.jtxtNombreProd.getText());
        product.setDescripcion(this.vista.jtxtDescripcionProd.getText());
        product.setStock(Integer.parseInt(this.vista.jspnStockProducto.getValue().toString()));
        product.setPrecioCoste(Float.parseFloat(this.vista.jtxtPCoste.getText()));
        product.setPrecioVenta(Float.parseFloat(this.vista.jtxtPVenta.getText()));
        product.setIdCategoria(this.vista.jcbxCategoria.getSelectedIndex());
    }

    /*void GuardaryActualizarProductoConCategoria() {
        //int idCat = this.vista.jcbxCategoria.getItemAt(vista.jcbxCategoria.getSelectedIndex().getCodCat();
        product = new Producto();
        product.setCodProd(this.vista.jtxtCodProd.getText());
        product.setNomProd(this.vista.jtxtNombreProd.getText());
        product.setDescripcion(this.vista.jtxtDescripcionProd.getText());
        product.setStock(Integer.parseInt(this.vista.jspnStockProducto.getValue().toString()));
        product.setPrecioCoste(Float.parseFloat(this.vista.jtxtPCoste.getText()));
        product.setPrecioVenta(Float.parseFloat(this.vista.jtxtPVenta.getText()));
        product.setIdCategoria(Integer.parseInt(this.vista.jcbxCategoria.getSelectedItem().toString()));
    }*/

    // implementando el metodo de interface que define los eventos sobre el formulario
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.jbtnGuardarProd) {
            if (this.vista.jtxtCodProd.getText().isEmpty()
                    || this.vista.jtxtNombreProd.getText().isEmpty()
                    || this.vista.jtxtPCoste.getText().isEmpty()
                    || this.vista.jtxtPVenta.getText().isEmpty()
                    || this.vista.jtxtDescripcionProd.getText().isEmpty()) {
                Mensajes.m1("Completar campos del producto");
            } else {
                LeerProducto();
                ProductoDAO producto = new ProductoDAO();
                producto.InsertarProducto(product);
                Main.ListaProd.ListaProduct = producto.SincronizarListaProducto();
                ActualizarFormulario();
                Mensajes.m1("Produco registrado correctamente!!!");
            }

        }

        if (e.getSource() == vista.jbtnConsultarProd) {
            String codbuscado = Mensajes.m2("Ingrese el código del producto a buscar :");
            ProductoDAO dao = new ProductoDAO();
            product = dao.BuscarProductoPorCodigo(codbuscado);
            if (product == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla de productos");
                vista.jtxtCodProd.requestFocus();
            } else {
                // actualizamos el formulario con el objeto recuperado
                this.vista.jtxtCodProd.setText(product.getCodProd());
                this.vista.jtxtNombreProd.setText(product.getNomProd());
                this.vista.jtxtDescripcionProd.setText(product.getDescripcion());
                this.vista.jspnStockProducto.setValue(product.getStock());
                this.vista.jcbxCategoria.setSelectedIndex(product.getIdCategoria() - 1);
                this.vista.jtxtPCoste.setText(product.getPrecioCoste() + ""); // se agrega ese espacio para que sea ya una cadena
                this.vista.jtxtPVenta.setText(product.getPrecioVenta() + "");
            }
        }

        if (e.getSource() == vista.jbtnActualizarProd) {
            if (this.vista.jtxtCodProd.getText().isEmpty()
                    || this.vista.jtxtNombreProd.getText().isEmpty()
                    || this.vista.jtxtPCoste.getText().isEmpty()
                    || this.vista.jtxtPVenta.getText().isEmpty()
                    || this.vista.jtxtDescripcionProd.getText().isEmpty()) {
                Mensajes.m1("Busque el producto");
            } else {
                LeerProducto();
                ProductoDAO dao = new ProductoDAO();
                dao.ActualizarProducto(product);
                ActualizarFormulario();
                Mensajes.m1("Datos del producto actualizado");
            }
        }
        if (e.getSource() == vista.jbtnEliminarProd) {
            int respuesta = Mensajes.m3("¿Desea eliminar el producto?", "Confirmación");
            if (respuesta == 0) { // 0= Yes
                ProductoDAO dao = new ProductoDAO();
                dao.EliminarProducto(vista.jtxtCodProd.getText());
                ActualizarFormulario();
            }
        }

        if (e.getSource() == vista.jbtnLimpiarCampos) {
            LimpiarEntradas();
        }

        if (e.getSource() == vista.jPregresar) {
            FormMenu fmenu = new FormMenu();
            fmenu.setVisible(true);
            fmenu.setTitle("Menú principal - Minimarket Mas Mas");
            fmenu.setLocationRelativeTo(null);
            vista.dispose();
            ControladorMenu controladorMenu = new ControladorMenu(fmenu, login);
        }
    }

    void ActualizarFormulario() {
        ProductoDAO producto = new ProductoDAO();

        // codigo para listar el combo
        ArrayList<Categoria> listacategor = producto.SincronizarListaCategoriasEnProducto();
        vista.jcbxCategoria.removeAllItems();

        for (int i = 0; i < listacategor.size(); i++) {
            
            vista.jcbxCategoria.addItem(listacategor.get(i).getNomCat());
        }
        // codigo para listar el combo

        Main.ListaProd.ListaProduct = producto.SincronizarListaProducto();
        Main.ListaProd.MostrarEnTabla(vista.jTableProductos); // lo mostramos en tabla
        LimpiarEntradas();
    }
}

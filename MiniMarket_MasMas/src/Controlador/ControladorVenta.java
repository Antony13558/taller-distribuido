package Controlador;

import Dao.EmpleadoDAO;
import Dao.ImprimirDAO;
import Dao.ProductoDAO;
import Dao.VentaDAO;
import Formatos.Mensajes;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Login;
import Modelo.Producto;

import Modelo.Venta;
import Principal.Main;
import Vista.FormMenu;

import Vista.FormVentas;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;


public class ControladorVenta implements ActionListener {
    
    FormVentas vista;
    Producto prod;
    DefaultTableModel modelo = new DefaultTableModel();//comentado por mi
    int item = 0;
    Venta ven = new Venta();
    Login login = new Login();
    Empleado emplead;
    int cant = 0;
    
    Cliente cli = new Cliente();
    
    public ControladorVenta(FormVentas f1, Login lg) {
        login.setDni(lg.getDni());
        login.setPass(lg.getPass());
        login.setRol(lg.getRol());
        
        this.vista = f1;
        this.vista.jButtonGenerarVenta.addActionListener(this);
        this.vista.jButtonImprimir.addActionListener(this);
        this.vista.jButtonRegresar.addActionListener(this);
        this.vista.jButtonBuscarCliente.addActionListener(this);
        this.vista.jButtonAgregar.addActionListener(this);
        this.vista.btnReporteVentas.addActionListener(this);
        this.vista.jButtonCancelarVenta.addActionListener(this);
        this.vista.jButtonBuscarProd.addActionListener(this);
        
        Main.ListaVenta.MostrarEncabezadoTabla(vista.jTableVenta); // lo mostramos en la tabla

        this.vista.jtxtValorVenta.setEnabled(false);
        this.vista.jtxtSubtotal.setEnabled(false);
        this.vista.jtxtIGV.setEnabled(false);
        this.vista.jtxtTotalPagar.setEnabled(false);
        this.vista.jButtonImprimir.setEnabled(false);
        this.vista.jspnCantidadProducto.setEnabled(false);
        this.vista.jTextFieldPrecioUnitario.setEnabled(false);
        this.vista.jTextFieldStock.setEnabled(false);
        this.vista.jButtonAgregar.setEnabled(false);
        this.vista.jButtonCancelarVenta.setEnabled(false);
        this.vista.jtxtNomCliente.setEnabled(false);
        this.vista.jtxtNombre.setEnabled(false);
        this.vista.jtxtNomCliente.setDisabledTextColor(Color.DARK_GRAY);
        this.vista.jtxtTotalPagar.setDisabledTextColor(Color.DARK_GRAY);//Dar color a campo bloqueado
        this.vista.jtxtValorVenta.setDisabledTextColor(Color.DARK_GRAY);
        this.vista.jtxtSubtotal.setDisabledTextColor(Color.DARK_GRAY);
        this.vista.jtxtIGV.setDisabledTextColor(Color.DARK_GRAY);
        this.vista.jTextFieldPrecioUnitario.setDisabledTextColor(Color.DARK_GRAY);
        this.vista.jTextFieldStock.setDisabledTextColor(Color.DARK_GRAY);
        this.vista.jtxtNombre.setDisabledTextColor(Color.DARK_GRAY);
        
        if (login.getRol() != 1) {
            this.vista.btnReporteVentas.setVisible(false);
        }
    }
    
    void ActualizarFormulario(boolean estado) {
        VentaDAO venta = new VentaDAO();
        Main.ListaVenta.Lista = venta.SincronizarListaVenta();
        
        Main.ListaVenta.MostrarEncabezadoTabla(vista.jTableVenta); // lo mostramos en la tabla
        if (estado) {
            //LimpiarEntrada();
        }
    }
    
    public String GenerarCodigoVenta() {
        VentaDAO venta = new VentaDAO();
        String id = venta.IdVentas();
        int idver = Integer.parseInt(id) + 1;
        String idstring = String.valueOf(idver);
        System.out.println(this.vista.jcbxTipoDoc.getSelectedItem());
        if (this.vista.jcbxTipoDoc.getSelectedItem().equals("BOLETA")) {
            DecimalFormat df = new DecimalFormat("B16-00000");
            String correlativo = df.format(idver);
            return (correlativo);
        } else {
            DecimalFormat df = new DecimalFormat("F17-00000");
            String correlativo = df.format(idver);
            return (correlativo);
        }
    }

    //guardar producto en el objeto
    public void LeerVenta() {
        
        ven.setCodVenta(GenerarCodigoVenta());
        ven.setNomCliente(this.vista.jtxtNomCliente.getText());
        ven.setDni_o_ruc(Integer.parseInt(this.vista.jtxtDniORuc.getText()));
        ven.setTipoDeComprobante(String.valueOf(this.vista.jcbxTipoDoc.getSelectedItem()));
        ven.setIdEmpleado(2);
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date dateObj = calendar.getTime();
        String formattedDate = dtf.format(dateObj);
        ven.setFechaVenta(formattedDate);
    }
    
    public void LimpiarEntrads(boolean estado) {
        
        this.vista.jTextFieldStock.setText("");
        
        this.vista.jspnCantidadProducto.setValue(0);
        this.vista.jTextFieldPrecioUnitario.setText("");
        this.vista.jtxtNombre.setText("");
        
        if (estado) {
            this.vista.jtxtNomCliente.setText("");
            this.vista.jtxtDniORuc.setText("");
            //his.vista.jTextFieldTotalxProducto.setText("");
            this.vista.jtxtValorVenta.setText("");
            this.vista.jtxtSubtotal.setText("");
            this.vista.jtxtIGV.setText("");
            this.vista.jtxtTotalPagar.setText("");
            this.vista.jtxtDescuento.setText("");
            this.vista.jTextFieldCodProc.setText("");

            for (int i = 0; i < this.vista.jTableVenta.getRowCount(); i++) {
                    modelo.removeRow(i);

                }
            
        }
        
    }
    
    public double CalcularPrecioProducto() {
        int cantidad = Integer.parseInt(this.vista.jspnCantidadProducto.getValue().toString());
        double totalProducto = prod.getPrecioVenta() * cantidad;
        return totalProducto;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButtonGenerarVenta) {
            if (this.vista.jtxtNomCliente.getText().isEmpty() || this.vista.jtxtDniORuc.getText().isEmpty()) {
                Mensajes.m1("Completar datos del cliente");
            } else {
                this.vista.jButtonImprimir.setEnabled(true);
                LeerVenta();
                
                System.out.println("guardado");
                VentaDAO venta = new VentaDAO();
                
                venta.InsertarVenta(ven);
                
                for (int i = 0; i < this.vista.jTableVenta.getRowCount(); i++) {
                    
                    String idv = venta.IdVentas();
                    int idve = Integer.parseInt(idv);
                    
                    int idproducto = Integer.parseInt(this.vista.jTableVenta.getValueAt(i, 1).toString());
                    String nombre = this.vista.jTableVenta.getValueAt(i, 2).toString();
                    int cantidad = Integer.parseInt(this.vista.jTableVenta.getValueAt(i, 3).toString());
                    double precioVenta = Double.parseDouble(this.vista.jTableVenta.getValueAt(i, 4).toString());
                    double total = Double.parseDouble(this.vista.jTableVenta.getValueAt(i, 5).toString());
                    
                    int IdProducto = venta.IdProducto(idproducto);
                    int stock = venta.obtenerStock(idproducto);
                    
                    int StockActual = stock - cantidad;
                    if (StockActual <= 0) {
                        Mensajes.m1("Stock insuficiente");
                    } else {
                        venta.InsertarDetalleVenta(ven, idve, IdProducto, cantidad, precioVenta, total);
                        venta.ActualizarStock(StockActual, IdProducto);
                        Mensajes.m1("Su compra se realizo con exito");
                    }                    
                }
                
            }
        }
        
        if(e.getSource()== vista.jButtonBuscarCliente){
            
            /*--Consulta json---*/
            String numDocumento = this.vista.jtxtDniORuc.getText();
            
            try {
                URL url = new URL("https://apirest.sbperu.com/v2/consultar-dni/"+numDocumento+"?token=cVaFW4vL9XycVRaxuEEcHYM8VcDNSpe4aQvPwYMwMevHsqppKT6dN7s4sTMvQg9ufa8FaZsWsvvgMjhq933H4mhJYvbAUsgaHZ7w");
                
                System.out.println(url);
                
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                
                int responseCode = conn.getResponseCode();
                
                if(responseCode != 200){
                    Mensajes.m1("El DNI no pudo se encontrado");
                    throw new RuntimeException("Ocurrio un erro: "+ responseCode); 
                }else{
                    StringBuilder info = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    
                    while(scanner.hasNext()){
                        info.append(scanner.nextLine());
                    }
                    scanner.close();
                    
                    JSONArray jsonArray = new JSONArray("["+info.toString()+"]");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    
                    JSONArray jsonArrayData = new JSONArray("["+jsonObject.get("data")+"]");
                    JSONObject jsonObjectData = jsonArrayData.getJSONObject(0);
                    System.out.println(jsonObjectData.get("nombres"));
                    
                    cli.setNombre(jsonObjectData.get("nombres").toString());
                    cli.setApellidoPaterno(jsonObjectData.get("apellidoPaterno").toString());
                    cli.setApellidoMaterno(jsonObjectData.get("apellidoMaterno").toString());

                    this.vista.jtxtNomCliente.setText(jsonObjectData.get("nombres").toString()+" "+jsonObjectData.get("apellidoPaterno").toString()+" "+jsonObjectData.get("apellidoMaterno").toString());
                    
                }
   
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
               
        if (e.getSource() == vista.jButtonBuscarProd) {
            //Buscando producto con Api Rest
            
            this.vista.jButtonAgregar.setEnabled(true);
            this.vista.jspnCantidadProducto.setEnabled(true);
            
            String codbuscado = this.vista.jTextFieldCodProc.getText();
            ProductoDAO dao = new ProductoDAO();
            
            prod = dao.BuscarProductoPorCodigoAPI(codbuscado);
            
            if (prod == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla Producto");
                vista.jTextFieldCodProc.requestFocus();
            } else {
                this.vista.jTextFieldStock.setText(Integer.toString(prod.getStock()));
                this.vista.jTextFieldPrecioUnitario.setText(Double.toString(prod.getPrecioVenta()));
                //this.vista.jTextFieldTotalxProducto.setText(String.valueOf(CalcularPrecioProducto()));
                this.vista.jtxtNombre.setText(prod.getNomProd());
            }
            
            /*
            //codigo para buscar directo a la db 
            
            this.vista.jButtonAgregar.setEnabled(true);
            this.vista.jspnCantidadProducto.setEnabled(true);
            
            String codbuscado = this.vista.jTextFieldCodProc.getText();
            ProductoDAO dao = new ProductoDAO();
            
            prod = dao.BuscarProductoPorCodigo(codbuscado);
            
            if (prod == null) {
                Mensajes.m1("El codigo " + codbuscado + " no existe en la tabla Producto");
                vista.jTextFieldCodProc.requestFocus();
            } else {
                this.vista.jTextFieldStock.setText(Integer.toString(prod.getStock()));
                this.vista.jTextFieldPrecioUnitario.setText(Double.toString(prod.getPrecioVenta()));
                //this.vista.jTextFieldTotalxProducto.setText(String.valueOf(CalcularPrecioProducto()));
            }
            */
        }
        
        if (e.getSource() == vista.jButtonAgregar) {
            
            modelo = (DefaultTableModel) vista.jTableVenta.getModel();//comentado por mi
            //DefaultTableModel tmp = (DefaultTableModel) vista.jTableVenta.getModel();
            item = item + 1;
            String codigo = prod.getCodProd();
            int stock = prod.getStock();
            double precio = prod.getPrecioVenta();
            double total = CalcularPrecioProducto();
            String nombre = prod.getNomProd();
            int cantidad = Integer.parseInt(this.vista.jspnCantidadProducto.getValue().toString());
            
            this.vista.jButtonCancelarVenta.setEnabled(true);
            
            if (stock <= 0) {
                JOptionPane.showMessageDialog(vista, "No hay suficiente Stock");
            } else if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista, "Ingresar cantidad");
            } else {
                
                ArrayList lista = new ArrayList();
                lista.add(item);
                lista.add(codigo);
                lista.add(nombre);
                lista.add(cantidad);
                lista.add(precio);
                lista.add(total);
                
                Object[] ob = new Object[6];
                ob[0] = lista.get(0);
                ob[1] = lista.get(1);
                ob[2] = lista.get(2);
                ob[3] = lista.get(3);
                ob[4] = lista.get(4);
                ob[5] = lista.get(5);
                Main.ListaVenta.MostrarNuevo(vista.jTableVenta, ob, modelo); //comentado por mi
                ///modelo.addRow (ob);
                // vista.jTableVenta.setModel(modelo);
                this.vista.jtxtValorVenta.setText(String.valueOf(ven.CalcularValorVenta(this.vista.jTableVenta)));
                
                if (this.vista.jtxtDescuento.getText().isEmpty()) {
                    this.vista.jtxtSubtotal.setText(String.valueOf(ven.CalcularSubtotal(this.vista.jtxtValorVenta.getText(), "0")));
                } else {
                    this.vista.jtxtSubtotal.setText(String.valueOf(ven.CalcularSubtotal(this.vista.jtxtValorVenta.getText(), this.vista.jtxtDescuento.getText())));
                }
                
                this.vista.jtxtIGV.setText(String.valueOf(ven.CalcularTotalIGV(this.vista.jtxtSubtotal.getText())));
                
                this.vista.jtxtTotalPagar.setText(String.valueOf(ven.CalcularTotalPagar(this.vista.jtxtSubtotal.getText(), this.vista.jtxtIGV.getText())));
                
                LimpiarEntrads(false);
            }
            
            
        }
        
        if (e.getSource() == vista.jButtonImprimir) {
            
            ImprimirDAO imprimir = new ImprimirDAO();
            
            LeerVenta();
            
            System.out.println("guardado");
            VentaDAO venta = new VentaDAO();
            String idv = venta.IdVentas();
            int idventa = Integer.parseInt(idv);
            double totales = 0;
            for (int i = 0; i < this.vista.jTableVenta.getRowCount(); i++) {
                
                int idve = Integer.parseInt(idv);
                
                int idproducto = Integer.parseInt(this.vista.jTableVenta.getValueAt(i, 1).toString());
                String nombre = this.vista.jTableVenta.getValueAt(i, 2).toString();
                int cantidad = Integer.parseInt(this.vista.jTableVenta.getValueAt(i, 3).toString());
                double precioVenta = Double.parseDouble(this.vista.jTableVenta.getValueAt(i, 4).toString());
                double total = Double.parseDouble(this.vista.jTableVenta.getValueAt(i, 5).toString());
                
                totales = totales + total;
                
            }
            
            EmpleadoDAO dao = new EmpleadoDAO();
            emplead = dao.BuscarEmpleadoPorCodigo(login.getDni());
            String apellidoEmpreado = emplead.getApellidos();
            
            imprimir.pdfV(idventa, totales, apellidoEmpreado);
            
            LimpiarEntrads(true);
            
            this.vista.jButtonImprimir.setEnabled(false);
        }
        
        if (e.getSource() == vista.jButtonRegresar) {
            
            FormMenu fmenu = new FormMenu();
            fmenu.setVisible(true);
            fmenu.setTitle("Menú principal - Minimarket Mas Mas");
            fmenu.setLocationRelativeTo(null);
            vista.dispose();
            ControladorMenu controladorMenu = new ControladorMenu(fmenu, login);
        }
        
        if (e.getSource() == vista.btnReporteVentas) {
            
            ImprimirDAO imprimir = new ImprimirDAO();
            
            try {
                imprimir.pdfr();
                
            } catch (DocumentException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (e.getSource() == vista.jButtonCancelarVenta) {
            LimpiarEntrads(true);
            
        }
        
    }
    
}

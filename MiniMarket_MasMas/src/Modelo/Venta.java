package Modelo;

import javax.swing.JTable;

public class Venta {

    // atributos
    private String codVenta;
    private String nomCliente;
    private int dni_o_ruc;
    private String fechaVenta;
    private String tipoDeComprobante;
    private int idEmpleado;

    // constructor

    public Venta(String codVenta, String nomCliente, int dni_o_ruc, String fechaVenta, String tipoDeComprobante, int idEmpleado) {
        this.codVenta = codVenta;
        this.nomCliente = nomCliente;
        this.dni_o_ruc = dni_o_ruc;
        this.fechaVenta = fechaVenta;
        this.tipoDeComprobante = tipoDeComprobante;
        this.idEmpleado = idEmpleado;
    }

    
    public Venta() {
    }
    
    public Object[] CrearVenta() {
       Object[] fila ={codVenta,nomCliente,dni_o_ruc,fechaVenta, tipoDeComprobante ,idEmpleado}  ;
       return fila;
    }

    // metodos
    public boolean VentasDiarias() {
        return true;
    }

    public boolean VentasSemanales() {
        return true;
    }

    public boolean VentasMensuales() {
        return true;
    }

    public boolean NuevaVenta() {
        return true;
    }

    public boolean ActualizarStock() {
        return true;
    }
    
    public double CalcularValorVenta(JTable tabla) {
        double valorventa = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            valorventa = valorventa + Double.parseDouble(String.valueOf(tabla.getValueAt(i, 5)));
        }
        return valorventa;
    }

    public double CalcularSubtotal(String valorventa, String descuento) {
        return Double.parseDouble(valorventa) - Double.parseDouble(descuento);
    }

    public double CalcularTotalIGV(String subtotal) {
        return Double.parseDouble(subtotal) * 0.18;
    }
    
    public double CalcularTotalPagar(String subtotal, String igv) {
        return Double.parseDouble(subtotal) + Double.parseDouble(igv);
    }
    
    //getter y setter

    public String getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(String codVenta) {
        this.codVenta = codVenta;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public int getDni_o_ruc() {
        return dni_o_ruc;
    }

    public void setDni_o_ruc(int dni_o_ruc) {
        this.dni_o_ruc = dni_o_ruc;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public void setTipoDeComprobante(String tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}

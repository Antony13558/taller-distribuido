package Modelo;

import java.text.DecimalFormat;

public class Producto {

    // atributos
    DecimalFormat df = new DecimalFormat("#.00");
    private String codProd;
    private String nomProd;
    private String descripcion;
    private int idCategoria;
    private String categoria;
    private int stock;
    private double precioCoste;
    private double precioVenta;
    private int idproducto;
    private double IGV = 0.18; 

    //String fechaVenc;
    // constructor
    public Producto() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    // getter y setter
    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioCoste() {
        return precioCoste;
    }

    public void setPrecioCoste(double precioCoste) {
        this.precioCoste = precioCoste;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // metodos propios
    public double GananciaNeta() {
        return precioVenta - precioCoste; // 9.00 - 5.00 = 4.00 -> Esto sería la ganancia neta y que servría para los reportes
    }

    public double ImpuestoProducto() {
        return (IGV * precioVenta); // Ejemplo: 0.18 * 9.00 = 1.62 -> Esto sería el impuesto a aumentar
    }

    public double PrecioFinalDeVenta() {
        return precioVenta + ImpuestoProducto(); // 9.00 + 1.62 = 10.62 -> Esto sería el precio final de venta del producto
    }

    // redefinir el metodo de la interface
    public Object[] RegistroProducto(int numeracion) {
        Object[] fila = {numeracion, codProd, nomProd, descripcion, categoria, stock,
            df.format(precioCoste), df.format(precioVenta), df.format(PrecioFinalDeVenta())};
        return fila;
    }
}

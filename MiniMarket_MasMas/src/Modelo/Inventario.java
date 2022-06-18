
package Modelo;

public class Inventario {
    // atributos
    private String codProd; // autogenerado
    private String nomProd;
    private String descripcion;
    private int categoria;
    private int    stock;
    
    // constructor
    public String getCodProd()                 { return codProd; }
    public void setCodProd(String codProd)     { this.codProd = codProd; }
    public String getNomProd()              { return nomProd; }
    public void setNomProd(String nomProd)  { this.nomProd = nomProd; }
    public String getDescripcion()              { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCategoria()                { return categoria; }
    public void setCategoria(int categoria) { this.categoria = categoria; }
    public int getStock()                       { return stock; }
    public void setStock(int stock)             { this.stock = stock; }
    
    // redefiniendo el metodo para la tabla inventario
    public Object[] RegistroInventario(int numeracion) {
        Object[] fila = {numeracion, codProd, nomProd, descripcion, categoria, stock};
        return fila;
    }
    
}

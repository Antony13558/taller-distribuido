package Modelo;

public class Categoria {

    // atributos
    private int codCat;
    private String NomCat;
    private String descripcion;

    // constructor
    public Categoria() {
    }

    // sobrecarga
    public Categoria(int codCat, String nomCat) {
        this.codCat = codCat;
        this.NomCat = NomCat;
    }
    
    // Getter y Setter
    public int getCodCat() {
        return codCat;
    }

    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    public String getNomCat() {
        return NomCat;
    }

    public void setNomCat(String NomCat) {
        this.NomCat = NomCat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object[] RegistroCategorias(int numeracion) {
        Object[] fila = {numeracion, NomCat, descripcion};
        return fila;
    }

    public String toString() {
        return NomCat;
    }
}

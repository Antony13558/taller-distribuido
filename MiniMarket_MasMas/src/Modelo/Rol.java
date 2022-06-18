
package Modelo;

public class Rol {
    // atributos
    private int codRol;
    private String NomRol;
    
    // constructor
    public Rol() {}
    
    // sobrecarga
    public Rol(int idrol, String nombrerol) {
        this.codRol = idrol;
        this.NomRol = nombrerol;
    }
    
    
    // Getter y Setter
    public int getCodRol()               { return codRol; }
    public void setCodRol(int codRol)    { this.codRol = codRol; }
    public String getNomRol()               { return NomRol; }
    public void setNomRol(String NomRol)    { this.NomRol = NomRol;}
    
    
    public Object[] RegistroRoles(int numeracion) {
        Object[] fila = {numeracion, NomRol};
        return fila;
    }
    
    public String toString() {
        return NomRol;
    }
}


package Modelo;

public class Empleado {
    // atributos
    private int idemp;
    private String dni;
    private String nombres;
    private String apellidos;
    private int telefono;
    private int idcargo;
    private String nombrecargo;
    private String contrasena;
    private String usuario;
    
    // constructor
    public Empleado() {}
    
    // getter y setter
    public String getDni()          { return dni; }
    public void setDni(String Dni)  { this.dni = Dni; }
    public String getNombres()      { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos()    { return apellidos;}
    public void setApellidos(String apellidos) { this.apellidos = apellidos;}
    public int getTelefono()         { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono;}
    public int getIdcargo()        { return idcargo; }
    public void setIdcargo(int idcargo) { this.idcargo = idcargo; }
    public String getNombreCargo() { return nombrecargo; }
    public void setNombreCargo(String nombrecargo) { this.nombrecargo = nombrecargo;}
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena;}
    public int getIdemp() { return idemp; }
    public void setIdemp(int idemp) { this.idemp = idemp; }
    

    public Object[] RegistroEmpleados(int numeracion) {
         Object[] fila = {numeracion, dni, nombres, apellidos, telefono, nombrecargo, contrasena};
        return fila;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

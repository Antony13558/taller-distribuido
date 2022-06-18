
package Modelo;

public class Login {
    private String dni;
    private String pass;
    private int idrol;

    public Login() {
    }

    public Login(String dni, String pass, int idrol) {
        this.dni = dni;
        this.pass = pass;
        this.idrol = idrol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRol() {
        return idrol;
    }

    public void setRol(int rol) {
        this.idrol = rol;
    }
    
    
    
}

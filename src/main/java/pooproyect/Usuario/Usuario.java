package pooproyect.Usuario;

public class Usuario {

    private String nombre;
    private String ID;

    public Usuario(String nombre, String ID) {
        this.nombre = nombre;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
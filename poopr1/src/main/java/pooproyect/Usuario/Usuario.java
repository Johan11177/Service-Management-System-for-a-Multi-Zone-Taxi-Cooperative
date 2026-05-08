package pooproyect.Usuario;

public abstract class Usuario {

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

}
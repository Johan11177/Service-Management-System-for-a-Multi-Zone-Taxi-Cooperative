package pooproyect.Usuario;

import pooproyect.Vehiculo.Vehiculo;

public class Conductor extends Usuario {
    private Vehiculo vehiculo;
    private boolean disponible;
    
    public Conductor(String nombre, String ID, Vehiculo vehiculo) {
        super(nombre, ID);
        this.vehiculo = new Vehiculo(vehiculo.getPlaca(), vehiculo.getMarca());
        this.disponible = true;
    }

    public String getNombre() {
        return super.getNombre();
    }

    public String getID() {
        return super.getID();
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void setID(String ID) {
        super.setID(ID);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
 
    public String toString() {
        return "ID: " + getID() + ", Nombre: " + getNombre() + ", Vehículo: " + vehiculo.getMarca() + " - " + vehiculo.getPlaca() + ", Disponible: " + (disponible ? "Sí" : "No");
    }
   

}

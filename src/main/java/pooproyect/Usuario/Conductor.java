package pooproyect.Usuario;

import java.util.ArrayList;

import pooproyect.TipoDeServicio.Taxi;
import pooproyect.Vehiculo.Vehiculo;

public class Conductor extends Usuario {
    private Vehiculo vehiculo;
    private boolean disponible;
    private ArrayList<Taxi> servicios = new ArrayList<>();

    public Conductor(String nombre, String ID, Vehiculo vehiculo) {
        super(nombre, ID);
        this.vehiculo = new Vehiculo(vehiculo.getPlaca(), vehiculo.getMarca());
        this.disponible = true;
        this.servicios = new ArrayList<>();
    }

    public void agregarServicio(Taxi taxi) {
        servicios.add(taxi);
    }

    public boolean puedeAtender(String tipoServicio) {
        for (Taxi servicio : servicios) {
            if (servicio.getTipoServicio().equalsIgnoreCase(tipoServicio)) {
                return true;
            }
        }
        return false;
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

      public ArrayList<Taxi> getServicios() {
        return servicios;
    }

   public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ").append(getID())
      .append(", Nombre: ").append(getNombre())
      .append(", Vehículo: ").append(vehiculo.getMarca()).append(" - ").append(vehiculo.getPlaca())
      .append(", Disponible: ").append(disponible ? "Sí" : "No")
      .append(", Servicios: ");

    if (servicios.isEmpty()) {
        sb.append("Sin servicios asignados");
    } else {
        for (Taxi t : servicios) {
            sb.append(t.getTipoServicio()).append(" | ");
        }
    }

    return sb.toString();
}

}

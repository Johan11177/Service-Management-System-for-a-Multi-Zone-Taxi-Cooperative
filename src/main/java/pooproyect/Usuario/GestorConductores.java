package pooproyect.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

import pooproyect.TipoDeServicio.Taxi;
import pooproyect.Vehiculo.Vehiculo;


public class GestorConductores {
    Scanner sc = new Scanner(System.in);

    private boolean disponible;
    private ArrayList<Taxi> servicios;
    private ArrayList<Conductor> conductores;

    public GestorConductores() {
        this.disponible = true;
        this.servicios = new ArrayList<>();
        this.conductores = new ArrayList<>();
    }

    public void agregarServicio(Taxi servicio) {
        servicios.add(servicio);
    }

    public void agregarConductor() {
        String idGenerado = "C-" + (conductores.size() + 1);

        System.out.println("Ingrese el nombre del conductor:");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la Marca del Vehículo:");
        String marca = sc.nextLine();

        System.out.println("Ingrese la Placa del Vehículo:");
        String placa = sc.nextLine();

        conductores.add(new Conductor(nombre, idGenerado, new Vehiculo(marca, placa)));
        System.out.println("Conductor registrado con ID: " + idGenerado);
    }

    public void MostrarConductores() {
        for (Conductor c : conductores) {
            System.out.println(c);
        }
    }

    public boolean puedeAtender(String tipoServicio) {
        for (Taxi servicio : servicios) {
            if (servicio.getTipoServicio().equalsIgnoreCase(tipoServicio)) {
                return true;
            }
        }
        return false;
    }

    public void ocuparConductor() {
        disponible = false;
    }

    public void liberarConductor() {
        disponible = true;
    }

    public ArrayList<Conductor> getConductores() {
        return conductores;
    }

    public ArrayList<Taxi> getServicios() {
        return servicios;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Conductor c : conductores) {
            sb.append("ID: ").append(c.getID()).append(", Nombre: ").append(c.getNombre())
                    .append(", Vehículo: ").append(c.getVehiculo().getMarca()).append(" - ").append(c.getVehiculo().getPlaca())
                    .append(", Disponible: ").append(disponible ? "Sí" : "No").append("\n");
            
        }
        return sb.toString();
    }
}
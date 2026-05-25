package pooproyect.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

import pooproyect.Main.EntradaUsuario;
import pooproyect.TipoDeServicio.TaxiConBaul;
import pooproyect.TipoDeServicio.TaxiEstandar;
import pooproyect.TipoDeServicio.TaxiTransporteMascotas;
import pooproyect.Vehiculo.Vehiculo;

public class GestorConductores {
    private Scanner sc = EntradaUsuario.get();

    private boolean disponible;
    private ArrayList<Conductor> conductores;

    public GestorConductores() {
        this.disponible = true;
        this.conductores = new ArrayList<>();
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

    public void habilitarServicioConductor() {
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores registrados.");
            return;
        }

        MostrarConductores();
        System.out.println("Seleccione el numero del conductor:");
        int indice = sc.nextInt();
        sc.nextLine();

        if (indice < 1 || indice > conductores.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        Conductor conductor = conductores.get(indice - 1);

        System.out.println("1. Taxi Estandar");
        System.out.println("2. Taxi con Baul");
        System.out.println("3. Taxi Transporte Mascotas");
        System.out.println("Seleccione el tipo de servicio:");

        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1) {
            conductor.agregarServicio(new TaxiEstandar());
        } else if (tipo == 2) {
            conductor.agregarServicio(new TaxiConBaul());
        } else if (tipo == 3) {
            conductor.agregarServicio(new TaxiTransporteMascotas());
        } else {
            System.out.println("Opcion invalida");
            return;
        }

        System.out.println("Servicio asignado correctamente.");
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

 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Conductor c : conductores) {
            sb.append("ID: ").append(c.getID()).append(", Nombre: ").append(c.getNombre())
                    .append(", Vehículo: ").append(c.getVehiculo().getMarca()).append(" - ")
                    .append(c.getVehiculo().getPlaca())
                    .append(", Disponible: ").append(disponible ? "Sí" : "No").append("\n");
        }
        return sb.toString();
    }
}

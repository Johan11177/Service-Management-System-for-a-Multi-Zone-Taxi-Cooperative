package pooproyect.Conductores;

import java.util.ArrayList;

import pooproyect.TipoDeServicio.Taxi;

public class Conductores {

    private String nombre;
    private boolean disponible;

    private ArrayList<Taxi> servicios;

    public Conductores(String nombre) {

        this.nombre = nombre;
        this.disponible = true;

        this.servicios = new ArrayList<>();
    }

    public void agregarServicio(Taxi servicio) {

        servicios.add(servicio);
    }

    public boolean puedeAtender(String tipoServicio) {

        for (Taxi servicio : servicios) {

            if (servicio.getTipoServicio()
                    .equalsIgnoreCase(tipoServicio)) {

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

    public String getNombre() {

        return nombre;
    }

    public boolean isDisponible() {

        return disponible;
    }

    public ArrayList<Taxi> getServicios() {

        return servicios;
    }

    @Override
    public String toString() {

        String texto = "";

        for (Taxi servicio : servicios) {

            texto += servicio.getTipoServicio() + " ";
        }

        return "Conductor: " + nombre +
               "\nDisponible: " + disponible +
               "\nServicios: " + texto;
    }
}
package pooproyect.Conductores;

import java.util.ArrayList;

import pooproyect.Servicio.Servicio;

public class Conductores {

    private String nombre;
    private boolean disponible;

    private ArrayList<Servicio> servicios;

    public Conductores(String nombre) {

        this.nombre = nombre;
        this.disponible = true;

        servicios = new ArrayList<>();
    }

    public void agregarServicio(Servicio servicio) {

        servicios.add(servicio);
    }

    public boolean puedeAtender(String tipoServicio) {

        for (Servicio servicio : servicios) {

            if (servicio.getNombre()
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

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }
    

    @Override
    public String toString() {

        String texto = "";

        for (Servicio servicio : servicios) {

            texto += servicio.getNombre() + " ";
        }

        return "Conductor: " + nombre +
               "\nDisponible: " + disponible +
               "\nServicios: " + texto;
    }
}
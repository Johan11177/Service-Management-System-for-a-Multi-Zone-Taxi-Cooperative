package pooproyect.Servicio;

import pooproyect.Tarifas.Tarifa;

public abstract class Servicio {

    protected String nombre;
    protected Tarifa tarifa;

    public Servicio(String nombre, Tarifa tarifa) {

        this.nombre = nombre;
        this.tarifa = tarifa;
    }

    public String getNombre() {
        return nombre;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    @Override
    public String toString() {

        return nombre;
    }
}
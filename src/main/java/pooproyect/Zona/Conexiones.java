package pooproyect.Zona;

public class Conexiones {

    private Zonas origen;
    private Zonas destino;
    private boolean Habilitada;
    private int tiempoEstimado;
    private double kiloMetros;

    public Conexiones(Zonas origen, Zonas destino, boolean Habilitada, int tiempoEstimado, double kiloMetros) {
        this.origen = origen;
        this.destino = destino;
        this.Habilitada = Habilitada;
        this.tiempoEstimado = tiempoEstimado;
        this.kiloMetros = kiloMetros;
    }


    public Zonas getOrigen() {
        return origen;
    }

    public Zonas getDestino() {
        return destino;
    }

    public double getKiloMetros() {
        return kiloMetros;
    }
    }

    public boolean isHabilitada() {
        return Habilitada;
    }

    public void setHabilitada(boolean Habilitada) {
        this.Habilitada = Habilitada;
    }
    public int getTiempoEstimado() {
        return tiempoEstimado;
    }
}
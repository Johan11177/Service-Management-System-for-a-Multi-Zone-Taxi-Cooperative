package pooproyect.Zona;

public class Conexiones {

    private Zonas origen;
    private Zonas destino;
    private boolean Habilitada;
    private int tiempoEstimado;

    public Conexiones(Zonas origen, Zonas destino, boolean Habilitada, int tiempoEstimado) {
        this.origen = origen;
        this.destino = destino;
        this.Habilitada = Habilitada;
        this.tiempoEstimado = tiempoEstimado;
    }


    public Zonas getOrigen() {
        return origen;
    }

    public Zonas getDestino() {
        return destino;
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
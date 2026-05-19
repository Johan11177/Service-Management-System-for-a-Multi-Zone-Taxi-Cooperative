package pooproyect.Zona;

public class Conexiones {

    private Zonas origen;
    private Zonas destino;
    private boolean Habilitada;

    public Conexiones(Zonas origen, Zonas destino, boolean Habilitada) {
        this.origen = origen;
        this.destino = destino;
        this.Habilitada = Habilitada;
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

}
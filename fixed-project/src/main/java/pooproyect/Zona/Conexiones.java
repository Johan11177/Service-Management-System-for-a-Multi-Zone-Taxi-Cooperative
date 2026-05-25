package pooproyect.Zona;

public class Conexiones {

    private Zonas origen;
    private Zonas destino;
    private int idOrigen;
    private int idDestino;
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

    public Conexiones(int idOrigen, int idDestino) {
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
    }

    public Zonas getOrigen() {
        return origen;
    }

    public void setOrigen(Zonas origen) {
        this.origen = origen;
    }

    public Zonas getDestino() {
        return destino;
    }

    public void setDestino(Zonas destino) {
        this.destino = destino;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public boolean isHabilitada() {
        return Habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        Habilitada = habilitada;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public double getKiloMetros() {
        return kiloMetros;
    }

    public void setKiloMetros(double kiloMetros) {
        this.kiloMetros = kiloMetros;
    }

    
}
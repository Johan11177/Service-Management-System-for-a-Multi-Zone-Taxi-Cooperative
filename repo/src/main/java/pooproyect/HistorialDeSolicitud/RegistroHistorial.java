package pooproyect.HistorialDeSolicitud;

public class RegistroHistorial {
    private int idSolicitud;
    private String nombreCliente;
    private String zonaDeorigen;
    private String zonaDestino;
    private String tipoServicio;
    private String nombreConductor;
    private String idConductor;
    private String placaVehiculo;
    private double tarifaFinal;
    private int tiempoEstimado;
    private String estado;
    private String fecha;
    private String hora;

    public RegistroHistorial(int idSolicitud, String nombreCliente, String zonaDeorigen,
                            String zonaDestino, String tipoServicio, String nombreConductor,
                            String idConductor, String placaVehiculo, double tarifaFinal,
                            int tiempoEstimado, String estado, String fecha, String hora) {
        this.idSolicitud = idSolicitud;
        this.nombreCliente = nombreCliente;
        this.zonaDeorigen = zonaDeorigen;
        this.zonaDestino = zonaDestino;
        this.tipoServicio = tipoServicio;
        this.nombreConductor = nombreConductor;
        this.idConductor = idConductor;
        this.placaVehiculo = placaVehiculo;
        this.tarifaFinal = tarifaFinal;
        this.tiempoEstimado = tiempoEstimado;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getZonaDeorigen() {
        return zonaDeorigen;
    }

    public String getZonaDestino() {
        return zonaDestino;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public double getTarifaFinal() {
        return tarifaFinal;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "ID Solicitud: " + idSolicitud + "\n" +
                "Cliente: " + nombreCliente + "\n" +
                "Origen: " + zonaDeorigen + " -> Destino: " + zonaDestino + "\n" +
                "Tipo de Servicio: " + tipoServicio + "\n" +
                "Conductor: " + nombreConductor + " (ID: " + idConductor + ")" + "\n" +
                "Placa: " + placaVehiculo + "\n" +
                "Tarifa: $" + tarifaFinal + "\n" +
                "Tiempo Estimado: " + tiempoEstimado + " min" + "\n" +
                "Estado: " + estado + "\n" +
                "Fecha: " + fecha + " | Hora: " + hora;
    }
}

package pooproyect.Solicitudes;

public class Solicitudes {
    private int IDsolicitud;
    private String zonaDeorigen;
    private String ZonaDestino;
    private String TipoDeServicio;
    private String Hora;
    private String Fecha;
    private String EstadoSolicitud;
    private String motivoCancelacion;
    private String nombreCliente;
    private String nombreConductor;
    private String idConductor;
    private String placaVehiculo;
    private double tarifaEstimada;
    private int tiempoEstimado;
    

    public Solicitudes(int iDsolicitud, String zonaDeorigen, String zonaDestino, String tipoDeServicio, String hora,
            String fecha) {
        IDsolicitud = iDsolicitud;
        this.zonaDeorigen = zonaDeorigen;
        ZonaDestino = zonaDestino;
        TipoDeServicio = tipoDeServicio;
        Hora = hora;
        Fecha = fecha;
        EstadoSolicitud = "En espera";
        this.motivoCancelacion = null;
        this.nombreCliente = "";
        this.nombreConductor = "";
        this.idConductor = "";
        this.placaVehiculo = "";
        this.tarifaEstimada = 0.0;
        this.tiempoEstimado = 0;
    }

    public int getIDsolicitud() {
        return IDsolicitud;
    }

    public void setIDsolicitud(int iDsolicitud) {
        IDsolicitud = iDsolicitud;
    }

    public String getZonaDeorigen() {
        return zonaDeorigen;
    }

    public void setZonaDeorigen(String zonaDeorigen) {
        this.zonaDeorigen = zonaDeorigen;
    }

    public String getZonaDestino() {
        return ZonaDestino;
    }

    public void setZonaDestino(String zonaDestino) {
        ZonaDestino = zonaDestino;
    }

    public String getTipoDeServicio() {
        return TipoDeServicio;
    }

    public void setTipoDeServicio(String tipoDeServicio) {
        TipoDeServicio = tipoDeServicio;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getEstadoSolicitud() {
        return EstadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        EstadoSolicitud = estadoSolicitud;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivo) {
        this.motivoCancelacion = motivo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public double getTarifaEstimada() {
        return tarifaEstimada;
    }

    public void setTarifaEstimada(double tarifa) {
        this.tarifaEstimada = tarifa;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempo) {
        this.tiempoEstimado = tiempo;
    }

    @Override
    public String toString() {
        return "Solicitudes [IDsolicitud=" + IDsolicitud + ", zonaDeorigen=" + zonaDeorigen + ", ZonaDestino="
                + ZonaDestino + ", TipoDeServicio=" + TipoDeServicio + ", Hora=" + Hora + ", Fecha=" + Fecha
                + ", EstadoSolicitud=" + EstadoSolicitud + "]";
    }
}

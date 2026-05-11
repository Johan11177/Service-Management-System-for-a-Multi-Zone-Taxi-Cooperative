package pooproyect.Solicitudes;

public class Solicitudes {
    private int IDsolicitud;
    private String zonaDeorigen;
    private String ZonaDestino;
    private String TipoDeServicio;
    private String Hora;
    private String Fecha;
    private boolean EstadoSolicitud;
    


    public Solicitudes(int iDsolicitud, String zonaDeorigen, String zonaDestino, String tipoDeServicio, String hora,
            String fecha, boolean estadoSolicitud) {
        IDsolicitud = iDsolicitud++;
        this.zonaDeorigen = zonaDeorigen;
        ZonaDestino = zonaDestino;
        TipoDeServicio = tipoDeServicio;
        Hora = hora;
        Fecha = fecha;
        EstadoSolicitud = estadoSolicitud = "En espera";
    }



    public void RecibirSolicitud() {
        /* 
        get.zonaorigen;
        get.zonadestino;
        get.tipoDeServicio;
        get.fechayhora;
        get.EstadoSolicitud;(en espera en atencion, canelada, finalizada)
        */
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



    public boolean isEstadoSolicitud() {
        return EstadoSolicitud;
    }



    public void setEstadoSolicitud(boolean estadoSolicitud) {
        EstadoSolicitud = estadoSolicitud;
    }

}

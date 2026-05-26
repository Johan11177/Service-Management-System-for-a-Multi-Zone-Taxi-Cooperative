package pooproyect.Solicitudes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MotivosCancelacion {
    private int idSolicitud;
    private String motivo;
    private String fechaCancelacion;
    private String horaCancelacion;
    private String nombreCliente;

    public MotivosCancelacion(int idSolicitud, String motivo, String nombreCliente) {
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.nombreCliente = nombreCliente;
        this.fechaCancelacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.horaCancelacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public String getHoraCancelacion() {
        return horaCancelacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    @Override
    public String toString() {
        return "MotivosCancelacion [ID Solicitud=" + idSolicitud + ", Motivo=" + motivo
                + ", Fecha=" + fechaCancelacion + ", Hora=" + horaCancelacion
                + ", Cliente=" + nombreCliente + "]";
    }
}

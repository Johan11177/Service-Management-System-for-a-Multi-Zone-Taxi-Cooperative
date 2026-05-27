package pooproyect.Excepciones;

public class SolicitudInvalidaException extends Exception {
    public SolicitudInvalidaException(String mensaje) {
        super("Solicitud inválida: " + mensaje);
    }
}

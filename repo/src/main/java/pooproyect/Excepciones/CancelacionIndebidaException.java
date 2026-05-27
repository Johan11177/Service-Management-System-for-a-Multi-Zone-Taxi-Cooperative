package pooproyect.Excepciones;

public class CancelacionIndebidaException extends Exception {
    public CancelacionIndebidaException(int idSolicitud) {
        super("No se puede cancelar la solicitud " + idSolicitud + ": no existe o ya fue procesada.");
    }
}

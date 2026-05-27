package pooproyect.Excepciones;

public class ZonaInexistenteException extends Exception {
    public ZonaInexistenteException(String nombreZona) {
        super("La zona '" + nombreZona + "' no existe en el sistema.");
    }
}

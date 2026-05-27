package pooproyect.Excepciones;

public class ConductorNoHabilitadoException extends Exception {
    public ConductorNoHabilitadoException(String tipoServicio) {
        super("No hay conductores disponibles habilitados para el servicio: " + tipoServicio);
    }
}

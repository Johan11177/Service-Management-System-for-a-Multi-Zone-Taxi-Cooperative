package pooproyect.Excepciones;

public class DatosIncompletosException extends Exception {
    public DatosIncompletosException(String campo) {
        super("Datos incompletos: el campo '" + campo + "' es obligatorio y no puede estar vacío.");
    }
}

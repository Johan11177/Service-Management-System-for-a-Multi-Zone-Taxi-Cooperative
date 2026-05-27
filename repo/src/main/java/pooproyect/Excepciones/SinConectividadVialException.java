package pooproyect.Excepciones;

public class SinConectividadVialException extends Exception {
    public SinConectividadVialException(String origen, String destino) {
        super("No existe conectividad vial habilitada entre '" + origen + "' y '" + destino + "'.");
    }
}

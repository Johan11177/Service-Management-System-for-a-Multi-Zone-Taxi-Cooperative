package pooproyect.HistorialDeSolicitud;

import java.util.ArrayList;

public class HistorialSolicitudes {
    private ArrayList<RegistroHistorial> registros;

    public HistorialSolicitudes() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(RegistroHistorial registro) {
        registros.add(registro);
    }

    public void mostrarHistorial() {
        if (registros.isEmpty()) {
            System.out.println("No hay registros en el historial.");
            return;
        }

        System.out.println("\n=== HISTORIAL DE SOLICITUDES ATENDIDAS ===");
        for (RegistroHistorial r : registros) {
            System.out.println(r);
            System.out.println("---");
        }
    }

    public ArrayList<RegistroHistorial> getRegistros() {
        return registros;
    }
}

package pooproyect.Persistencia;

import pooproyect.Zona.Conexiones;

import java.io.*;
import java.util.ArrayList;

/**
 * Persiste el estado de habilitación de las conexiones viales.
 * Principio SRP: única responsabilidad — guardar/cargar estado de la red vial.
 */
public class PersistenciaRedVial {

    private static final String ARCHIVO = "red_vial.txt";

    /**
     * Guarda el estado actual (habilitada/deshabilitada) de cada conexión.
     * El índice de posición en la lista actúa como identificador.
     */
    public static void guardarEstado(ArrayList<Conexiones> conexiones) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < conexiones.size(); i++) {
                bw.write(i + ";" + conexiones.get(i).isHabilitada());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar estado de red vial: " + e.getMessage());
        }
    }

    /**
     * Carga el estado guardado y lo aplica sobre la lista de conexiones existente.
     * No crea conexiones nuevas — solo restaura el flag habilitada.
     */
    public static void cargarEstado(ArrayList<Conexiones> conexiones) {
        File f = new File(ARCHIVO);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] partes = linea.split(";");
                if (partes.length < 2) continue;
                int idx = Integer.parseInt(partes[0].trim());
                boolean habilitada = Boolean.parseBoolean(partes[1].trim());
                if (idx >= 0 && idx < conexiones.size()) {
                    conexiones.get(idx).setHabilitada(habilitada);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar estado de red vial: " + e.getMessage());
        }
    }
}

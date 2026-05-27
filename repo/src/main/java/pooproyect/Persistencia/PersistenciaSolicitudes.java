package pooproyect.Persistencia;

import pooproyect.Solicitudes.MotivosCancelacion;
import pooproyect.Solicitudes.Solicitudes;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase responsable de guardar y cargar el historial de solicitudes y la cola.
 * Principio SRP: solo gestiona la persistencia de solicitudes.
 * Principio OCP: puede extenderse para otros formatos sin modificar el código base.
 */
public class PersistenciaSolicitudes {

    private static final Path BASE_DIR = initBaseDir();
    private static final String ARCHIVO_HISTORIAL = BASE_DIR.resolve("historial.txt").toString();
    private static final String ARCHIVO_COLA = BASE_DIR.resolve("cola.txt").toString();
    private static final String SEPARADOR = "|";

    private static Path initBaseDir() {
        try {
            Path location = Paths.get(PersistenciaSolicitudes.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
            if (Files.isRegularFile(location)) {
                location = location.getParent();
            }
            Path base = location;
            if (base != null) {
                Path fileName = base.getFileName();
                if (fileName != null && "classes".equals(fileName.toString())) {
                    base = base.getParent();
                }
                fileName = base != null ? base.getFileName() : null;
                if (fileName != null && "target".equals(fileName.toString())) {
                    base = base.getParent();
                }
            }
            if (base == null) {
                base = Paths.get(System.getProperty("user.dir"));
            }
            return base;
        } catch (URISyntaxException e) {
            return Paths.get(System.getProperty("user.dir"));
        }
    }

    // ──────────────────────── HISTORIAL ────────────────────────

    public static void guardarHistorial(ArrayList<Solicitudes> historial) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAL))) {
            for (Solicitudes s : historial) {
                bw.write(serializar(s));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar historial: " + e.getMessage());
        }
    }

    public static ArrayList<Solicitudes> cargarHistorial() {
        return cargarDesdeArchivo(ARCHIVO_HISTORIAL);
    }

    // ──────────────────────── COLA ────────────────────────

    public static void guardarCola(ArrayList<Solicitudes> cola) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_COLA))) {
            for (Solicitudes s : cola) {
                bw.write(serializar(s));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar cola: " + e.getMessage());
        }
    }

    public static ArrayList<Solicitudes> cargarCola() {
        return cargarDesdeArchivo(ARCHIVO_COLA);
    }

    // ──────────────────────── HELPERS ────────────────────────

    public static String serializar(Solicitudes s) {
        return s.getIDsolicitud() + SEPARADOR
                + s.getNombreCliente() + SEPARADOR
                + s.getZonaDeorigen() + SEPARADOR
                + s.getZonaDestino() + SEPARADOR
                + s.getTipoDeServicio() + SEPARADOR
                + s.getHora() + SEPARADOR
                + s.getFecha() + SEPARADOR
                + s.getEstadoSolicitud() + SEPARADOR
                + nvl(s.getNombreConductor()) + SEPARADOR
                + nvl(s.getIdConductor()) + SEPARADOR
                + nvl(s.getPlacaVehiculo()) + SEPARADOR
                + s.getTarifaEstimada() + SEPARADOR
                + s.getTiempoEstimado() + SEPARADOR
                + nvl(s.getMotivoCancelacion());
    }

    public static ArrayList<Solicitudes> cargarDesdeArchivo(String archivo) {
        ArrayList<Solicitudes> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split("\\" + SEPARADOR, -1);
                if (p.length < 14) continue;

                Solicitudes s = new Solicitudes(
                        Integer.parseInt(p[0].trim()),
                        p[2].trim(), p[3].trim(), p[4].trim(),
                        p[5].trim(), p[6].trim()
                );
                s.setNombreCliente(p[1].trim());
                s.setEstadoSolicitud(p[7].trim());
                s.setNombreConductor(p[8].trim());
                s.setIdConductor(p[9].trim());
                s.setPlacaVehiculo(p[10].trim());
                s.setTarifaEstimada(Double.parseDouble(p[11].trim()));
                s.setTiempoEstimado(Integer.parseInt(p[12].trim()));
                if (!p[13].trim().isEmpty()) s.setMotivoCancelacion(p[13].trim());
                lista.add(s);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar solicitudes (" + archivo + "): " + e.getMessage());
        }
        return lista;
    }

    public static String nvl(String v) {
        return (v == null) ? "" : v;
    }
}

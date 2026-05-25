package pooproyect.Zona;

import java.util.ArrayList;
import java.util.Scanner;

import pooproyect.Main.EntradaUsuario;

public class GestorConexionesRutas {
    private Scanner sc = EntradaUsuario.get();

    private ArrayList<Conexiones> conexiones;
    private GestorZonas gestorZonas;

    public GestorConexionesRutas(GestorZonas gestorZonas) {
        this.gestorZonas = gestorZonas;
        this.conexiones = new ArrayList<>();
        AgregarConexiones();
    }

    public GestorConexionesRutas() {
    }

    public void AgregarConexiones() {
        if (!conexiones.isEmpty()) {
            return;
        }

        conexiones.add(new Conexiones(gestorZonas.getZonas().get(0), gestorZonas.getZonas().get(1), true, 15, 10));
        conexiones.add(new Conexiones(gestorZonas.getZonas().get(1), gestorZonas.getZonas().get(2), true, 10, 5));
        conexiones.add(new Conexiones(gestorZonas.getZonas().get(2), gestorZonas.getZonas().get(5), true, 15, 6));
        conexiones.add(new Conexiones(gestorZonas.getZonas().get(2), gestorZonas.getZonas().get(3), true, 8, 4));
        conexiones.add(new Conexiones(gestorZonas.getZonas().get(3), gestorZonas.getZonas().get(4), true, 6, 3));
        conexiones.add(new Conexiones(gestorZonas.getZonas().get(4), gestorZonas.getZonas().get(5), true, 18, 8));
    }

    public void MostrarConexiones() {
        System.out.println("\n=== Conexiones Disponibles ===");
        int contador = 1;

        for (Conexiones c : conexiones) {
            String estado = c.isHabilitada() ? "Habilitada" : "Deshabilitada";

            System.out.println("[" + contador + "] "
                    + c.getOrigen().getNombreZona()
                    + " <--> "
                    + c.getDestino().getNombreZona());

            System.out.println("    Estado: " + estado
                    + " | Tiempo: " + c.getTiempoEstimado()
                    + " min | Distancia: "
                    + c.getKiloMetros() + " km");

            contador++;
            System.out.println();
        }
    }

    public void HabilitarDeshabilitarConexion() {
        if (conexiones.isEmpty()) {
            System.out.println("No hay conexiones registradas.");
            return;
        }

        MostrarConexiones();

        System.out.print("Ingrese el numero de la conexion: ");
        int indice = sc.nextInt();

        if (indice < 1 || indice > conexiones.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        Conexiones conexion = conexiones.get(indice - 1);
        conexion.setHabilitada(!conexion.isHabilitada());

        String nuevoEstado = conexion.isHabilitada() ? "Habilitada" : "Deshabilitada";

        System.out.println("Conexion entre "
                + conexion.getOrigen().getNombreZona()
                + " y "
                + conexion.getDestino().getNombreZona()
                + " ahora esta "
                + nuevoEstado + ".");
    }

    public ArrayList<Conexiones> getConexiones() {
        return conexiones;
    }

    public GestorZonas getGestorZonas() {
        return gestorZonas;
    }
}

package pooproyect.Zona;

import java.util.Scanner;

public class InterfazConexionVial {
    Scanner sc = new Scanner(System.in);
    private GestorZonas gestorZonas;
    private GestorConexionesRutas rutas;
    private Grafo grafo;

    public InterfazConexionVial() {
        gestorZonas = new GestorZonas();
        gestorZonas.agregarZonas();
        rutas = new GestorConexionesRutas(gestorZonas);
        rutas.agregarConexiones();
        grafo = new Grafo(rutas);
        grafo.construirGrafo();
    }

    public void iniciar() {
        int origen;
        int destino;

        System.out.println("===== CONEXIONES VIALES =====");

        for (Zonas z : gestorZonas.getZonas()) {
            System.out.println((z.getIdZona() + 1) + ". " + z.getNombreZona());
        }

        // ORIGEN
        System.out.print("Seleccione origen: ");
        origen = sc.nextInt();

        while (origen < 1 || origen > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            origen = sc.nextInt();
        }

        // DESTINO
        System.out.print("Seleccione destino: ");
        destino = sc.nextInt();

        while (destino < 1 || destino > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            destino = sc.nextInt();
        }

        // AJUSTAR INDICES
        origen--;
        destino--;

        int[][] matriz = grafo.getMatriz();

        // BFS
        BFS bfs = new BFS(matriz);
        boolean existeCamino = bfs.hayConexion(origen, destino);
        System.out.println();

        if (existeCamino) {
            System.out.println("Hay Conexion Vial");
            // DIJKSTRA
            Dijkstra dijkstra = new Dijkstra(matriz);
            int tiempo = dijkstra.calcularRutaMasCorta(origen, destino);

            System.out.println("Tiempo estimado: "+ tiempo + " minutos");

        } else {
            System.out.println("No hay Conexion Vial");
        }
    }
}
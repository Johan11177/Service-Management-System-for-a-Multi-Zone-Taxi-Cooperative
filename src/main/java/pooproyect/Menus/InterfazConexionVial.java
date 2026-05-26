package pooproyect.Menus;

import java.util.Scanner;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Zona.BFS;
import pooproyect.Zona.Dijkstra;
import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;
import pooproyect.Zona.GrafoTiempo;

public class InterfazConexionVial {
    private Scanner sc = EntradaUsuario.get();
    private GestorZonas gestorZonas;
    private GestorConexionesRutas rutas;
    private GrafoTiempo grafo;

    public InterfazConexionVial(GestorZonas gestorZonas, GestorConexionesRutas rutas) {
        this.gestorZonas = gestorZonas;
        this.rutas = rutas;
        this.grafo = new GrafoTiempo(rutas);
    }

    public void iniciar() {
    
       // int[][] matriz = grafo.construirGrafo;.getMatrizTiempo();
        //int[][] matrizKm = grafo.getMatrizKilometros();
       /* 
        BFS bfs = new BFS(matriz);
        boolean existeCamino = bfs.hayConexion(origen, destino);
        System.out.println();

        if (existeCamino) {
            System.out.println("Hay Conexion Vial");
            Dijkstra dijkstraTiempo = new Dijkstra(matriz);
            int tiempo = dijkstraTiempo.calcularRutaMasCorta(origen, destino);
            System.out.println("Tiempo estimado: " + tiempo + " minutos");

            Dijkstra dijkstraKm = new Dijkstra(matrizKm);
            int kilometro = dijkstraKm.calcularRutaMasCorta(origen, destino);
            System.out.println("kilometros estimados: " + kilometro + " Kilometros");
        } else {
            System.out.println("No hay Conexion Vial");
        }
            */
    }
}

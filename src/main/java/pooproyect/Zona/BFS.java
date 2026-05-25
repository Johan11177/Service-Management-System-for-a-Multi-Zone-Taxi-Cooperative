package pooproyect.Zona;

import java.util.LinkedList;
import java.util.Queue;
//Breadth-First Search (Búsqueda en anchura)
//derecho de uso exclusivo estudiantes del profesor capacho

public class BFS {

    private int[][] matriz;

    public BFS(int[][] matriz) {
        this.matriz = matriz;
    }
    //pr 2 conexiones indirectas, falta algoritmo para calcular tiempos de viaje....
    // pr 1 hecha, es innecesaria la matriz de adyacencia, se puede hacer con las conexiones directas e indirectas, 
    // falta algoritmo para calcular tiempos de viaje....
    //va bien de momento

    // Recorre la matriz de adyacencia y busca rutas directos o idirectos
    public boolean hayConexion(int origen, int destino) {

        boolean[] visitados = new boolean[matriz.length];

        Queue<Integer> cola = new LinkedList<>();
        cola.add(origen);
        visitados[origen] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (actual == destino) {
                return true;

            }

            for (int i = 0; i < matriz.length; i++) {

                if (matriz[actual][i] != 0 && !visitados[i]) {
                    cola.add(i);
                    visitados[i] = true;

                }

            }

        }

        return false;

    }
}

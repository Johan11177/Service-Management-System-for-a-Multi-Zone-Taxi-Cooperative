package pooproyect.Zona;

import java.util.LinkedList;
import java.util.Queue;
//Breadth-First Search (Búsqueda en anchura)

public class BFS {

    private int[][] matriz;

    public BFS(int[][] matriz) {
        this.matriz = matriz;
    }

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

                if (matriz[actual][i] == 1 && !visitados[i]) {
                    cola.add(i);
                    visitados[i] = true;

                }

            }

        }

        return false;

    }
}

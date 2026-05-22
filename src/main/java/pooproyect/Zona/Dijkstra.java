//calcula las rutas mas cortas entre dos zonas utilizando el algoritmo de Dijkstra
//tambien calcula los tiempos de viaje
//derecho de uso exclusivo estudiantes del profesor capacho

package pooproyect.Zona;

import java.util.Arrays;

public class Dijkstra {

    private int[][] matriz;

    public Dijkstra(int[][] matriz) {
        this.matriz = matriz;
    }

    public int calcularRutaMasCorta(int origen, int destino) {

        int n = matriz.length;
        int[] distancias = new int[n];
        boolean[] visitados = new boolean[n];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origen] = 0;

        for (int i = 0; i < n - 1; i++) {
            int actual = obtenerNodoMenorDistancia(distancias, visitados);
            if (actual == -1) {
                break;
            }

            visitados[actual] = true;

            for (int vecino = 0; vecino < n; vecino++) {
                if (!visitados[vecino] && matriz[actual][vecino] != 0) {
                    if (distancias[actual] != Integer.MAX_VALUE && distancias[actual]
                            + matriz[actual][vecino] < distancias[vecino]) {

                        distancias[vecino] = distancias[actual] + matriz[actual][vecino];
                    }
                }
            }
        }
        return distancias[destino];
    }

    private int obtenerNodoMenorDistancia(
            int[] distancias,
            boolean[] visitados) {

        int minimo = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for (int i = 0; i < distancias.length; i++) {

            if (!visitados[i] && distancias[i] < minimo) {
                minimo = distancias[i];
                indiceMinimo = i;
            }
        }
        return indiceMinimo;
    }
}
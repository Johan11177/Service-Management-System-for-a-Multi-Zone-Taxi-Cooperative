package pooproyect.Zona;

public class Grafo {
    private int[][] matrizTiempo;
    private int[][] matrizKilometros;
    private GestorConexionesRutas rutas;

    public Grafo(GestorConexionesRutas rutas) {
        this.rutas = rutas;
        construirGrafo();

    }

    public void construirGrafo() {
        int tamaño = rutas.getGestorZonas().getZonas().size();
        matrizTiempo = new int[tamaño][tamaño];
        matrizKilometros = new int[tamaño][tamaño];

        for (Conexiones c : rutas.getConexiones()) {
            if (c.isHabilitada()) {
                int origen = c.getOrigen().getIdZona();
                int destino = c.getDestino().getIdZona();
                // Matriz tiempo
                int tiempo = c.getTiempoEstimado();
                matrizTiempo[origen][destino] = tiempo;
                matrizTiempo[destino][origen] = tiempo; 

                // Matriz kilómetros
                int kilometros = (int) c.getKiloMetros();
                //Ida
                matrizKilometros[origen][destino] = kilometros;
                //vuelta
                matrizKilometros[destino][origen] = kilometros;


            }

    }
}

    //prueba 1 conexiones diresta, falta indirectas pediente...
    /*
     * public boolean hayConexion(int origen, int destino) {
     * 
     * boolean existeConexion = matriz[origen][destino] != 0;
     * 
     * if (existeConexion) {
     * System.out.println("Hay conexion vial");
     * } else {
     * System.out.println("No hay conexion vial");
     * }
     * return existeConexion;
     * 
     * }
     */

    public int[][] getMatrizTiempo() {
    return matrizTiempo;
}

public int[][] getMatrizKilometros() {
    return matrizKilometros;
}
    
}


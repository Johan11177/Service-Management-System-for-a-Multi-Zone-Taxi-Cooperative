package pooproyect.Zona;

public class Grafo {

    private int[][] matriz;
    private GestorConexionesRutas rutas;

    public Grafo(GestorConexionesRutas rutas) {
        this.rutas = rutas;

    }

    public void construirGrafo() {
        int tamaño = rutas.getGestorZonas().getZonas().size();
        matriz = new int[tamaño][tamaño];

        for (Conexiones c : rutas.getConexiones()) {
            if (c.isHabilitada()) {
                int origen = c.getOrigen().getIdZona();
                int destino = c.getDestino().getIdZona();
                matriz[origen][destino] = 1;

            }

        }

    }

    /*
     * public boolean hayConexion(int origen, int destino) {
     * 
     * boolean existeConexion = matriz[origen][destino] == 1;
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

    public int[][] getMatriz() {
        return matriz;
    }

}

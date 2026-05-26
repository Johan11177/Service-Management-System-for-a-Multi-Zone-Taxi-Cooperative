package pooproyect.Zona;

public class GrafoTiempo extends Grafo {

    public GrafoTiempo(GestorConexionesRutas rutas) {
        super(rutas);
        construirGrafo();
    }

    @Override
    public void construirGrafo() {
        int tamaño = rutas.getGestorZonas().getZonas().size();
        matriz = new int[tamaño][tamaño];

        for (Conexiones c : rutas.getConexiones()) {
            if (c.isHabilitada()) {
                int origen = c.getOrigen().getIdZona();
                int destino = c.getDestino().getIdZona();
                int tiempo = c.getTiempoEstimado();
                matriz[origen][destino] = tiempo;
                matriz[destino][origen] = tiempo;
            }
        }
    }
}
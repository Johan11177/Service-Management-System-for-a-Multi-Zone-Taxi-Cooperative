package pooproyect.Zona;

public class GrafoKilometros extends Grafo {

    public GrafoKilometros(GestorConexionesRutas rutas) {
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
                int kilometros = (int) c.getKiloMetros();
                matriz[origen][destino] = kilometros;
                matriz[destino][origen] = kilometros;
            }
        }
    }
}
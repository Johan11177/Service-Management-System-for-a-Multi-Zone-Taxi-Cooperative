package pooproyect.Tarifas;

import pooproyect.Zona.Dijkstra;

public class FabricaTarifasNormal extends FabricaTarifas {

    private Dijkstra dijkstra;

    public FabricaTarifasNormal(Dijkstra dijkstra) {
        this.dijkstra = dijkstra;
    }

    @Override
    public Tarifa crearTarifa(String tipo) {
        if (tipo.equals("Estandar")) {
            return new TarifaEstandar(dijkstra);
        }
        return null;
    }

}

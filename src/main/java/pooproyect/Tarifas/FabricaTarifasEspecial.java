package pooproyect.Tarifas;

import pooproyect.Zona.Dijkstra;

public class FabricaTarifasEspecial extends FabricaTarifas {

    private Dijkstra dijkstra;

    public FabricaTarifasEspecial(Dijkstra dijkstra) {
        this.dijkstra = dijkstra;
    }

    @Override
    //Crear tarifas para servicios especiales como Taxi con Baúl y Taxi para Mascotas
    public Tarifa crearTarifa(String tipo) {

        if (tipo.equals("Baul")) {
            return new TarifaBaul(dijkstra);
        } else if (tipo.equals("Mascotas")) {
            return new TarifaMascotas(dijkstra);
        }
        return null;
    }

}

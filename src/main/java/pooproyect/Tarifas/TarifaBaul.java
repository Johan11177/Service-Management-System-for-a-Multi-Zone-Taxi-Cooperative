package pooproyect.Tarifas;

import pooproyect.Zona.Dijkstra;

public class TarifaBaul implements Tarifa {
    private Dijkstra dijkstra;
    private double CostoBase = 5000;
    private double CostoPorKm = 1000;
    private double CostoAdicionalBaul = 2000; 

    public TarifaBaul(Dijkstra dijkstra, double costoBase, double costoPorKm, double costoAdicionalBaul) {
        this.dijkstra = dijkstra;
        CostoBase = costoBase;
        CostoPorKm = costoPorKm;
        CostoAdicionalBaul = costoAdicionalBaul;
    }

    public TarifaBaul() {
    }

    @Override
    public double CalcularTarifa(int origen, int destino) {
        System.out.println("Calculando tarifa para baúl");

        int km = dijkstra.calcularRutaMasCorta(origen, destino);
        return CostoBase + (km * CostoPorKm) + CostoAdicionalBaul; 

    }

}

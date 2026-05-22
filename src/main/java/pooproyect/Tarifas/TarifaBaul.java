package pooproyect.Tarifas;

import pooproyect.Zona.Dijkstra;

public class TarifaBaul implements Tarifa {
    private Dijkstra dijkstra;
    private double CostoBase = 5000;
    private double CostoPorKm = 1000;
    private double CostoAdicionalBaul = 2000;
    private double Costofinal; 

    public TarifaBaul(Dijkstra dijkstra, double costoBase, double costoPorKm, double costoAdicionalBaul) {
        this.dijkstra = dijkstra;
        CostoBase = costoBase;
        CostoPorKm = costoPorKm;
        CostoAdicionalBaul = costoAdicionalBaul;
        Costofinal = CostoBase + CostoAdicionalBaul;
    }

    public TarifaBaul() {
    }

    @Override
    public double CalcularTarifa(int origen, int destino) {
        System.out.println("Calculando tarifa para baúl");

        int km = dijkstra.calcularRutaMasCorta(origen, destino);
        Costofinal = CostoBase + (km * CostoPorKm) + CostoAdicionalBaul;
        return Costofinal;
    }

}
        




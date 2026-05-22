package pooproyect.Tarifas;

import pooproyect.Zona.Dijkstra;

public class TarifaEstandar implements Tarifa {
    private Dijkstra dijkstra;
    private double CostoBase = 5000;
    private double CostoPorKm = 1000;
    private double Costofinal;

    public TarifaEstandar(Dijkstra dijkstra, double costoBase, double costoPorKm) {
        this.dijkstra = dijkstra;
        CostoBase = costoBase;
        CostoPorKm = costoPorKm;
        Costofinal = CostoBase;
    }

    public TarifaEstandar() {
    }

    @Override
    public double CalcularTarifa(int origen, int destino) {
        int Km = dijkstra.calcularRutaMasCorta(origen, destino);
        System.out.println("Calculando tarifa estándar");
        Costofinal = CostoBase + (Km * CostoPorKm);
        return Costofinal;
    }

}

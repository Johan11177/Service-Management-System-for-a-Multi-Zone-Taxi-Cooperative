package pooproyect.Tarifas;

import pooproyect.Zona.Dijkstra;

public class TarifaMascotas implements Tarifa {
private Dijkstra dijkstra;
private double CostoBase = 5000;
private double CostoPorKm = 1000;
private double CostoAdicionalMascotas = 3000;
private double Costofinal;
    
public TarifaMascotas(Dijkstra dijkstra, double costoBase, double costoPorKm, double costoAdicionalMascotas) {
    this.dijkstra = dijkstra;
    CostoBase = costoBase;
    CostoPorKm = costoPorKm;
    CostoAdicionalMascotas = costoAdicionalMascotas;
    Costofinal = CostoBase + CostoAdicionalMascotas;
}

public TarifaMascotas() {
}

    @Override
    public double CalcularTarifa(int origen, int destino) {
        System.out.println("Calculando tarifa para mascotas");

        int km = dijkstra.calcularRutaMasCorta(origen, destino);
        return Costofinal =(km * CostoPorKm); 
    }

}

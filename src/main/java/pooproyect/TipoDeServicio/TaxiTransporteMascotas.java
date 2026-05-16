package pooproyect.TipoDeServicio;

public class TaxiTransporteMascotas extends Taxi {

    private double RecargoPorMascota = 3000; // Recargo por mascota

    public TaxiTransporteMascotas(double TarifaMinima, double CostoPorDistancia, double RecargoPorMascota) {
        super(TarifaMinima, CostoPorDistancia);
        this.RecargoPorMascota = RecargoPorMascota;
    }

    @Override
    double TarifaEstimada(double kilometros) {
      double incremento = TarifaMinima +(CostoPorDistancia * kilometros) + RecargoPorMascota;
        return incremento;
    }

}

package pooproyect.TipoDeServicio;

public class TaxiConBaul extends Taxi {
    private double RecargoPorBaul = 1500; // Recargo por baúl

    public TaxiConBaul(double TarifaMinima, double CostoPorDistancia) {
        super(TarifaMinima, CostoPorDistancia);
    }

    @Override
    double TarifaEstimada(double kilometros) {
        double incremento = TarifaMinima +(CostoPorDistancia * kilometros) + RecargoPorBaul;
        return incremento;
    }

}

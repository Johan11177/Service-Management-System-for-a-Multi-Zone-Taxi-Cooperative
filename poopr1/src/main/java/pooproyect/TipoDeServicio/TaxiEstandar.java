package pooproyect.TipoDeServicio;

public class TaxiEstandar extends TipoDeServicio {
 
    public TaxiEstandar(double TarifaMinima, double CostoPorDistancia) {
        super(TarifaMinima, CostoPorDistancia);
    }

    @Override
     double TarifaEstimada(double kilometros) {
     double incremento = TarifaMinima +(CostoPorDistancia * kilometros);
        return incremento;
    }


}

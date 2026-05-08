package pooproyect.TipoDeServicio;

public abstract class TipoDeServicio {

    double TarifaMinima = 5000;
    double CostoPorDistancia = 1500;//Costo por kilometro adicional 1500

    public TipoDeServicio (double TarifaMinima, double CostoPorDistancia) {
        this.TarifaMinima = TarifaMinima;
        this.CostoPorDistancia = CostoPorDistancia;
    }

    abstract double TarifaEstimada(double kilometros);
    
}



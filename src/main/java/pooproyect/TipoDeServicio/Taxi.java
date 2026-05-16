package pooproyect.TipoDeServicio;

public abstract class Taxi {

    double TarifaMinima = 5000;
    double CostoPorDistancia = 1500;//Costo por kilometro adicional 1500

    public Taxi (double TarifaMinima, double CostoPorDistancia) {
        this.TarifaMinima = TarifaMinima;
        this.CostoPorDistancia = CostoPorDistancia;
    }

    abstract double TarifaEstimada(double kilometros);
    
}



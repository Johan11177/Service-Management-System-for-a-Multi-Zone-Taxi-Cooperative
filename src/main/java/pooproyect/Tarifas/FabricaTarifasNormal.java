package pooproyect.Tarifas;

public class FabricaTarifasNormal extends FabricaTarifas {

    @Override
    public Tarifa crearTarifa(String tipo) {
        if (tipo.equals("Estandar")) {
            return new TarifaEstandar(null, 0, 0);
        }
        return null;
    }

}



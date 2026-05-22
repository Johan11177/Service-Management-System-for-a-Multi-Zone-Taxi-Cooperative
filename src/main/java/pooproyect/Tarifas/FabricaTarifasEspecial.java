package pooproyect.Tarifas;

public class FabricaTarifasEspecial extends FabricaTarifas {

    @Override
    public Tarifa crearTarifa(String tipo) {
        if (tipo.equals("Baul")) {
            return new TarifaBaul();
        } else if (tipo.equals("Mascotas")) {
            return new TarifaMascotas();
        }
        return null;
    }

}

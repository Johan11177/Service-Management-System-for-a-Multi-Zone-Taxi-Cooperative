package pooproyect.Zona;

import java.util.ArrayList;

public class GestorZonas {

    private ArrayList<Zonas> zonas;
    

   
    

    public GestorZonas() {
        zonas = new ArrayList<>();
    }

    public void agregarZonas() {
        zonas.add(new Zonas(0, "Aeropuerto"));
        zonas.add(new Zonas(1, "Rodadero"));
        zonas.add(new Zonas(2, "Centro Historico"));
        zonas.add(new Zonas(3, "Universidad"));
        zonas.add(new Zonas(4, "Mamatoco"));
        zonas.add(new Zonas(5, "Taganga"));
    }

    public ArrayList<Zonas> getZonas() {
        return zonas;
    }

}

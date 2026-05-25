package pooproyect.Zona;

import java.util.ArrayList;

public class GestorZonas {

    private ArrayList<Zonas> zonas;

    public GestorZonas() {
        zonas = new ArrayList<>();
        AgregarZonas();
    }

    public void AgregarZonas() {
        zonas.add(new Zonas(0, "Aeropuerto"));
        zonas.add(new Zonas(1, "Rodadero"));
        zonas.add(new Zonas(2, "Centro Historico"));
        zonas.add(new Zonas(3, "Universidad"));
        zonas.add(new Zonas(4, "Mamatoco"));
        zonas.add(new Zonas(5, "Taganga"));
    }

    public void MostrarZonas() {
        if (zonas.isEmpty()) {
            System.out.println("No hay zonas registradas.");
            return;
        }

        System.out.println("\n=== Zonas Disponibles ===");

        for (Zonas zona : zonas) {
            System.out.println("[" + (zona.getIdZona()+1) + "] " + zona.getNombreZona());
        }
        System.out.println();
    }

    public ArrayList<Zonas> getZonas() {
        return zonas;
    }
}
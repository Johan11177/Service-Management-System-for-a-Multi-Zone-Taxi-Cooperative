package pooproyect.Zona;

import java.util.ArrayList;

public class GestorConexionesRutas {

        private ArrayList<Conexiones> conexiones;
        private GestorZonas gestorZonas;

        public GestorConexionesRutas(GestorZonas gestorZonas) {

                this.gestorZonas = gestorZonas;
                this.conexiones = new ArrayList<>();

        }

        public void agregarConexiones() {

        conexiones.add(new Conexiones(
                gestorZonas.getZonas().get(0), // Aeropuerto
                gestorZonas.getZonas().get(1), // Rodadero
                true,
                15));

        conexiones.add(new Conexiones(
                gestorZonas.getZonas().get(1), // Rodadero
                gestorZonas.getZonas().get(2), // Centro Histórico
                true,
                12));

        conexiones.add(new Conexiones(
                gestorZonas.getZonas().get(2), // Centro Histórico
                gestorZonas.getZonas().get(5), // Taganga
                true,
                18));

        conexiones.add(new Conexiones(
                gestorZonas.getZonas().get(2), // Centro Histórico
                gestorZonas.getZonas().get(3), // Universidad
                true,
                10));

        conexiones.add(new Conexiones(
                gestorZonas.getZonas().get(3), // Universidad
                gestorZonas.getZonas().get(4), // Mamatoco
                true,
                6));        
        }

        public ArrayList<Conexiones> getConexiones() {

                return conexiones;

        }

    public GestorZonas getGestorZonas() {
        return gestorZonas;
    }
}

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
                                gestorZonas.getZonas().get(0), // aeropuerto a
                                gestorZonas.getZonas().get(1), // rodadero
                                true));

                conexiones.add(new Conexiones(
                                gestorZonas.getZonas().get(1), // rodadero a
                                gestorZonas.getZonas().get(2), // CentroHistorico
                                true));

                conexiones.add(new Conexiones(
                                gestorZonas.getZonas().get(2), // CentroHistorico a
                                gestorZonas.getZonas().get(5), // Taganga
                                true));

                conexiones.add(new Conexiones(
                                gestorZonas.getZonas().get(2), // CentroHistorico a
                                gestorZonas.getZonas().get(3), // Universidad
                                true));

                conexiones.add(new Conexiones(
                                gestorZonas.getZonas().get(3), // Universidad a
                                gestorZonas.getZonas().get(4), // mamatoco
                                true));

        }

        public ArrayList<Conexiones> getConexiones() {

                return conexiones;

        }

    public GestorZonas getGestorZonas() {
        return gestorZonas;
    }
}

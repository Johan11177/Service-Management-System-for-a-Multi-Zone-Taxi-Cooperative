package pooproyect.Solicitudes;

import java.util.ArrayList;

import pooproyect.Conductores.Conductor;
import pooproyect.Conductores.GestorConductores;
import pooproyect.Conductores.PersistenciaConductores;
import pooproyect.Persistencia.PersistenciaSolicitudes;
import pooproyect.Tarifas.FabricaTarifas;
import pooproyect.Tarifas.FabricaTarifasEspecial;
import pooproyect.Tarifas.FabricaTarifasNormal;
import pooproyect.Tarifas.Tarifa;
import pooproyect.Zona.Dijkstra;
import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;
import pooproyect.Zona.GrafoTiempo;

public class SolicitudEnEspera {
    private ArrayList<Solicitudes> cola;
    private ArrayList<Solicitudes> historial;
    private ArrayList<MotivosCancelacion> motivos;
    private GestorConductores gestorConductores;
    private GestorConexionesRutas gestorConexiones;
    private GestorZonas gestorZonas;
    private int siguienteId;

    public SolicitudEnEspera() {
        cola = new ArrayList<>();
        historial = new ArrayList<>();
        motivos = new ArrayList<>();
        siguienteId = 1;
    }

    public SolicitudEnEspera(GestorConductores gestorConductores,
                             GestorConexionesRutas gestorConexiones,
                             GestorZonas gestorZonas) {
        this();
        this.gestorConductores = gestorConductores;
        this.gestorConexiones = gestorConexiones;
        this.gestorZonas = gestorZonas;
    }

    public void setGestorConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
    }

    public int generarIdSolicitud() {
        return siguienteId++;
    }

    public void mostrarHistorial() {
        System.out.println("\n");
        if (historial.isEmpty()) {
            System.out.println("No hay solicitudes atendidas en el historial.");
            return;
        }

        System.out.println("=== HISTORIAL DE SOLICITUDES ATENDIDAS ===");
        for (Solicitudes s : historial) {
            System.out.println("\nID: " + s.getIDsolicitud());
            System.out.println("Cliente: " + s.getNombreCliente());
            System.out.println("De: " + s.getZonaDeorigen() + " → A: " + s.getZonaDestino());
            System.out.println("Servicio: " + s.getTipoDeServicio());
            System.out.println("Conductor: " + s.getNombreConductor() + " (ID: " + s.getIdConductor() + ")");
            System.out.println("Placa: " + s.getPlacaVehiculo());
            System.out.println("Tarifa: $" + s.getTarifaEstimada());
            System.out.println("Tiempo: " + s.getTiempoEstimado() + " min");
            System.out.println("Estado: " + s.getEstadoSolicitud());
            System.out.println("---");
        }
    }

    public void mostrarSolicitudesAtendidas() {
        System.out.println("\n\n\n");
        ArrayList<Solicitudes> atendidas = new ArrayList<>();
        for (Solicitudes s : historial) {
            if (s.getEstadoSolicitud().equals("ATENDIDA")) {
                atendidas.add(s);
            }
        }

        if (atendidas.isEmpty()) {
            System.out.println("No tienes solicitudes atendidas.");
            return;
        }

        System.out.println("=== MIS SOLICITUDES ATENDIDAS ===");
        for (Solicitudes s : atendidas) {
            System.out.println("\nID: " + s.getIDsolicitud());
            System.out.println("  De: " + s.getZonaDeorigen() + " → A: " + s.getZonaDestino());
            System.out.println("  Conductor: " + s.getNombreConductor());
            System.out.println("  Placa: " + s.getPlacaVehiculo());
            System.out.println("  Tarifa: $" + s.getTarifaEstimada());
            System.out.println("  Tiempo estimado: " + s.getTiempoEstimado() + " min");
        }
    }

    public void setGestorConexiones(GestorConexionesRutas gestorConexiones) {
        this.gestorConexiones = gestorConexiones;
    }

    public void setGestorZonas(GestorZonas gestorZonas) {
        this.gestorZonas = gestorZonas;
    }

    public void agregarSolicitud(Solicitudes s) {
        agregarSolicitud(s, true);
    }

    public void agregarSolicitud(Solicitudes s, boolean persistir) {
        cola.add(s);
        if (persistir) {
            PersistenciaSolicitudes.guardarCola(cola);
        }
    }

    public Solicitudes atenderSolicitud(String operadorNombre, int operadorId) {
        System.out.println("\n");
        if (cola.isEmpty()) {
            System.out.println("No hay solicitudes");
            return null;
        }

        if (gestorConductores == null || gestorConexiones == null || gestorZonas == null) {
            System.out.println("La solicitud no puede ser atendida por el momento," +
            "gracias por usar nuestros servicios ");
            return null;
        }

        Solicitudes solicitud = cola.get(0);
        String tipoServicio = solicitud.getTipoDeServicio();

        Conductor conductor = gestorConductores.encontrarConductorDisponible(tipoServicio);
        if (conductor == null) {
            System.out.println("No hay conductores disponibles habilitados para el servicio: " + tipoServicio);
            return null;
        }

        int origenId = buscarIdZona(solicitud.getZonaDeorigen());
        int destinoId = buscarIdZona(solicitud.getZonaDestino());

        if (origenId == -1 || destinoId == -1) {
            System.out.println("Lo sentimos, Zonas invalidas: origen=" + solicitud.getZonaDeorigen()
                    + ", destino=" + solicitud.getZonaDestino());
            return null;
        }

        GrafoTiempo grafoTiempo = new GrafoTiempo(gestorConexiones);
        Dijkstra dijkstra = new Dijkstra(grafoTiempo.getMatriz());
        int tiempoEstimado = dijkstra.calcularRutaMasCorta(origenId, destinoId);

        if (tiempoEstimado == Integer.MAX_VALUE) {
            System.out.println("No existe conectividad vial habilitada entre " + solicitud.getZonaDeorigen()
                    + " y " + solicitud.getZonaDestino() + ".");
            return null;
        }

        Tarifa tarifa = crearTarifaPorTipo(tipoServicio, dijkstra);
        if (tarifa == null) {
            System.out.println("No se pudo calcular la tarifa para el tipo de servicio: " + tipoServicio);
            return null;
        }

        double costoEstimado = tarifa.CalcularTarifa(origenId, destinoId);
        conductor.setDisponible(false);
        PersistenciaConductores.guardar(gestorConductores.getConductores());
        
        // Guardar información del conductor en la solicitud
        solicitud.setNombreConductor(conductor.getNombre());
        solicitud.setIdConductor(String.valueOf(conductor.getID()));
        solicitud.setPlacaVehiculo(conductor.getVehiculo().getPlaca());
        solicitud.setTarifaEstimada(costoEstimado);
        solicitud.setTiempoEstimado(tiempoEstimado);
        
        solicitud.setEstadoSolicitud("ATENDIDA");
        cola.remove(0);
        historial.add(solicitud);
        PersistenciaSolicitudes.guardarCola(cola);
        PersistenciaSolicitudes.guardarHistorial(historial);

        System.out.println("---SOLICITUD ATENDIDA ---");
        System.out.println("Operador: " + operadorNombre + " (ID " + operadorId + ")");
        System.out.println("Placa del vehículo: " + conductor.getVehiculo().getPlaca());
        System.out.println("Nombre del conductor: " + conductor.getNombre());
        System.out.println("Identificación del conductor: " + conductor.getID());
        System.out.println("Tarifa estimada del servicio: $" + costoEstimado);
        System.out.println("Tiempo estimado de llegada: " + tiempoEstimado + " minutos");
        System.out.println("-----------------------------");

        return solicitud;
    }

    public int buscarIdZona(String nombreZona) {
        for (int i = 0; i < gestorZonas.getZonas().size(); i++) {
            if (gestorZonas.getZonas().get(i).getNombreZona().equalsIgnoreCase(nombreZona)) {
                return i;
            }
        }
        return -1;
    }

    public Tarifa crearTarifaPorTipo(String tipoServicio, Dijkstra dijkstra) {
        String servicioClave = null;

        if (tipoServicio.equalsIgnoreCase("Taxi Estandar")) {
            servicioClave = "Estandar";
        } else if (tipoServicio.equalsIgnoreCase("Taxi con Baul")) {
            servicioClave = "Baul";
        } else if (tipoServicio.equalsIgnoreCase("Taxi Transporte Mascotas")) {
            servicioClave = "Mascotas";
        }

        if (servicioClave == null) {
            return null;
        }

        FabricaTarifas fabrica;
        if (servicioClave.equals("Estandar")) {
            fabrica = new FabricaTarifasNormal(dijkstra);
        } else {
            fabrica = new FabricaTarifasEspecial(dijkstra);
        }

        return fabrica.crearTarifa(servicioClave);
    }

    public void cancelarSolicitud(int id, MotivosCancelacion motivoRegistro) {
        System.out.println("\n\n\n");
        for (int i = 0; i < cola.size(); i++) {
            Solicitudes s = cola.get(i);

            if (s.getIDsolicitud() == id) {
                s.setEstadoSolicitud("CANCELADA");
                s.setMotivoCancelacion(motivoRegistro.getMotivo());
                cola.remove(i);
                motivos.add(motivoRegistro);

                PersistenciaSolicitudes.guardarCola(cola);
                System.out.println("Solicitud " + id + " cancelada");
                System.out.println(" Motivo: " + motivoRegistro.getMotivo());
                return;
            }
        }

        System.out.println("No se encontró la solicitud " + id);
    }

    public ArrayList<Solicitudes> getCola() {
        return cola;
    }

    public ArrayList<MotivosCancelacion> getMotivos() {
        return motivos;
    }

    public ArrayList<Solicitudes> getHistorial() {
        return historial;
    }

    public void agregarAlHistorial(Solicitudes s) {
        historial.add(s);
    }

    public int getSiguienteId() {
        return siguienteId;
    }
    public void setSiguienteId(int id) {
        this.siguienteId = id;
    }
}//clase terminada faltan persistencias, ya hoy terminamos

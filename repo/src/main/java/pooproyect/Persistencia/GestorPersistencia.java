package pooproyect.Persistencia;

import pooproyect.Solicitudes.SolicitudEnEspera;
import pooproyect.Zona.GestorConexionesRutas;

import java.util.ArrayList;

/**
 * Coordina la carga y guardado de todos los subsistemas al iniciar/cerrar.
 * Principio SRP: orquesta persistencia, no implementa lógica de negocio.
 * Principio DIP: depende de abstracciones (listas y gestores), no de detalles de I/O.
 */
public class GestorPersistencia {

    /**
     * Carga el estado persistido al arrancar el sistema.
     */
    public static void cargarTodo(SolicitudEnEspera solicitudesEnEspera,
                                   GestorConexionesRutas gestorConexiones) {
        // Cargar cola de solicitudes pendientes
        ArrayList solicitudesCola = PersistenciaSolicitudes.cargarCola();
        int maxId = 0;
        for (Object obj : solicitudesCola) {
            pooproyect.Solicitudes.Solicitudes solicitud =
                    (pooproyect.Solicitudes.Solicitudes) obj;
            solicitudesEnEspera.agregarSolicitud(solicitud, false);
            maxId = Math.max(maxId, solicitud.getIDsolicitud());
        }

        // Cargar historial
        ArrayList historialCargado = PersistenciaSolicitudes.cargarHistorial();
        for (Object obj : historialCargado) {
            pooproyect.Solicitudes.Solicitudes solicitud =
                    (pooproyect.Solicitudes.Solicitudes) obj;
            solicitudesEnEspera.agregarAlHistorial(solicitud);
            maxId = Math.max(maxId, solicitud.getIDsolicitud());
        }

        if (maxId > 0) {
            solicitudesEnEspera.setSiguienteId(maxId + 1);
        }

        // Restaurar estado de la red vial
        PersistenciaRedVial.cargarEstado(gestorConexiones.getConexiones());
    }

    /**
     * Guarda el estado completo antes de cerrar.
     */
    public static void guardarTodo(SolicitudEnEspera solicitudesEnEspera,
                                    GestorConexionesRutas gestorConexiones) {
        PersistenciaSolicitudes.guardarCola(solicitudesEnEspera.getCola());
        PersistenciaSolicitudes.guardarHistorial(solicitudesEnEspera.getHistorial());
        PersistenciaRedVial.guardarEstado(gestorConexiones.getConexiones());
        System.out.println("[Sistema] Estado guardado correctamente.");
    }
}

package pooproyect.operador;

import pooproyect.Solicitudes.Solicitudes;
import pooproyect.Usuario.Conductor;
import pooproyect.Solicitudes.SolicitudEnEspera;
import java.util.ArrayList;

public class Operador {
    
    private String nombre;
    private int id;
    private boolean disponible;
    private SolicitudEnEspera solicitudesEnEspera;
    private Conductor [] conductores;

    public Operador(String nombre, int id, SolicitudEnEspera solicitudesEnEspera) {
        this.nombre = nombre;
        this.id = id;
        this.disponible = true;
        this.solicitudesEnEspera = solicitudesEnEspera;
        this.conductores = new Conductor[10]; 
    }


    public void AtenderSiguiente(){
        solicitudesEnEspera.atenderSolicitud();
       
    }
    
    public void CancelarSolicitud(int idSolicitud){
        solicitudesEnEspera.cancelarSolicitud(idSolicitud);

    }

    public ArrayList<Solicitudes> ListarSolicitudesEnEspera(){
        ArrayList<Solicitudes> lista = solicitudesEnEspera.getCola();
        if (lista.isEmpty()) {
            System.out.println("No hay solicitudes en espera.");
        } else {
            System.out.println("=== Solicitudes en espera ===");
            lista.forEach(s -> System.out.println("  " + s));
        }
        return lista;

    }
        public void AgregarConductor(Conductor conductor) {
            for (int i = 0; i < conductores.length; i++) {
                if (conductores[i] == null) {
                    conductores[i] = conductor;
                    System.out.println("Conductor agregado: " + conductor.getNombre());
                    return;
                }
            }
            System.out.println("No se pueden agregar más conductores. Capacidad máxima alcanzada.");
        }
 }
 

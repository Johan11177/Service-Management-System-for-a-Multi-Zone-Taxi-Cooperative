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

    public Operador(String nombre, int id) {
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

    public void HabilitarConexion(){
        this.disponible = true;
    }

    public void DeshabilitarConexion(){
        this.disponible = false;
    }
 }
 


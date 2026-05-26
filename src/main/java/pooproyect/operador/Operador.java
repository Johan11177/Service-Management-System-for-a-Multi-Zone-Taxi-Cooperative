package pooproyect.operador;

import pooproyect.Solicitudes.Solicitudes;
import pooproyect.Solicitudes.MotivosCancelacion;
import pooproyect.Solicitudes.SolicitudEnEspera;
import pooproyect.Main.EntradaUsuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Operador {
    
    private String nombre;
    private int id;
    private SolicitudEnEspera solicitudesEnEspera;
    private Scanner sc = EntradaUsuario.get();

    public Operador(String nombre, int id, SolicitudEnEspera solicitudesEnEspera) {
        this.nombre = nombre;
        this.id = id;
        this.solicitudesEnEspera = solicitudesEnEspera;
    }
    

    public void AtenderSiguiente(){
        solicitudesEnEspera.atenderSolicitud(this.nombre, this.id);
    }
    
    public void CancelarSolicitud(int idSolicitud){
        System.out.println("\n");
        System.out.print("Ingrese el motivo de la cancelación (por operador): ");
        String motivo = sc.nextLine();
        MotivosCancelacion motivoRegistro = new MotivosCancelacion(idSolicitud, motivo, this.nombre);
        solicitudesEnEspera.cancelarSolicitud(idSolicitud, motivoRegistro);
    }

    public ArrayList<Solicitudes> ListarSolicitudesEnEspera(){
        System.out.println("\n");
        ArrayList<Solicitudes> lista = solicitudesEnEspera.getCola();
        if (lista.isEmpty()) {
            System.out.println("No hay solicitudes en espera.");
        } else {
            System.out.println("=== Solicitudes en espera ===");
            lista.forEach(s -> System.out.println("  " + s));
        }
        return lista;

    }
    
    public String getNombre() {
        return nombre;
    }

    public int getID() {
        return id;
    }
}


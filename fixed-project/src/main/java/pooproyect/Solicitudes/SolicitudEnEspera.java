package pooproyect.Solicitudes;

import java.util.ArrayList;
import java.util.Scanner;

import pooproyect.Main.EntradaUsuario;

public class SolicitudEnEspera {
    private Scanner sc = EntradaUsuario.get();
    private ArrayList<Solicitudes> cola;

    public SolicitudEnEspera() {
        cola = new ArrayList<>();
    }

    public void agregarSolicitud(Solicitudes s) {
        cola.add(s);
    }

    public Solicitudes atenderSolicitud() {
        if (cola.isEmpty()) {
            System.out.println("No hay solicitudes");
            return null;
        }

        Solicitudes s = cola.remove(0);
        s.setEstadoSolicitud("ATENDIDA");
        return s;
    }

    public void cancelarSolicitud(int id) {
        System.out.println("Motivo de cancelacion: ");
        String motivo = sc.nextLine();

        for (int i = 0; i < cola.size(); i++) {
            Solicitudes s = cola.get(i);

            if (s.getIDsolicitud() == id) {
                s.setEstadoSolicitud("CANCELADA");
                cola.remove(i);

                System.out.println("Solicitud cancelada");
                System.out.println("Motivo: " + motivo);
                return;
            }
        }

        System.out.println("No se encontro la solicitud");
    }

    public ArrayList<Solicitudes> getCola() {
        return cola;
    }
}

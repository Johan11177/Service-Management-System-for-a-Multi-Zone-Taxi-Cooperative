package pooproyect.Menus;

import java.util.Scanner;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Solicitudes.CrearSolicitud;
import pooproyect.Solicitudes.MotivosCancelacion;
import pooproyect.Solicitudes.SolicitudEnEspera;

public class MenuCliente implements InterfaceMenu {
    private CrearSolicitud s;
    private SolicitudEnEspera solicitudesEnEspera;
    private Scanner sc = EntradaUsuario.get();

    public MenuCliente(CrearSolicitud s, SolicitudEnEspera solicitudesEnEspera) {
        this.s = s;
        this.solicitudesEnEspera = solicitudesEnEspera;
    }
    
    @Override
    public void CrearMenu() {
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("\n\n");
            System.out.println("==Menu Cliente==");
            System.out.println("");
            System.out.println("1. Hacer Solicitud");
            System.out.println("2. Ver mis solicitudes atendidas");
            System.out.println("3. Cancelar Solicitud");
            System.out.println("4. Volver");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> { 
                    s.crearSolicitud(); 
                }
                case 2 -> {
                    solicitudesEnEspera.mostrarSolicitudesAtendidas();
                }
                case 3 -> {
                    System.out.print("Ingrese el ID de la solicitud a cancelar: ");
                    int idSolicitud = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingrese el motivo de la cancelacion: ");
                    String motivo = sc.nextLine();
                    String nombreCliente = s.getNombreClienteActual();
                    MotivosCancelacion motivoRegistro = new MotivosCancelacion(idSolicitud, motivo, nombreCliente);
                    solicitudesEnEspera.cancelarSolicitud(idSolicitud, motivoRegistro);
                }
                case 4 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }

    public CrearSolicitud getS() {
        return s;
    }
}

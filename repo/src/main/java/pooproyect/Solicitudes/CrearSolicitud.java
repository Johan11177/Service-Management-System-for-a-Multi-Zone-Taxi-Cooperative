package pooproyect.Solicitudes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import pooproyect.Main.EntradaUsuario;
import pooproyect.Usuario.Cliente;
import pooproyect.Zona.Conexiones;
import pooproyect.Zona.GestorZonas;

public class CrearSolicitud {
    private GestorZonas gestorZonas;
    private Conexiones conexiones;
    private Cliente cliente;
    private SolicitudEnEspera solicitudEnEspera;
    private Scanner sc = EntradaUsuario.get();
    private String nombreClienteActual;

    public CrearSolicitud(Conexiones conexiones, GestorZonas gestorZonas, Cliente cliente, SolicitudEnEspera solicitudEnEspera) {
        this.conexiones = conexiones;
        this.gestorZonas = gestorZonas;
        this.cliente = cliente;
        this.solicitudEnEspera = solicitudEnEspera;
        this.nombreClienteActual = "";
    }

    public void crearSolicitud() {
        System.out.println("\n\n\n");
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        cliente.setNombre(nombre);
        this.nombreClienteActual = nombre;

        // Tipo de servicio
        System.out.println("\nSeleccione el tipo de servicio:");
        System.out.println("  1. Estandar");
        System.out.println("  2. Baul / Parrilla (equipaje voluminoso)");
        System.out.println("  3. Mascotas");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        while (op < 1 || op > 3) {
            System.out.println("Ingrese una opcion correcta");
            op = sc.nextInt();
        }
        sc.nextLine();
        String tipoServicio;

        if (op == 1) {
            tipoServicio = "Taxi Estandar";
        } else if (op == 2) {
            tipoServicio = "Taxi con Baul";
        } else {
            tipoServicio = "Taxi Transporte Mascotas";
        }

        gestorZonas.MostrarZonas();

        // Origen
        System.out.print("Seleccione origen: ");
        int origen = sc.nextInt();
        while (origen < 1 || origen > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            origen = sc.nextInt();
        }
        sc.nextLine();
        conexiones.setIdOrigen(origen - 1);

        // Destino
        System.out.print("Seleccione destino: ");
        int destino = sc.nextInt();
        while (destino < 1 || destino > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            destino = sc.nextInt();
        }
        sc.nextLine();
        conexiones.setIdDestino(destino - 1);

        String nombreOrigen = gestorZonas.getZonas().get(origen - 1).getNombreZona();
        String nombreDestino = gestorZonas.getZonas().get(destino - 1).getNombreZona();

        int idSolicitud = solicitudEnEspera.generarIdSolicitud();
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        Solicitudes solicitud = new Solicitudes(idSolicitud, nombreOrigen, nombreDestino, tipoServicio, hora, fecha);
        solicitud.setNombreCliente(nombre);
        solicitudEnEspera.agregarSolicitud(solicitud);

        System.out.println("\n Solicitud registrada con ID: " + idSolicitud);
        System.out.println(" Tipo de servicio: " + tipoServicio);
        System.out.println(" Zona de origen: " + nombreOrigen);
        System.out.println(" Zona de destino: " + nombreDestino);
    }

    public String getNombreClienteActual() {
        return nombreClienteActual;
    }
}

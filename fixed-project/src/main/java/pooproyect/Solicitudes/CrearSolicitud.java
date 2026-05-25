package pooproyect.Solicitudes;

import java.util.Scanner;

import pooproyect.Main.EntradaUsuario;
import pooproyect.Usuario.Cliente;
import pooproyect.Zona.Conexiones;
import pooproyect.Zona.GestorZonas;

public class CrearSolicitud {
    private GestorZonas gestorZonas;
    private Conexiones conexiones;
    private Cliente cliente;
    private Scanner sc = EntradaUsuario.get();

    public CrearSolicitud(Conexiones conexiones, GestorZonas gestorZonas, Cliente cliente) {
        this.conexiones = conexiones;
        this.gestorZonas = gestorZonas;
        this.cliente = cliente;
    }

    public void crearSolicitud() {
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        cliente.setNombre(nombre);

        gestorZonas.MostrarZonas();

        System.out.print("Seleccione origen: ");
        int origen = sc.nextInt();
        conexiones.setIdOrigen(origen - 1);

        while (origen < 1 || origen > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            origen = sc.nextInt();
        }

        System.out.print("Seleccione destino: ");
        int destino = sc.nextInt();
        conexiones.setIdDestino(destino - 1);

        while (destino < 1 || destino > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            destino = sc.nextInt();
        }
    }
}

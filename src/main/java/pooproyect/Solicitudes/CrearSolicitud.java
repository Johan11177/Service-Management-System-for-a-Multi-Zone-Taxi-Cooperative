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
        String tipoServicio;

        if (op== 1) {
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
        conexiones.setIdOrigen(origen - 1);
        // ajusta el indice para que sea igual al del arreglo

        // Destino
        System.out.print("Seleccione destino: ");
        int destino = sc.nextInt();
        while (destino < 1 || destino > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            destino = sc.nextInt();
        }
        conexiones.setIdDestino(destino - 1);
        // ajusta el indice para que sea igual al del arreglo :)
    }
}

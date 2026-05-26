package pooproyect.Menus;

import java.util.Scanner;

import pooproyect.Conductores.GestorConductores;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Menus.MenuOperadores.MenuGestionConductores;
import pooproyect.Menus.MenuOperadores.MenuGestionVial;
import pooproyect.Menus.MenuOperadores.MenuOperedores;
import pooproyect.Reportes.GestorReportes;
import pooproyect.Solicitudes.CrearSolicitud;
import pooproyect.Solicitudes.SolicitudEnEspera;
import pooproyect.Usuario.Cliente;
import pooproyect.Zona.Conexiones;
import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;
import pooproyect.operador.Operador;

public class SystemUI {

    private Scanner sc = EntradaUsuario.get();
    private MenuOperedores menuOperadores;
    private MenuConductores menuConductores;
    private MenuCliente menuCliente;

 public SystemUI() {

    GestorZonas gestorZonas = new GestorZonas();
    GestorConexionesRutas gestorConexiones = new GestorConexionesRutas(gestorZonas);
    GestorConductores gestorConductores = new GestorConductores();

    SolicitudEnEspera solicitudesEnEspera =
            new SolicitudEnEspera(gestorConductores, gestorConexiones, gestorZonas);

    Cliente cliente = new Cliente("", "CLI-1");

    Conexiones conexiones = new Conexiones(0, 0);

    CrearSolicitud crearSolicitud =
            new CrearSolicitud(conexiones, gestorZonas, cliente, solicitudesEnEspera);

    Operador operador =
            new Operador("Operador Principal", 1, solicitudesEnEspera);

    GestorReportes gestorReportes = new GestorReportes();

    MenuGestionVial menuGesVial =
            new MenuGestionVial(gestorZonas, gestorConexiones);

    MenuGestionConductores menuGesConductores =
            new MenuGestionConductores(gestorConductores);

    this.menuOperadores =
            new MenuOperedores(
                    menuGesVial,
                    menuGesConductores,
                    operador,
                    solicitudesEnEspera,
                    gestorConductores
            );

    this.menuConductores =
            new MenuConductores(gestorReportes);

    this.menuCliente =
            new MenuCliente(crearSolicitud, solicitudesEnEspera);
}

    public void iniciar() {
        int opcion;

        do {
            System.out.println("");
            System.out.println("===== SISTEMA PRINCIPAL ====");
            System.out.println("1. Menu De Operador");
            System.out.println("2. Menu de Conductores");
            System.out.println("3. Menu de Clientes");
            System.out.println("4. Salir");
            System.out.println("");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> { menuOperadores.CrearMenu(); }
                case 2 -> { menuConductores.CrearMenu(); }
                case 3 -> { menuCliente.CrearMenu(); }
                case 4 -> { System.out.println("Saliendo del sistema..."); }
                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 4);
    }
}

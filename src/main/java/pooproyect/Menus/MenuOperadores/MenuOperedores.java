package pooproyect.Menus.MenuOperadores;

import java.util.Scanner;
import pooproyect.Conductores.GestorConductores;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Menus.InterfaceMenu;
import pooproyect.Solicitudes.SolicitudEnEspera;
import pooproyect.operador.Operador;

public class MenuOperedores implements InterfaceMenu {
    private Scanner sc = EntradaUsuario.get();
    private MenuGestionVial menuGesVial;
    private MenuGestionConductores menuGesConductores;
    private Operador operador;
    private SolicitudEnEspera solicitudesEnEspera;
    private GestorConductores gestorConductores;

    public MenuOperedores(MenuGestionVial menuGesVial,
                         MenuGestionConductores menuGesConductores,
                         Operador operador,
                         SolicitudEnEspera solicitudesEnEspera,
                         GestorConductores gestorConductores) {
        this.menuGesVial = menuGesVial;
        this.menuGesConductores = menuGesConductores;
        this.operador = operador;
        this.solicitudesEnEspera = solicitudesEnEspera;
        this.gestorConductores = gestorConductores;
    }

    @Override
    public void CrearMenu() {
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("\n");
            System.out.println("==Menu de Operadores==");
            System.out.println("");
            System.out.println("1. Ver solicitudes en espera");
            System.out.println("2. Atender siguiente solicitud");
            System.out.println("3. Cancelar solicitud");
            System.out.println("4. Finalizar servicio");
            System.out.println("5. Ver historial");
            System.out.println("6. Gestionar conductores");
            System.out.println("7. Gestionar conexiones viales");
            System.out.println("8. Volver");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> {
                    operador.ListarSolicitudesEnEspera();
                }
                case 2 -> {
                    operador.AtenderSiguiente();
                }
                case 3 -> {
                    System.out.print("Ingrese el ID de la solicitud a cancelar: ");
                    int idSolicitud = sc.nextInt();
                    sc.nextLine();
                    operador.CancelarSolicitud(idSolicitud);
                }
                case 4 -> {
                    System.out.print("Ingrese el ID del conductor para finalizar servicio: ");
                    String idConductor = sc.nextLine();
                    gestorConductores.finalizarServicioConductor(idConductor);
                }
                case 5 -> {
                    solicitudesEnEspera.mostrarHistorial();
                }
                case 6 -> {
                    menuGesConductores.CrearMenu();
                }
                case 7 -> {
                    menuGesVial.CrearMenu();
                }
                case 8 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
}

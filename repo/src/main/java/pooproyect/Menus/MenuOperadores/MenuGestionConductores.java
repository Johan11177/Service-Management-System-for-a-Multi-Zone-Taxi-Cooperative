package pooproyect.Menus.MenuOperadores;

import java.util.Scanner;

import pooproyect.Conductores.GestorConductores;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Menus.InterfaceMenu;

public class MenuGestionConductores implements InterfaceMenu {
    private GestorConductores gestorConductores;
    private Scanner sc = EntradaUsuario.get();

    public MenuGestionConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
    }

    @Override
    public void CrearMenu() {
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("\n");
            System.out.println("==Gestion De Conductores==");
            System.out.println("");
            System.out.println("1. agregar Conductor");
            System.out.println("2. Ver Conductores Disponibles");
            System.out.println("3. Habilitar Servicio");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> {
                    gestorConductores.agregarConductor();
                }
                case 2 -> {
                    gestorConductores.MostrarConductores();
                }
                case 3 -> {
                    gestorConductores.habilitarServicioConductor();
                }
                case 4 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
}

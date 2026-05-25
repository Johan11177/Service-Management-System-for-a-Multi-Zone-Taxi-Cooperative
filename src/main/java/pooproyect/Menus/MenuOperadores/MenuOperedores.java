package pooproyect.Menus.MenuOperadores;

import java.util.Scanner;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Menus.InterfaceMenu;

public class MenuOperedores implements InterfaceMenu {
    private Scanner sc = EntradaUsuario.get();
    private MenuGestionVial menuGesVial;
    private MenuGestionConductores menuGesConductores;

    public MenuOperedores(MenuGestionVial menuGesVial, MenuGestionConductores menuGesConductores) {
        this.menuGesVial = menuGesVial;
        this.menuGesConductores = menuGesConductores;
    }

    @Override
    public void CrearMenu() {
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("==Menu de Operadores==");
            System.out.println("");
            System.out.println("1. Registrar solicitud\n" +
                    "2. Ver solicitudes en espera\n" +
                    "3. Atender siguiente solicitud\n" +
                    "4. Cancelar solicitud\n" +
                    "5. Finalizar servicio\n" +
                    "6. Ver historial\n" +
                    "7. Gestionar conductores\n" +
                    "8. Gestionar conexiones viales\n" +
                    "9. Volver");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            switch (op) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                }
                case 6 -> {
                }
                case 7 -> {
                    menuGesConductores.CrearMenu();
                }
                case 8 -> {
                    menuGesVial.CrearMenu();
                }
                case 9 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                    System.out.println("");
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
}

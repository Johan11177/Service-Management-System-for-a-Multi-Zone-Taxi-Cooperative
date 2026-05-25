package pooproyect.Menus;

import java.util.Scanner;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Menus.MenuOperadores.MenuOperedores;

public class SystemUI {
    private Scanner sc = EntradaUsuario.get();
    private MenuOperedores menuOperadores;
    private MenuConductores menuConductores;
    private MenuCliente menuCliente;
    
    public SystemUI(MenuOperedores menuOperadores, MenuConductores menuConductores, MenuCliente menuCliente) {
        this.menuOperadores = menuOperadores;
        this.menuConductores = menuConductores;
        this.menuCliente = menuCliente;
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

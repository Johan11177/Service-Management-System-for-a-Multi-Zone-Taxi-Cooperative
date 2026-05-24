package pooproyect.Menus;

import java.util.Scanner;

public class SystemUI {
    Scanner sc = new Scanner(System.in);
    private MenuOperedores menuOperadores;
    private MenuConductores menuConductores;
    private MenuCliente menuCliente;
    
    public SystemUI(MenuOperedores menuOperadores, MenuConductores menuConductores, MenuCliente menuCliente) {
        this.menuOperadores = new MenuOperedores();
        this.menuConductores = new MenuConductores();
        this.menuCliente = new menuCliente();
    }

     public SystemUI() {
        
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

                case 1 -> {
                    menuOperadores.CrearMenu();
                }

                case 2 -> {
                    menuConductores.CrearMenu();
                }

                case 3 -> {
                    menuCliente.CrearMenu();
                }

                case 4 -> {
                    System.out.println("Saliendo del sistema...");
                }

                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 4);
    }
}
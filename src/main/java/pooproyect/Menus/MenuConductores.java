package pooproyect.Menus;

import java.util.Scanner;

public class MenuConductores implements InterfaceMenu {

@Override
 public void CrearMenu() {
        Scanner sc = new Scanner(System.in);
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("==Menu Conductores==");
            System.out.println("1. Hacer Reporte");
            System.out.println("2. Volver");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            switch (op) {

                case 1 -> { 
                    
                 }
                    

                case 2 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                    System.out.println("");
                }

                default -> System.out.println("Opcion invalida");

            }
        }
    }

}

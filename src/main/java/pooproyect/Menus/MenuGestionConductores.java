package pooproyect.Menus;

import java.util.Scanner;

import pooproyect.Usuario.GestorConductores;

public class MenuGestionConductores implements InterfaceMenu {
 private GestorConductores gestorConductores;

    public MenuGestionConductores(GestorConductores gestorConductores) {
        this.gestorConductores = gestorConductores;
    }

    @Override
 public void CrearMenu() {
        Scanner sc = new Scanner(System.in);
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("==Gestion De Conductores==");
            System.out.println("");
            System.out.println("1. agregar Conductor");
            System.out.println("2. Ver Conductores Disponibles");
            System.out.println("3. Habilitar Servicio");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            switch (op) {

                case 1 -> { 
                    gestorConductores.agregarConductor();
                 }
                    
                case 2 -> {
                  gestorConductores.MostrarConductores();
                }

                case 3 -> {
                    
                }

                case 4 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                    System.out.println("");
                }

                default -> System.out.println("Opcion invalida");

            }
        }
    }
}
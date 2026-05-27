package pooproyect.Menus;

import java.util.Scanner;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Reportes.GestorReportes;

public class MenuConductores implements InterfaceMenu {
    private Scanner sc = EntradaUsuario.get();
    private GestorReportes gestorReportes;
    
    

    public MenuConductores(GestorReportes gestorReportes) {
        this.gestorReportes = gestorReportes;
    }



    @Override
    public void CrearMenu() {
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("==Menu Conductores==");
            System.out.println("1. Hacer Reporte");
            System.out.println("2. Volver");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> {
                    

                    System.out.println("Escriba el motivo de su reporte 🙈: ");
                    String motivo= sc.nextLine();
                    gestorReportes.agregarReportes(motivo);


                    
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

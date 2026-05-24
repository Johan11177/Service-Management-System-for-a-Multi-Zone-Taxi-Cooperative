package pooproyect.Menus;

import java.util.Scanner;

import pooproyect.Solicitudes.CrearSolicitud;

public class MenuCliente implements InterfaceMenu {
    private CrearSolicitud s;

    public MenuCliente(CrearSolicitud s) {
        this.s = s;
    }
    
    @Override
    public void CrearMenu() {
        Scanner sc = new Scanner(System.in);
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("==Menu Cliente==");
            System.out.println("1. Hacer Solicitud");
            System.out.println("2. Cancelar Servicio");
            System.out.println("3. Volver");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            switch (op) {

                case 1 -> {
                    s.crearSolicitud();
                }

                case 2 -> {

                }

                case 3 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                    System.out.println("");
                }

                default -> System.out.println("Opcion invalida");

            }
        }
    }

    public CrearSolicitud getS() {
        return s;
    }
    
}

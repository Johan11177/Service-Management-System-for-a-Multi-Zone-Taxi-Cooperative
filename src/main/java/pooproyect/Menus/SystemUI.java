package pooproyect.Menus;

import java.util.Scanner;

import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;

public class SystemUI {

    Scanner sc = new Scanner(System.in);

    private GestorZonas gestorZonas;
    private GestorConexionesRutas gestorConexiones;

    public SystemUI() {

        gestorZonas = new GestorZonas();
        gestorConexiones = new GestorConexionesRutas(gestorZonas);
    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("");
            System.out.println("===== SISTEMA PRINCIPAL ====");
            System.out.println("1. Conexion vial");
            System.out.println("2. Gestion De Red Vial");
            System.out.println("3. Salir");
            System.out.println("");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {

                    InterfazConexionVial conexion = new InterfazConexionVial(gestorZonas, gestorConexiones);
                    conexion.iniciar();
                }

                case 2 -> {

                    MenuGestionVial menuGestionVial = new MenuGestionVial(gestorZonas, gestorConexiones);
                    menuGestionVial.CrearMenu();
                }

                case 3 -> {
                    System.out.println("Saliendo del sistema...");
                }

                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 3);
    }
}
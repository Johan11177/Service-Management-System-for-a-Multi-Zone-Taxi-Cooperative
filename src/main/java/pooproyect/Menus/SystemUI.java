package pooproyect.Menus;

import java.util.Scanner;

import pooproyect.Usuario.GestorConductores;
import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;

public class SystemUI {

    Scanner sc = new Scanner(System.in);
 
    private GestorConductores gestorConductores;
    private GestorConexionesRutas gestorConexiones;
    private GestorZonas gestorZonas;

    public SystemUI() {

        gestorZonas = new GestorZonas();
        gestorConexiones = new GestorConexionesRutas(gestorZonas);
        gestorConductores = new GestorConductores();
    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("");
            System.out.println("===== SISTEMA PRINCIPAL ====");
            System.out.println("1. Conexion vial");
            System.out.println("2. Gestion De Red Vial");
            System.out.println("3. Gestion De Conductores");
            System.out.println("4. Salir");
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
                    MenuGestionConductores menuGestionConductores = new MenuGestionConductores(gestorConductores);
                    menuGestionConductores.CrearMenu();
                }

                case 4 -> {
                    System.out.println("Saliendo del sistema...");
                }

                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 4);
    }
}
package pooproyect.Main;

import java.util.Scanner;

import pooproyect.Zona.InterfazConexionVial;

public class SystemUI {

    Scanner sc = new Scanner(System.in);

    public SystemUI() {

    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("");
            System.out.println("===== SISTEMA PRINCIPAL ====");
            System.out.println("1. Conexion vial");
            System.out.println("2. Salir");
            System.out.println("");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {
                    InterfazConexionVial conexion = new InterfazConexionVial();
                    conexion.iniciar();
                }

                case 2 -> System.out.println("Saliendo...");

                default -> System.out.println("Opcion invalida");

            }

        } while (opcion != 2);

    }

}

package pooproyect.Menus.MenuOperadores;

import java.util.Scanner;
import pooproyect.Main.EntradaUsuario;
import pooproyect.Menus.InterfaceMenu;
import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;

public class MenuGestionVial implements InterfaceMenu {
    private GestorZonas gestorZonas;
    private GestorConexionesRutas gestorConexiones;
    private Scanner sc = EntradaUsuario.get();

    public MenuGestionVial(GestorZonas gestorZonas, GestorConexionesRutas gestorConexiones) {
        this.gestorZonas = gestorZonas;
        this.gestorConexiones = gestorConexiones;
    }

    @Override
    public void CrearMenu() {
        int op;
        boolean volver = false;

        while (!volver) {
            System.out.println("\n");
            System.out.println("==Gestion De Red vial==");
            System.out.println("");
            System.out.println("1. Ver Zonas");
            System.out.println("2. ver Conexiones");
            System.out.println("3. Habilitar/deshabilitar Conexion");
            System.out.println("4. Volver al Menu Principal");
            System.out.println("");
            System.out.println("Seleccione una opcion:");

            op = sc.nextInt();
            switch (op) {
                case 1 -> {
                    System.out.println("");
                    gestorZonas.MostrarZonas();

                }
                case 2 -> {
                    System.out.println("");
                    gestorConexiones.MostrarConexiones();
                }
                case 3 -> {
                    System.out.println("");
                    gestorConexiones.HabilitarDeshabilitarConexion();
                }
                case 4 -> {
                    System.out.println("Volviendo...");
                    volver = true;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
}

package pooproyect.Main;

import pooproyect.Menus.MenuCliente;
import pooproyect.Menus.MenuConductores;
import pooproyect.Menus.SystemUI;
import pooproyect.Menus.MenuOperadores.MenuOperedores;
import pooproyect.Solicitudes.CrearSolicitud;
import pooproyect.Solicitudes.SolicitudEnEspera;
import pooproyect.Solicitudes.Solicitudes;
import pooproyect.Usuario.Cliente;
import pooproyect.Zona.Conexiones;
import pooproyect.Zona.GestorConexionesRutas;
import pooproyect.Zona.GestorZonas;

public class Main {
    public static void main(String[] args) {
        // Infraestructura compartida
        SolicitudEnEspera cola = new SolicitudEnEspera();

        // Zonas y conexiones
        GestorZonas gestorZonas = new GestorZonas();
        GestorConexionesRutas gestorConexiones = new GestorConexionesRutas(gestorZonas);

        // Cliente por defecto (el nombre se actualiza al crear la solicitud)
        Cliente cliente = new Cliente("", "CLI-1");
        Conexiones conexiones = new Conexiones(0, 0);

        // Menus
        MenuOperedores menuOperadores = new MenuOperedores();
        MenuConductores menuConductores = new MenuConductores();
        CrearSolicitud crearSolicitud = new CrearSolicitud(conexiones, gestorZonas, cliente);
        MenuCliente menuCliente = new MenuCliente(crearSolicitud);

        SystemUI UI = new SystemUI(menuOperadores, menuConductores, menuCliente);
        UI.iniciar();

        // Prueba de la cola
        Solicitudes s1 = new Solicitudes(1, "gaira", "centro", "Baul", "18:32", "20/20");
        cola.agregarSolicitud(s1);
        System.out.println(s1);
        cola.atenderSolicitud();
        cola.cancelarSolicitud(1);
        System.out.println(s1);
    }
}

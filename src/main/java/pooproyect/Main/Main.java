package pooproyect.Main;

import pooproyect.Menus.MenuCliente;
import pooproyect.Menus.MenuConductores;
import pooproyect.Menus.MenuOperedores;
import pooproyect.Menus.SystemUI;
import pooproyect.Solicitudes.SolicitudEnEspera;
import pooproyect.Solicitudes.Solicitudes;

public class Main {
    public static void main(String[] args) {
       //probrar que la cola funciona en el programa
         SolicitudEnEspera cola = new SolicitudEnEspera();
         Solicitudes s1 = new Solicitudes(1, "gaira", "centro", "Baul", "18:32", "20/20");
    
         SystemUI UI = new SystemUI(menuOperadores, menuConductores, menuCliente);
         UI.iniciar();
         
        cola.agregarSolicitud(s1);
        System.out.println(s1);
        cola.atenderSolicitud();
        cola.cancelarSolicitud(1);
        System.out.println(s1);
        
        
    }
}


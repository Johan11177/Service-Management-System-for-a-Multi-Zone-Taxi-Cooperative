package pooproyect.Usuario;

import pooproyect.Solicitudes.SolicitudEnEspera;

public class Cliente extends Usuario {
    private SolicitudEnEspera SoliEspera;
    
    public Cliente(String nombre, String ID) {
        super(nombre, ID);
    }

    public void CancelarServicio() {
  System.out.println("Servicio cancelado");
}
}

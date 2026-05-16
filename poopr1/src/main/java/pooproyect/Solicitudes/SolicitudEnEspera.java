package pooproyect.Solicitudes;

import java.util.ArrayList;

public class SolicitudEnEspera {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Solicitudes>cola;


    public Colasilicitudes(){
        cola= new ArrayList<>();
    }
    public void agregarSolicitud(Solicitud s){
        cola.add(s);
    }

    public AtenderSolicitud(){
        if(cola.isEmpty()){
            System.out.println("No hay solicitudes");
            return null;
        }
        return cola.remove(0);
        s.setEstadoSolicitud("ATENDIDA");

        

    }
    public CancelarSolicitud(){
        System.out.println("Motivo de cancelacion: ");
        String motivo = sc.nextLine();
        for(int i=0; i <= cola.size(); i++){

            Solicitud s = cola(i);
            if(s.getID==ID){
                s.estadoSolicitud("Cancelada");
                cola.remove(i);
                System.out.println("Solicitud Cancelada");
                System.out.println(motivo);
               
            }

        }
    }


}

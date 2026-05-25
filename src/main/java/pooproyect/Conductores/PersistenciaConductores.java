package pooproyect.Conductores;


import pooproyect.TipoDeServicio.Taxi;
import pooproyect.TipoDeServicio.TaxiConBaul;
import pooproyect.TipoDeServicio.TaxiEstandar;
import pooproyect.TipoDeServicio.TaxiTransporteMascotas;
import pooproyect.Vehiculo.Vehiculo;
import java.io.*;
import java.util.ArrayList;

public class PersistenciaConductores {

    private static final String ARCHIVO = "conductores.txt";

    public static void guardar(ArrayList<Conductor> conductores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Conductor c : conductores) {

                StringBuilder servicios = new StringBuilder();
                for (Taxi taxi : c.getServicios()) {
                    servicios.append(taxi.getTipoServicio()).append(",");
                }

                bw.write(
                    c.getNombre() + ";" +
                    c.getID() + ";" +
                    c.getVehiculo().getMarca() + ";" +
                    c.getVehiculo().getPlaca() + ";" +
                    servicios
                ); 
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static ArrayList<Conductor> cargar() {
        ArrayList<Conductor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;

                String[] datos = linea.split(";");
                // datos[0]=nombre | datos[1]=id | datos[2]=marca | datos[3]=placa | datos[4]=servicios

                Conductor c = new Conductor(
                    datos[0],
                    datos[1],
                    new Vehiculo(datos[2], datos[3])
                );
                
                if (datos.length > 4 && !datos[4].isBlank()) {
                    String[] servicios = datos[4].split(",");
                    for (String tipo : servicios) {
                        switch (tipo.trim()) {
                            case "Estandar"             -> c.agregarServicio(new TaxiEstandar());
                            case "Con Baul"             -> c.agregarServicio(new TaxiConBaul());
                            case "Transporte Mascotas"  -> c.agregarServicio(new TaxiTransporteMascotas());
                        }
                    }
                }

                lista.add(c);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }

        return lista;
    }
}
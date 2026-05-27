package pooproyect.Conductores;


import pooproyect.TipoDeServicio.Taxi;
import pooproyect.TipoDeServicio.TaxiConBaul;
import pooproyect.TipoDeServicio.TaxiEstandar;
import pooproyect.TipoDeServicio.TaxiTransporteMascotas;
import pooproyect.Vehiculo.Vehiculo;
import java.io.*;
import java.util.ArrayList;

public class PersistenciaConductores {
//maicol mete el txt en la carpeta conductores para que este mejor organizado
    private static final String ARCHIVO = "conductores.txt";

    public static void guardar(ArrayList<Conductor> conductores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Conductor c : conductores) {

                StringBuilder servicios = new StringBuilder();
                for (Taxi taxi : c.getServicios()) {
                    if (servicios.length() > 0) {
                        servicios.append(",");
                    }
                    servicios.append(taxi.getTipoServicio());
                }

                bw.write(
                    c.getNombre() + ";" +
                    c.getID() + ";" +
                    c.getVehiculo().getMarca() + ";" +
                    c.getVehiculo().getPlaca() + ";" +
                    c.isDisponible() + ";" +
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

                boolean disponible = true;
                String serviciosTexto = "";

                if (datos.length == 5) {
                    if (datos[4].equalsIgnoreCase("true") || datos[4].equalsIgnoreCase("false")) {
                        disponible = Boolean.parseBoolean(datos[4]);
                    } else {
                        serviciosTexto = datos[4];
                    }
                } else if (datos.length > 5) {
                    disponible = Boolean.parseBoolean(datos[4]);
                    serviciosTexto = datos[5];
                }

                c.setDisponible(disponible);

                if (!serviciosTexto.isBlank()) {
                    String[] servicios = serviciosTexto.split(",");
                    for (String tipo : servicios) {
                        switch (tipo.trim()) {
                            case "Taxi Estandar"             -> c.agregarServicio(new TaxiEstandar());
                            case "Taxi con Baul"             -> c.agregarServicio(new TaxiConBaul());
                            case "Taxi Transporte Mascotas"  -> c.agregarServicio(new TaxiTransporteMascotas());
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
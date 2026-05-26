package pooproyect.Reportes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorReportes {
    private static final String ARCHIVO = "reportes.txt";
    private ArrayList<Reporte> reportes = new ArrayList<>();
    
    public GestorReportes(){
        cargar();
    }
    public void agregarReportes(String motivo){
        reportes.add(new Reporte(motivo));
        guardar();
        System.out.println("Reporte enviado 🌚");

    }
    public void mostrarReportes(){
        if(reportes.isEmpty()){
            System.out.println("No hay reportes");
            return;
        }
        for(Reporte r:reportes){
            System.out.println(r);

        }
    }

    private void guardar() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))){
            for (Reporte r: reportes) {
                bw.write(r.getMotivo());
                bw.newLine();
            }
            
        }catch (IOException e){
            System.out.println("Error al tratar de guardar reporte: "+ e.getMessage());

        }
        
    }
    private void cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                reportes.add(new Reporte(linea));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar reportes: " + e.getMessage());
        }
    }   

}      
        
        
    

    



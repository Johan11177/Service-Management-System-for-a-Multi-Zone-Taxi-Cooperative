package pooproyect.Reportes;

public class Reporte {
    private String motivo;

    public Reporte(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Reporte [motivo=" + motivo + "]";
    }
    

}

package pooproyect.Zona;

public abstract class Grafo {
    protected int[][] matriz;
    protected GestorConexionesRutas rutas;

    public Grafo(GestorConexionesRutas rutas) {
        this.rutas = rutas;
        construirGrafo();
    }

    public abstract void construirGrafo();

    public int[][] getMatriz() {
        return matriz;
    }
}
package pooproyect.Zona;

import java.util.Scanner;

public class InterfazConexionVial {

    Scanner sc = new Scanner(System.in);

    private GestorZonas gestorZonas; // intancia gestor zona y crear atributo de ese tipo
    private GestorConexionesRutas rutas; // instancia GCR y crear atributo de ese tipo
    private Grafo grafo;// instancia Grafo y crea variable de ese tipo

    public InterfazConexionVial() {
        // constructor
        gestorZonas = new GestorZonas();// creo el objeto
        gestorZonas.agregarZonas();// agrego las zonas dentro del arreglo
        rutas = new GestorConexionesRutas(gestorZonas);// creo el objeto
        rutas.agregarConexiones();// agrego las conexiones dentro del arreglo
        grafo = new Grafo(rutas);// creo el objeto

    }

    // inicia el menu para el usuario
    public void iniciar() {

        int origen;
        int destino;
        grafo.construirGrafo();

        System.out.println("===== CONEXIONES VIALES =====");

        // crea el menu interactivo
        for (Zonas z : gestorZonas.getZonas()) {
            System.out.println((z.getIdZona() + 1) + ". " + z.getNombreZona());

        }

        // VALIDAR ORIGEN
        System.out.print("Seleccione origen: ");
        origen = sc.nextInt();

        while (origen < 1 || origen > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            System.out.print("Seleccione origen nuevamente: ");
            origen = sc.nextInt();

        }

        // VALIDAR DESTINO
        System.out.print("Seleccione destino: ");
        destino = sc.nextInt();

        while (destino < 1 || destino > gestorZonas.getZonas().size()) {
            System.out.println("Ingrese una opcion correcta");
            System.out.print("Seleccione destino nuevamente: ");
            destino = sc.nextInt();

        }
        // AJUSTAR A INDICES
        origen--;
        destino--;

        int[][] matrizVial = grafo.getMatriz();

        // 2. Creas el objeto BFS y le inyectas esa matriz en su constructor
        BFS algoritmoBfs = new BFS(matrizVial);

        System.out.println("\nBuscando rutas disponibles");

        // 3. El BFS hace toda la búsqueda en base a lo que el usuario puso
        boolean existeCamino = algoritmoBfs.hayConexion(origen, destino);

        // 4. Muestras el resultado final en la interfaz por consola
        if (existeCamino) {
            System.out.println("");
            System.out.println("Hay Conexion Vial");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("No hay Conexion Vial");
            System.out.println("");
        }
    }

}


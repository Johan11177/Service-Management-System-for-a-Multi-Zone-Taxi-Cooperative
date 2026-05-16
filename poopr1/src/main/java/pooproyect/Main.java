package pooproyect;

public class Main {
    public static void main(String[] args) {
       //probrar que la cola funciona en el programa
         Cola cola = new Cola();
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        System.out.println("Cola después de encolar 1, 2, 3: " + cola);
        int dequeued = cola.dequeue();
        System.out.println("Elemento desencolado: " + dequeued);
        System.out.println("Cola después de desencolar: " + cola);
        
    }
}


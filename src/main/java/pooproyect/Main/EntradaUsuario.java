package pooproyect.Main;

import java.util.Scanner;

public class EntradaUsuario {
    private static final Scanner sc = new Scanner(System.in);

    private EntradaUsuario() {}

    public static Scanner get() {
        return sc;
    }
}

package pooproyect.Vehiculo;

public class Vehiculo {
 private String marca;
 private String placa;

public Vehiculo(String marca, String placa) {
    this.marca = marca;
    this.placa = placa;
}

public String getMarca() {
    return marca;
}

public String getPlaca() {
    return placa;
}

}
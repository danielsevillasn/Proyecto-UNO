package Excepciones;

public class CartaLanzadaNoValida extends RuntimeException {
    public CartaLanzadaNoValida(String message) {
        super(message);
    }
}

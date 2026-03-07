package Excepciones;

/**
 * Excepcion que se lanza cuando la carta lanzada no es valida
 * 
 * @author DaniS y Libio
 */
public class CartaLanzadaNoValida extends RuntimeException {
    public CartaLanzadaNoValida(String message) {
        super(message);
    }
}

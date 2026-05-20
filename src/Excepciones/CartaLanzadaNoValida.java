package Excepciones;

/**
 * Excepcion que se lanza cuando la carta lanzada no es valida
 * 
 * @author DaniS y Libio
 */
public class CartaLanzadaNoValida extends Exception {
    // Constructor que cuando se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public CartaLanzadaNoValida(String mensaje) {
        super(mensaje);
    }
}

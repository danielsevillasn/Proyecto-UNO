package Excepciones;

/**
 * Excepcion que se lanza cuando se quiere reiniciar del juego
 * 
 * @author DaniS y Libio
 */
public class ReiniciarJuego extends Exception {
    // Constructor que cuando se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public ReiniciarJuego(String mensaje) {
        super(mensaje);
    }
}
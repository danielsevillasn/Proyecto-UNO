package Excepciones;

/**
 * Excepcion que se lanza cuando el modo de juego no es valido
 * 
 * @author DaniS y Libio
 */
public class ModoDeJuegoNoValido extends Exception{
    // Constructor que cuanfo se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public ModoDeJuegoNoValido(String mensaje) {
        super(mensaje);
    }
}

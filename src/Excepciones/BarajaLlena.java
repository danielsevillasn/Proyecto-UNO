package Excepciones;

/**
 * Excepcion que se lanza cuando la baraja esta llena
 * 
 * @author DaniS y Libio
 */
public class BarajaLlena extends Exception {
    // Constructor que cuanfo se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public BarajaLlena(String Mensaje) {
        super(Mensaje);
    }

}

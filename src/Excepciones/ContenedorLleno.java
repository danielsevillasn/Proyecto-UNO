package Excepciones;

/**
 * Excepcion que se lanza cuando la carta lanzada no es valida
 * 
 * @author DaniS y Libio
 */
public class ContenedorLleno extends Exception {
    // Constructor que cuanfo se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public ContenedorLleno(String mensaje) {
        super(mensaje);
    }
}

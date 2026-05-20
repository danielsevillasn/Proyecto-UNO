package Excepciones;

/**
 * Excepción que se lanza cuando se intenta añadir una carta y el contenedor
 * está lleno
 * 
 * @author DaniS y Libio
 */
public class ContenedorLleno extends Exception {
    // Constructor que cuando se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public ContenedorLleno(String mensaje) {
        super(mensaje);
    }
}

package Excepciones;

/**
 * Excepción que se lanza cuando se intenta extraer una carta de un contenedor
 * vacío
 * o se proporciona un índice inválido.
 * 
 * @author DaniS y Libio
 */
public class ContenedorVacio extends Exception {
    // Constructor que cuando se utiliza getMessage() imprime el mensaje impuesto
    // donde se inicializo la excepcion
    public ContenedorVacio(String mensaje) {
        super(mensaje);
    }
}
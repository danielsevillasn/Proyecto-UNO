package Excepciones;

/**
 * Excepción que se lanza cuando se intenta extraer una carta de un contenedor
 * vacío
 * o se proporciona un índice inválido.
 * 
 * @author DaniS y Libio
 */
public class ContenedorVacio extends Exception {
    public ContenedorVacio(String mensaje) {
        super(mensaje);
    }
}
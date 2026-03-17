package Excepciones;

/**
 * Excepcion que se lanza cuando se quiere salir del juego
 * 
 * @author DaniS y Libio
 */
public class SalirDelJuego extends Exception{
    //Constructor que cuanfo se utiliza getMessage() imprime el mensaje impuesto donde se inicializo la excepcion
    public SalirDelJuego(String mensaje) {
        super(mensaje);
    }
}

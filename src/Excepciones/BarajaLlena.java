package Excepciones;

/**
 * Excepcion que se lanza cuando la carta lanzada no es valida
 * 
 * @author DaniS y Libio
 */
public class BarajaLlena extends Exception {
    public BarajaLlena(String Mensaje) {
        super(Mensaje);
        
    }


}

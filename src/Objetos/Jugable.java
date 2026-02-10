package Objetos;

/**
 * Interfaz que habilita el metodo puedePonerseSobre para su utilización
 * 
 * @author DaniS y Libio
 */
public interface Jugable {
    /**
     * @param mesa La carta que está actualmente en el centro del tablero.
     * @return true si la carta actual cumple las reglas para ser jugada.
     */
    boolean puedePonerseSobre(Carta mesa);
}
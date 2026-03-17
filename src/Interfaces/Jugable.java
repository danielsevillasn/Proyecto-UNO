package Interfaces;

import Excepciones.CartaLanzadaNoValida;
import Objetos.Carta;

/**
 * Interfaz que habilita el método puedePonerseSobre para su utilización
 * 
 * @author DaniS y Libio
 */
public interface Jugable {
    /**
     * @param mesa La carta que está actualmente en el centro del tablero.
     * @return true si la carta actual cumple las reglas para ser jugada.
     */
    boolean puedePonerseSobre(Carta mesa) throws CartaLanzadaNoValida;
}
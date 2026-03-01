package Objetos;

/**
 * Clase que establece el turno del juego
 * 
 * @author DaniS y Libio
 */
public class Turno {
    public int actual = 0;

    public void siguiente(int total) {
        actual = (actual + 1) % total;
    }
}
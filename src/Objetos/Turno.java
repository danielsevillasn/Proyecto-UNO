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
        //Sirve para crear turnos dependiendo de la cantidad de jugadores haciendo que cuando llegue al ultimo jugador vuelva al primero
        //Por ejemplo, si esta en el 4 jugador y pasa al siguiente, la operación seria 3+1 % 4, lo cual da 0, pasando así al jugador 0 o el primero
    }
}
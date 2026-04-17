package Objetos;

/**
 * Clase que establece el turno del juego
 * 
 * @author DaniS y Libio
 */
public class Turno {
    // Atributos/////////////////////
    private int actual;
    private int contadorTurno;
    private int sentido;

    // Metodos////////////////////////

    // Constructor por defecto
    public Turno() {
        actual = 0;
        contadorTurno = 1;
        this.sentido = 1;
    }

    // Getter
    public int getActual() {
        return actual;
    }

    public int getContadorTurno() {
        return contadorTurno;
    }

    public int getSentido() {
        return sentido;
    }

    // Setter
    public void setActual(int actual) {
        this.actual = actual;
    }

    public void setContadorTurno(int contadorTurno) {
        this.contadorTurno = contadorTurno;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    // Otros metodos
    /**
     * Sirve para crear turnos dependiendo de la cantidad de jugadores haciendo que
     * cuando llegue al ultimo jugador vuelva al primero
     * Por ejemplo, si esta en el 4 jugador y pasa al siguiente, la operación seria
     * 3+1 % 4, lo cual da 0, pasando así al jugador 0 o el primero
     * 
     * @param total numero entero que representa la cantidad total de jugadores
     */
    public void siguiente(int total) {
        actual = (actual + sentido + total) % total; // Sumamos 'total' antes del % para evitar resultados negativos
        contadorTurno++;
    }

    /**
     * Método que sirve para cambiar el sentido de la partida, si el sentido es 1 va
     * para adelante
     * si el sentido es -1 hacia atrás
     * 
     * @param 'nada'
     */
    public void cambiarSentido() {
        this.sentido *= -1;
    }

    // toString
    @Override
    public String toString() {
        return "TURNO ACTUAL:" + contadorTurno;
    }
}

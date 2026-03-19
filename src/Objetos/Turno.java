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

    // Metodos////////////////////////

    // Constructor por defecto
    public Turno(){
        actual = 0;
        contadorTurno = 1;
    }
    
    // Getter
    public int getActual() {
        return actual;
    }

    public int getContadorTurno() {
        return contadorTurno;
    }

    
    // Setter
    public void setActual(int actual) {
        this.actual = actual;
    }
    
    public void setContadorTurno(int contadorTurno) {
        this.contadorTurno = contadorTurno;
    }
    
    // Otros metodos
    /**
     * Sirve para crear turnos dependiendo de la cantidad de jugadores haciendo que
     * cuando llegue al ultimo jugador vuelva al primero
     * Por ejemplo, si esta en el 4 jugador y pasa al siguiente, la operación seria
     * 3+1 % 4, lo cual da 0, pasando así al jugador 0 o el primero
     * 
     * @param total
     */
    public void siguiente(int total) {
        actual = (actual + 1) % total;
        contadorTurno++;
    }

    // toString
    @Override
    public String toString() {
        return "TURNO ACTUAL:" + contadorTurno;
    }
}
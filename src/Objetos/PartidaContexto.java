package Objetos;

import java.util.HashMap;

/**
 * Clase que agrupa los elementos principales de la partida para simplificar el paso de parámetros
 * 
 * @author DaniS y Libio
 */
public class PartidaContexto {
    // Atributos/////////////////////
    private Tablero tablero;
    private Turno controladorTurnos;
    private HashMap<Integer, Jugador> jugadores;
    private int cantidadJugadores;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con parámetros
    public PartidaContexto(Tablero tablero, Turno controladorTurnos, HashMap<Integer, Jugador> jugadores, int cantidadJugadores) {
        this.tablero = tablero;
        this.controladorTurnos = controladorTurnos;
        this.jugadores = jugadores;
        this.cantidadJugadores = cantidadJugadores;
    }

    // Getter
    public Tablero getTablero() {
        return tablero;
    }

    public Turno getControladorTurnos() {
        return controladorTurnos;
    }

    public HashMap<Integer, Jugador> getJugadores() {
        return jugadores;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }
}
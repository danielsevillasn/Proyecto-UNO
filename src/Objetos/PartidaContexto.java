package Objetos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que agrupa los elementos principales de la partida para simplificar el
 * paso de parámetros
 * 
 * @author DaniS y Libio
 */
public class PartidaContexto implements Serializable{
    // Atributos/////////////////////
    private Tablero tablero;
    private Turno controladorTurnos;
    private HashMap<Integer, Jugador> jugadores;
    private int cantidadJugadores;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con parámetros
    public PartidaContexto(Tablero tablero, Turno controladorTurnos, Map<Integer, Jugador> jugadores,
            int cantidadJugadores) {
        this.tablero = tablero;
        this.controladorTurnos = controladorTurnos;
        this.jugadores = (HashMap<Integer, Jugador>) jugadores;
        this.cantidadJugadores = cantidadJugadores;
    }

    // Getter
    public Tablero getTablero() {
        return tablero;
    }

    public Turno getControladorTurnos() {
        return controladorTurnos;
    }

    public Map<Integer, Jugador> getJugadores() {
        return jugadores;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setControladorTurnos(Turno controladorTurnos) {
        this.controladorTurnos = controladorTurnos;
    }

    public void setJugadores(Map<Integer, Jugador> jugadores) {
        this.jugadores = (HashMap<Integer, Jugador>) jugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    // Otros metodos
    /**
     * * Atajo para avanzar el turno sin escribir toda la lógica de cantidad de
     * jugadores
     */
    public void pasarSiguiente() {
        this.controladorTurnos.siguiente(this.cantidadJugadores);
    }

    /**
     * Atajo para obtener directamente al jugador que tiene el turno
     */
    public Jugador jugadorActual() {
        return this.jugadores.get(this.controladorTurnos.getActual());
    }

    // To String
    @Override
    public String toString() {
        return "PartidaContexto{" +
                "jugadores=" + jugadores.size() +
                ", turnoActual=" + controladorTurnos.getActual() +
                '}';
    }
}
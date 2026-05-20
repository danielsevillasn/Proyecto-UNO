package Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que agrupa los elementos principales de la partida para simplificar el
 * paso de parámetros e implementa la interfaz
 * serializable para permitirnos serializar instancias de este objeto.
 * 
 * @author DaniS y Libio
 */
public class PartidaContexto implements Serializable {
    // Atributos/////////////////////
    private Tablero tablero;
    private Turno controladorTurnos;
    private HashMap<Integer, Jugador> jugadores;
    private int cantidadJugadores;
    private ArrayList<String> nombresJugadores;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con parámetros
    public PartidaContexto(Tablero tablero, Turno controladorTurnos, Map<Integer, Jugador> jugadores,
            int cantidadJugadores, List<String> nombresJugadores) {
        this.tablero = tablero;
        this.controladorTurnos = controladorTurnos;
        this.jugadores = (HashMap<Integer, Jugador>) jugadores;
        this.cantidadJugadores = cantidadJugadores;
        this.nombresJugadores = (ArrayList<String>) nombresJugadores;
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

    public List<String> getNombresJugadores() {
        return nombresJugadores;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    // Setter
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

    public void setNombresJugadores(List<String> nombresJugadores) {
        this.nombresJugadores = (ArrayList<String>) nombresJugadores;
    }

    // Otros metodos
    /**
     * * Atajo para avanzar el turno sin escribir toda la lógica de cantidad de
     * jugadores
     * 
     * @param 'ninguno'
     */
    public void pasarSiguiente() {
        this.controladorTurnos.siguiente(this.cantidadJugadores);
    }

    /**
     * Atajo para obtener directamente al jugador que tiene el turno
     * 
     * @param 'ninguno'
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
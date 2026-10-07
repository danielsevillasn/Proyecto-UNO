package MetodosSecundarios;

import java.util.ArrayList;
import java.util.Map;

import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.CartaNormal;
import Objetos.Jugador;

import static MetodosSecundarios.UnoEngine.*;

/**
 * Clase que gestiona las reglas especiales del modo "UNO Siete-0"
 * 
 * @author DaniS
 */
public class UnoSiete0ModoJuego {

    /**
     * Aplica la regla especial del modo "UNO siete-0".
     * Si la carta jugada es un 0 o un 7, se activa el efecto correspondiente.
     *
     * @param carta La carta que acaba de salir al tablero
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void aplicarRegla(Carta carta) throws InterruptedException, ReiniciarJuego {
        if (!(carta instanceof CartaNormal)) {
            return;
        }

        CartaNormal normal = (CartaNormal) carta;

        if (normal.getNumero() == 0) {
            efectoCero();
        } else if (normal.getNumero() == 7) {
            efectoSiete();
        }
    }

    /**
     * Regla del "0":
     * Cada vez que se descarta un 0, todos los jugadores pasan su mano
     * al siguiente jugador en la dirección del juego.
     * 
     * @param 'ninguno'
     */
    public static void efectoCero() {
        int total = nJugadores();
        int sentido = turnos().getSentido();
        Map<Integer, Jugador> jugadores = contextoPartida.getJugadores();

        System.out.println("\nREGLA DEL 0 ACTIVADA");
        System.out.println("Todos los jugadores pasan su mano al siguiente jugador...\n");

        // Almacenar todas las manos temporalmente para evitar sobrescrituras
        ArrayList<ArrayList<Carta>> manosTemporales = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            Jugador j = jugadores.get(i);
            if (j != null) {
                manosTemporales.add(new ArrayList<>(j.getMano().getLista()));
            } else {
                manosTemporales.add(new ArrayList<>());
            }
        }

        // Asignar las manos rotadas
        for (int i = 0; i < total; i++) {
            Jugador jugadorOrigen = jugadores.get(i);
            int idDestinatario = (i + sentido + total) % total;
            Jugador jugadorDestino = jugadores.get(idDestinatario);

            if (jugadorOrigen != null && jugadorDestino != null) {
                jugadorDestino.getMano().getLista().clear();
                jugadorDestino.getMano().getLista().addAll(manosTemporales.get(i));

                System.out.println("  " + jugadorOrigen.getNombre() + " → " + jugadorDestino.getNombre()
                        + " (" + manosTemporales.get(i).size() + " cartas)");
            }

        }
    }

    /**
     * Regla del "7":
     * El jugador que lo tira debe intercambiar su mano con la mano de otro
     * jugador elegido por él.
     *
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void efectoSiete() throws InterruptedException, ReiniciarJuego {
        Jugador jugadorActual = jugador;

        if (jugadorActual == null) {
            return;
        }

        int total = nJugadores();
        int indiceJugadorElegido = -1;

        System.out.println("\nREGLA DEL 7 ACTIVADA");

        // Mostrar lista de jugadores disponibles
        System.out.println("Jugadores disponibles:");
        for (int i = 0; i < total; i++) {
            if (i != turnos().getActual()) {
                Jugador jugadorDisponible = contextoPartida.getJugadores().get(i);
                if (jugadorDisponible != null) {
                    System.out.println("  " + i + " - " + jugadorDisponible.getNombre());
                }
            }
        }

        // Pedir que elija con quién intercambiar
        while (indiceJugadorElegido < 0 || indiceJugadorElegido >= total
                || indiceJugadorElegido == turnos().getActual()) {
            indiceJugadorElegido = Datos.pedirEntero("\n" + jugadorActual.getNombre()
                    + ", elige con quién intercambiar tu mano (0-" + (total - 1) + "): ");
        }

        Jugador jugadorElegido = contextoPartida.getJugadores().get(indiceJugadorElegido);

        if (jugadorElegido == null) {
            return;
        }

        // Intercambiar manos
        ArrayList<Carta> cartasJugadorActual = new ArrayList<>(jugadorActual.getMano().getLista());
        ArrayList<Carta> cartasJugadorElegido = new ArrayList<>(jugadorElegido.getMano().getLista());

        jugadorActual.getMano().getLista().clear();
        jugadorElegido.getMano().getLista().clear();

        jugadorActual.getMano().getLista().addAll(cartasJugadorElegido);
        jugadorElegido.getMano().getLista().addAll(cartasJugadorActual);

        System.out.println("\n" + jugadorActual.getNombre() + " intercambia su mano con " + jugadorElegido.getNombre());
        System.out.println(jugadorActual.getNombre() + " ahora tiene " + jugadorActual.getNumCartas()
                + " cartas.");
        System.out.println(jugadorElegido.getNombre() + " ahora tiene " + jugadorElegido.getNumCartas() + " cartas\n");
    }
}
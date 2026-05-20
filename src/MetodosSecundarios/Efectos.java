package MetodosSecundarios;

import Enumerados.Color;
import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.CartaEspecial;
import Objetos.Jugador;

import static MetodosSecundarios.UnoEngine.*;

/**
 * Clase que gestiona los efectos de las cartas especiales
 * 
 * @author DaniS y Libio
 */
public class Efectos {

    /**
     * Método que recoge todos los efectos de las cartas especiales implementadas y
     * los hace funcionar
     * 
     * @param cartaTirada Carta que ha sido tirada por el jugador
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void efectosCartasEspeciales(Carta cartaTirada) throws InterruptedException, ReiniciarJuego {
        CartaEspecial c = (CartaEspecial) cartaTirada;
        switch (c.getTiposEspeciales()) {
            case REVERSA:
                reversa();
                break;
            case BLOQUEO:
                bloqueo();
                break;
            case CHUPATE2:
                chupate(2, cartaTirada);
                break;
            case CHUPATE4:
                chupate(4, cartaTirada);
                break;
            case CAMBIOCOLOR:
                cambiarColor(cartaTirada);
                break;
        }
    }

    /**
     * Método que realiza la accion de la carta 'reversa':
     * cambia el sentido del juego y si son únicamente dos jugadores salta el turno
     * del siguiente
     * 
     * @param 'ninguno'
     */
    private static void reversa() {
        turnos().cambiarSentido();

        // Si son solo 2 jugadores entonces saltamos el turno del jugador que le
        // precedia
        if (nJugadores() == 2) {
            siguiente();
        }
        System.out.println("¡El sentido ha cambiado!");
    }

    /**
     * Método que realiza la accion de la carta 'bloqueo':
     * Recoge en un objeto jugador el jugador saltado para hallar su id para así
     * mostrar su nombre y previamente saltarle el turno
     * 
     * @param 'ninguno'
     */
    private static void bloqueo() {
        Jugador jugadorSaltado;
        jugadorSaltado = contextoPartida.getJugadores().get(hallarIdJugador()); // Consultamos quién va a ser
                                                                                // bloqueado
        System.out.println("¡" + jugadorSaltado.getNombre() + " ha sido bloqueado y pierde su turno!");
        siguiente();
    }

    /**
     * Método que realiza la accion de la carta 'chupateDos':
     * Recoge en un objeto jugador el jugador que va a chupar para hallar su id para
     * así hacer que chupe las cartas respectivas, luego saltar el turno y si es un
     * chupate 4 cambiar el color
     * 
     * @param numeroCartas indica el numero de cartas del chupate
     * @param cartaTirada  carta que se ha tirado en el tablero
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    private static void chupate(int numeroCartas, Carta cartaTirada)
            throws InterruptedException, ReiniciarJuego {
        Jugador jugadorChupete;
        jugadorChupete = contextoPartida.getJugadores().get(hallarIdJugador());
        if (numeroCartas == 4) {
            cambiarColor(cartaTirada);
        }
        System.out.println("¡" + jugadorChupete.getNombre() + " chupa " + numeroCartas + " cartas y pierde su turno!");
        chuparCartas(numeroCartas, jugadorChupete);
        siguiente();
    }

    /**
     * Método que sirve para realizar la accion de chupar cartas
     * 
     * @param numeroCartas expresa la cantidad de cartas que el jugador ha de chupar
     * @param j            recoge el jugador que tiene que chupar las cartas
     * @throws InterruptedException para los thread sleep
     */
    private static void chuparCartas(int numeroCartas, Jugador j) throws InterruptedException {
        Carta cartaRobada;
        for (int i = 0; i < numeroCartas; i++) {
            if (!j.tieneManoLlena()) {
                cartaRobada = tablero().tirarCarta();
                j.recibirCarta(cartaRobada);
                System.out.println("Recibe un: " + cartaRobada);
            }
        }
        Thread.sleep(Datos.milisegundos);
    }

    /**
     * Método que realiza la accion de la carta 'cambio color'
     * 
     * @param cartaTirada carta que se ha tirado en el tablero
     * @throws ReiniciarJuego para reinciar el juego cuando se quiera
     */
    private static void cambiarColor(Carta cartaTirada) throws ReiniciarJuego {
        boolean datoValido = false;
        do {
            int opcion = Menus.menuCambioColor();
            switch (opcion) {
                case 1:
                    cartaTirada.setColor(Color.ROJO);
                    datoValido = true;
                    break;
                case 2:
                    cartaTirada.setColor(Color.AMARILLO);
                    datoValido = true;
                    break;
                case 3:
                    cartaTirada.setColor(Color.VERDE);
                    datoValido = true;
                    break;
                case 4:
                    cartaTirada.setColor(Color.AZUL);
                    datoValido = true;
                    break;
                default:
                    System.out.println("Esa opcion no es válida");
                    break;
            }
        } while (!datoValido);
    }

    /**
     * Método que halla el id del jugador seleccionado mediante un sistema parecido
     * al de los turnos
     * 
     * @param 'ninguno'
     * @return entero que representa el id del jugador actual
     */
    private static int hallarIdJugador() {
        // Si el sentido es el normal entonces
        int actual = turnos().getActual();
        int sentido = turnos().getSentido();// 1 o -1
        // Sumamos la cantidad de jugadores para evitar números negativos al restar
        // El operador % (módulo) asegura que el índice siempre esté en el rango
        // correcto
        return (actual + sentido + nJugadores())
                % nJugadores();
    }
}
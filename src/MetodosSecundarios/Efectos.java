package MetodosSecundarios;

import Enumerados.Color;
import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.CartaEspecial;
import Objetos.Jugador;
import Objetos.PartidaContexto;

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
    public static void efectosCartasEspeciales(Carta cartaTirada, PartidaContexto ctx) throws InterruptedException, ReiniciarJuego {
        CartaEspecial c = (CartaEspecial) cartaTirada;
        switch (c.getTiposEspeciales()) {
            case REVERSA:
                reversa(ctx);
                break;
            case BLOQUEO:
                bloqueo(ctx);
                break;
            case CHUPATE2:
                chupate(2, cartaTirada, ctx);
                break;
            case CHUPATE4:
                chupate(4, cartaTirada, ctx);
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
    private static void reversa(PartidaContexto ctx) {
        ctx.getControladorTurnos().cambiarSentido();

        // Si son solo 2 jugadores entonces saltamos el turno del jugador que le
        // precedia
        if (ctx.getCantidadJugadores() == 2) {
            ctx.getControladorTurnos().siguiente(ctx.getCantidadJugadores());
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
    private static void bloqueo(PartidaContexto ctx) {
        Jugador jugadorSaltado;
        jugadorSaltado = ctx.getJugadores().get(hallarIdJugador(ctx)); // Consultamos quién va a ser bloqueado
        System.out.println("¡" + jugadorSaltado.getNombre() + " ha sido bloqueado y pierde su turno!");
        ctx.getControladorTurnos().siguiente(ctx.getCantidadJugadores());
    }

    /**
     * Método que realiza la accion de la carta 'chupateDos':
     * Recoge en un objeto jugador el jugador que va a chupar para hallar su id para
     * así hacer que chupe las cartas respectivas, luego saltar el turno y si es un
     * chupate 4 cambiar el color
     * 
     * @param cartaTirada carta que se ha tirado en el tablero
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    private static void chupate(int numeroCartas, Carta cartaTirada, PartidaContexto ctx)
            throws InterruptedException, ReiniciarJuego {
        Jugador jugadorChupete;
        jugadorChupete = ctx.getJugadores().get(hallarIdJugador(ctx));
        if (numeroCartas == 4) {
            cambiarColor(cartaTirada);
        }
        System.out.println("¡" + jugadorChupete.getNombre() + " chupa " + numeroCartas + " cartas y pierde su turno!");
        chuparCartas(numeroCartas, jugadorChupete, ctx);
        ctx.getControladorTurnos().siguiente(ctx.getCantidadJugadores());
    }

    /**
     * Método que sirve para realizar la accion de chupar cartas
     * 
     * @param numeroCartas expresa la cantidad de cartas que el jugador ha de chupar
     * @param j            recoge el jugador que tiene que chupar las cartas
     * @throws InterruptedException para los thread sleep
     */
    private static void chuparCartas(int numeroCartas, Jugador j, PartidaContexto ctx) throws InterruptedException {
        Carta cartaRobada;
        for (int i = 0; i < numeroCartas; i++) {
            if (!j.tieneManoLlena()) {
                cartaRobada = ctx.getTablero().tirarCarta();
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
            System.out.println("A que color quieres cambiar?");
            System.out.println("1- Rojo");
            System.out.println("2- Amarillo");
            System.out.println("3- Verde");
            System.out.println("4- Azul");

            int opcion = Datos.pedirEntero("Elige un color(1-4):");
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
     * @return entero que representa el id del jugador actual
     */
    private static int hallarIdJugador(PartidaContexto ctx) {
        // Si el sentido es el normal entonces
        int actual = ctx.getControladorTurnos().getActual();
        int sentido = ctx.getControladorTurnos().getSentido();// 1 o -1
        // Sumamos la cantidad de jugadores para evitar números negativos al restar
        // El operador % (módulo) asegura que el índice siempre esté en el rango
        // correcto
        return (actual + sentido + ctx.getCantidadJugadores()) % ctx.getCantidadJugadores();
    }
}
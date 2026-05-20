package MetodosSecundarios;

import java.util.Collections;

import Excepciones.CartaLanzadaNoValida;
import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.CartaEspecial;
import Objetos.Jugador;

import static MetodosSecundarios.UnoEngine.*;

/**
 * Clase que gestiona las mecánicas internas del flujo de juego
 * 
 * @author DaniS y Libio
 */
public class Mecanicas {

    /**
     * Metodo para repartir las cartas iniciales a todos los jugadores
     * 
     * @param nombresCargados nombres de los jugadores (por referencia)
     */
    public static void repartoInicial() {
        for (int i = 0; i < nJugadores(); i++) {
            contextoPartida.getJugadores().put(i, new Jugador(contextoPartida.getNombresJugadores().get(i)));
            for (int c = 0; c < 7; c++) {
                contextoPartida.getJugadores().get(i)
                        .recibirCarta(tablero().tirarCarta());
            }
        }
    }

    /**
     * Método que sirve para ordenar la baraja de los jugadores
     * 
     * @param 'ninguno'
     */
    public static void ordenarBarajaJugadores() {
        for (int i = 0; i < nJugadores(); i++) {
            Collections.sort(contextoPartida.getJugadores().get(i).getMano().getLista());
        }
    }

    /**
     * Método que roba una carta de la baraja chupona
     * 
     * @param 'ninguno'
     */
    public static void robarCarta() {
        Carta cartaRobada;
        cartaRobada = tablero().tirarCarta();
        if (!jugador.tieneManoLlena()) {
            jugador.recibirCarta(cartaRobada);
            System.out.println("Has recibido un: " + cartaRobada);
        }
    }

    /**
     * Método que mira si la carta que se acaba de tirar es valida o no
     * 
     * @param opcionCarta numero de carta seleccionada
     * @throws CartaLanzaNoValida excepcion que se lanza si la carta no es valida
     */
    public static boolean cartaSacada(int opcionCarta) throws CartaLanzadaNoValida {
        Carta cartaSeleccionada = jugador.getMano().obtener(opcionCarta);
        Carta cartaEnMesa = tablero().verCartaEnLaMesa();
        return cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
    }

    /**
     * Método que funciona si la carta sacada no es valida y chupa una carta
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void cartaSacadaNoValida() throws InterruptedException {
        Carta cartaRobada;
        cartaRobada = tablero().tirarCarta();
        if (!jugador.tieneManoLlena()) {
            jugador.recibirCarta(cartaRobada);
        }
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
    }

    /**
     * Aplica el efecto especial de la primera carta, según las reglas de UNO.
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void aplicarEfectoPrimeraCarta() throws InterruptedException, ReiniciarJuego {
        Carta carta = tablero().verCartaEnLaMesa();
        System.out.println("Primera carta en la mesa: " + carta);

        if (carta instanceof CartaEspecial especial) {
            switch (especial.getTiposEspeciales()) {
                case CHUPATE2:
                    efectoChupate2();
                    break;
                case CHUPATE4:
                    efectoChupate4(carta);
                    break;
                case CAMBIOCOLOR:
                    efectoCambioColor(carta);
                    break;
                case BLOQUEO:
                    efectoBloqueo();
                    break;
                case REVERSA:
                    efectoReversa();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Método que desarrolla el efecto del chupate dos cuando sale de primera carta
     * 
     * @param 'ninguno'
     */
    private static void efectoChupate2() {
        Jugador primerJugador = actual();
        for (int i = 0; i < 2; i++) {
            if (!primerJugador.tieneManoLlena())
                primerJugador.recibirCarta(tablero().tirarCarta());
        }
        System.out.println(primerJugador.getNombre() + " roba 2 cartas y pierde turno (efecto +2 inicial)");
        siguiente();
    }

    /**
     * Método que desarrolla el efecto del chupate cuatro cuando sale de primera
     * carta
     * 
     * @param carta el chupate 4 que volvera a la baraja
     * @throws InterruptedException para los thread sleeps
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void efectoChupate4(Carta carta) throws InterruptedException, ReiniciarJuego {
        System.out.println("¡El +4 no puede ser carta inicial! Se devuelve y se roba otra.");
        tablero().meter(carta);
        tablero().getChupona().barajar();
        tablero().dejar(tablero().tirarCarta());
        aplicarEfectoPrimeraCarta();
    }

    /**
     * Método que desarrolla el efecto del cambio de color cuando sale de primera
     * carta
     * 
     * @param carta el cambio de color que será actualizado
     * @throws ReiniciarJuego para reinciar el juego cuando se quiera
     */
    public static void efectoCambioColor(Carta carta) throws ReiniciarJuego {
        System.out.println("Ha salido un cambio de color. El primer jugador elige color.");
        int color = Menus.menuCambioColor();
        switch (color) {
            case 1:
                carta.setColor(Enumerados.Color.ROJO);
                break;
            case 2:
                carta.setColor(Enumerados.Color.AMARILLO);
                break;
            case 3:
                carta.setColor(Enumerados.Color.VERDE);
                break;
            case 4:
                carta.setColor(Enumerados.Color.AZUL);
                break;
            default:
                System.out.println("Ese color no existe");
                break;
        }
    }

    /**
     * Método que desarrolla el efecto del bloqueo cuando sale de primera carta
     * 
     * @param 'ninguno'
     */
    public static void efectoBloqueo() {
        System.out.println("Ha salido un 'Bloqueo': el primer jugador pierde el turno.");
        siguiente();
    }

    /**
     * Método que desarrolla el efecto de la reversa cuando sale de primera carta
     * 
     * @param 'ninguno'
     */
    public static void efectoReversa() {
        System.out.println(
                "Ha salido un 'Reversa': se invierte el sentido y el primer jugador pierde el turno.");
        siguiente();
        turnos().cambiarSentido();
    }
}
package MetodosSecundarios;

import java.util.ArrayList;
import java.util.Collections;
import Excepciones.CartaLanzadaNoValida;
import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.CartaEspecial;
import Objetos.Jugador;

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
    public static void repartoInicial(ArrayList<String> nombresCargados) {
        for (int i = 0; i < UnoEngine.nJugadores(); i++) {
            UnoEngine.contextoPartida.getJugadores().put(i, new Jugador(nombresCargados.get(i)));
            for (int c = 0; c < 7; c++) {
                UnoEngine.contextoPartida.getJugadores().get(i)
                        .recibirCarta(UnoEngine.tablero().tirarCarta());
            }
        }
    }

    /**
     * Método que sirve para ordenar la baraja de los jugadores
     */
    public static void ordenarBarajaJugadores() {
        for (int i = 0; i < UnoEngine.nJugadores(); i++) {
            Collections.sort(UnoEngine.contextoPartida.getJugadores().get(i).getMano().getLista());
        }
    }

    /**
     * Método que roba una carta de la baraja chupona
     * 
     * @param 'ninguno'
     */
    public static void robarCarta() {
        Carta cartaRobada;
        cartaRobada = UnoEngine.tablero().tirarCarta();
        if (!UnoEngine.jugador.tieneManoLlena()) {
            UnoEngine.jugador.recibirCarta(cartaRobada);
            System.out.println("Has recibido un: " + cartaRobada);
        }
    }

    /**
     * Método que mira si la carta que se acaba de tirar es valida o no
     * 
     * @param 'ninguno'
     */
    public static boolean cartaSacada(int opcionCarta) throws CartaLanzadaNoValida {
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        cartaSeleccionada = UnoEngine.jugador.getMano().obtener(opcionCarta);
        cartaEnMesa = UnoEngine.tablero().verCartaEnLaMesa();
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
        cartaRobada = UnoEngine.tablero().tirarCarta();
        if (!UnoEngine.jugador.tieneManoLlena()) {
            UnoEngine.jugador.recibirCarta(cartaRobada);
        }
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
    }

    /**
     * Aplica el efecto especial de la primera carta, según las reglas de UNO.
     */
    public static void aplicarEfectoPrimeraCarta() throws InterruptedException, ReiniciarJuego {
        Carta carta = UnoEngine.tablero().verCartaEnLaMesa();
        System.out.println("Primera carta en la mesa: " + carta);

        if (carta instanceof CartaEspecial especial) {
            switch (especial.getTiposEspeciales()) {
                case CHUPATE2:
                    Jugador primerJugador = UnoEngine.actual();
                    for (int i = 0; i < 2; i++) {
                        if (!primerJugador.tieneManoLlena())
                            primerJugador.recibirCarta(UnoEngine.tablero().tirarCarta());
                    }
                    System.out.println(primerJugador.getNombre() + " roba 2 cartas y pierde turno (efecto +2 inicial)");
                    UnoEngine.siguiente();
                    break;
                case CHUPATE4:
                    System.out.println("¡El +4 no puede ser carta inicial! Se devuelve y se roba otra.");
                    UnoEngine.tablero().meter(carta);
                    UnoEngine.tablero().getChupona().barajar();
                    UnoEngine.tablero().dejar(
                            UnoEngine.tablero().tirarCarta());
                    aplicarEfectoPrimeraCarta();
                    break;
                case CAMBIOCOLOR:
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
                    }
                    break;
                case BLOQUEO:
                    System.out.println("Ha salido un 'Bloqueo': el primer jugador pierde el turno.");
                    UnoEngine.siguiente();
                    Thread.sleep(Datos.milisegundos);
                    break;
                case REVERSA:
                    System.out.println(
                            "Ha salido un 'Reversa': se invierte el sentido y el primer jugador pierde el turno.");
                    UnoEngine.turnos().cambiarSentido();
                    if (UnoEngine.nJugadores() > 2) {
                        UnoEngine.siguiente();
                    }
                    Thread.sleep(Datos.milisegundos);
                    break;
                default:
                    break;
            }
        }
    }
}
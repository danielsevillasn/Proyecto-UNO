package MetodosSecundarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import Excepciones.CartaLanzadaNoValida;
import Objetos.Carta;
import Objetos.Jugador;
import Objetos.Tablero;

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
    public static void repartoInicial(int cantidadActualJugadores, HashMap<Integer, Jugador> jugadores, Tablero tablero, ArrayList<String> nombresCargados) {
        for (int i = 0; i < cantidadActualJugadores; i++) {
            jugadores.put(i, new Jugador(nombresCargados.get(i)));
            for (int c = 0; c < 7; c++) {
                jugadores.get(i).recibirCarta(tablero.tirarCarta());
            }
        }
    }

    /**
     * Método que sirve para ordenar la baraja de los jugadores
     */
    public static void ordenarBarajaJugadores(int cantidadActualJugadores, HashMap<Integer, Jugador> jugadores) {
        for (int i = 0; i < cantidadActualJugadores; i++) {
            Collections.sort(jugadores.get(i).getMano().getLista());
        }
    }

    /**
     * Método que roba una carta de la baraja chupona
     * 
     * @param 'ninguno'
     */
    public static void robarCarta(Jugador jugador, Tablero tablero) {
        Carta cartaRobada;
        cartaRobada = tablero.tirarCarta();
        if (!jugador.tieneManoLlena()) {
            jugador.recibirCarta(cartaRobada);
            System.out.println("Has recibido un: " + cartaRobada);
        }
    }

    /**
     * Método que mira si la carta que se acaba de tirar es valida o no
     * 
     * @param 'ninguno'
     */
    public static boolean cartaSacada(Jugador jugador, Tablero tablero, int opcionCarta) throws CartaLanzadaNoValida {
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        cartaSeleccionada = jugador.getMano().obtener(opcionCarta);
        cartaEnMesa = tablero.verCartaEnLaMesa();
        return cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
    }

    /**
     * Método que funciona si la carta sacada no es valida y chupa una carta
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void cartaSacadaNoValida(Jugador jugador, Tablero tablero) throws InterruptedException {
        Carta cartaRobada;
        cartaRobada = tablero.tirarCarta();
        if (!jugador.tieneManoLlena()) {
            jugador.recibirCarta(cartaRobada);
        }
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
    }
}
package MetodosSecundarios;

import java.util.ArrayList;
import java.util.Collections;
import Excepciones.CartaLanzadaNoValida;
import Objetos.Carta;
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
        for (int i = 0; i < UnoEngine.contextoPartida.getCantidadJugadores(); i++) {
            UnoEngine.contextoPartida.getJugadores().put(i, new Jugador(nombresCargados.get(i)));
            for (int c = 0; c < 7; c++) {
                UnoEngine.contextoPartida.getJugadores().get(i).recibirCarta(UnoEngine.contextoPartida.getTablero().tirarCarta());
            }
        }
    }

    /**
     * Método que sirve para ordenar la baraja de los jugadores
     */
    public static void ordenarBarajaJugadores() {
        for (int i = 0; i < UnoEngine.contextoPartida.getCantidadJugadores(); i++) {
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
        cartaRobada = UnoEngine.contextoPartida.getTablero().tirarCarta();
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
        cartaEnMesa = UnoEngine.contextoPartida.getTablero().verCartaEnLaMesa();
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
        cartaRobada = UnoEngine.contextoPartida.getTablero().tirarCarta();
        if (!UnoEngine.jugador.tieneManoLlena()) {
            UnoEngine.jugador.recibirCarta(cartaRobada);
        }
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
    }
}
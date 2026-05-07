package MetodosSecundarios;

import java.util.ArrayList;
import java.util.Collections;
import Excepciones.CartaLanzadaNoValida;
import Objetos.Carta;
import Objetos.Jugador;
import Objetos.PartidaContexto;

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
    public static void repartoInicial(PartidaContexto ctx, ArrayList<String> nombresCargados) {
        for (int i = 0; i < ctx.getCantidadJugadores(); i++) {
            ctx.getJugadores().put(i, new Jugador(nombresCargados.get(i)));
            for (int c = 0; c < 7; c++) {
                ctx.getJugadores().get(i).recibirCarta(ctx.getTablero().tirarCarta());
            }
        }
    }

    /**
     * Método que sirve para ordenar la baraja de los jugadores
     */
    public static void ordenarBarajaJugadores(PartidaContexto ctx) {
        for (int i = 0; i < ctx.getCantidadJugadores(); i++) {
            Collections.sort(ctx.getJugadores().get(i).getMano().getLista());
        }
    }

    /**
     * Método que roba una carta de la baraja chupona
     * 
     * @param 'ninguno'
     */
    public static void robarCarta(Jugador jugador, PartidaContexto ctx) {
        Carta cartaRobada;
        cartaRobada = ctx.getTablero().tirarCarta();
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
    public static boolean cartaSacada(Jugador jugador, PartidaContexto ctx, int opcionCarta) throws CartaLanzadaNoValida {
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        cartaSeleccionada = jugador.getMano().obtener(opcionCarta);
        cartaEnMesa = ctx.getTablero().verCartaEnLaMesa();
        return cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
    }

    /**
     * Método que funciona si la carta sacada no es valida y chupa una carta
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void cartaSacadaNoValida(Jugador jugador, PartidaContexto ctx) throws InterruptedException {
        Carta cartaRobada;
        cartaRobada = ctx.getTablero().tirarCarta();
        if (!jugador.tieneManoLlena()) {
            jugador.recibirCarta(cartaRobada);
        }
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
    }
}
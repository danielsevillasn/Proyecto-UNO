package MetodosSecundarios;

import Excepciones.CartaLanzadaNoValida;
import Excepciones.ReiniciarJuego;

import java.util.ArrayList;
import java.util.HashMap;

import Enumerados.Tipos;
import Objetos.Carta;
import Objetos.Jugador;
import Objetos.Tablero;
import Objetos.Turno;
import Objetos.PartidaContexto;

/**
 * Clase que gestiona el flujo del juego
 * 
 * @author DaniS y Libio
 */
public class UnoEngine {

    private static Tablero tablero;
    private static Turno controladorTurnos;
    private static HashMap<Integer, Jugador> jugadores;
    protected static PartidaContexto contextoPartida;
    private static boolean fin = false;
    private static boolean cartaValida = false;
    private static int opcionCarta = -1;
    private static int cantidadActualJugadores;
    protected static Jugador jugador;

    /**
     * Lógica principal de la partida
     * Controla el flujo de turnos,
     * validación de jugadas y condiciones de victoria
     * Se inicia el tablero con todos sus componentes
     * 
     * @param nombresCargados array con todos los nombres (por referencia)
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void partida(ArrayList<String> nombresCargados) throws InterruptedException, ReiniciarJuego {
        // Inicialización de componentes de juego
        tablero = new Tablero();
        controladorTurnos = new Turno();
        tablero.inicializarBaraja();
        fin = false;

        cantidadActualJugadores = Juego.cantidadActualJugadores;
        jugadores = new HashMap<>();

        contextoPartida = new PartidaContexto(tablero, controladorTurnos, jugadores, cantidadActualJugadores);

        Mecanicas.repartoInicial(nombresCargados);

        tablero.dejar(tablero.tirarCarta());

        Mecanicas.aplicarEfectoPrimeraCarta();

        flujoDeLaPartida();

        Pantallas.PantallaFinal();
    }

    /**
     * Metodo que reproduce el flujo de la partida
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    private static void flujoDeLaPartida() throws InterruptedException, ReiniciarJuego {
        while (!fin) {
            // Escoge al jugador correspondiente, basado en el turno actual
            Mecanicas.ordenarBarajaJugadores();
            jugador = actual();

            // Imprime el tablero, con el turno, el jugador y las cartas
            verTablero();

            // Resolucion de la carta que quieres sacar
            accionSacarCarta();

            // Validación carta sacada
            cartaSacadaValida();

            Thread.sleep(Datos.milisegundos);
            System.out.println("\n  * " + tablero + " *");
            Thread.sleep(Datos.milisegundos);

            // Cambio de turno
            Datos.pulsaEnter();

            // Si nadie ha ganado, pasamos al siguiente turno
            if (!fin)
                controladorTurnos.siguiente(cantidadActualJugadores);
        }
    }

    /**
     * Método que muestra la interfaz gráfica del tablero excepto la de la accion
     * 
     * @param 'ninguno'
     * @throws InterruptedException Para los saltos de lineas
     */
    private static void verTablero() throws InterruptedException {
        Datos.saltoDeLineas();
        System.out.println("\n--- TURNO DE: " + jugador.getNombre() + " ---");
        System.out.println("  - " + controladorTurnos + " -");
        System.out.println("Mesa: " + tablero.verCartaEnLaMesa());

        // Mostrar la mano del jugador actual
        for (int i = 0; i < jugador.getNumCartas(); i++) {
            System.out.print(i + ":" + jugador.getMano().obtener(i) + " ");
        }
        System.out.println(jugador.getNumCartas() + ":[ROBAR]");
    }

    /**
     * Método que sirve para sacar la carta que quieres o para robar carta
     * 
     * @param 'ninguno'
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     * @throws InterruptedException para los thread sleep
     */
    private static void accionSacarCarta() throws ReiniciarJuego, InterruptedException {
        boolean salir = false;
        while (!salir) {
            opcionCarta = Datos.pedirEntero("Acción: ");
            if (opcionCarta == jugador.getNumCartas()) {
                // Opción Robar
                Mecanicas.robarCarta();
                cartaValida = false;
                salir = true;
            } else {
                try {
                    cartaValida = Mecanicas.cartaSacada(opcionCarta);
                    salir = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("La carta que quieres lanzar no esta dentro del limite de la baraja");
                } catch (NullPointerException e) {
                    System.out.println("No existe la carta seleccionada");
                } catch (CartaLanzadaNoValida e) {
                    System.out.println(e.getMessage());
                    Mecanicas.cartaSacadaNoValida();
                    cartaValida = false;
                    salir = true;
                }
            }
        }
    }

    /**
     * Método que funciona si la carta sacada es valida y la tira
     * 
     * @param 'ninguno'
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     * @throws InterruptedException para los thread sleep
     */
    private static void cartaSacadaValida() throws InterruptedException, ReiniciarJuego {
        Carta cartaTirada;
        if (cartaValida) {
            cartaTirada = jugador.jugarCarta(opcionCarta);

            if (cartaTirada.getTipo() == Tipos.ESPECIAL) {
                Efectos.efectosCartasEspeciales(cartaTirada);
            }

            tablero.dejar(cartaTirada);
            System.out.println("La carta que has tirado es: " + cartaTirada);
            // Si el jugador se queda sin cartas el juego termina
            if (jugador.getNumCartas() == 0) {
                fin = true;
                Menus.nombreJugador = jugador.getNombre();
            }
        }
    }

    //Metodos atajo
    //Sirven para reducir codigo en efectos y en mecanicas
    public static Jugador actual() {
        return contextoPartida.jugadorActual();
    }

    public static Tablero tablero() {
        return contextoPartida.getTablero();
    }

    public static Turno turnos() {
        return contextoPartida.getControladorTurnos();
    }

    public static int nJugadores() {
        return contextoPartida.getCantidadJugadores();
    }

    public static void siguiente() {
        contextoPartida.pasarSiguiente();
    }
}
package MetodosSecundarios;

import Excepciones.CartaLanzadaNoValida;
import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.Jugador;
import Objetos.Tablero;
import Objetos.Turno;

public class UnoEngine {

    private static Tablero tablero;
    private static Turno controladorTurnos;
    private static Jugador[] lista;
    private static boolean fin = false;
    private static boolean cartaValida = false;
    private static int opcionCarta = -1;
    private static int cantidadActualJugadores;

    /**
     * Lógica principal de la partida
     * Controla el flujo de turnos,
     * validación de jugadas y condiciones de victoria
     * Se inicia el tablero con todos sus componentes
     * 
     * @param cantidadActualJugadores variable tipo int
     * @param nombresCargados         array con todos los nombres
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void partida(String[] nombresCargados) throws InterruptedException, ReiniciarJuego {
        // Inicialización de componentes de juego
        tablero = new Tablero();
        controladorTurnos = new Turno();
        tablero.inicializar();
        fin = false;
        cantidadActualJugadores = Juego.cantidadActualJugadores;
        lista = new Jugador[cantidadActualJugadores];

        repartoInicial(nombresCargados);

        tablero.dejar(tablero.tirarCarta());

        flujoDeLaPartida();

        Pantallas.PantallaFinal();
    }

    /**
     * Metodo para repartir las cartas iniciales a todos los jugadores
     * 
     * @param nombresCargados nombres de los jugadores
     */
    private static void repartoInicial(String[] nombresCargados) {
        for (int i = 0; i < cantidadActualJugadores; i++) {
            lista[i] = new Jugador(nombresCargados[i]);
            for (int c = 0; c < 7; c++)
                lista[i].recibirCarta(tablero.tirarCarta());
        }
    }

    /**
     * Metodo que reproduce el flujo de la partida
     * 
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    private static void flujoDeLaPartida() throws InterruptedException, ReiniciarJuego {
        Jugador jugador;
        while (!fin) {
            jugador = lista[controladorTurnos.getActual()];

            verTablero(jugador);

            while (true) {
                opcionCarta = Datos.pedirEntero("Acción: ");
                if (opcionCarta == jugador.getNumCartas()) {
                    // Opción Robar
                    robarCarta(jugador);
                    cartaValida = false;
                    break;
                } else {
                    try {
                        cartaValida = cartaSacada(jugador);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("La carta que quieres lanzar no esta dentro del limite de la baraja");
                    } catch (NullPointerException e) {
                        System.out.println("No existe la carta seleccionada");
                    } catch (CartaLanzadaNoValida e) {
                        System.out.println(e.getMessage());
                        cartaSacadaNoValida(jugador);
                        break;
                    }
                }
            }

            cartaSacadaValida(jugador);

            Datos.pulsaEnter();
            Datos.saltoDeLineas();

            // Si nadie ha ganado, pasamos al siguiente turno
            if (!fin)
                controladorTurnos.siguiente(cantidadActualJugadores);
        }
    }

    /**
     * Método que muestra la interfaz gráfica del tablero excepto la de la accion
     * 
     * @param jugador objeto jugador que representa al jugador que le toca
     */
    private static void verTablero(Jugador jugador) {
        System.out.println("\n--- TURNO DE: " + jugador.getNombre() + " ---");
        System.out.println("    - " + controladorTurnos + " -");
        System.out.println("Mesa: " + tablero.verCartaEnLaMesa());

        // Mostrar la mano del jugador actual
        for (int i = 0; i < jugador.getNumCartas(); i++) {
            System.out.print(i + ":" + jugador.mano[i] + " ");
        }
        System.out.println(jugador.getNumCartas() + ":[ROBAR]");
    }

    /**
     * Método que roba una carta de la baraja chupona
     * 
     * @param jugador objeto jugador que representa al jugador que le toca
     */
    private static void robarCarta(Jugador jugador) {
        Carta cartaRobada;
        cartaRobada = tablero.tirarCarta();
        jugador.recibirCarta(cartaRobada);
        System.out.println("Has recibido un: " + cartaRobada);
    }

    /**
     * Método que mira si la carta que se acaba de tirar es valida o no
     * 
     * @param jugador objeto jugador que representa al jugador que le toca
     */
    private static boolean cartaSacada(Jugador jugador) throws CartaLanzadaNoValida {
        boolean cartaValida;
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        cartaSeleccionada = jugador.mano[opcionCarta];
        cartaEnMesa = tablero.verCartaEnLaMesa();
        cartaValida = cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
        return cartaValida;
    }

    /**
     * Método que funciona si la carta sacada es valida y la tira
     * 
     * @param jugador objeto jugador que representa al jugador que le toca
     */
    private static void cartaSacadaValida(Jugador jugador) {
        Carta cartaTirada;
        if (cartaValida) {
            cartaTirada = jugador.jugarCarta(opcionCarta);
            tablero.dejar(cartaTirada);
            System.out.println("La carta que has tirado es: " + cartaTirada);
            // Si el jugador se queda sin cartas el juego termina
            if (jugador.getNumCartas() == 0) {
                fin = true;
                Pantallas.nombreJugador = jugador.getNombre();
            }
        }
    }

    /**
     * Método que funciona si la carta sacada no es valida y chupa una carta
     * 
     * @param jugador objeto jugador que representa al jugador que le toca
     * @throws InterruptedException para los thread sleep
     */
    private static void cartaSacadaNoValida(Jugador jugador) throws InterruptedException {
        Carta cartaRobada;
        cartaRobada = tablero.tirarCarta();
        jugador.recibirCarta(cartaRobada);
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
        cartaValida = false;
    }

    /**
     * Configura el número de jugadores para el juego
     * 
     * @param cantidadActualJugadores variable tipo int
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static int configurarJugadores() throws InterruptedException, ReiniciarJuego {
        int numJugadores;
        Pantallas.PantallaJugadores();

        numJugadores = pedirNumJugadores();

        cantidadActualJugadores = numJugadores;

        // Nombres por defecto sobreescritos
        Juego.nombresCargados = new String[cantidadActualJugadores];
        for (int i = 0; i < cantidadActualJugadores; i++) {
            Juego.nombresCargados[i] = Datos.pedirCadena("Nombre Jugador " + (i + 1) + ": ");
        }

        Pantallas.jugadores = "" + cantidadActualJugadores;
        return cantidadActualJugadores;
    }

    /**
     * Método que pide el numero de jugadores y que comprueba que no se pase del rango habilitado
     * 
     * @return valor entero que representa el numero de jugadores
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    private static int pedirNumJugadores() throws InterruptedException, ReiniciarJuego {
        int numJugadores;
        boolean rangoJugadores = false;
        do {
            numJugadores = Datos.pedirEntero("¿Cuántos jugadores (2-6)? ");
            if (numJugadores >= 2 && numJugadores <= 6) {
                rangoJugadores = true;
            } else {
                Datos.entradaIncorrecta();
            }
        } while (!rangoJugadores);
        return numJugadores;
    }

    /**
     * Método para seleccionar el modo de juego
     * 
     * @param ModoDeJuego variable tipo String que representa que modo de juego esta seleccionado
     * @return ModoDeJuego variable tipo String que representa que modo de juego se ha seleccionado
     */
    public static String modoDeJuegoSeleccionado(String ModoDeJuego) {
        if (ModoDeJuego.equals("1")) {
            ModoDeJuego = "Clásico";
        } else if (ModoDeJuego.equals("2") || ModoDeJuego.equals("3")) {
            ModoDeJuego = "Otro";
        }
        return ModoDeJuego;
    }
}

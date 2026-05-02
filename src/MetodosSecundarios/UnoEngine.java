package MetodosSecundarios;

import Excepciones.CartaLanzadaNoValida;
import Excepciones.NombreUsuarioNoValido;
import Excepciones.ReiniciarJuego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import Enumerados.Color;
import Enumerados.Tipos;
import Objetos.Carta;
import Objetos.CartaEspecial;
import Objetos.CartaNormal;
import Objetos.Jugador;
import Objetos.Tablero;
import Objetos.Turno;

/**
 * Clase que gestiona el flujo del juego
 * 
 * @author DaniS y Libio
 */
public class UnoEngine {

    private static Tablero tablero;
    private static Turno controladorTurnos;
    private static HashMap<Integer, Jugador> jugadores;
    private static boolean fin = false;
    private static boolean cartaValida = false;
    private static int opcionCarta = -1;
    private static int cantidadActualJugadores;
    private static Jugador jugador;

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

        repartoInicial(nombresCargados);

        tablero.dejar(tablero.tirarCarta());

        flujoDeLaPartida();

        Pantallas.PantallaFinal();
    }

    /**
     * Metodo para repartir las cartas iniciales a todos los jugadores
     * 
     * @param nombresCargados nombres de los jugadores (por referencia)
     */
    private static void repartoInicial(ArrayList<String> nombresCargados) {
        for (int i = 0; i < cantidadActualJugadores; i++) {
            jugadores.put(i, new Jugador(nombresCargados.get(i)));
            for (int c = 0; c < 7; c++) {
                jugadores.get(i).recibirCarta(tablero.tirarCarta());
            }
        }
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
            ordenarBarajaJugadores();
            jugador = jugadores.get(controladorTurnos.getActual());

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
            Datos.saltoDeLineas();

            // Si nadie ha ganado, pasamos al siguiente turno
            if (!fin)
                controladorTurnos.siguiente(cantidadActualJugadores);
        }
    }

    private static void ordenarBarajaJugadores(){
        for (int i = 0; i < cantidadActualJugadores; i++) {
            Collections.sort(jugadores.get(i).getMano().getLista());
        }
    }

    /**
     * Método que muestra la interfaz gráfica del tablero excepto la de la accion
     * 
     * @param 'ninguno'
     */
    private static void verTablero() {
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
                robarCarta();
                cartaValida = false;
                salir = true;
            } else {
                try {
                    cartaValida = cartaSacada();
                    salir = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("La carta que quieres lanzar no esta dentro del limite de la baraja");
                } catch (NullPointerException e) {
                    System.out.println("No existe la carta seleccionada");
                } catch (CartaLanzadaNoValida e) {
                    System.out.println(e.getMessage());
                    cartaSacadaNoValida();
                    salir = true;
                }
            }
        }
    }

    /**
     * Método que roba una carta de la baraja chupona
     * 
     * @param 'ninguno'
     */
    private static void robarCarta() {
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
    private static boolean cartaSacada() throws CartaLanzadaNoValida {
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        cartaSeleccionada = jugador.getMano().obtener(opcionCarta);
        cartaEnMesa = tablero.verCartaEnLaMesa();
        return cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
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
                efectosCartasEspeciales(cartaTirada);
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

    /**
     * Método que recoge todos los efectos de las cartas especiales implementadas y
     * los hace funcionar
     * 
     * @param cartaTirada Carta que ha sido tirada por el jugador
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    private static void efectosCartasEspeciales(Carta cartaTirada) throws InterruptedException, ReiniciarJuego {
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
        controladorTurnos.cambiarSentido();

        // Si son solo 2 jugadores entonces saltamos el turno del jugador que le
        // precedia
        if (cantidadActualJugadores == 2) {
            controladorTurnos.siguiente(cantidadActualJugadores);
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
        jugadorSaltado = jugadores.get(hallarIdJugador()); // Consultamos quién va a ser bloqueado
        System.out.println("¡" + jugadorSaltado.getNombre() + " ha sido bloqueado y pierde su turno!");
        controladorTurnos.siguiente(cantidadActualJugadores);
    }

    /**
     * Método que halla el id del jugador seleccionado mediante un sistema parecido
     * al de los turnos
     * 
     * @return entero que representa el id del jugador actual
     */
    private static int hallarIdJugador() {
        // Si el sentido es el normal entonces
        int actual = controladorTurnos.getActual();
        int sentido = controladorTurnos.getSentido(); // 1 o -1

        // Sumamos la cantidad de jugadores para evitar números negativos al restar
        // El operador % (módulo) asegura que el índice siempre esté en el rango
        // correcto
        return (actual + sentido + cantidadActualJugadores) % cantidadActualJugadores;
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
    private static void chupate(int numeroCartas, Carta cartaTirada) throws InterruptedException, ReiniciarJuego {
        Jugador jugadorChupete;
        jugadorChupete = jugadores.get(hallarIdJugador());
        if (numeroCartas == 4) {
            cambiarColor(cartaTirada);
        }
        System.out.println("¡" + jugadorChupete.getNombre() + " chupa " + numeroCartas + " cartas y pierde su turno!");
        chuparCartas(numeroCartas, jugadorChupete);
        controladorTurnos.siguiente(cantidadActualJugadores);
    }

    /**
     * Método que sirve para realizar la accion de chupar cartas
     * 
     * @param numeroCartas expresa la cantidad de cartas que el jugador ha de chupar
     * @param j            recoge el jugador que tiene que chupar las cartas
     * @throws InterruptedException para los thread sleep
     */
    private static void chuparCartas(int numeroCartas, Jugador j) throws InterruptedException {
        Carta cartaRobada = new CartaNormal();
        for (int i = 0; i < numeroCartas; i++) {
            if (!j.tieneManoLlena()) {
                cartaRobada = tablero.tirarCarta();
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
     * Método que funciona si la carta sacada no es valida y chupa una carta
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    private static void cartaSacadaNoValida() throws InterruptedException {
        Carta cartaRobada;
        cartaRobada = tablero.tirarCarta();
        if (!jugador.tieneManoLlena()) {
            jugador.recibirCarta(cartaRobada);
        }
        System.out.println("!CHUPAS UNA CARTA!\n");
        Thread.sleep(Datos.milisegundos);
        System.out.println("Has recibido un: " + cartaRobada);
        cartaValida = false;
    }

    /**
     * Configura el número de jugadores para el juego
     * 
     * @param 'ninguno'
     * @return valor entero que representa la cantidad actual de jugadores
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static int configurarJugadores() throws InterruptedException, ReiniciarJuego {
        int numJugadores;

        numJugadores = Menus.menuJugadores();

        cantidadActualJugadores = numJugadores;

        Juego.nombresCargados.clear();

        pedirNombreJugadores();

        Menus.jugadores = "" + cantidadActualJugadores;
        return cantidadActualJugadores;
    }

    /**
     * Método que pide el nombre de los jugadores, valida segun las condiciones
     * impuestas que esta correcto y lo mete en el array de los nombres del juego
     * 
     * @throws ReiniciarJuego para reinciar el juego cuando se quiera
     */
    private static void pedirNombreJugadores() throws ReiniciarJuego {
        boolean nombreValido;
        String nombre;
        for (int i = 0; i < cantidadActualJugadores; i++) {
            nombreValido = false;
            do {
                try {
                    nombre = Datos.pedirCadena("Nombre Jugador " + (i + 1) + ": ");

                    validacionNombre(nombre);

                    Juego.nombresCargados.add(nombre);
                    nombreValido = true;
                } catch (NombreUsuarioNoValido e) {
                    System.out.println(e.getMessage());
                    System.out.println("Para que tu usuario sea válido, debe cumplir:");

                    // Imprime las sugerencias del array list sugerencias
                    for (String sugerencia : e.getSugerencias()) {
                        System.out.println("- " + sugerencia);
                    }
                }
            } while (!nombreValido);
        }
        Datos.pulsaEnter();
    }

    /**
     * Método que valida el nombre de un usuario de tal forma que siga las
     * condiciones impuestas en la excepcion
     * 
     * @param nombreUsuario variable tipo String que representa el nombre del
     *                      usuario
     * @throws NombreUsuarioNoValido excepcion que recoge todas las condiciones para
     *                               luego mostrarlas en caso de que no se cumplan
     */
    public static void validacionNombre(String nombreUsuario) throws NombreUsuarioNoValido {
        // Inicalizamos la excepcion pasando por parametro el nombre
        NombreUsuarioNoValido nombreUsuarioNoValido = new NombreUsuarioNoValido(nombreUsuario);

        // Si la excepción detecta que se ha cumplido alguna condicion, por lo cual hay
        // sugerencias, lanzamos una excepcion
        if (!nombreUsuarioNoValido.getSugerencias().isEmpty()) {
            throw nombreUsuarioNoValido;
        }

        // Si la excepcion no ocurre entonces el usuario se registra
        System.out.println("Usuario " + nombreUsuario + " registrado con éxito.");
    }
}

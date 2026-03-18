package MetodosSecundarios;

import Excepciones.CartaLanzadaNoValida;
import Excepciones.ReiniciarJuego;
import Objetos.Carta;
import Objetos.Jugador;
import Objetos.Tablero;
import Objetos.Turno;

public class UnoEngine {

    /**
     * Lógica principal de la partida
     * Controla el flujo de turnos,
     * validación de jugadas y condiciones de victoria
     * Se inicia el tablero con todos sus componentes
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void partida(int cantidadActualJugadores, String[] nombresCargados) throws InterruptedException, ReiniciarJuego {
        // Inicialización de componentes de juego
        Tablero t = new Tablero();
        t.inicializar();
        Turno controlador = new Turno();
        Jugador[] lista = new Jugador[cantidadActualJugadores];
        boolean fin = false;
        boolean cartaValida;
        int opcionCarta;
        Jugador j;
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        Carta cartaRobada;
        Carta cartaTirada;

        // Reparto inicial, 7 cartas por jugador
        for (int i = 0; i < cantidadActualJugadores; i++) {
            lista[i] = new Jugador(nombresCargados[i]);
            for (int c = 0; c < 7; c++)
                lista[i].recibirCarta(t.tirarCarta());
        }

        // Coloca la primera carta en la mesa para empezar
        t.dejar(t.tirarCarta());

        // Bucle de juego, hasta que alguien se quede sin cartas
        while (!fin) {
            j = lista[controlador.actual];
            System.out.println("\n--- TURNO DE: " + j.getNombre() + " ---");
            System.out.println("Mesa: " + t.verCartaEnLaMesa());

            // Mostrar la mano del jugador actual
            for (int i = 0; i < j.getNumCartas(); i++) {
                System.out.print(i + ":" + j.mano[i] + " ");
            }
            System.out.println(j.getNumCartas() + ":[ROBAR]");

            while (true) {
                opcionCarta = Datos.pedirEntero("Acción: ");
                if (opcionCarta == j.getNumCartas()) {
                    // Opción Robar
                    cartaRobada = t.tirarCarta();
                    j.recibirCarta(cartaRobada);
                    System.out.println("Has recibido un: " + cartaRobada);
                    cartaValida = false;
                    break;
                } else {
                    try {
                        cartaSeleccionada = j.mano[opcionCarta];
                        cartaEnMesa = t.verCartaEnLaMesa();
                        cartaValida = cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("La carta que quieres lanzar no esta dentro del limite de la baraja");
                    } catch (NullPointerException e) {
                        System.out.println("No existe la carta seleccionada");
                    } catch (CartaLanzadaNoValida e) {
                        System.out.println(e.getMessage());
                        cartaRobada = t.tirarCarta();
                        j.recibirCarta(cartaRobada);
                        System.out.println("!CHUPAS UNA CARTA!\n");
                        Thread.sleep(1000);
                        System.out.println("Has recibido un: " + cartaRobada);
                        cartaValida = false;
                        break;
                    }
                }
            }
            // Si la carta es válida entonces tira la carta
            if (cartaValida) {
                cartaTirada = j.jugarCarta(opcionCarta);
                t.dejar(cartaTirada);
                System.out.println("La carta que has tirado es: " + cartaTirada);
                // Condición de victoria: 0 cartas
                if (j.getNumCartas() == 0) {
                    fin = true;
                    pantallas.NombreJugador = j.getNombre();
                }
            }
            Datos.pulsaEnter();
            Datos.saltoDeLineas();

            // Si nadie ha ganado, pasamos al siguiente turno
            if (!fin)
                controlador.siguiente(cantidadActualJugadores);
        }
        // Mostrar pantalla de ganador
        pantallas.PantallaFinal();
    }

    /**
     * Configura el número de jugadores para el juego
     * 
     * @param nombresCargados
     * @param cantidadActualJugadores
     * @throws InterruptedException para los thread sleep
     */
    public static int configurarJugadores(int cantidadActualJugadores) throws InterruptedException, ReiniciarJuego {
        int numJugadores;
        boolean rangoJugadores = false;
        pantallas.PantallaJugadores();

        do {
            numJugadores = Datos.pedirEntero("¿Cuántos jugadores (2-6)? ");
            if (numJugadores >= 2 && numJugadores <= 6) {
                rangoJugadores = true;
            } else {
                Datos.entradaIncorrecta();
            }
        } while (!rangoJugadores);

        cantidadActualJugadores = numJugadores;

        // Nombres por defecto sobreescritos
        Juego.nombresCargados = new String[cantidadActualJugadores];
        for (int i = 0; i < cantidadActualJugadores; i++) {
            Juego.nombresCargados[i] = Datos.pedirCadena("Nombre Jugador " + (i + 1) + ": ");
        }

        pantallas.Jugadores = "" + cantidadActualJugadores;
        return cantidadActualJugadores;
    }

    /**
     * Método para seleccionar el modo de juego
     * 
     * @param ModoDeJuego
     * @return ModoDeJuego
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

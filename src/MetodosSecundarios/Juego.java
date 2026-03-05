package MetodosSecundarios;

import Excepciones.CartaLanzadaNoValida;
import Objetos.Carta;
import Objetos.Jugador;
import Objetos.Tablero;
import Objetos.Turno;

import java.util.Scanner;

/**
 * Clase que estructurada mediante una serie de métodos para todo el juego
 *
 * @author DaniS y Libio
 */
public class Juego {

    static Scanner s = new Scanner(System.in);
    // Configuración inicial por defecto
    private String[] nombresCargados = { "Jugador 1", "Jugador 2" };
    private int cantidadActual = 2;

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     */
    public void ejecutarSistemaCompleto() throws InterruptedException {
        String opcion1 = "";
        String opcion2;
        boolean salir = false;
        pantallas.PantallaUNO();

        while (!opcion1.equals("-1")) {
            opcion1 = pantallas.PantallaInicio();
            switch (opcion1) {
                case "1":
                    while (!salir) {
                        opcion2 = pantallas.PantallaMenu();

                        switch (opcion2) {
                            case "1": // Configurar modo de juego
                                pantallas.ModoDeJuego = pantallas.PantallaModosDeJuego();
                                break;
                            case "2": // Configurar nombres y cantidad de jugadores
                                configurarJugadores();
                                break;
                            case "3": // Mostrar instrucciones
                                pantallas.PantallaReglas();
                                break;
                            case "4": // Iniciar una partida
                                Datos.saltoDeLíneas();
                                partida();
                                break;
                            case "5": // Salir del programa
                                salir = true;
                                break;
                            default:
                                System.out.println(
                                        "El mensaje introducido por pantalla no es valido, escoge una de las opciones");
                                break;
                        }
                    }
                    break;
                case "2":
                    opcion1 = "-1";
                    break;
                default:
                    System.out.println("El mensaje introducido por pantalla no es valido, escoge una de las opciones");
                    break;
            }
        }
    }

    /**
     * Solicita por consola el número de participantes y sus respectivos nombres.
     */
    public void configurarJugadores() throws InterruptedException {
        int numJugadores;
        boolean rangoJugadores = false;
        pantallas.PantallaJugadores();

        do {
            numJugadores = Datos.pedirEntero("¿Cuántos jugadores (2-4)? ");
            if (numJugadores >= 2 && numJugadores <= 4) {
                rangoJugadores = true;
            } else {
                System.out.println("Error: El número debe estar entre 2 y 4.");
            }
        } while (!rangoJugadores);

        cantidadActual = numJugadores;

        // Nombres por defecto sobreescritos
        nombresCargados = new String[cantidadActual];
        for (int i = 0; i < cantidadActual; i++) {
            nombresCargados[i] = Datos.pedirCadena("Nombre Jugador " + (i + 1) + ": ");
        }

        pantallas.Jugadores = String.valueOf(cantidadActual);
    }

    /**
     * Lógica principal de la partida
     * Controla el flujo de turnos,
     * validación de jugadas y condiciones de victoria
     * Se inicia el tablero con todos sus componentes
     */
    private void partida() throws InterruptedException {
        // Inicialización de componentes de juego
        Tablero t = new Tablero();
        t.inicializar();
        Turno controlador = new Turno();
        Jugador[] lista = new Jugador[cantidadActual];
        boolean fin = false;
        boolean cartaValida;
        int opcionCarta;
        Jugador j;
        Carta cartaSeleccionada;
        Carta cartaEnMesa;
        Carta cartaRobada;
        Carta cartaTirada;

        // Reparto inicial, 7 cartas por jugador
        for (int i = 0; i < cantidadActual; i++) {
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
                }else{
                    try{
                        cartaSeleccionada = j.mano[opcionCarta];
                        cartaEnMesa = t.verCartaEnLaMesa();
                        cartaValida = cartaSeleccionada.puedePonerseSobre(cartaEnMesa);
                        break;
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("La carta que quieres lanzar no esta dentro del límite de la baraja");
                    }catch (NullPointerException e){
                        System.out.println("No existe la carta seleccionada");
                    }catch (CartaLanzadaNoValida e) {
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
            if(cartaValida){
                cartaTirada = j.jugarCarta(opcionCarta);
                t.dejar(cartaTirada);
                System.out.println("La carta que has tirado es: " + cartaTirada);
                // Condición de victoria: 0 cartas
                if (j.getNumCartas() == 0) {
                    fin = true;
                    pantallas.NombreJugador = j.getNombre();
                }
            }
            System.out.println("\nDale enter para pasar el turno...");
            s.nextLine();
            Datos.saltoDeLíneas();

            // Si nadie ha ganado, pasamos al siguiente turno
            if (!fin)
                controlador.siguiente(cantidadActual);
        }
        // Mostrar pantalla de ganador
        pantallas.PantallaFinal();
    }
}
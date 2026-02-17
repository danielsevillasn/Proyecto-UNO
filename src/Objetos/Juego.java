package Objetos;

import MetodosAux.Datos;
import MetodosAux.pantallas;
import java.util.Scanner;

/**
 * Clase que estructurada mediante una serie de metodos para todo el juego
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
        String opcion2 = "";
        pantallas.PantallaUNO();

        while (!opcion1.equals("-1")) {
            opcion1 = pantallas.PantallaInicio();
            switch (opcion1) {
                case "1":
                    while (true) {
                        opcion2 = pantallas.PantallaMenu();

                        if (opcion2.equals("1")) { // Configurar modo de juego
                            pantallas.ModoDeJuego = pantallas.PantallaModosDeJuego();
                        } else if (opcion2.equals("2")) { // Configurar nombres y cantidad de jugadores
                            configurarJugadores();
                        } else if (opcion2.equals("3")) { // Mostrar instrucciones
                            pantallas.PantallaReglas();
                        } else if (opcion2.equals("4")) { // Iniciar una partida
                            Datos.saltoDeLíneas();
                            partida();
                        } else if (opcion2.equals("5")) { // Salir del programa
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
    public void configurarJugadores() {
        int numJugadores = -1;
        String opcion;
        boolean esNumero;

        while (numJugadores < 2 || numJugadores > 4) {
            opcion = Datos.pedirCadena("¿Cuántos jugadores (2-4)? ");

            if (opcion.isEmpty()) {
                System.out.println("No has introducido nada.");
            }else{
                esNumero = true;
                for (int i = 0; i < opcion.length(); i++) {
                    if (!Character.isDigit(opcion.charAt(i))) { // isDigit para que no se rompa para un acadena de
                                                                // caracteres
                        esNumero = false;
                    }
                }
    
                if (esNumero) {
                    numJugadores = Integer.parseInt(opcion);// Casting para pasar a int
                    if (numJugadores < 2 || numJugadores > 4) {
                        System.out.println("Solo 2, 3 o 4 jugadores.");
                    }
                } else {
                    System.out.println("Error: Introduce solo números (2-4).");
                    numJugadores = -1;
                }
            }
        }

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
     * 
     * Se inicia el tablero con todos sus componentes
     */
    private void partida() throws InterruptedException {
        // Inicialización de componentes de juego
        Tablero t = new Tablero();
        t.inicializar();
        Turno controlador = new Turno();
        Jugador[] lista = new Jugador[cantidadActual];
        boolean fin = false;
        int opcionCarta;
        Jugador j;
        String opcion;
        boolean esNumero;
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
            System.out.println("Mesa: " + t.verMesa());

            // Mostrar la mano del jugador actual
            for (int i = 0; i < j.getNumCartas(); i++) {
                System.out.print(i + ":" + j.mano[i] + " ");
            }
            System.out.println(j.getNumCartas() + ":[ROBAR]");

            // Bucle de validación de entrada, para que no se metan letras
            opcionCarta = -1;
            while (opcionCarta < 0 || opcionCarta > j.getNumCartas()) {
                opcion = Datos.pedirCadena("Acción: ");

                if (opcion.isEmpty()) {
                    System.out.println("No has introducido nada.");
                } else {
                    // Para evitar que lo que se introduce no sea un numero
                    esNumero = true;
                    for (int i = 0; i < opcion.length(); i++) {
                        if (!Character.isDigit(opcion.charAt(i))) { //isDigit para que no se rompa en caso de cadena de caracteres
                            esNumero = false;
                        }
                    }

                    //Por si acaso el usuario introduce opciones no validas
                    if (esNumero) {
                        opcionCarta = Integer.parseInt(opcion);
                        if (opcionCarta < 0 || opcionCarta > j.getNumCartas()) {
                            System.out.println("Número fuera de rango.");
                        }
                    } else {
                        System.out.println("Error, introduce la posicion de la carta que quieras sacar");
                        opcionCarta = -1;
                    }
                }
            }

            // Procesar la opción seleccionada
            if (opcionCarta == j.getNumCartas()) {
                // Opción Robar
                cartaRobada = t.tirarCarta();
                j.recibirCarta(cartaRobada);
                System.out.println("Has recibido un: " + cartaRobada);
            } else {
                // Opción Jugar Carta
                cartaSeleccionada = j.mano[opcionCarta];
                cartaEnMesa = t.verMesa();

                // Para ver si la carta que se juega se puede jugar en la mesa
                if ((cartaSeleccionada).puedePonerseSobre(cartaEnMesa)) {
                    cartaTirada = j.jugarCarta(opcionCarta);
                    t.dejar(cartaTirada);
                    System.out.println("La carta que has tirado es: "+cartaTirada);
                    // Condición de victoria: 0 cartas
                    if (j.getNumCartas() == 0) {
                        fin = true;
                        pantallas.NombreJugador = j.getNombre();
                    }
                    // Por si el usuario introduce una opción no válida
                } else {
                    System.out.println("¡Movimiento no válido! (Chupas una carta)");
                    cartaRobada = t.tirarCarta();
                    j.recibirCarta(cartaRobada);
                    System.out.println("Has recibido un: " + cartaRobada);
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
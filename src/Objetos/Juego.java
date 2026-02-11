package Objetos;

import MetodosAux.pantallas;
import java.util.Scanner;

/**
 * Clase que estructura mediante una serie de metodos todo el juego
 * 
 * @author DaniS y Libio
 */
public class Juego {

    static Scanner s = new Scanner(System.in);
    // Configuración inicial por defecto
    private String[] nombresCargados = { "Jugador 1", "Jugador 2" }; 
    private int cantidadActual = 2;

    /**
     * Orquestador principal del sistema. Controla el bucle del menú principal.
     */
    public void ejecutarSistemaCompleto() throws InterruptedException {
        pantallas.PantallaIncio(); 
        
        while (true) {
            String op = pantallas.PantallaMenu();
            
            if (op.equals("1")) { // Configurar modo de juego
                pantallas.ModoDeJuego = pantallas.PantallaModosDeJuego();
            } else if (op.equals("2")) { // Configurar nombres y cantidad de jugadores
                configurarJugadores();
            } else if (op.equals("3")) { // Mostrar instrucciones
                pantallas.PantallaReglas();
            } else if (op.equals("4")) { // Iniciar una partida
                partida();
            } else if (op.equals("5")) { // Salir del programa
                break;
            }
        }
    }

    /**
     * Solicita por consola el número de participantes y sus respectivos nombres.
     */
public void configurarJugadores() {
    int numJugadores = -1;
    
    while (numJugadores < 2 || numJugadores > 4) {
        System.out.print("¿Cuántos jugadores (2-4)? ");
        String opcion = s.nextLine();

        if (opcion.isEmpty()) {
            System.out.println("No has introducido nada.");
            continue;
        }

        boolean esNumero = true;
        for (int i = 0; i < opcion.length(); i++) {
            if (!Character.isDigit(opcion.charAt(i))) { //isDigit para que no se rompa para un acadena de caracteres
                esNumero = false;
            }
        }

        if (esNumero) {
            numJugadores = Integer.parseInt(opcion);
            if (numJugadores < 2 || numJugadores > 4) {
                System.out.println("Solo 2, 3 o 4 jugadores.");
            }
        } else {
            System.out.println("Error: Introduce solo números (2-4).");
            numJugadores = -1;
        }
    }
    
    cantidadActual = numJugadores;
    
    // Nombres normalmente...
    nombresCargados = new String[cantidadActual];
    for (int i = 0; i < cantidadActual; i++) {
        System.out.print("Nombre Jugador " + (i + 1) + ": ");
        nombresCargados[i] = s.nextLine();
    }
    
    pantallas.Jugadores = String.valueOf(cantidadActual);
}


    /**
     * Lógica principal de la partida. Controla el flujo de turnos,
     * validación de jugadas y condiciones de victoria.
     */
    private void partida() throws InterruptedException {
        // Inicialización de componentes de juego
        Tablero t = new Tablero();
        t.inicializar();
        Turno controlador = new Turno();
        Jugador[] lista = new Jugador[cantidadActual];

        // Reparto inicial: 7 cartas por jugador
        for (int i = 0; i < cantidadActual; i++) {
            lista[i] = new Jugador(nombresCargados[i]);
            for (int c = 0; c < 7; c++)
                lista[i].recibirCarta(t.tirarCarta());
        }
        
        // Coloca la primera carta en la mesa para empezar
        t.dejar(t.tirarCarta());

        boolean fin = false;
        // Bucle de juego (se ejecuta hasta que alguien se queda sin cartas)
        while (!fin) {
            Jugador j = lista[controlador.actual];
            System.out.println("\n--- TURNO DE: " + j.getNombre() + " ---");
            System.out.println("Mesa: " + t.verMesa());

            // Mostrar la mano del jugador actual
            for (int i = 0; i < j.getNumCartas(); i++)
                System.out.print(i + ":" + j.mano[i] + " ");
            System.out.println(j.getNumCartas() + ":[ROBAR]");

            // Bucle de validación de entrada (para evitar errores si el usuario mete letras)
            int sel = -1;
            while (sel < 0 || sel > j.getNumCartas()) {
                System.out.print("Acción: ");
                String opcion = s.nextLine();

                if (opcion.isEmpty()) {
                    System.out.println("No has introducido nada.");
                }else{
                    // Validación manual de que la cadena sea numérica
                    boolean esNumero = true;
                    for (int i = 0; i < opcion.length(); i++) {
                        if (!Character.isDigit(opcion.charAt(i))) {
                            esNumero = false;
                        }
                    }
    
                    if (esNumero) {
                        sel = Integer.parseInt(opcion);
                        if (sel < 0 || sel > j.getNumCartas()) {
                            System.out.println("Número fuera de rango.");
                        }
                    } else {
                        System.out.println("Error, introduce la posicion de la carta que quieras sacar");
                        sel = -1;
                    }
                }
                Thread.sleep(1000);
            }

            // Procesar la acción seleccionada
            if (sel == j.getNumCartas()) {
                // Opción Robar
                j.recibirCarta(t.tirarCarta());
            } else {
                // Opción Jugar Carta
                Carta cartaSeleccionada = j.mano[sel];
                Carta cartaEnMesa = t.verMesa();

                // Aplicación de reglas: ¿Es compatible la carta con la mesa?
                if (cartaSeleccionada.puedePonerseSobre(cartaEnMesa)) {
                    t.dejar(j.jugarCarta(sel));
                    // Condición de victoria: 0 cartas
                    if (j.getNumCartas() == 0) {
                        fin = true;
                        pantallas.NombreJugador = j.getNombre();
                    }
                } else {
                    System.out.println("¡Movimiento no válido!");
                }
            }
            
            // Si nadie ha ganado, pasamos al siguiente turno
            if (!fin)
                controlador.siguiente(cantidadActual);
        }
        // Mostrar pantalla de ganador
        pantallas.PantallaFinal();
    }
}
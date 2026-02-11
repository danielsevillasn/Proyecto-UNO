package MetodosAux;

import java.util.Scanner;

/**
 * Clase para imprimir todas las pantallas del juego
 * 
 * @author DaniS y Libio
 */
public class pantallas {
    // Scanner (Objeto) estatico que se podra utilizar en todos los metodos de la
    // clase
    static Scanner s = new Scanner(System.in);

    // Variables necesarias para que los métodos de impresión funcionen
    public static String ModoDeJuego = "Clásico";
    public static String Jugadores = "2";
    public static String NombreJugador = "Invitado";
    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaMenu() {
        System.out.println("==========Inicio=========");
        System.out.println("\t1- Modo de juego");
        System.out.println("\t2- Jugadores");
        System.out.println("\t3- Reglas");
        System.out.println("\t4- Iniciar juego");
        System.out.println("\t5- Salir");
        System.out.println("==========================");
        System.out.println("Modo de juego: " + ModoDeJuego + "\tjugadores: " + Jugadores);

        System.out.print("\tElija opción: ");
        return s.nextLine();
    }

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaModosDeJuego() {
        boolean salir = false;
        while(!salir){
            System.out.println("=========Modos de juego===========");
            System.out.println("\t1. Clásico");
            System.out.println("\t2. Otra modalidad");
            System.out.println("\t3. Otra modalidad");
            System.out.print("\tElija opción: ");
            ModoDeJuego = s.nextLine();
            if (ModoDeJuego.equals("1")) {
                ModoDeJuego = "Clásico";
                salir = true;
            } else if(ModoDeJuego.equals("2")||ModoDeJuego.equals("3")){
                ModoDeJuego = "Otro";
                salir = true;
            }
        }
        return (ModoDeJuego);
    }

    /**
     * Pantalla en la que ingresas los nombres y la cantidad de jugadores
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    public static int PantallaJugadores() {
        int cantidadActual = 2;
        System.out.println("============Jugadores============");
        return cantidadActual;
    }

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego
     * seleccionado
     * 
     * @param nada
     * @return nada
     */
    public static void PantallaReglas() {
        System.out.println("============Reglas============");
        System.out.println("Principales: ");
        System.out.println("El objetivo principal de UNO es ser el primer jugador en quedarse sin cartas, tras repartir 7 a cada uno.");
        System.out.println("En tu turno, debes igualar la carta superior de la pila por color, número o símbolo.");
        System.out.println("Modo de juego: " + ModoDeJuego);
        System.out.println("\nPulse enter para salir...");
        s.nextLine();
    }

    /**
     * Pantalla en la que sale "Ganaste" con el nombre del ganador
     * 
     * @param nada
     * @return nada
     */
    public static void PantallaFinal() throws InterruptedException{
        String AMARILLO = "\u001B[33m";
        String VERDE = "\u001B[32m";
        String RESET = "\u001B[0m";
        String ROJO = "\u001B[31m";
        String mensajeJugador = "Jugador: " + NombreJugador + "!";


        System.out.println(VERDE);
        Thread.sleep(1000);
        System.out.println("  ██████   █████  ███    ██  █████  ███████ ████████ ███████ ");
        System.out.println(" ██       ██   ██ ████   ██ ██   ██ ██         ██    ██      ");
        System.out.println(" ██   ███ ███████ ██ ██  ██ ███████ ███████    ██    █████   ");
        System.out.println(" ██    ██ ██   ██ ██  ██ ██ ██   ██      ██    ██    ██      ");
        System.out.println("  ██████  ██   ██ ██   ████ ██   ██ ███████    ██    ███████ ");
        System.out.println();
        Thread.sleep(1000);
        System.out.printf(AMARILLO + "%37s%n" + RESET, "¡ENHORABUENA!");
        Thread.sleep(1000);
        System.out.printf(ROJO + "%40s%n" + RESET, mensajeJugador);
        Thread.sleep(2000);
        System.out.println("\n\n\n\n");
    }

    /**
     * Pantalla en la que sale UNO en grande para dar inicio al programa
     * 
     * @param nada
     * @return nada
     */
    public static void PantallaIncio() throws InterruptedException {
        String AMARILLO = "\u001B[33m";
        String RESET = "\u001B[0m";
        String ROJO = "\u001B[31m";

        System.out.println(AMARILLO);
        Thread.sleep(1000);
        System.out.println(" ██    ██ ███    ██  ██████  ");
        Thread.sleep(1000);
        System.out.println(" ██    ██ ████   ██ ██    ██ ");
        Thread.sleep(1000);
        System.out.println(" ██    ██ ██ ██  ██ ██    ██ ");
        Thread.sleep(1000);
        System.out.println(" ██    ██ ██  ██ ██ ██    ██ ");
        Thread.sleep(1000);
        System.out.println("  ██████  ██   ████  ██████  ");
        Thread.sleep(1000);
        System.out.print(RESET);
        System.out.println(ROJO);

        Thread.sleep(2000);
        System.out.println("Dale enter para comenzar...");
        s.nextLine();
        System.out.println(RESET);
        System.out.println();
    }
}
// Son solo cambio de prueba de una rama

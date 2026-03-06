package MetodosSecundarios;

import java.util.Scanner;

/**
 * Clase para imprimir todas las pantallas del juego
 * 
 * @author DaniS y Libio
 */
public class pantallas {
    // Scanner (Objeto) estático que se podrá utilizar en todos los métodos de la
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
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaMenu() throws InterruptedException {
        Datos.saltoDeLineas();
        System.out.println("==========Inicio=========");
        System.out.println("\t1- Modo de juego");
        System.out.println("\t2- Jugadores");
        System.out.println("\t3- Reglas");
        System.out.println("\t4- Iniciar juego");
        System.out.println("\t5- Atrás <--");
        System.out.println("==========================");
        System.out.println("Modo de juego: " + ModoDeJuego + "\tjugadores: " + Jugadores);

        return (Datos.pedirCadena("\tElija opción (1-5): "));
    }

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaModosDeJuego() throws InterruptedException {
        Datos.saltoDeLineas();
        boolean salir = false;
        while (!salir) {
            System.out.println("=========Modos de juego===========");
            System.out.println("\t1. Clásico");
            System.out.println("\t2. Otra modalidad");
            System.out.println("\t3. Otra modalidad");
            ModoDeJuego = Datos.pedirCadena("\tElija opción (1-3): ");
            if (ModoDeJuego.equals("1")) {
                ModoDeJuego = "Clásico";
                salir = true;
            } else if (ModoDeJuego.equals("2") || ModoDeJuego.equals("3")) {
                ModoDeJuego = "Otro";
                salir = true;
            }
        }
        return (ModoDeJuego);
    }

    /**
     * Pantalla en la que ingresas los nombres y la cantidad de jugadores
     * 
     * @param 'ninguno'
     * @return cantidadActual valor inicial para la cantidad de jugadores
     */
    public static void PantallaJugadores() throws InterruptedException {
        Datos.saltoDeLineas();
        System.out.println("============Jugadores============");
    }

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego
     * seleccionado
     * 
     * @param 'nada'
     * @return nada
     */
    public static void PantallaReglas() throws InterruptedException {
        Datos.saltoDeLineas();
        System.out.println("========== REGLAS DEL JUEGO UNO ==========");
        System.out.println();
        System.out.println("OBJETIVO DEL JUEGO:");
        System.out.println("- Ser el primero en quedarse sin cartas en la mano");
        System.out.println();
        System.out.println("PREPARACIÓN:");
        System.out.println("- Se reparten 7 cartas a cada jugador.");
        System.out.println("- Se deja el mazo boca abajo en el centro (mazo de robo)");
        System.out.println("- Se da la vuelta a la primera carta del mazo para iniciar");
        System.out.println("  la pila de descarte.");
        System.out.println();
        System.out.println("TURNO DE JUEGO:");
        System.out.println("- En tu turno debes jugar UNA carta que COINCIDA en color,");
        System.out.println("  número o símbolo con la carta superior de la pila de descarte");
        System.out.println("- Si no puedes o no quieres jugar, robas UNA carta del mazo");
        System.out.println("- Si la carta robada se puede jugar, puedes decidir jugarla");
        System.out.println("  inmediatamente o quedártela en la mano.");
        System.out.println();
        System.out.println("CARTAS NUMÉRICAS (0-9):");
        System.out.println("- Solo sirven para coincidir por número o color, no tienen");
        System.out.println("  efectos especiales");
        System.out.println();
        System.out.println("Pulse enter para continuar");
        s.nextLine();
    }

    /**
     * Pantalla en la que sale "Ganaste" con el nombre del ganador
     * 
     * @param 'nada'
     * @return nada
     */
    public static void PantallaFinal() throws InterruptedException {
        Datos.saltoDeLineas();
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
    }

    /**
     * Pantalla en la que sale UNO en grande para dar inicio al programa
     * 
     * @param 'nada'
     * @return nada
     */
    public static void PantallaUNO() throws InterruptedException {
        Datos.saltoDeLineas();
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
        System.out.println("Dale enter para comenzar..." + RESET);
        s.nextLine();
    }

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaInicio() throws InterruptedException {
        Datos.saltoDeLineas();
        System.out.println("==========Bienvenido/a a UNO=========");
        System.out.println("\t1- Jugar");
        System.out.println("\t2- Salir");
        return Datos.pedirCadena("\tElija opción (1-2): ");
    }
}

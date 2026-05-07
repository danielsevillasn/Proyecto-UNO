package MetodosSecundarios;

/**
 * Clase para imprimir las pantallas graficas del juego
 * 
 * @author DaniS y Libio
 */
public class Pantallas {
    // Variables estaticas que permiten cambiar de color a la hora de imprimir por
    // pantalla
    private static final String AMARILLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private static final String ROJO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego
     * seleccionado
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void PantallaReglas() throws InterruptedException {
        Datos.saltoDeLineas();

        System.out.println("========== REGLAS DEL JUEGO UNO ==========");
        Thread.sleep(Datos.milisegundos+1000);
        System.out.println();
        System.out.println("OBJETIVO DEL JUEGO:");
        System.out.println("- Ser el primero en quedarse sin cartas en la mano");
        Thread.sleep(Datos.milisegundos+1000);
        System.out.println();
        System.out.println("PREPARACIÓN:");
        System.out.println("- Se reparten 7 cartas a cada jugador.");
        System.out.println("- Se deja el mazo boca abajo en el centro (mazo de robo)");
        System.out.println("- Se da la vuelta a la primera carta del mazo para iniciar");
        System.out.println("  la pila de descarte.");
        Thread.sleep(Datos.milisegundos+1000);
        System.out.println();
        System.out.println("TURNO DE JUEGO:");
        System.out.println("- En tu turno debes jugar UNA carta que COINCIDA en color,");
        System.out.println("  número o símbolo con la carta superior de la pila de descarte");
        System.out.println("- Si no puedes o no quieres jugar, robas UNA carta del mazo");
        System.out.println("- Si la carta robada se puede jugar, puedes decidir jugarla");
        System.out.println("  inmediatamente o quedártela en la mano.");
        Thread.sleep(Datos.milisegundos+1000);
        System.out.println();
        System.out.println("CARTAS NUMÉRICAS (0-9):");
        System.out.println("- Solo sirven para coincidir por número o color, no tienen");
        System.out.println("  efectos especiales");
        Thread.sleep(Datos.milisegundos+1000);
        System.out.println("\nCARTAS ESPECIALES: ");
        System.out.println("- Son cartas que tienen habilidades especiales en el flujo de la partida");
        System.out.println("\n- REVERSA: Sirve para cambiar el sentido de los turnos. Esta carta tiene colores");
        System.out.println("\n- BLOQUEO: Sirve para bloquear el siguiente turno, es decir, impedir ");
        System.out.println("  que el próximo jugador juegue su turno. Esta carta tiene colores");
        System.out.println("\n- CHUPA 2: Carta especial que sirve para obligar al siguiente jugador ");
        System.out.println("  coger dos cartas de la chupona. Esta carta tiene colores");
        System.out.println("\n- CHUPA 4: Carta especial que hace que el próximo jugador tenga que ");
        System.out.println("  coger 4 cartas de la baraja de chupona. Además esta carta no tiene color ");
        System.out.println("  lo que significa que se puede lanzar cuando quieras. También al ");
        System.out.println("  lanzar esta carta puedes cambiar el color al que tú quieras.");
        System.out.println("\n- CAMBIO DE COLOR: Carta especial que no tiene color, lo que significa");
        System.out.println("  ");
        System.out.println();
        Datos.pulsaEnter();
    }

    /**
     * Pantalla en la que sale "Ganaste" con el nombre del ganador
     * 
     * @param 'nada'
     * @throws InterruptedException para los thread sleep
     */
    public static void PantallaFinal() throws InterruptedException {
        Datos.saltoDeLineas();

        String mensajeJugador = "Jugador: " + Menus.nombreJugador + "!";

        System.out.println(VERDE);
        Thread.sleep(Datos.milisegundos);
        System.out.println("  ██████   █████  ███    ██  █████  ███████ ████████ ███████ ");
        System.out.println(" ██       ██   ██ ████   ██ ██   ██ ██         ██    ██      ");
        System.out.println(" ██   ███ ███████ ██ ██  ██ ███████ ███████    ██    █████   ");
        System.out.println(" ██    ██ ██   ██ ██  ██ ██ ██   ██      ██    ██    ██      ");
        System.out.println("  ██████  ██   ██ ██   ████ ██   ██ ███████    ██    ███████ ");
        System.out.println();
        Thread.sleep(Datos.milisegundos);
        System.out.printf(AMARILLO + "%37s%n" + RESET, "¡ENHORABUENA!");
        Thread.sleep(Datos.milisegundos);
        System.out.print(ROJO + "\t\t\t" + mensajeJugador + RESET);
        Thread.sleep(Datos.milisegundos + 1000);
        System.out.println();
        Datos.pulsaEnter();
    }

    /**
     * Pantalla en la que sale UNO en grande para dar inicio al programa
     * 
     * @param 'nada'
     * @throws InterruptedException para los thread sleep
     */
    public static void PantallaUNO() throws InterruptedException {
        Datos.saltoDeLineas();

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
        Menus.s.nextLine();
    }
}

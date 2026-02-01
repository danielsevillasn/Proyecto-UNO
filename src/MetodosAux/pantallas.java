package MetodosAux;
import java.util.Scanner;

/**
 * Clase para imprimir todas las pantallas del juego
 * 
 * @author DaniS y Libio
 */
public class pantallas {
    //Scanner (Objeto) estatico que se podra utilizar en todos los metodos de la clase
    static Scanner s = new Scanner(System.in);

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaMenu() {
        String InicioUno = "";
        System.out.println();
        System.out.println("==========Inicio=========");
        System.out.println("\t1- Modo de juego");
        System.out.println("\t2- Jugadores");
        System.out.println("\t3- Reglas");
        System.out.println("\t4- Iniciar juego");
        System.out.println("\t5- Salir");
        System.out.println("==========================");
        System.out.print("\tElija opción: ");

        System.out.println("Modo de juego: " + ModoDeJuego + "\tjugadores: " + Jugadores);
        
        InicioUno = s.nextLine();

        return (InicioUno);
    }

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaModosDeJuego() {
        String ModoDeJuego = "";
        System.out.println("=========Modos de juego===========");
        System.out.println("\t1. Clásico");
        System.out.println("\t2. Otra modalidad");
        System.out.println("\t3. Otra modalidad");
        System.out.print("\tElija opción: ");
        ModoDeJuego = s.nextLine();
        switch (ModoDeJuego) {
            case "1":
                ModoDeJuego = "Clásico";
                break;
            default:
                break;
        }
        return (ModoDeJuego);
    }

    /**
     * Pantalla en la que ingresas los nombres y la cantidad de jugadores
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    public static String PantallaJugadores() {
        String PantallaJugadores = "";
        System.out.println("============Jugadores============");
        System.out.print("1- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        System.out.print("2- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        System.out.print("3- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        System.out.print("4- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        return (PantallaJugadores);
    }

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego seleccionado
     * 
     * @param nada
     * @return nada
     */
    public static void PantallaReglas(){
        System.out.println("============Reglas============");
        System.out.println("Principales: ");
        System.out.println("El objetivo principal de UNO es ser el primer jugador en quedarse sin cartas, tras repartir 7 a cada uno.");
        System.out.println("En tu turno, debes igualar la carta superior de la pila por color, número o símbolo.");
        System.out.println("Si no tienes, roba una del mazo. Al quedar con una carta, grita \\\\\\\"¡UNO!\\\\\\\" o serás penalizado");
        System.out.println("Si no tienes, roba una del mazo. Al quedar con una carta, grita \\\\\\\"¡UNO!\\\\\\\" o serás penalizado");
        System.out.println("Si no tienes, roba una del mazo. Al quedar con una carta, grita \\\\\\\"¡UNO!\\\\\\\" o serás penalizado");
        System.out.println("Modo de juego: "+ModoDejuego);
        System.out.println("\nPulse enter para salir...");
    }

    
    /**
     * Pantalla en la que sale "Ganaste" con el nombre del ganador
     * 
     * @param nada
     * @return nada
     */
    public static void PantallaFinal(){
        String AMARILLO = "\u001B[33m";
        String VERDE = "\u001B[32m";
        String RESET = "\u001B[0m";
        String ROJO = "\u001B[31m";

        System.out.println(VERDE);
        System.out.println("  ██████   █████  ███    ██  █████  ███████ ████████ ███████ ");
        System.out.println(" ██       ██   ██ ████   ██ ██   ██ ██         ██    ██      ");
        System.out.println(" ██   ███ ███████ ██ ██  ██ ███████ ███████    ██    █████   ");
        System.out.println(" ██    ██ ██   ██ ██  ██ ██ ██   ██      ██    ██    ██      ");
        System.out.println("  ██████  ██   ██ ██   ████ ██   ██ ███████    ██    ███████ ");
        System.out.println();
        System.out.println(AMARILLO + "\t\t\t¡GANASTE!" + RESET);
        System.out.println(ROJO+"\t\t  Jugador:"+NombreJugador+"!"+ RESET);
    }

    /**
     * Pantalla en la que sale UNO en grande para dar inicio al programa
     * 
     * @param nada
     * @return nada
     */
    public static void PantallaIncio() throws InterruptedException{
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
        Thread.sleep(2000);
    }
}

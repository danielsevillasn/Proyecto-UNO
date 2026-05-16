package MetodosSecundarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Objetos.Jugador;

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
     * Pantalla en la que sale "Ganaste" con el nombre del ganador
     * 
     * @param 'nada'
     * @throws InterruptedException para los thread sleep
     */
    public static void pantallaFinal() throws InterruptedException {
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
    public static void pantallaUNO() throws InterruptedException {
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

    /**
     * método para mostar las estadístcas del jeugo al terminar la partida
     * 
     * @param ganador
     * @param jugadores
     */
    public static void mostrarEstadisticas(Jugador ganador, java.util.Collection<Jugador> jugadores) throws InterruptedException {
        Datos.saltoDeLineas();
        
        System.out.println("=========================================\n");
        String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss a").format(LocalDateTime.now());
        System.out.println("Fecha y Hora: " + fecha + "\n");
        System.out.println("Ganador: " + ganador.getNombre() + "\n");
        System.out.println("Jugadores de la partida:\n");
        for (Jugador j : jugadores) {
            System.out.println(
                    "  - " + j.getNombre() + " (Cartas robadas en total: " + j.getCartasRobadasTotales() + ")\n");
        }
        System.out.println("=========================================\n\n");

        Datos.pulsaEnter();
    }
}

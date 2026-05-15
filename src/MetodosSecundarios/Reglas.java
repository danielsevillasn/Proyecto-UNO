package MetodosSecundarios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Reglas {

    private static final String ARCHIVO_REGLAS = "reglas.txt";

    /**
     * Guarda una copia de las reglas
     */
    public static void guardarReglas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_REGLAS))) {
            bw.write("=== REGLAS DEL JUEGO UNO ===");
            bw.newLine();
            bw.write("1. Cada jugador recibe 7 cartas al comenzar.");
            bw.newLine();
            bw.write("2. Se debe jugar una carta que coincida en color, numero o simbolo con la del centro.");
            bw.newLine();
            bw.write("3. Si no tienes carta para jugar, debes robar una del mazo.");
            bw.newLine();
            bw.write("4. Cuando te quede una sola carta, debes avisar diciendo UNO.");
            bw.newLine();
            bw.write("5. El primer jugador en quedarse sin cartas gana la partida.");
            bw.newLine();
            
            System.out.println("Reglas guardadas correctamente en " + ARCHIVO_REGLAS);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de reglas: " + e.getMessage());
        }
    }

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego
     * seleccionado
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void pantallaReglas() throws InterruptedException {
        Datos.saltoDeLineas();

        System.out.println("========== REGLAS DEL JUEGO UNO ==========");
        Thread.sleep(Datos.milisegundos + 1000);
        System.out.println();
        System.out.println("OBJETIVO DEL JUEGO:");
        System.out.println("- Ser el primero en quedarse sin cartas en la mano");
        Thread.sleep(Datos.milisegundos + 1000);
        System.out.println();
        System.out.println("PREPARACIÓN:");
        System.out.println("- Se reparten 7 cartas a cada jugador.");
        System.out.println("- Se deja el mazo boca abajo en el centro (mazo de robo)");
        System.out.println("- Se da la vuelta a la primera carta del mazo para iniciar");
        System.out.println("  la pila de descarte.");
        Thread.sleep(Datos.milisegundos + 1000);
        System.out.println();
        System.out.println("TURNO DE JUEGO:");
        System.out.println("- En tu turno debes jugar UNA carta que COINCIDA en color,");
        System.out.println("  número o símbolo con la carta superior de la pila de descarte");
        System.out.println("- Si no puedes o no quieres jugar, robas UNA carta del mazo");
        System.out.println("- Si la carta robada se puede jugar, puedes decidir jugarla");
        System.out.println("  inmediatamente o quedártela en la mano.");
        Thread.sleep(Datos.milisegundos + 1000);
        System.out.println();
        System.out.println("CARTAS NUMÉRICAS (0-9):");
        System.out.println("- Solo sirven para coincidir por número o color, no tienen");
        System.out.println("  efectos especiales");
        Thread.sleep(Datos.milisegundos + 1000);
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
     * Método que muestra las reglas
     */
    public static void mostrarReglas() throws InterruptedException{
        try {
            System.out.println("archivo de reglas creado correctamente");
            Thread.sleep(Datos.milisegundos);

            pantallaReglas();
        } catch (InterruptedException e) {
            System.out.println("Error en las pausas de la pantalla de reglas: " + e.getMessage());
        }
    }
}
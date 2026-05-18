package MetodosSecundarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import Objetos.Jugador;

/**
 * Clase que contiene metodos relacionados con el almacenamiento de los datos
 * 
 * @author Dani S y Libio
 */
public class AlmacenamientoDatos {
    private static final String ARCHIVO_REGLAS = "src\\Archivos\\reglas.txt";
    private static final String ARCHIVO_ESTADISTICAS = "src\\Archivos\\estadisticas.txt";

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego
     * seleccionado
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void pantallaReglas() throws InterruptedException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_REGLAS));
            String linea = "";

            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
                Thread.sleep(750);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo");
        }
        Datos.pulsaEnter();
        Datos.saltoDeLineas();

    }

    /**
     * Método para guardar las estadisticas de la partida y para que poder mostrar
     * las estadisticas en caso de querer
     * 
     * @param ganador   el jugador que ha ganado
     * @param jugadores mapa de los jugadores de la partida
     * @param mostrar   variable booleana que determina si se muestran las
     *                  estadísticas o no
     * @throws InterruptedException para los thread sleep
     */
    public static void finalizarYGuardarEstadísticas(Jugador ganador, HashMap<Integer, Jugador> jugadores,
            boolean mostrar)
            throws InterruptedException {

        Datos.saltoDeLineas();

        // Formateo de fecha y diseño
        String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss a").format(LocalDateTime.now());
        String separador = "========================================================================\n";

        // 1. Creamos y acumulamos todo el texto en una variable String
        String reporteFinal = separador;
        reporteFinal += "Fecha y Hora: " + fecha + "\n";
        reporteFinal += "Ganador: " + ganador.getNombre() + "\n";
        reporteFinal += "Jugadores de la partida:\n";

        // Recorremos directamente los valores del HashMap
        for (Jugador j : jugadores.values()) {
            reporteFinal += "  - " + j.getNombre()
                    + " (Cartas robadas en total: " + j.getCartasRobadasTotales()
                    + " | Cartas jugadas en total: " + j.getCartasJugadasTotales() + ")\n";
        }
        reporteFinal += separador + "\n";

        // 2. Lo mostramos por pantalla
        if (mostrar) {
            System.out.print(reporteFinal);
        }

        // 3. Lo guardamos en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ESTADISTICAS, true))) {
            bw.write(reporteFinal);
        } catch (IOException e) {
            System.out.println("Error al registrar las estadísticas en el archivo: " + e.getMessage());
        }

        Datos.pulsaEnter();
    }
}

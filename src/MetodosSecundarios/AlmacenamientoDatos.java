package MetodosSecundarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import Objetos.Jugador;
import Objetos.Turno;

/**
 * Clase que contiene metodos relacionados con el almacenamiento de los datos
 * 
 * @author Dani S y Libio
 */
public class AlmacenamientoDatos {

    // Rutas de los archivos guardados en el proyecto
    private static final Path CARPETA_ARCHIVOS = Paths.get("src", "Archivos");

    private static final Path RUTA_REGLAS = CARPETA_ARCHIVOS.resolve("reglas.txt");
    private static final Path RUTA_ESTADISTICAS = CARPETA_ARCHIVOS.resolve("estadisticas.txt");

    private static final String NOMBRE_ARCHIVO_REGLAS = RUTA_REGLAS.toString();
    private static final String NOMBRE_ARCHIVO_ESTADISTICAS = RUTA_ESTADISTICAS.toString();

    /**
     * Pantalla en la que sale todas las reglas del juego y del modo de juego
     * seleccionado
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     */
    public static void pantallaReglas() throws InterruptedException {
        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO_REGLAS))) {
            String linea = "";

            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
                Thread.sleep(Datos.milisegundos);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
            System.out.println(e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo");
            System.out.println(e.getLocalizedMessage());
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
     * @param turno     turnos de la partida
     * @param mostrar   variable booleana que determina si se muestran las
     *                  estadísticas o no
     * @throws InterruptedException para los thread sleep
     */
    public static void finalizarYGuardarEstadisticas(Jugador ganador, Map<Integer, Jugador> jugadores, Turno turno,
            boolean mostrar) throws InterruptedException {
        Datos.saltoDeLineas();

        String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss a").format(LocalDateTime.now());
        String separador = "========================================================================\n";

        String reporteFinal = separador;
        reporteFinal += "Fecha y Hora: " + fecha + "\n";
        reporteFinal += "Turnos jugados: " + turno.getContadorTurno() + "\n";
        reporteFinal += "Ganador: " + ganador.getNombre() + "\n";
        reporteFinal += "Jugadores de la partida:\n";

        for (Jugador j : jugadores.values()) {
            reporteFinal += "  - " + j.getNombre()
                    + " (Cartas robadas en total: " + j.getCartasRobadasTotales()
                    + " | Cartas jugadas en total: " + j.getCartasJugadasTotales() + ")\n";
        }
        reporteFinal += separador + "\n";

        if (mostrar) {
            System.out.print(reporteFinal);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO_ESTADISTICAS, true))) {
            bw.write(reporteFinal);
        } catch (IOException e) {
            System.out.println("Error al registrar las estadísticas en el archivo: " + e.getMessage());
        }

        Datos.pulsaEnter();
    }
}

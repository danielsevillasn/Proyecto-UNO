package MetodosSecundarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import Objetos.Jugador;
import Objetos.PartidaContexto;

/**
 * Clase que contiene metodos relacionados con el almacenamiento de los datos
 * 
 * @author Dani S y Libio
 */
public class AlmacenamientoDatos {
    /**
     * Rutas de los archivos guardados en el proyecto
     */
    private static final Path CARPETA_ARCHIVOS = Paths.get("src", "Archivos");

    // 2. Resolvemos las rutas de forma segura para cualquier SO
    private static Path rutaReglas = CARPETA_ARCHIVOS.resolve("reglas.txt");
    private static Path rutaEstadísticas = CARPETA_ARCHIVOS.resolve("estadisticas.txt");
    private static Path rutaPartida = CARPETA_ARCHIVOS.resolve("partida_guardada.dat");

    private final static String ARCHIVO_REGLAS = rutaReglas.toString();
    private final static String ARCHIVO_ESTADISTICAS = rutaEstadísticas.toString();
    private final static String ARCHIVO_PARTIDA = rutaPartida.toString();

    /**
     * 
     * 
     * @return
     */
    public static boolean existePartidaGuardada() {
        File archivo = new File(ARCHIVO_PARTIDA);
        return archivo.exists() && archivo.isFile();
    }

    public static void guardarPartida(PartidaContexto contexto) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PARTIDA))) {
            out.writeObject(contexto);
            System.out.println("Partida guardada correctamente en binario.");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida en binario: " + e.getMessage());
        }
    }

    // Cargar partida desde binario
    public static PartidaContexto cargarPartida() {
        File archivo = new File(ARCHIVO_PARTIDA);
        if (!archivo.exists()) {
            System.out.println("No existe una partida guardada en binario.");
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_PARTIDA))) {
            Object obj = in.readObject();
            if (obj instanceof PartidaContexto contexto) {
                System.out.println("Partida cargada correctamente desde binario.");
                return contexto;
            } else {
                System.out.println("El archivo de guardado no contiene una partida válida.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la partida en binario: " + e.getMessage());
            return null;
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
    public static void finalizarYGuardarEstadísticas(Jugador ganador, Map<Integer, Jugador> jugadores,
            boolean mostrar) throws InterruptedException {
        Datos.saltoDeLineas();

        String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss a").format(LocalDateTime.now());
        String separador = "========================================================================\n";

        String reporteFinal = separador;
        reporteFinal += "Fecha y Hora: " + fecha + "\n";
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ESTADISTICAS, true))) {
            bw.write(reporteFinal);
        } catch (IOException e) {
            System.out.println("Error al registrar las estadísticas en el archivo: " + e.getMessage());
        }

        Datos.pulsaEnter();
    }
}

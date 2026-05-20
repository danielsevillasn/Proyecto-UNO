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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import Excepciones.ReiniciarJuego;
import Excepciones.SalirDelJuego;
import Objetos.Jugador;
import Objetos.PartidaContexto;
import Objetos.Turno;

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

    private static Path rutaReglas = CARPETA_ARCHIVOS.resolve("reglas.txt");
    private static Path rutaEstadisticas = CARPETA_ARCHIVOS.resolve("estadisticas.txt");
    private static Path rutaPartida = CARPETA_ARCHIVOS.resolve("partida_guardada.dat");

    private static final String ARCHIVO_REGLAS = rutaReglas.toString();
    private static final String ARCHIVO_ESTADISTICAS = rutaEstadisticas.toString();
    private static final String ARCHIVO_PARTIDA = rutaPartida.toString();

    /**
     * Método que comprueba que el archivo partida sea un archivo y que exista
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     * @throws SalirDelJuego        para salir del juego cuando quieras
     */
    public static void partidaGuardada() throws InterruptedException, ReiniciarJuego, SalirDelJuego {
        File archivoPartida = new File(ARCHIVO_PARTIDA);
        if (archivoPartida.exists() && archivoPartida.isFile()) {
            pedirReanudarPartida();
        } else if (!archivoPartida.exists()) {
            System.out.println("El archivo no existe");
        } else {
            System.out.println("No es un archivo lo que se indica en la ruta");
        }
    }

    /**
     * Método que pide si quieres reanudar la partida anterior o no
     * 
     * @param archivoPartida archivo en el que se guarda la partida
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     * @throws SalirDelJuego        para salir del juego cuando quieras
     */
    private static void pedirReanudarPartida()
            throws InterruptedException, ReiniciarJuego, SalirDelJuego {
        boolean salir = false;
        Datos.saltoDeLineas();
        System.out.println("Se ha detectado una partida interrumpida.");
        while (!salir) {
            String respuesta = Datos.pedirCadena("¿Deseas reanudar la partida anterior? (S/N): ");

            switch (respuesta) {
                case "S":
                    UnoEngine.contextoPartida = AlmacenamientoDatos.cargarPartida();
                    UnoEngine.reanudarPartida();
                    Juego.iniciarJuego();
                    break;
                case "N":
                    try{
                        Files.delete(rutaPartida);
                    }catch(IOException e){
                        System.out.println("No se encuentra el archivo de la partida");
                        System.out.println(e.getLocalizedMessage());
                    }
                    salir = true;
                    break;

                default:
                    System.out.println("Tienes que poner \"S\" o \"N\", intentalo de nuevo");
                    break;
            }
        }
    }

    /**
     * Método que permite guardar la partida actual en un archivo en binario con el
     * respectivo contexto de la partida actual
     * 
     * @param contexto de la partida actual
     */
    public static void guardarPartida(PartidaContexto contexto) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PARTIDA))) {
            out.writeObject(contexto);
            System.out.println("Partida guardada correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    /**
     * Método que carga la partida guardada leyendo el archivo binario y pasandolo a
     * objeto
     * 
     * @param 'ninguno'
     * @return devuelve el contexto de la partida guardada
     */
    public static PartidaContexto cargarPartida() {
        File archivo = new File(ARCHIVO_PARTIDA);
        if (!archivo.exists()) {
            System.out.println("No existe una partida guardada en binario.");
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_PARTIDA))) {
            Object obj = in.readObject();
            if (obj instanceof PartidaContexto contexto) {
                System.out.println("\nPartida cargada correctamente");
                return contexto;
            } else {
                System.out.println("El archivo de guardado no contiene una partida válida.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
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
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_REGLAS))){
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ESTADISTICAS, true))) {
            bw.write(reporteFinal);
        } catch (IOException e) {
            System.out.println("Error al registrar las estadísticas en el archivo: " + e.getMessage());
        }

        Datos.pulsaEnter();
    }
}

package MetodosSecundarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import Enumerados.Color;
import Enumerados.TiposEspeciales;
import Objetos.Carta;
import Objetos.CartaEspecial;
import Objetos.CartaNormal;
import Objetos.Contenedor;
import Objetos.Jugador;
import Objetos.PartidaContexto;
import Objetos.Tablero;
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
    private static final String RUTA_ARCHIVO = "src\\Archivos\\partida_guardada.dat";
    private static final String ARCHIVO_REGLAS = "src\\Archivos\\reglas.txt";
    private static final String ARCHIVO_ESTADISTICAS = "src\\Archivos\\estadisticas.txt";

    public static boolean existePartidaGuardada() {
        File archivo = new File(RUTA_ARCHIVO);
        return archivo.exists() && archivo.isFile();
    }

    public static void guardarPartida(PartidaContexto contexto) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            // 1. Datos del controlador de turnos
            Turno t = contexto.getControladorTurnos();
            escritor.println(t.getActual());
            escritor.println(t.getContadorTurno());
            escritor.println(t.getSentido());

            // 2. Cantidad de jugadores
            int cantidadJugadores = contexto.getCantidadJugadores();
            escritor.println(cantidadJugadores);

            // 3. Guardar el mapa de jugadores y sus cartas en mano
            for (int i = 0; i < cantidadJugadores; i++) {
                Jugador j = contexto.getJugadores().get(i);
                if (j != null) {
                    escritor.println(j.getNombre());

                    Contenedor<Carta> mano = j.getMano();
                    escritor.println(mano.size());

                    // Se usa el método obtener(index) propio de Contenedor
                    for (int k = 0; k < mano.size(); k++) {
                        Carta c = mano.obtener(k);
                        escribirCarta(escritor, c);
                    }
                }
            }

            // 4. Guardar datos del Tablero (Mesa y Baraja Chupona)
            Tablero tablero = contexto.getTablero();

            // Carta en la mesa (Última del contenedor descarte)
            Carta mesa = tablero.verCartaEnLaMesa();
            if (mesa != null) {
                escribirCarta(escritor, mesa);
            } else {
                escritor.println("null");
            }

            // Cartas restantes en la baraja chupona
            Contenedor<Carta> chupona = tablero.getChupona();
            escritor.println(chupona.size());
            for (int i = 0; i < chupona.size(); i++) {
                Carta c = chupona.obtener(i);
                escribirCarta(escritor, c);
            }

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static Object cargarPartida() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return null;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            // 1. Reconstruir Turno
            Turno turnoAux = new Turno();
            turnoAux.setActual(Integer.parseInt(lector.readLine()));
            turnoAux.setContadorTurno(Integer.parseInt(lector.readLine()));
            turnoAux.setSentido(Integer.parseInt(lector.readLine()));

            // 2. Cantidad de jugadores
            int cantidadJugadores = Integer.parseInt(lector.readLine());

            // 3. Reconstruir mapa de jugadores con sus manos
            HashMap<Integer, Jugador> jugadoresAux = new HashMap<>();
            for (int i = 0; i < cantidadJugadores; i++) {
                String nombre = lector.readLine();
                Jugador jugador = new Jugador(nombre);

                int cartasEnMano = Integer.parseInt(lector.readLine());
                for (int k = 0; k < cartasEnMano; k++) {
                    Carta c = leerCarta(lector);
                    if (c != null) {
                        jugador.getMano().añadir(c);
                    }
                }
                jugadoresAux.put(i, jugador);
            }

            // 4. Reconstruir Tablero
            Tablero tableroAux = new Tablero();

            // Leer carta de la mesa y colocarla en el descarte
            Carta mesaAux = leerCarta(lector);
            if (mesaAux != null) {
                tableroAux.dejar(mesaAux);
            }

            // Leer baraja chupona
            int cartasChupona = Integer.parseInt(lector.readLine());
            for (int i = 0; i < cartasChupona; i++) {
                Carta c = leerCarta(lector);
                if (c != null) {
                    tableroAux.meter(c);
                }
            }

            return new PartidaContexto(tableroAux, turnoAux, jugadoresAux, cantidadJugadores);

        } catch (Exception e) {
            System.out.println("Error al recuperar la partida guardada: " + e.getMessage());
            return null;
        }
    }

    public static void borrarPartidaGuardada() {
        File archivo = new File(RUTA_ARCHIVO);
        if (archivo.exists()) {
            archivo.delete();
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
    public static void finalizarYGuardarEstadísticas(Jugador ganador, HashMap<Integer, Jugador> jugadores,
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

    // Métodos de serialización de texto basados en la herencia de objetos reales
    private static void escribirCarta(PrintWriter escritor, Carta c) {
        if (c instanceof CartaNormal) {
            CartaNormal cn = (CartaNormal) c;
            escritor.println("NORMAL;" + cn.getColor() + ";" + cn.getNumero());
        } else if (c instanceof CartaEspecial) {
            CartaEspecial ce = (CartaEspecial) c;
            escritor.println("ESPECIAL;" + ce.getColor() + ";" + ce.getTiposEspeciales());
        }
    }

    private static Carta leerCarta(BufferedReader lector) throws IOException {
        String linea = lector.readLine();
        if (linea == null || linea.equals("null")) {
            return null;
        }

        String[] partes = linea.split(";");
        String stringTipo = partes[0];
        Color color = Color.valueOf(partes[1]);

        if (stringTipo.equals("NORMAL")) {
            int numero = Integer.parseInt(partes[2]);
            return new CartaNormal(numero, color);
        } else {
            TiposEspeciales especial = TiposEspeciales.valueOf(partes[2]);
            return new CartaEspecial(especial, color);
        }
    }
}

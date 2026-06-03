package MetodosSecundarios;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona una base de datos que implementa un ranking de victorias
 * obtenidas por un jugador
 * 
 * @author DaniS y Libio
 */
public class GestorRankingBD {

    // Ruta de la base de datos
    private static final Path RUTA_PARTIDA = Paths.get("src", "Archivos").resolve("ranking.db");
    private static final String NOMBRE_ARCHIVO_RANKING = "jdbc:sqlite:"+RUTA_PARTIDA.toString();

    /**
     * Método que inicializa la base de datos creando una tabla con el ganador
     * 
     * @param 'nada'
     */
    public static void inicializarBD() {
        try {
            Class.forName("org.sqlite.JDBC");
            String tablaPerfilJugador = "CREATE TABLE IF NOT EXISTS perfil_jugador ("
                    + "codigo INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nombre VARCHAR(100) UNIQUE, "
                    + "victorias INTEGER"
                    + ");";

            try (Connection conexion = DriverManager.getConnection(NOMBRE_ARCHIVO_RANKING);
                    Statement declaracion = conexion.createStatement()) {
                declaracion.execute(tablaPerfilJugador);
                System.out.println("Base de datos de Ranking inicializada.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("No se encuentra el driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    /**
     * Método que primero comprueba que el jugador existe, si existe actualiza el
     * numero de victorias a su nombre en la tabla
     * si no se inserta en la tabla una victoria
     * 
     * @param 'nada'
     */
    public static void registrarVictoria(String nombreJugador) {
        String selectJugador = "SELECT victorias FROM perfil_jugador WHERE nombre = ?";
        String insertNuevoJugador = "INSERT INTO perfil_jugador (nombre, victorias) VALUES (?, 1)";
        String updateJugadorExistente = "UPDATE perfil_jugador SET victorias = victorias + 1 WHERE nombre = ?";
        boolean existe;

        try (Connection conn = DriverManager.getConnection(NOMBRE_ARCHIVO_RANKING)) {

            try (PreparedStatement pstmtSelect = conn.prepareStatement(selectJugador)) {
                pstmtSelect.setString(1, nombreJugador);
                try (ResultSet resultado = pstmtSelect.executeQuery()) {
                    existe = resultado.next();
                }
            }

            if (existe) {
                try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateJugadorExistente)) {
                    pstmtUpdate.setString(1, nombreJugador);
                    pstmtUpdate.executeUpdate();
                }
            } else {
                try (PreparedStatement pstmtInsert = conn.prepareStatement(insertNuevoJugador)) {
                    pstmtInsert.setString(1, nombreJugador);
                    pstmtInsert.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar victoria: " + e.getMessage());
        }
    }

    /**
     * Realiza un select de la tabla perfil jugador y saca el top 5 de jugadores con
     * más victorias
     * 
     * @param 'nada'
     */
    public static void mostrarTopJugadores() {
        String sql = "SELECT nombre, victorias FROM perfil_jugador ORDER BY victorias DESC LIMIT 5";

        try (Connection conn = DriverManager.getConnection(NOMBRE_ARCHIVO_RANKING);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- TOP MEJORES JUGADORES (GLOBAL) ---");
            while (rs.next()) {
                System.out.println("Jugador: " + rs.getString("nombre") +
                        " | Victorias: " + rs.getInt("victorias"));
            }
            System.out.println("--------------------------------------\n");

        } catch (SQLException e) {
            System.out.println("Error al consultar el ranking: " + e.getMessage());
        }
    }

    /**
     * Elimina el perfil del jugador indicado por parametro del ranking
     * 
     * @param 'nada'
     */
    public static void eliminarPerfil(String nombreJugador) {
        String sql = "DELETE FROM perfil_jugador WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(NOMBRE_ARCHIVO_RANKING);
                PreparedStatement pstmt = conn.prepareStatement(sql)) { //

            pstmt.setString(1, nombreJugador);
            int afectadas = pstmt.executeUpdate();

            if (afectadas > 0) {
                System.out.println("Perfil eliminado con éxito.");
            } else {
                System.out.println("No se encontró al jugador.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar perfil: " + e.getMessage());
        }
    }

    /**
     * ALTER TABLE (RA8 d)
     * Ejecuta este método desde el Modo Developer para cumplir el requisito de tu
     * profesora.
     */
    public static void añadirColumnaRacha() {
        String sql = "ALTER TABLE perfil_jugador ADD COLUMN racha_actual INTEGER DEFAULT 0";

        try (Connection conn = DriverManager.getConnection(NOMBRE_ARCHIVO_RANKING);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla alterada: Nueva columna 'racha_actual' añadida.");

        } catch (SQLException e) {
            System.out.println("Aviso al alterar la tabla (quizás la columna ya exista): " + e.getMessage());
        }
    }
}
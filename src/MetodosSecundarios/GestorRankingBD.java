package MetodosSecundarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase paralela para gestionar un ranking global usando SQLite (RA8 y RA9)
 */
public class GestorRankingBD {

    // Ruta de la base de datos (se creará automáticamente) [cite: 48]
    private static final String URL = "jdbc:sqlite:src/Archivos/ranking.db";

    /**
     * CONEXIÓN Y CREATE (RA9 a, b | RA8 e)
     */
    public static void inicializarBD() {
        try {
            Class.forName("org.sqlite.JDBC");
            String sql = "CREATE TABLE IF NOT EXISTS perfil_jugador ("
                    + "codigo INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nombre VARCHAR(100) UNIQUE, "
                    + "victorias INTEGER"
                    + ");";

            try (Connection conn = DriverManager.getConnection(URL);
                    Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Base de datos de Ranking inicializada.");
            }
        } catch (ClassNotFoundException e) {

            System.err.println("¡ERROR CRÍTICO! No se encuentra el driver JDBC. Revisa tu carpeta lib.");

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error al inicializar la BD: " + e.getMessage());

        }
    }

    /**
     * INSERT Y UPDATE (RA9 c, e | RA8 f, g)
     */
    public static void registrarVictoria(String nombreJugador) {
        String sqlSelect = "SELECT victorias FROM perfil_jugador WHERE nombre = ?";
        String sqlInsert = "INSERT INTO perfil_jugador (nombre, victorias) VALUES (?, 1)";
        String sqlUpdate = "UPDATE perfil_jugador SET victorias = victorias + 1 WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(URL)) {

            // 1. Comprobamos si el jugador ya existe (SELECT) [cite: 209, 212]
            boolean existe;
            try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
                pstmtSelect.setString(1, nombreJugador);
                try (ResultSet rs = pstmtSelect.executeQuery()) {
                    existe = rs.next(); // Si hay un resultado, existe [cite: 187, 215]
                }
            }

            // 2. Si existe, actualizamos. Si no, insertamos. [cite: 216, 223]
            if (existe) {
                try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
                    pstmtUpdate.setString(1, nombreJugador);
                    pstmtUpdate.executeUpdate();
                }
            } else {
                try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
                    pstmtInsert.setString(1, nombreJugador);
                    pstmtInsert.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar victoria: " + e.getMessage());
        }
    }

    /**
     * SELECT (RA9 d, f | RA8 g)
     */
    public static void mostrarTopJugadores() {
        String sql = "SELECT nombre, victorias FROM perfil_jugador ORDER BY victorias DESC LIMIT 5";

        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) { // [cite: 212, 213, 214]

            System.out.println("\n--- TOP MEJORES JUGADORES (GLOBAL) ---");
            while (rs.next()) { // Iteramos los resultados [cite: 187, 215]
                System.out.println("Jugador: " + rs.getString("nombre") +
                        " | Victorias: " + rs.getInt("victorias"));
            }
            System.out.println("--------------------------------------\n");

        } catch (SQLException e) {
            System.out.println("Error al consultar el ranking: " + e.getMessage());
        }
    }

    /**
     * DELETE (RA9 e | RA8 g)
     */
    public static void eliminarPerfil(String nombreJugador) {
        String sql = "DELETE FROM perfil_jugador WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) { //

            pstmt.setString(1, nombreJugador);
            int afectadas = pstmt.executeUpdate(); // [cite: 218]

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

        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla alterada: Nueva columna 'racha_actual' añadida.");

        } catch (SQLException e) {
            System.out.println("Aviso al alterar la tabla (quizás la columna ya exista): " + e.getMessage());
        }
    }
}
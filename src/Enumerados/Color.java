package Enumerados;

/**
 * Enum que crea la variable color para almacenar todos los colores del juego
 * 
 * @author DaniS y Libio
 */
public enum Color {
    ROJO("\u001B[31m"),
    AZUL("\u001B[34m"),
    VERDE("\u001B[32m"),
    AMARILLO("\u001B[33m"),
    NEGRO("\u001B[47;30m"); // Fondo blanco, letra negra

    // Atributos
    private final String codigoAnsi;

    // Constructor para instanciar un enumerado con un parametro
    Color(String codigoAnsi) {
        this.codigoAnsi = codigoAnsi;
    }

    // Getter
    public String getCodigoAnsi() {
        return codigoAnsi;
    }
}
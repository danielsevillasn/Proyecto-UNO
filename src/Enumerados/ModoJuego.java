package Enumerados;

/**
 * Enum que define los modos de juego disponibles en UNO
 * 
 * @author DaniS
 */
public enum ModoJuego {
    CLASICO("Clásico"),
    SIETE_CERO("Uno Siete-0");

    private final String nombre;

    ModoJuego(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
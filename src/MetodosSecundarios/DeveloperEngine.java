package MetodosSecundarios;

import java.util.Scanner;

/**
 * Clase para probar el juego sin pausas para no perder tiempo
 * 
 * @author DaniS y Libio
 */

public class DeveloperEngine {
    static Scanner s = new Scanner(System.in);
    // Configuración inicial por defecto
    private String[] nombresCargados = { "Jugador 1", "Jugador 2" };
    private int cantidadActual = 2;
    
    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     */
    public void ejecutarSistemaCompleto() throws InterruptedException {
        String opcion1 = "";
        boolean salir = false;
        Datos.milisegundos = 0;
        Juego.Sistema(opcion1, salir, nombresCargados, cantidadActual);
    }

}

package MetodosSecundarios;

import java.util.Scanner;

/**
 * Clase que estructurada mediante una serie de métodos para todo el juego
 *
 * @author DaniS y Libio
 */
public class Juego {

    static Scanner s = new Scanner(System.in);
    // Configuración inicial por defecto
    public String[] nombresCargados = { "Jugador 1", "Jugador 2" };
    public int cantidadActual = 2;

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     */
    public void ejecutarSistemaCompleto() throws InterruptedException {
        String opcion1 = "";
        boolean salir = false;
        pantallas.PantallaUNO();
        
        Sistema(opcion1, salir, nombresCargados, cantidadActual);
    }

    public static void Sistema(String opcion1, boolean salir, String[] nombresCargados, int cantidadActual) throws InterruptedException {
        String opcion2;
        while (!opcion1.equals("-1")) {
            opcion1 = pantallas.PantallaInicio();
            switch (opcion1) {
                case "1":
                    while (!salir) {
                        opcion2 = pantallas.PantallaMenu();

                        switch (opcion2) {
                            case "1": // Configurar modo de juego
                                pantallas.ModoDeJuego = pantallas.PantallaModosDeJuego();
                                break;
                            case "2": // Configurar nombres y cantidad de jugadores
                                Datos.saltoDeLineas();
                                UnoEngine.configurarJugadores(nombresCargados, cantidadActual);
                                break;
                            case "3": // Mostrar instrucciones
                                Datos.saltoDeLineas();
                                pantallas.PantallaReglas();
                                break;
                            case "4": // Iniciar una partida
                                Datos.saltoDeLineas();
                                UnoEngine.partida(cantidadActual, nombresCargados);
                                break;
                            case "5": // Salir del programa
                                salir = true;
                                break;
                            default:
                                Datos.entradaIncorrecta();
                                break;
                        }
                    }
                    break;
                case "2":
                    opcion1 = "-1";
                    break;
                default:
                    Datos.entradaIncorrecta();
                    break;
            }
        }
    }
    
}
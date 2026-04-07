package MetodosSecundarios;

import java.util.Scanner;

import Excepciones.ReiniciarJuego;
import Excepciones.SalirDelJuego;

/**
 * Clase que estructurada mediante una serie de métodos para todo el juego
 *
 * @author DaniS y Libio
 */
public class Juego {

    static Scanner s = new Scanner(System.in);
    // Configuración inicial por defecto
    protected static String[] nombresCargados = { "Jugador 1", "Jugador 2" };
    public static int cantidadActualJugadores;
    private static boolean salir;

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void iniciarJuego() throws InterruptedException, ReiniciarJuego {
        Datos.saltoDeLineas();
        cantidadActualJugadores = 2;
        Pantallas.modoDeJuego = "Clásico";

        menuInicio();
    }

    /**
     * Menu de opciones que permite cambiar el modo de ejecucion y salir del juego
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void menuInicio() throws InterruptedException, ReiniciarJuego {
        int modoEjecucion;
        salir = false;
        while (!salir) {
            try {
                modoEjecucion = Pantallas.eleccionDeEjecucion();
                switch (modoEjecucion) {
                    case 1:
                        ejecutarSistemaCompleto();
                        salir = true;
                        break;
                    case 2:
                        ejecutarSistemaCompletoDeveloper();
                        salir = true;
                        break;
                    default:
                        Datos.entradaIncorrecta();
                        Thread.sleep(Datos.milisegundos);
                        break;
                }
            } catch (SalirDelJuego e) {
                System.out.println(e.getMessage());
                salir = true;
            }
        }
    }

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void ejecutarSistemaCompleto() throws InterruptedException, ReiniciarJuego {
        Pantallas.PantallaUNO();
        Sistema();
    }

    /**
     * Método que ejecuta el sistema de desarrollador (sin tiempos de espera ni
     * pantalla principal)
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void ejecutarSistemaCompletoDeveloper()
            throws InterruptedException, ReiniciarJuego {
        Datos.milisegundos = 0;
        Sistema();
    }

    /**
     * Método que ejecuta el sistema de juego completo con sus menús
     * 
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     * @param 'ninguno'
     */
    public static void Sistema() throws InterruptedException, ReiniciarJuego {
        String opcion1 = "";
        try {
            while (true) {
                opcion1 = Pantallas.PantallaInicio();
                switch (opcion1) {
                    case "1":
                        flujoDelSistema();
                        break;
                    case "2":
                        throw new SalirDelJuego("Has salido del juego");
                    default:
                        Datos.entradaIncorrecta();
                        break;
                }
            }
        } catch (ReiniciarJuego e) {
            reinicio();
            System.out.println("\n" + e.getMessage());
            Datos.pulsaEnter();
            Juego.iniciarJuego();
        } catch (SalirDelJuego e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que reproduce el flujo por el que circula el sistema
     * 
     * @param salir valor booleano que expresa cuando sale del flujo
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void flujoDelSistema() throws InterruptedException, ReiniciarJuego {
        String opcion2;
        salir = false;
        while (!salir) {
            opcion2 = Pantallas.PantallaMenu();

            switch (opcion2) {
                case "1": // Configurar modo de juego
                    Pantallas.PantallaModosDeJuego();
                    break;
                case "2": // Configurar nombres y cantidad de jugadores
                    cantidadActualJugadores = UnoEngine.configurarJugadores();
                    break;
                case "3": // Mostrar instrucciones
                    Pantallas.PantallaReglas();
                    break;
                case "4": // Iniciar una partida
                    Datos.saltoDeLineas();
                    UnoEngine.partida(nombresCargados);
                    break;
                case "5": // Salir del programa
                    salir = true;
                    break;
                default:
                    Datos.entradaIncorrecta();
                    break;
            }
        }
    }

    /**
     * Flujo de reinicio del sistema
     * 
     * @param 'ninguno'
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void reinicio() throws InterruptedException, ReiniciarJuego {
        System.out.println("\n\nReiniciando...");
        Thread.sleep(2000);
        Datos.saltoDeLineas();

        menuReinicio();

    }

    /**
     * Menu que muestra como quieres reiniciar el sistema
     * 
     * @param 'ninguno'
     * @throws ReiniciarJuego para reiniciar el juego cuando se quiera
     */
    public static void menuReinicio() throws ReiniciarJuego {
        salir = false;
        String opcionReinicio = "";
        while (!salir) {
            System.out.println("Quieres reiniciar el estado del juego (Nombres, modo y jugadores)?");
            opcionReinicio = Datos.pedirCadena("Ingrese \"s\" si sí o \"n\" si no: ");
            switch (opcionReinicio) {
                case "s":
                    resetearEstado();
                    salir = true;
                    break;
                case "n":
                    salir = true;
                    break;
                default:
                    System.out.println("El mensaje introducido no es un \"s\" o un \"n\" ");
                    break;
            }
        }
    }

    /**
     * Metodo que resetea el estado del juego al predeterminado cuando se reiniciar
     * el juego
     * 
     * @param 'ninguno'
     */
    public static void resetearEstado() {
        Juego.nombresCargados = new String[] { "Jugador 1", "Jugador 2" };
        Juego.cantidadActualJugadores = 2;

        Pantallas.modoDeJuego = "Clásico";
        Pantallas.jugadores = "2";
        Pantallas.nombreJugador = "Invitado";

        Datos.milisegundos = 1000;

        System.out.println("\nEstado del juego restablecido correctamente.");
    }
}
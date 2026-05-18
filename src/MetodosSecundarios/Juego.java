package MetodosSecundarios;

import java.util.ArrayList;
import java.util.Scanner;

import Enumerados.ModoEjecucion;
import Excepciones.ReiniciarJuego;
import Excepciones.SalirDelJuego;

/**
 * Clase que establece la raiz del juego
 *
 * @author DaniS y Libio
 */
public class Juego {

    static Scanner s = new Scanner(System.in);
    // Configuración inicial por defecto
    protected static ArrayList<String> nombresCargados = new ArrayList<>();
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
        nombresCargados.clear();
        nombresCargados.add("Jugador1");
        nombresCargados.add("Jugador2");
        Menus.modoDeJuego = "Clásico";

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
                modoEjecucion = Menus.menuEjecucion();
                switch (modoEjecucion) {
                    case 1:
                        ejecutarSistemaCompleto(ModoEjecucion.NORMAL);
                        salir = true;
                        break;
                    case 2:
                        ejecutarSistemaCompleto(ModoEjecucion.DEVELOPER);
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
     * @param modo el modo de ejecución (NORMAL o DEVELOPER)
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     */
    public static void ejecutarSistemaCompleto(ModoEjecucion modo) throws InterruptedException, ReiniciarJuego {
        if (modo == ModoEjecucion.NORMAL) {
            Pantallas.pantallaUNO();
        } else {
            Datos.milisegundos = 0;
        }
        sistema();
    }

    /**
     * Método que ejecuta el sistema de juego completo con sus menús
     * 
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reiniciar el juego cuando se quiera
     * @param 'ninguno'
     */
    public static void sistema() throws InterruptedException, ReiniciarJuego {
        String opcion1 = "";
        try {
            while (true) {
                opcion1 = Menus.menuBienvenida();
                switch (opcion1) {
                    case "1":
                        flujoDelSistema();
                        break;
                    case "2":
                        throw new SalirDelJuego();
                    default:
                        Datos.entradaIncorrecta();
                        break;
                }
            }
        } catch (ReiniciarJuego e) {
            reinicio();
            System.out.println("\n" + e.getMessage());
            Thread.sleep(Datos.milisegundos);
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
            opcion2 = Menus.menuInicio();

            switch (opcion2) {
                case "1": // Configurar modo de juego
                    Menus.menuModoDeJuego();
                    break;
                case "2": // Configurar nombres y cantidad de jugadores
                    cantidadActualJugadores = Registro.configurarJugadores();
                    break;
                case "3": // Mostrar instrucciones
                    AlmacenamientoDatos.pantallaReglas();
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

        Menus.menuReinicio();

    }

    /**
     * Metodo que resetea el estado del juego al predeterminado cuando se reiniciar
     * el juego
     * 
     * @param 'ninguno'
     */
    public static void resetearEstado() {
        nombresCargados.clear();
        cantidadActualJugadores = 2;

        Menus.modoDeJuego = "Clásico";
        Menus.jugadores = "2";
        Menus.nombreJugador = "Invitado";

        Datos.milisegundos = 1000;

        System.out.println("\nEstado del juego restablecido correctamente.");
    }
}

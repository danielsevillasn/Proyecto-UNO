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
    public static String[] nombresCargados = { "Jugador 1", "Jugador 2" };
    public static int cantidadActualJugadores;
    private static int modoDeTipoDeEjecutar;

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     * 
     * @throws InterruptedException para los thread sleep
     * @throws SalirDelJuego para salir del juego cuando se quiera
     * @param 'ninguno'
     */
    public static void iniciarJuego() throws InterruptedException, SalirDelJuego, ReiniciarJuego {
        Datos.saltoDeLineas();
        cantidadActualJugadores = 2;
        Pantallas.modoDeJuego = "Clásico";
        while (true) {
            try {
                modoDeTipoDeEjecutar = Pantallas.eleccionDeTipoDeEjecutar();
                if (modoDeTipoDeEjecutar == 1) {
                    ejecutarSistemaCompleto();
                    break;
                } else if (modoDeTipoDeEjecutar == 2) {
                    ejecutarSistemaCompletoDeveloper();
                    break;
                }else{
                    System.out.println("Opcion invalidad, introduce un numero entero del 0-2");
                    Thread.sleep(Datos.milisegundos);
                }
            } catch (SalirDelJuego e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     * 
     * @throws InterruptedException para los thread sleep
     * @param 'ninguno'
     */
    public static void ejecutarSistemaCompleto() throws InterruptedException, SalirDelJuego, ReiniciarJuego {
        Pantallas.PantallaUNO();
        Sistema();
    }

    /**
     * Método que ejecuta el sistema de desarrollador (sin tiempos de espera ni
     * pantalla principal)
     * 
     * @throws InterruptedException para los thread sleep
     * @throws SalirDelJuego para salir del juego cuando se quiera
     * @param 'ninguno'
     */
    public static void ejecutarSistemaCompletoDeveloper()
            throws InterruptedException, SalirDelJuego, ReiniciarJuego {
        Datos.milisegundos = 0;
        Sistema();
    }

    /**
     * Metodo que resetea el estado del juego al predeterminado cuando se reiniciar
     * el juego
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

    /**
     * Método que ejecuta el sistema de juego completo con sus menús
     * 
     * @throws InterruptedException para los thread sleep
     * @throws SalirDelJuego para salir del juego cuando se quiera
     * @throws ReiniciarJuego para reiniciar el juego cuando se quiera
     * @param 'ninguno'
     */
    public static void Sistema() throws InterruptedException, SalirDelJuego, ReiniciarJuego {
        String opcion1 = "";
        boolean salir = false;
        String opcionReinicio = "";
        String opcion2;
        try {
            while (true) {

                opcion1 = Pantallas.PantallaInicio();
                salir = false;
                switch (opcion1) {
                    case "1":
                        while (!salir) {
                            opcion2 = Pantallas.PantallaMenu();

                            switch (opcion2) {
                                case "1": // Configurar modo de juego
                                    Pantallas.modoDeJuego = Pantallas.PantallaModosDeJuego();
                                    break;
                                case "2": // Configurar nombres y cantidad de jugadores
                                    Datos.saltoDeLineas();
                                    cantidadActualJugadores = UnoEngine.configurarJugadores(cantidadActualJugadores);
                                    break;
                                case "3": // Mostrar instrucciones
                                    Datos.saltoDeLineas();
                                    Pantallas.PantallaReglas();
                                    break;
                                case "4": // Iniciar una partida
                                    Datos.saltoDeLineas();
                                    UnoEngine.partida(cantidadActualJugadores, nombresCargados);
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
                        throw new SalirDelJuego("Has salido del juego");
                    default:
                        Datos.entradaIncorrecta();
                        break;
                }
            }
        } catch (ReiniciarJuego e) {
            System.out.println("\n\nReiniciando...");
            Thread.sleep(2000);
            Datos.saltoDeLineas();
            salir = false;

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
            System.out.println("\n" + e.getMessage());
            Datos.pulsaEnter();
            Juego.iniciarJuego();
        } catch (SalirDelJuego e) {
            System.out.println(e.getMessage());
        }

    }

}
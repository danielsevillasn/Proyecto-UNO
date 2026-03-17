package MetodosSecundarios;

import java.util.Scanner;

import Excepciones.CartaLanzadaNoValida;
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
    private int modoDeTipoDeEjecutar;

    //Constructor por defecto que inicializa el valor cantidad jugadores
    public Juego() throws InterruptedException, SalirDelJuego, CartaLanzadaNoValida{
        cantidadActualJugadores = 2;
        modoDeTipoDeEjecutar = 1;
    }

    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     * @throws InterruptedException para los thread sleep
     * @throws SalirDelJuego para salir del juego cuando se quiera
     * @param 'ninguno'
     */
    public void iniciarJuego()throws InterruptedException, SalirDelJuego, CartaLanzadaNoValida{
        Datos.saltoDeLineas();
        try{
            modoDeTipoDeEjecutar = pantallas.eleccionDeTipoDeEjecutar();
            if(modoDeTipoDeEjecutar == 1){
                ejecutarSistemaCompleto();
            }else if(modoDeTipoDeEjecutar == 2){
                ejecutarSistemaCompletoDeveloper();
            }
        }catch(SalirDelJuego e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Menú principal del sistema, que ejecuta el sistema completo
     * @throws InterruptedException para los thread sleep
     * @param 'ninguno'
     */
    public void ejecutarSistemaCompleto() throws InterruptedException, SalirDelJuego, CartaLanzadaNoValida {
        pantallas.PantallaUNO();
        Sistema();
    }

    /**
     * Método que ejecuta el sistema de desarrollador (sin tiempos de espera ni pantalla principal)
     * @throws InterruptedException para los thread sleep
     * @param 'ninguno'
     */
    public void ejecutarSistemaCompletoDeveloper() throws InterruptedException, SalirDelJuego, CartaLanzadaNoValida{
        Datos.milisegundos = 0;
        Sistema();
    }

    /**
     * Método que ejecuta el sistema de juego completo con sus menús
     * @throws InterruptedException para los thread sleep
     * @param 'ninguno'
     */
    public static void Sistema() throws InterruptedException, SalirDelJuego, CartaLanzadaNoValida {
        String opcion1 = "";
        boolean salir = false;
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
                                cantidadActualJugadores = UnoEngine.configurarJugadores(cantidadActualJugadores);
                                break;
                            case "3": // Mostrar instrucciones
                                Datos.saltoDeLineas();
                                pantallas.PantallaReglas();
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
                    opcion1 = "-1";
                    break;
                default:
                    Datos.entradaIncorrecta();
                    break;
            }
        }
    }

}
package MetodosSecundarios;

import java.util.Scanner;

import Excepciones.ReiniciarJuego;
import Excepciones.SalirDelJuego;

/**
 * Clase para imprimir todos los menus del juego
 * 
 * @author DaniS y Libio
 */
public class Menus {
    // Scanner (Objeto) estático que se podrá utilizar en todos los métodos de la
    // clase
    static Scanner s = new Scanner(System.in);

    // Variables necesarias para que los métodos de impresión funcionen
    public static String modoDeJuego = "Clásico";
    public static String jugadores = "2";
    public static String nombreJugador = "";

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo String
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static String menuBienvenida() throws InterruptedException, ReiniciarJuego {
        Datos.saltoDeLineas();

        System.out.println("==========Bienvenido/a a UNO=========");
        System.out.println("\t1- Jugar");
        System.out.println("\t2- Salir");
        return Datos.pedirCadena("\tElija opción (1-2): ");
    }

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo String
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static String menuInicio() throws InterruptedException, ReiniciarJuego {
        Datos.saltoDeLineas();

        System.out.println("==========Inicio=========");
        System.out.println("\t1- Modo de juego");
        System.out.println("\t2- Jugadores");
        System.out.println("\t3- Reglas");
        System.out.println("\t4- Iniciar juego");
        System.out.println("\t5- Atrás <--");
        System.out.println("==========================");
        System.out.println("Modo de juego: " + modoDeJuego + "\tjugadores: " + jugadores);

        return (Datos.pedirCadena("\tElija opción (1-5): "));
    }

    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo String
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static void menuModoDeJuego() throws InterruptedException, ReiniciarJuego {
        Datos.saltoDeLineas();

        System.out.println("=========Modos de juego===========");
        System.out.println("\t1. Clásico");
        System.out.println("\t2. Otra modalidad");

        modoDeJuegoSeleccionado();

    }

    /**
     * Método para seleccionar el modo de juego
     * 
     * @param ModoDeJuego variable tipo String que representa que modo de juego esta
     *                    seleccionado
     * @return ModoDeJuego variable tipo String que representa que modo de juego se
     *         ha seleccionado
     * @throws ReiniciarJuego para reinciar el juego cuando se quiera
     */
    public static void modoDeJuegoSeleccionado() throws ReiniciarJuego {
        boolean salir = false;
        while (!salir) {
            int opcion = Datos.pedirEntero("\tElija opción (1-3): ");
            switch (opcion) {
                case 1:
                    modoDeJuego = "Clásico";
                    salir = true;
                    break;
                case 2:
                    modoDeJuego = "Otro";
                    salir = true;
                    break;

                default:
                    System.out.println("La opcion introducida no es valida, elige entre el 1 y el 3");
                    break;
            }
        }
    }

    /**
     * Método que pide el numero de jugadores y que comprueba que no se pase del
     * rango habilitado
     * 
     * @param 'ninguno'
     * @return valor entero que representa el numero de jugadores
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static int menuJugadores() throws InterruptedException, ReiniciarJuego {
        int numJugadores;
        boolean rangoJugadores = false;

        Datos.saltoDeLineas();
        System.out.println("============Jugadores============");

        do {
            numJugadores = Datos.pedirEntero("¿Cuántos jugadores (2-8)? ");
            if (numJugadores >= 2 && numJugadores <= 8) {
                rangoJugadores = true;
            } else {
                Datos.entradaIncorrecta();
            }
        } while (!rangoJugadores);
        return numJugadores;
    }

    /**
     * Muestra al usuario un menú de opciones que permite ejecutar el juego de
     * diferentes formas
     * 
     * @param 'ninguno'
     * @return Opción introducida por teclado tipo int
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     * @throws SalirDelJuego        para salir del juego cuando se quiera
     */
    public static int menuEjecucion() throws InterruptedException, ReiniciarJuego, SalirDelJuego {
        Datos.saltoDeLineas();

        int resultado = 0;
        System.out.println("Como quieres ejecutar el juego?");
        System.out.println("\t1- Normal");
        System.out.println("\t2- Developer");
        System.out.println("\t0- Salir");
        resultado = Datos.pedirEntero("\tElija opción (0-2): ");
        if (resultado == 0) {
            throw new SalirDelJuego();
        }
        return resultado;
    }

    /**
     * Menu que muestra como quieres reiniciar el sistema
     * 
     * @param 'ninguno'
     * @throws ReiniciarJuego para reiniciar el juego cuando se quiera
     */
    public static void menuReinicio() throws ReiniciarJuego {
        boolean salir = false;
        String opcionReinicio = "";
        while (!salir) {
            System.out.println("Quieres reiniciar el estado del juego (Nombres, modo y jugadores)?");
            opcionReinicio = Datos.pedirCadena("Ingrese \"s\" si sí o \"n\" si no: ");
            switch (opcionReinicio) {
                case "s":
                    Juego.resetearEstado();
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
}

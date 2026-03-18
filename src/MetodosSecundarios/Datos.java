package MetodosSecundarios;

import java.util.Scanner;

import Excepciones.ReiniciarJuego;

/**
 * Clase para todos los métodos o funcionalidades propias de la Entrada/Salida
 * 
 * @author DaniS y Libio
 */
public class Datos {
    // Scanner (Objeto) estático que se podrá utilizar en todos los métodos de la
    // clase
    static Scanner s = new Scanner(System.in);
    static int milisegundos = 1000;

    /**
     * Pide una cadena de caracteres y la devuelve
     * 
     * 
     * @param mensaje de petición de datos tipo String
     * @return Dato introducido por teclado tipo string
     * @throws InterruptedException para los thread sleep
     * @throws SalirDelJuego
     */
    // Los parámetros pueden ser variables u objetos y estos se diferencian en:
    // Los parámetros se copian y no se modifican en el código principal
    // Y los objetos se copian y si se modifican en el código principal
    public static String pedirCadena(String mensaje) throws InterruptedException, ReiniciarJuego {
        System.out.print(mensaje);
        String entrada = s.nextLine().trim();

        if (entrada.equalsIgnoreCase("reiniciar")) {
            throw new ReiniciarJuego("Regresando al menú principal...");
        }

        return entrada;
    }

    /**
     * Pide un entero y lo devuelve
     * 
     * @param mensaje de petición de datos tipo entero
     * @return Dato introducido por teclado tipo entero
     * @throws InterruptedException para los thread sleep
     * @throws SalirDelJuego
     */
    public static int pedirEntero(String mensaje) throws InterruptedException, ReiniciarJuego {

        while (true) {
            System.out.print(mensaje);
            String entrada = s.nextLine().trim();

            // Para que el usuario vuelva a iniciar el juego cuando quiera
            if (entrada.equalsIgnoreCase("reiniciar")) {
                throw new ReiniciarJuego("Regresando al menú principal...");
            }

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                entradaIncorrecta();
            }
        }
    }

    /**
     * Método que solo sirve para pulsar enter cuando lo pide por pantalla
     */
    public static void pulsaEnter() {
        System.out.println("Pulsa enter para continuar");
        s.nextLine();
    }

    /**
     * Solamente es un mensaje para entradas incorrectas
     */
    public static void entradaIncorrecta() {
        System.out.println("Mensaje no válido, introduce los valores sugeridos.");
    }

    /**
     * Salto de líneas para cuando se cambie de menu/salto de escena
     * 
     * @throws InterruptedException para los thread sleep
     */
    public static void saltoDeLineas() throws InterruptedException {
        Thread.sleep(milisegundos);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

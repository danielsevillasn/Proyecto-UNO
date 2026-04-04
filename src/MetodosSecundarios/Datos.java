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
    public static int milisegundos = 1000;

    /**
     * Pide una cadena de caracteres y la devuelve
     * 
     * 
     * @param mensaje de petición de datos tipo String
     * @return Dato introducido por teclado tipo string
     * @throws ReiniciarJuego para reiniciar el juego cuando se quiera
     */
    public static String pedirCadena(String mensaje) throws ReiniciarJuego {
        String entrada;
        while (true) {
            System.out.print(mensaje);
            entrada = s.nextLine().trim();
            if(!entrada.isEmpty()){
                break;
            }
            System.out.println("No se puede introducir un valor nulo");
        }

        // Para que el usuario vuelva a iniciar el juego cuando quiera
        solicitarReinicio(entrada);

        return entrada;
    }

    /**
     * Pide un entero y lo devuelve
     * 
     * @param mensaje de petición de datos tipo entero
     * @return Dato introducido por teclado tipo entero
     * @throws ReiniciarJuego para reiniciar el juego cuando se quiera
     */
    public static int pedirEntero(String mensaje) throws ReiniciarJuego {

        while (true) {
            System.out.print(mensaje);
            String entrada = s.nextLine().trim();

            // Para que el usuario vuelva a iniciar el juego cuando quiera
            solicitarReinicio(entrada);

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                entradaIncorrecta();
            }
        }
    }

    /**
     * Método que solo sirve para pulsar enter cuando lo pide por pantalla
     * 
     * @param 'ninguno'
     */
    public static void pulsaEnter() {
        System.out.println("Pulsa enter para continuar");
        s.nextLine();
    }

    /**
     * Solamente es un mensaje para entradas incorrectas
     * 
     * @param 'ninguno'
     */
    public static void entradaIncorrecta() {
        System.out.println("Mensaje no válido, introduce los valores sugeridos.");
    }

    /**
     * Salto de líneas para cuando se cambie de menu/salto de escena
     * 
     * @throws InterruptedException para los thread sleep
     * @param 'ninguno'
     */
    public static void saltoDeLineas() throws InterruptedException {
        Thread.sleep(milisegundos);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Método que lanza la excepcion reiniciar juego si se introduce terminar
     * 
     * @param entrada valor tipo String que representa la entrada de informacion
     * @throws ReiniciarJuego para reiniciar el juego cuando se quiera
     */
    public static void solicitarReinicio(String entrada) throws ReiniciarJuego {
        if (entrada.equalsIgnoreCase("reiniciar")) {
            throw new ReiniciarJuego("Regresando al menú principal...");
        }
    }
}

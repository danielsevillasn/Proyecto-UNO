package MetodosSecundarios;
import java.security.Principal;
import java.util.Scanner;

/**
 * Clase para todos los métodos o funcionalidades propias de la Entrada/Salida
 * 
 * @author DaniS y Libio
 */
public class Datos {
    //Scanner (Objeto) estático que se podrá utilizar en todos los métodos de la clase
    static Scanner s = new Scanner(System.in);

    
    /**
     * Pide una cadena de caracteres y la devuelve
     * 
     * 
     * @param mensaje de petición de datos tipo String
     * @return Dato introducido por teclado tipo string
     */
    //Los parámetros pueden ser variables u objetos y estos se diferencian en:
    //Los parámetros se copian y no se modifican en el código principal
    //Y los objetos se copian y si se modifican en el código principal
    public static String pedirCadena(String mensaje){
        System.out.print(mensaje);
        return s.nextLine();
    }

    /**
     * Pide un entero y lo devuelve
     * 
     * 
     * @param mensaje de petición de datos tipo entero
     * @return Dato introducido por teclado tipo entero
     */
    public static int pedirEntero(String mensaje) throws InterruptedException{
    Scanner teclas = new Scanner(System.in);
    
    while (true) {
        System.out.print(mensaje);
        String entrada = teclas.nextLine().trim();
        
        //Para que el usuario vuelva a iniciar el juego cuando quiera
        if (entrada.equalsIgnoreCase("terminar")) {
            Juego juego = new Juego();
            juego.ejecutarSistemaCompleto(); //Vuelve a ejecutar el sistema
            return 0; // No importa el valor
        }
        
        try {

            
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido o 'terminar'");
        }
    }
}


    /**
     * Salto de líneas para cuando se cambie de menu/salto de escena 
     * 
     * @param 'nada'
     * @return nada
     */
    public static void saltoDeLineas() throws InterruptedException{
        Thread.sleep(1000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Solicita por consola el número de participantes y sus respectivos nombres.
     */
    public static void configurarJugadores(String[] nombresCargados, int cantidadActual) throws InterruptedException {
        int numJugadores;
        boolean rangoJugadores = false;
        pantallas.PantallaJugadores();

        do {
            numJugadores = Datos.pedirEntero("¿Cuántos jugadores (2-4)? ");
            if (numJugadores >= 2 && numJugadores <= 4) {
                rangoJugadores = true;
            } else {
                System.out.println("Error: El número debe estar entre 2 y 4.");
            }
        } while (!rangoJugadores);

        cantidadActual = numJugadores;

        // Nombres por defecto sobreescritos
        nombresCargados = new String[cantidadActual];
        for (int i = 0; i < cantidadActual; i++) {
            nombresCargados[i] = Datos.pedirCadena("Nombre Jugador " + (i + 1) + ": ");
        }

        pantallas.Jugadores = String.valueOf(cantidadActual);
    }
}

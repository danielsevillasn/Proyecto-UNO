package MetodosSecundarios;
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
    public static int pedirEntero(String mensaje){
        int dato = 0;
        boolean datoValido = false;
        do {
            try {
                System.out.print(mensaje);
                dato = Integer.parseInt(s.nextLine());
                datoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("El mensaje introducido no es un numero entero");
            }
        } while (!datoValido);
        return dato;
    }

    /**
     * Salto de líneas para cuando se cambie de menu/salto de escena 
     * 
     * @param 'nada'
     * @return nada
     */
    public static void saltoDeLíneas() throws InterruptedException{
        Thread.sleep(1000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

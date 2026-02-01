package MetodosAux;
import java.util.Scanner;

/**
 * Clase para todos los metodos o funcionalidades propias de la Entrada/Salida
 * 
 * @author DaniS y Libio
 */
public class Datos {
    //Scanner (Objeto) estatico que se podra utilizar en todos los metodos de la clase
    static Scanner s = new Scanner(System.in);

    
    /**
     * Pide una cadena de carateres y la devuelve
     * 
     * 
     * @param mensaje de peticion de datos tipo String
     * @return Dato introducido por teclado tipo string
     */
    //Los parametros pueden ser variables o objetos y estos se diferencian en:
    //Los parametros se copian y no se modifican en el codigo principal
    //Y los objetos se copian y si se modifican en el codigo principal
    public static String pedirCadena(String mensaje){
        System.out.print("Dame "+mensaje);
        String dato = s.nextLine();
        return(dato);
    }

    /**
     * Pide un entero y lo devuelve
     * 
     * 
     * @param mensaje de peticion de datos tipo entero
     * @return Dato introducido por teclado tipo entero
     */
    public static int pedirEntero(String mensaje){
        System.out.print("Dame "+mensaje);
        int dato = s.nextInt();
        return dato;
    }
}

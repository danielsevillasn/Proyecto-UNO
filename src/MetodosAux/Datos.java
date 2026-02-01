package MetodosAux;

import java.util.Scanner;

//Todos los parametros de esta clase son por valor, es decir, se copian y no modifican su valor en la clase principal
/**
 * Clase para todos los metodos o funcionalidades propias de la Entrada/Salida
 *
 */
public class Datos {
    //Scanner (Objeto) estatico que se podra utilizar en todos los metodos de la clase
    static Scanner sc = new Scanner(System.in);

    //Documentacion del metodo y ha de ir siempre
    /**
     * Muestra al usuario un menú de opciones; pide que teclee una de ellas y
     * devuelve la Opción introducida
     * 
     * @param ninguno
     * @return Opción introducida por teclado tipo String
     */
    //Como no tiene el void entonces si devuelve un valor
    public static String InicioUno() {
        String InicioUno = "";
        System.out.println();
        System.out.println("==========Inicio=========");
        System.out.println("\t1- Modo de juego");
        System.out.println("\t2- Jugadores");
        System.out.println("\t3- Reglas");
        System.out.println("\t4- Iniciar juego");
        System.out.println("\t5- Salir");
        System.out.println("==========================");
        System.out.print("\tElija opción: ");

        System.out.println("Modo de juego: " + ModoDeJuego + "\tjugadores: " + Jugadores);
        
        InicioUno = sc.nextLine();

        //Devuelve el valor de la variable
        return (InicioUno);
    }

    public static String ModoDeJuego() {
        String ModoDeJuego = "";
        System.out.println("=========Modos de juego===========");
        System.out.println("\t1. Clásico");
        System.out.println("\t2. Otra modalidad");
        System.out.println("\t3. Otra modalidad");
        System.out.print("\tElija opción: ");
        ModoDeJuego = sc.nextLine();
        switch (ModoDeJuego) {
            case "1":
                ModoDeJuego = "Clásico";
                break;
            default:
                break;
        }
        return (ModoDeJuego);
    }

    public static String PantallaJugadores() {
        String PantallaJugadores = "";
        System.out.println("============Jugadores============");
        System.out.print("1- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        System.out.print("2- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        System.out.print("3- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        System.out.print("4- ");
        System.out.println("\nPulse enter para continuar, 0 para salir");
        return (PantallaJugadores);
    }

    public static void PantallaReglas(){
        System.out.println("============Reglas============");
        System.out.println("Principales: ");
        System.out.println("El objetivo principal de UNO es ser el primer jugador en quedarse sin cartas, tras repartir 7 a cada uno.");
        System.out.println("En tu turno, debes igualar la carta superior de la pila por color, número o símbolo.");
        System.out.println("Si no tienes, roba una del mazo. Al quedar con una carta, grita \\\\\\\"¡UNO!\\\\\\\" o serás penalizado");
        System.out.println("Si no tienes, roba una del mazo. Al quedar con una carta, grita \\\\\\\"¡UNO!\\\\\\\" o serás penalizado");
        System.out.println("Si no tienes, roba una del mazo. Al quedar con una carta, grita \\\\\\\"¡UNO!\\\\\\\" o serás penalizado");
        System.out.println("Modo de juego: "+ModoDejuego);
        System.out.println("\nPulse enter para salir...");
    }


















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
        String dato = sc.nextLine();
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
        int dato = sc.nextInt();
        return dato;
    }
}

/**
 * eporfheqoprfjqepiuruf
 * erogheqo`r´
 * eqr+pijg
 * e+oqrig
 * qe+onrig
 */
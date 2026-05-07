package MetodosSecundarios;

import Excepciones.NombreUsuarioNoValido;
import Excepciones.ReiniciarJuego;

/**
 * Clase que gestiona el registro y validación de los jugadores
 * 
 * @author DaniS y Libio
 */
public class Registro {

    /**
     * Configura el número de jugadores para el juego
     * 
     * @return valor entero que representa la cantidad actual de jugadores
     * @throws InterruptedException para los thread sleep
     * @throws ReiniciarJuego       para reinciar el juego cuando se quiera
     */
    public static int configurarJugadores() throws InterruptedException, ReiniciarJuego {
        int numJugadores;

        numJugadores = Menus.menuJugadores();

        Juego.nombresCargados.clear();

        pedirNombreJugadores(numJugadores);

        Menus.jugadores = "" + numJugadores;
        return numJugadores;
    }

    /**
     * Método que pide el nombre de los jugadores, valida segun las condiciones
     * impuestas que esta correcto y lo mete en el array de los nombres del juego
     * 
     * @param cantidadActualJugadores cantidad de nombres a pedir
     * @throws ReiniciarJuego para reinciar el juego cuando se quiera
     */
    private static void pedirNombreJugadores(int cantidadActualJugadores) throws ReiniciarJuego {
        boolean nombreValido;
        String nombre;
        for (int i = 0; i < cantidadActualJugadores; i++) {
            nombreValido = false;
            do {
                try {
                    nombre = Datos.pedirCadena("Nombre Jugador " + (i + 1) + ": ");

                    validacionNombre(nombre);

                    Juego.nombresCargados.add(nombre);
                    nombreValido = true;
                } catch (NombreUsuarioNoValido e) {
                    System.out.println(e.getMessage());
                    System.out.println("Para que tu usuario sea válido, debe cumplir:");

                    // Imprime las sugerencias del array list sugerenciasÇ
                    for (String sugerencia : e.getSugerencias()) {
                        System.out.println("- " + sugerencia);
                    }
                }
            } while (!nombreValido);
        }
        Datos.pulsaEnter();
    }

    /**
     * Método que valida el nombre de un usuario de tal forma que siga las
     * condiciones impuestas en la excepcion
     * 
     * @param nombreUsuario variable tipo String que representa el nombre del
     *                      usuario
     * @throws NombreUsuarioNoValido excepcion que recoge todas las condiciones para
     *                               luego mostrarlas en caso de que no se cumplan
     */
    public static void validacionNombre(String nombreUsuario) throws NombreUsuarioNoValido {
        // Inicalizamos la excepcion pasando por parametro el nombre
        NombreUsuarioNoValido nombreUsuarioNoValido = new NombreUsuarioNoValido(nombreUsuario);

        // Si la excepción detecta que se ha cumplido alguna condicion, por lo cual hay
        // sugerencias, lanzamos una excepcion
        if (!nombreUsuarioNoValido.getSugerencias().isEmpty()) {
            throw nombreUsuarioNoValido;
        }

        // Si la excepcion no ocurre entonces el usuario se registra
        System.out.println("Usuario " + nombreUsuario + " registrado con éxito.");
    }
}
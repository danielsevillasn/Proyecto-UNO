package Principal;

import Excepciones.ReiniciarJuego;
import MetodosSecundarios.Juego;

/**
 * Inicializa el juego UNO
 * 
 * @author DaniS y Libio
 */
public class UNO {
    public static void main(String[] args) throws InterruptedException, ReiniciarJuego {
        //Metodo estático de la clase Juego que inicia el flujo del juego
        Juego.iniciarJuego();
    }
}

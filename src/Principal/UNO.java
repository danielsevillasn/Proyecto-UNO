package Principal;

import Excepciones.ReiniciarJuego;
import Excepciones.SalirDelJuego;
import MetodosSecundarios.Juego;

/**
 * Inicializa el juego UNO, creando un objeto de la clase JuegoUno y llamando a
 * su método ejecutarSistemaCompleto()
 * 
 * @author DaniS y Libio
 */
public class UNO {
    public static void main(String[] args) throws InterruptedException, SalirDelJuego, ReiniciarJuego {
        //Metodo estático de la clase Juego que inicia el flujo del juego
        Juego.iniciarJuego();
    }
}

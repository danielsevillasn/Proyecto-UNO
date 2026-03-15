package Principal;

import MetodosSecundarios.Juego;

/**
 * Inicializa el juego UNO, creando un objeto de la clase JuegoUno y llamando a
 * su método ejecutarSistemaCompleto()
 * 
 * @author DaniS y Libio
 */
public class UNO {
    public static void main(String[] args) throws InterruptedException {
        Juego juego = new Juego();
        juego.ejecutarSistemaCompleto();
    }
}

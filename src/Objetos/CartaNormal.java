package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;

/**
 * Herencia de la clase carta
 * * @author DaniS y Libio
 */
public class CartaNormal extends Carta {

    public CartaNormal() {
        super();
    }

    public CartaNormal(int n, Color c) {
        super(n, c, Tipos.NORMAL);
    }
    //El método puedePonerseSobre se hereda automáticamente de Carta
}
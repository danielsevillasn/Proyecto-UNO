package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;

/**
 * Herencia de la clase carta que es parte del tipo normal y que tiene el
 * polimorfismo de puedePonerseSobre
 * 
 * @author DaniS y Libio
 */
public class CartaNormal extends Carta {

    public CartaNormal() {
        super();
    }

    public CartaNormal(int n, Color c) {
        super(n, c, Tipos.NORMAL);
    }

    /*
     * @Override
     * public void chuparCartas() {
     * }
     */

    /**
     * Método para mirar la carta sobre la mesa y ver si la carta seleccionada por
     * el jugador se puede sacar
     *
     * @param mesa variable que nos indica que carta está sobre la mesa
     */
    @Override
    public boolean puedePonerseSobre(Carta mesa) {
        boolean mismoColor;
        boolean mismoNumero;
        boolean sePuede = false;

        if (mesa == null) {
            sePuede = true;
        }

        if (this.getColor() == mesa.getColor()) {
            mismoColor = true;
        } else {
            mismoColor = false;
        }

        if (this.getNumero() == mesa.getNumero()) {
            mismoNumero = true;
        } else {
            mismoNumero = false;
        }

        if (mismoColor || mismoNumero) {
            sePuede = true;
        }

        if (!sePuede) {
            throw new CartaLanzadaNoValida(
                    "La carta lanzada no es valida, lanza una carta que sea del mismo color o numero");
        } else {
            return true;
        }
    }
}

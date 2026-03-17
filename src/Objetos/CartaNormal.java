package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;

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
    /**
     * Implementación genérica del movimiento de cartas
     * Se puede poner sobre la mesa si coincide color o número
     * Lanza la excepcion de CartaLanzadaNoValida en caso de que la carta que se quiera lanzar no se peda
     * @param 'ninguno'
     * @throws CartaLanzadaNoValida 
     */
    @Override
    public boolean puedePonerseSobre(Carta mesa) throws CartaLanzadaNoValida {
        boolean mismoColor = false;
        boolean mismoNumero = false;
        
        if (mesa == null) {
            return true;
        }

        if(color == mesa.getColor()){
            mismoColor = true;
        }

        if(numero == mesa.getNumero()){
            mismoNumero = true;
        }

        if (mismoColor || mismoNumero) {
            return true;
        } else {
            throw new CartaLanzadaNoValida(
                    "La carta lanzada no es valida, lanza una carta que sea del mismo color o numero");
        }
    }
}
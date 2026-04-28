package Objetos;

import Enumerados.Color;
import Enumerados.TiposEspeciales;
import MetodosSecundarios.Calculos;

/**
 * Clase tablero que gestiona el mazo de robo (chupona) y la pila de descarte.
 * * @author DaniS y Libio
 */
public class Tablero {

    // Atributos/////////////////////

    private final Carta[] chupona;
    private int topeChupona;
    private final Carta[] descarte;
    private int topeDescarte;

    // Metodos////////////////////////

    // Constructor por defecto
    public Tablero() {
        topeChupona = 0;
        topeDescarte = 0;
        chupona = new Carta[108];
        descarte = new Carta[108];
    }

    // Otros metodos
    /**
     * Inicializa el juego creando las cartas por color y número (duplicando
     * todos los números excepto el 0 por color) y baraja el mazo resultante
     * 
     * @param 'ninguno'
     */
    public void inicializarBaraja() {

        inicializarCartasNormales();

        inicializarCartasEspeciales();

        mezclarBaraja();
    }

    /**
     * Sirve para meter las cartas normales en el mazo
     * 
     * @param 'nada'
     */
    private void inicializarCartasNormales() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k <= 9; k++) {
                    if (!(k == 0 && i == 1)) {
                        chupona[topeChupona++] = new CartaNormal(k, Color.values()[j]);
                    }
                }
            }
        }
    }

    /**
     * Sirve para meter las cartas especiales que tienen colores en el mazo
     * 
     * @param 'nada'
     */
    private void inicializarCartasEspeciales() {
        // Bloqueo, Reversa y chupate dos duplicados por color
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    chupona[topeChupona++] = new CartaEspecial(TiposEspeciales.values()[j], Color.values()[k]);
                }
            }
        }

        // Cambio color y chupate cuatro deben aparecer 4 veces cada una
        for (int i = 0; i < 4; i++) {
            chupona[topeChupona++] = new CartaEspecial(TiposEspeciales.values()[3], Color.NEGRO);
            chupona[topeChupona++] = new CartaEspecial(TiposEspeciales.values()[4], Color.NEGRO);
        }
    }

    /**
     * Mezcla la baraja empleando numeros aleatorios
     * 
     * @param 'nada'
     */
    private void mezclarBaraja() {
        Carta temp;
        for (int i = 0; i < topeChupona; i++) {
            // Genera un índice aleatorio entre 0 e i
            int r = Calculos.aleatorio(0, i);
            temp = chupona[i];
            chupona[i] = chupona[r];
            chupona[r] = temp;
        }
    }

    /**
     * Extrae y devuelve la carta superior de la pila de robo (chupona).
     * 
     * @return El objeto Carta extraído o null si el mazo está vacío
     * @param 'ninguno'
     */
    public Carta tirarCarta() {
        if (topeChupona > 0) {
            return chupona[--topeChupona];
        } else {
            return null;
        }
    }

    /**
     * Coloca una carta específica sobre el montón de descarte e incrementa el tope.
     * 
     * @param c Objeto Carta que el jugador lanza a la mesa.
     */
    public void dejar(Carta c) {
        descarte[topeDescarte++] = c;
    }

    /**
     * Permite consultar cuál es el objeto carta que está actualmente en la cima del
     * descarte sin quitarla.
     * 
     * @return El objeto Carta que se encuentra visible en la mesa.
     * @param 'ninguno'
     */
    public Carta verCartaEnLaMesa() {
        return descarte[topeDescarte - 1];
    }

    // ToString
    @Override
    public String toString() {
        return "Numero de cartas en la baraja chupona = " + topeChupona + " cartas";
    }
}
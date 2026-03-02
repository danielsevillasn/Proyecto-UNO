package Objetos;

import Enumerados.Color;

/**
 * Clase tablero que imprime el tablero de juego
 * 
 * @author DaniS y Libio
 */
public class Tablero {
    // Atributos
    private final Carta[] chupona = new Carta[108];   // Mazo principal de donde los jugadores roban
    private int topeChupona = 0;                      // Índice para controlar la cantidad de cartas en la chupona (Pila de la que se cogen cartas)
    private final Carta[] descarte = new Carta[108];  // Mazo de descarte donde se juegan las cartas
    private int topeDescarte = 0;                      // Índice para controlar la última carta jugada en la mesa

    /**
     * Inicializa el juego creando las cartas por color y número,
     * y posteriormente baraja el mazo resultante.
     */
    public void inicializar() {
        Carta temp;
        // Generamos las cartas recorriendo los Enums de Color y los números del 0 al 9
        for (Color c : Color.values()) {
            for (int i = 0; i <= 9; i++) {
                // Se almacena el objeto hijo en un array de tipo padre.
                chupona[topeChupona++] = new CartaNormal(i, c);
            }
        }
        
        //Intercambia cada posición con otra aleatoria
        for (int i = 0; i < topeChupona; i++) {
            int r = (int) (Math.random() * topeChupona);
            temp = chupona[i];
            chupona[i] = chupona[r];
            chupona[r] = temp;
        }
    }

    /**
     * Roba la carta superior de la pila de cartas
     * @return El objeto Carta extraído o null si el mazo está vacío
     */
    public Carta tirarCarta() {
        if (topeChupona > 0) {
            // Decrementa el tope y devuelve la carta en esa posición
            return chupona[--topeChupona];
        } else {
            return null; // El mazo se ha agotado
        }
    }

    /**
     * Coloca una carta sobre el montón de descarte.
     * @param c Objeto Carta que el jugador lanza a la mesa.
     */
    public void dejar(Carta c) {
        descarte[topeDescarte++] = c;
    }

    /**
     * Permite consultar cuál es la carta que está actualmente en la cima del descarte.
     * @return El objeto Carta que se encuentra visible en la mesa.
     */
    public Carta verMesa() {
        // Devuelve la última carta añadida sin extraerla del array
        return descarte[topeDescarte - 1];
    }
}
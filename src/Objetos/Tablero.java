package Objetos;

/**
 * Clase tablero que imprime el tablero de juego
 * 
 * @author DaniS y Libio
 */
public class Tablero {
    // Atributos
    private Carta[] chupona = new Carta[108];   // Mazo principal de donde los jugadores roban
    private int topeC = 0;                      // Índice para controlar la cantidad de cartas en la chupona
    private Carta[] descarte = new Carta[108];  // Mazo de descarte donde se juegan las cartas
    private int topeD = 0;                      // Índice para controlar la última carta jugada en la mesa

    /**
     * Inicializa el juego creando las cartas por color y número,
     * y posteriormente baraja el mazo resultante.
     */
    public void inicializar() {
        // Generación de cartas recorriendo los Enums de Color y los números del 0 al 9
        for (Color c : Color.values()) {
            for (int n = 0; n <= 9; n++) {
                // Aplicación de Polimorfismo: CartaNormal hereda de Carta.
                // Se almacena el objeto hijo en un array de tipo padre.
                chupona[topeC++] = new CartaNormal(n, c);
            }
        }
        
        // Algoritmo de barajado: Intercambia cada posición con otra aleatoria
        for (int i = 0; i < topeC; i++) {
            int r = (int) (Math.random() * topeC);
            Carta temp = chupona[i];
            chupona[i] = chupona[r];
            chupona[r] = temp;
        }
    }

    /**
     * Extrae (roba) la carta superior del mazo de robo.
     * @return El objeto Carta extraído o null si el mazo está vacío.
     */
    public Carta tirarCarta() {
        if (topeC > 0) {
            // Decrementa el tope y devuelve la carta en esa posición
            return chupona[--topeC];
        } else {
            return null; // El mazo se ha agotado
        }
    }

    /**
     * Coloca una carta sobre el montón de descarte.
     * @param c Objeto Carta que el jugador lanza a la mesa.
     */
    public void dejar(Carta c) {
        descarte[topeD++] = c;
    }

    /**
     * Permite consultar cuál es la carta que está actualmente en la cima del descarte.
     * @return El objeto Carta que se encuentra visible en la mesa.
     */
    public Carta verMesa() {
        // Devuelve la última carta añadida sin extraerla del array
        return descarte[topeD - 1];
    }
}
package Objetos;

/**
 * Clase jugador que guarda los nombres y la baraja de cartas
 * 
 * @author DaniS y Libio
 */
public class Jugador {
    // Atributos/////////////////////
    private final String nombre;
    public Carta[] mano = new Carta[21]; // Hay un límite de 20 cartas
    private int numCartas = 0;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con un parametro
    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    // Getter
    public String getNombre() {
        return nombre;
    }

    public int getNumCartas() {
        return numCartas;
    }

    // Setter
    public void setNumCartas(int numCartas) {
        this.numCartas = numCartas;
    }

    // Otros metodos
    /**
     * Método para recibir una carta aleatoria en tu mano
     * 
     * @param carta objeto carta que es la carta a recibir
     */
    public void recibirCarta(Carta carta) {
        if (carta != null) {
            mano[numCartas++] = carta;
        }
    }

    /**
     * Método para jugar las cartas y disminuir el tamaño
     * de tu mano
     * 
     * @param n numero entero que representa el numero de la carta
     * @return carta objeto carta que es la carta buscada
     */
    public Carta jugarCarta(int n) {
        Carta carta = mano[n];
        for (int j = n; j < numCartas - 1; j++) {
            mano[j] = mano[j + 1];
        }
        mano[--numCartas] = null;
        return carta;
    }

    /**
     * Método que devuelve un booleano dependiendo de si el jugador tiene la baraja
     * llena o no
     * 
     * @return valor boleano que determina si la mano esta llena o no
     */
    public boolean tieneManoLlena() {
        if (numCartas >= mano.length) {
            System.out.println("El jugador tiene la mano llena");
            return true;
        }
        return false;
    }
}

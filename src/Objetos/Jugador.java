package Objetos;

/**
 * Clase jugador que guarda los nombres y la baraja de cartas
 * 
 * @author DaniS y Libio
 */
public class Jugador {
    private final String nombre;
    public Carta[] mano = new Carta[100]; //Hay un límite de 100 cartas
    protected int numCartas = 0;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void recibirCarta(Carta carta) {
        if (carta != null){
            mano[numCartas++] = carta;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumCartas() {
        return numCartas;
    }

    public Carta jugarCarta(int n) {
        Carta carta = mano[n];
        for (int j = n; j < numCartas - 1; j++){
            mano[j] = mano[j + 1];
        }
        mano[--numCartas] = null;
        return carta;
    }
}

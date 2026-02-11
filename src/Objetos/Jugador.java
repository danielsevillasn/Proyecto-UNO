package Objetos;

/**
 * Clase jugador que guarda los nombres y la baraja de cartas
 * 
 * @author DaniS y Libio
 */
public class Jugador {
    private String nombre;
    protected Carta[] mano = new Carta[100];
    protected int numCartas = 0;

    public Jugador(String n) {
        this.nombre = n;
    }

    public void recibirCarta(Carta c) {
        if (c != null)
            mano[numCartas++] = c;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumCartas() {
        return numCartas;
    }

    public Carta jugarCarta(int i) {
        Carta c = mano[i];
        for (int j = i; j < numCartas - 1; j++)
            mano[j] = mano[j + 1];
        mano[--numCartas] = null;
        return c;
    }
}

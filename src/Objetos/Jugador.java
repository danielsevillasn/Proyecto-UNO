package Objetos;

/**
 * Clase que representa al jugador y su mano de cartas.
 * 
 * @author DaniS y Libio
 */
public class Jugador {
    // Atributos/////////////////////
    private final String nombre;
    private Contenedor<Carta> mano;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con un parametro
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Contenedor<>();
    }

    // Getter
    public String getNombre() {
        return nombre;
    }

    public int getNumCartas() {
        return mano.size();
    }

    public Contenedor<Carta> getMano() {
        return mano;
    }

    // Otros metodos
    /**
     * Juega una carta de la mano
     * 
     * @param n Índice de la carta seleccionada
     * @return La carta extraída
     */
    public Carta jugarCarta(int n) {
        return mano.extraer(n);
    }

    /**
     * Recibe una carta y la guarda en el contenedor
     * 
     * @param carta Objeto carta a añadir
     */
    public void recibirCarta(Carta carta) {
        // Nota: Con Contenedor (ArrayList) ya no hay límite de 20,
        // pero mantenemos la estructura por tus apuntes.
        mano.añadir(carta);
    }

    /**
     * Método que devuelve un booleano dependiendo de si el jugador tiene la baraja
     * llena o no
     * 
     * @return valor boleano que determina si la mano esta llena o no
     */
    public boolean tieneManoLlena() {
        if (mano.size() >= 21) {
            System.out.println("El jugador tiene la mano llena");
            return true;
        }
        return false;
    }
}

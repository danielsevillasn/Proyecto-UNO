package Objetos;

import Excepciones.ContenedorLleno;
import Excepciones.ContenedorVacio;

/**
 * Clase que representa al jugador y su mano de cartas.
 * 
 * @author DaniS y Libio
 */
public class Jugador {
    // Atributos/////////////////////
    private final String nombre;
    private static int idIncrementado;
    private final int id;
    private Contenedor<Carta> mano;
    private final int limiteMano;
    private int cartasRobadasTotales;
    private int cartasJugadasTotales;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con un parametro
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Contenedor<>();
        limiteMano = 21;
        id = idIncrementado++;
    }

    // Getter
    public String getNombre() {
        return nombre;
    }

    public int getCartasJugadasTotales() {
        return cartasJugadasTotales;
    }

    public int getNumCartas() {
        return mano.size();
    }

    public int getId() {
        return id;
    }

    public Contenedor<Carta> getMano() {
        return mano;
    }

    public int getCartasRobadasTotales() {
        return cartasRobadasTotales;
    }

    // Otros metodos
    /**
     * Juega una carta de la mano
     * 
     * @param n Índice de la carta seleccionada
     * @return La carta extraída
     */
    public Carta jugarCarta(int n) {
        try {
            Carta cartaExtraida = mano.extraer(n);
            cartasJugadasTotales++;
            return cartaExtraida;
        } catch (ContenedorVacio e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Recibe una carta y la guarda en el contenedor
     * 
     * @param carta Objeto carta a añadir
     */
    public void recibirCarta(Carta carta) {
        try {
            mano.añadir(carta, limiteMano);
            cartasRobadasTotales++; // Registra cada carta que entra a la mano
        } catch (ContenedorLleno e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que devuelve un booleano dependiendo de si el jugador tiene la baraja
     * llena o no
     * 
     * @return valor boleano que determina si la mano esta llena o no
     */
    public boolean tieneManoLlena() {
        if (mano.size() >= limiteMano) {
            System.out.println("El jugador tiene la mano llena");
            return true;
        }
        return false;
    }
}

package Objetos;

import java.io.Serializable;

import Excepciones.ContenedorLleno;
import Excepciones.ContenedorVacio;

/**
 * Clase que representa al jugador y su mano de cartas e implementa la interfaz
 * serializable para permitirnos serializar instancias de este objeto.
 * 
 * @author DaniS y Libio
 */
public class Jugador implements Serializable {
    // Atributos/////////////////////
    private final String nombre;
    private static int idIncrementado;
    private final int id;
    private Contenedor<Carta> mano;
    private final int limiteMano;
    private int cartasRobadasTotales;
    private int cartasJugadasTotales;
    private boolean gritoUno;

    // Metodos////////////////////////

    // Constructor para instanciar objeto con un parametro
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Contenedor<>();
        limiteMano = 21;
        id = idIncrementado++;
        cartasRobadasTotales = 0;
        cartasJugadasTotales = 0;
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

    public boolean getGritoUno() {
        return gritoUno;
    }

    // Setter
    public void setGritoUno(boolean gritoUno) {
        this.gritoUno = gritoUno;
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
            if (mano.size() == 1) {
                gritoUno = false; // aún no ha gritado UNO
            } else if (mano.size() == 0) {
                gritoUno = true; // si se queda sin cartas, no importa
            }
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
            if (mano.size() == 1) {
                gritoUno = false;
            }
        } catch (ContenedorLleno e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que devuelve un booleano dependiendo de si el jugador tiene la baraja
     * llena o no
     * 
     * @param 'ninguno'
     * @return valor boleano que determina si la mano esta llena o no
     */
    public boolean tieneManoLlena() {
        if (mano.size() >= limiteMano) {
            System.out.println("El jugador tiene la mano llena");
            return true;
        }
        return false;
    }

    /**
     * El jugador grita UNO
     * 
     * @param 'ninguno'
     */
    public void gritarUno() {
        gritoUno = true;
        System.out.println("¡" + nombre + " ha gritado UNO!");
    }
}

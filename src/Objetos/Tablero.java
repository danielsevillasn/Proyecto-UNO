package Objetos;

import java.io.Serializable;

import Enumerados.Color;
import Enumerados.TiposEspeciales;
import Excepciones.ContenedorVacio;

/**
 * Clase tablero que gestiona el mazo de robo (chupona) y la pila de descarte.
 * e implementa la interfaz serializable para permitirnos serializar instancias
 * de este objeto.
 * 
 * @author DaniS y Libio
 */
public class Tablero implements Serializable {

    // Atributos/////////////////////

    private Contenedor<Carta> chupona;
    private Contenedor<Carta> descarte;

    // Metodos////////////////////////

    // Constructor por defecto
    public Tablero() {
        this.chupona = new Contenedor<>();
        this.descarte = new Contenedor<>();
        inicializarBaraja();
    }

    // Getter
    public Contenedor<Carta> getChupona() {
        return chupona;
    }

    public Contenedor<Carta> getDescarte() {
        return descarte;
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

        chupona.barajar();
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
                        chupona.añadir(new CartaNormal(k, Color.values()[j]));
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
                    chupona.añadir(new CartaEspecial(TiposEspeciales.values()[j], Color.values()[k]));
                }
            }
        }

        // Cambio color y chupate cuatro deben aparecer 4 veces cada una
        for (int i = 0; i < 4; i++) {
            chupona.añadir(new CartaEspecial(TiposEspeciales.values()[3], Color.NEGRO));
            chupona.añadir(new CartaEspecial(TiposEspeciales.values()[4], Color.NEGRO));
        }
    }

    /**
     * Saca la última carta del mazo de robo
     * 
     * @return El objeto Carta extraído o null si el mazo está vacío
     * @param 'ninguno'
     */
    public Carta tirarCarta() {
        try {
            return chupona.extraer(chupona.size() - 1);
        } catch (ContenedorVacio e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Coloca una carta en el montón de descarte
     * 
     * @param c Objeto Carta que el jugador lanza a la mesa.
     */
    public void dejar(Carta c) {
        descarte.añadir(c);
    }

    /**
     * Añade una carta en especifico a la baraja
     * 
     * @param carta carta que se quiere meter en la baraja chupona
     */
    public void meter(Carta carta) {
        chupona.añadir(carta);
    }

    /**
     * Mira la carta que está arriba en el descarte
     * 
     * @param 'ninguno'
     * @return El objeto Carta que se encuentra visible en la mesa.
     */
    public Carta verCartaEnLaMesa() {
        return descarte.obtener(descarte.size() - 1);
    }

    // ToString
    @Override
    public String toString() {
        return "Numero de cartas en la baraja chupona = " + chupona.size() + " cartas";
    }

}
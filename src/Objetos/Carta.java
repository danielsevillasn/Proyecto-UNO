package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Interfaces.Jugable;
import Excepciones.CartaLanzadaNoValida;

/**
 * Clase Carta con implementación de herencias, enum e interfaz
 * * @author DaniS y Libio
 */
public abstract class Carta implements Jugable {
    private final Color color;
    private final int numero;
    protected final Tipos tipo;

    public Carta() {
        color = Color.AMARILLO;
        numero = 0;
        tipo = Tipos.NORMAL;
    }

    public Carta(int n, Color c, Tipos tipo) {
        this.numero = n;
        this.color = c;
        this.tipo = tipo;
    }

    public Color getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }

    /**
     * Implementación genérica del movimiento de cartas
     * Se puede poner sobre la mesa si coincide color o número
     * Lanza la excepcion de CartaLanzadaNoValida en caso de que la carta que se quiera lanzar no se peda
     * @param 'ninguno'
     */
    @Override
    public boolean puedePonerseSobre(Carta mesa) {
        if (mesa == null) {
            return true;
        }

        boolean mismoColor = (this.color == mesa.getColor());
        boolean mismoNumero = (this.numero == mesa.getNumero());

        if (mismoColor || mismoNumero) {
            return true;
        } else {
            throw new CartaLanzadaNoValida(
                    "La carta lanzada no es valida, lanza una carta que sea del mismo color o numero");
        }
    }

    /**
     * @toString que colorea la carta y la muestra por pantalla en el tablero
     */
    @Override
    public String toString() {
        String c = switch (color) {
            case ROJO -> "\u001B[31m";
            case AZUL -> "\u001B[34m";
            case VERDE -> "\u001B[32m";
            case AMARILLO -> "\u001B[33m";
        };
        return c + "[" + color + " " + numero + "]" + "\u001B[0m";
    }
}
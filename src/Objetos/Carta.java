package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Interfaces.Jugable;

/**
 * Clase Carta con implementación de herencias, enum e interfaz
 * 
 * @author DaniS y Libio
 */
public abstract class Carta implements Jugable {
    private final Color color;
    private final int numero;
    protected final Tipos tipo;
    
    //El final en ambos sirve para que cuando sea signe un valor este no se cambien en ningún momento de la partida

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

    // public abstract void chuparCartas();

    //toString para mostrar los colores de cada carta por pantalla
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

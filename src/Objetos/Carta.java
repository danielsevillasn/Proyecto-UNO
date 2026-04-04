package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Interfaces.Jugable;

/**
 * Clase Carta con implementación de herencias, enum e interfaz
 * * @author DaniS y Libio
 */
public abstract class Carta implements Jugable {
    // Atributos/////////////////////
    protected final Color color;
    protected final int numero;
    protected final Tipos tipo;

    // Metodos////////////////////////

    // Constructor por defecto
    protected Carta() {
        color = Color.AMARILLO;
        numero = 0;
        tipo = Tipos.NORMAL;
    }

    // Constructor para instanciar objeto con dos parametros
    protected Carta(int n, Color c, Tipos tipo) {
        this.numero = n;
        this.color = c;
        this.tipo = tipo;
    }

    // Getter
    public Color getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }
    
    // toString
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
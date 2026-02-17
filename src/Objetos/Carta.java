package Objetos;

/**
 * Clase Carta con implementacion de herencias, enum e interfaz
 * 
 * @author DaniS y Libio
 */
public abstract class Carta implements Jugable{
    private final Color color; 
    private final int numero;
    
    //El final en ambos sirve para que cuando sea signe un valor este no se cambien en ningun momento de la partida

    public Carta() {
        color = Color.AMARILLO;
        numero = 0;
    }

    public Carta(int n, Color c) {
        this.numero = n;
        this.color = c;
    }

    public Color getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }

    // public abstract void chuparCartas();

    //toString para mostrar los colores de caada carta por pantalla
    @Override
    public String toString() {
        String c = switch (color) {
            case ROJO -> "\u001B[31m";
            case AZUL -> "\u001B[34m";
            case VERDE -> "\u001B[32m";
            case AMARILLO -> "\u001B[33m";
            default -> "\u001B[0m";
        };
        return c + "[" + color + " " + numero + "]" + "\u001B[0m";
    }
}

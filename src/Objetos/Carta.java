package Objetos;

public abstract class Carta {
    private final String color;
    private final int numero;

    public Carta(int n, String c) {
        this.numero = n;
        this.color = c;
    }

    public String getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }

    //public abstract void chuparCartas();

    @Override
    public String toString() {
        String c = switch (color) {
            case "Rojo" -> "\u001B[31m";
            case "Azul" -> "\u001B[34m";
            case "Verde" -> "\u001B[32m";
            case "Amarillo" -> "\u001B[33m";
            default -> "\u001B[0m";
        };
        return c + "[" + color + " " + numero + "]" + "\u001B[0m";
    }
}

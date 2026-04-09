package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;
import MetodosSecundarios.Calculos;

/**
 * Herencia de la clase carta
 * * @author DaniS y Libio
 */
public class CartaNormal extends Carta {
    // Atributos/////////////////////
    private int numero;

    // Metodos////////////////////////

    // Constructor por defecto
    public CartaNormal() {
        super();
        numero = Calculos.aleatorio(0, 9);
    }

    // Constructor para instanciar objeto con dos parametros
    public CartaNormal(int n, Color c) {
        super(c, Tipos.NORMAL);
        numero = n;
    }

    // Getter
    public int getNumero() {
        return numero;
    }

    // Setter
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Otros metodos
    /**
     * Implementación genérica del movimiento de cartas
     * Se puede poner sobre la mesa si coincide color o número
     * Lanza la excepcion de CartaLanzadaNoValida en caso de que la carta que se
     * quiera lanzar no se peda
     * 
     * @param 'ninguno'
     * @throws CartaLanzadaNoValida
     */
    @Override
    public boolean puedePonerseSobre(Carta mesa) throws CartaLanzadaNoValida {
        boolean mismoColor = false;
        boolean mismoNumero = false;

        if (mesa == null) {
            return true;
        }

        if (color == mesa.getColor()) {
            mismoColor = true;
        }

        if (mesa instanceof CartaNormal) {
            mismoNumero = true;
        }

        if (mismoColor || mismoNumero) {
            return true;
        } else {
            throw new CartaLanzadaNoValida(
                    "La carta lanzada no es valida, lanza una carta que sea del mismo color o numero");
        }
    }

    // ToString
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
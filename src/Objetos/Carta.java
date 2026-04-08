package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;
import Interfaces.EfectosCarta;

/**
 * Clase Carta con implementación de herencias, enum e interfaz
 * * @author DaniS y Libio
 */
public abstract class Carta implements EfectosCarta {
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

    public Tipos getTipo() {
        return tipo;
    }
    
    /**
     * @param mesa La carta que está actualmente en el centro del tablero.
     * @return true si la carta actual cumple las reglas para ser jugada.
     */
    public abstract boolean puedePonerseSobre(Carta mesa) throws CartaLanzadaNoValida;
}
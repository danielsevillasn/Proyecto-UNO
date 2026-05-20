package Objetos;

import java.io.Serializable;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;
import MetodosSecundarios.Calculos;

/**
 * Clase Carta con implementación de herencias, enum e interfaces necesarias
 * para ordenarlas y serializarlas
 * 
 * @author DaniS y Libio
 */
public abstract class Carta implements Comparable<Carta>, Serializable {
    // Atributos/////////////////////
    protected Color color;
    protected final Tipos tipo;
    protected static final String RESETCOLOR = "\u001B[0m";

    // Metodos////////////////////////

    // Constructor por defecto
    protected Carta() {
        color = Color.values()[Calculos.aleatorio(0, 3)];
        tipo = Tipos.values()[Calculos.aleatorio(0, 1)];
    }

    // Constructor para instanciar objeto con dos parametros
    protected Carta(Color c, Tipos tipo) {
        this.color = c;
        this.tipo = tipo;
    }

    // Getter
    public Color getColor() {
        return color;
    }

    public Tipos getTipo() {
        return tipo;
    }

    // Setter
    public void setColor(Color color) {
        this.color = color;
    }

    // Otros metodos
    /**
     * @param mesa La carta que está actualmente en el centro del tablero.
     * @return true si la carta actual cumple las reglas para ser jugada.
     */
    public abstract boolean puedePonerseSobre(Carta mesa) throws CartaLanzadaNoValida;

    /**
     * Sirve para extraer el codigo Ansi del enumerado color de tal forma que cambia
     * de color lo imprimido
     * 
     * @param 'ninguno'
     * @return devuelve el String del codigo Ansi
     */
    public String getFormatoColor() {
        return color.getCodigoAnsi();
    }

    // ToString
    @Override
    public String toString() {
        return getFormatoColor() + "[" + color + " ";
    }

    // Hashcode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        return result;
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carta other = (Carta) obj;
        if (color != other.color)
            return false;
        if (tipo != other.tipo)
            return false;
        return true;
    }

    // Compare to
    // Prioridad en el orden basada en 1. Tipo -> 2. Color
    @Override
    public int compareTo(Carta c) {
        int compareTipo = tipo.compareTo(c.tipo);
        if (compareTipo != 0) {
            return compareTipo; // Si el tipo es distinto, eso define el orden
        }
        // Si el tipo es igual, compara por color
        return color.compareTo(c.color);
    }

}
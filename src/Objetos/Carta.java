package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;
import MetodosSecundarios.Calculos;

/**
 * Clase Carta con implementación de herencias, enum e interfaz
 * 
 * @author DaniS y Libio
 */
public abstract class Carta {
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

}
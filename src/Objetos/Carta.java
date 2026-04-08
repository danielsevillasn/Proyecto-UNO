package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Interfaces.AccionesCarta;

/**
 * Clase Carta con implementación de herencias, enum e interfaz
 * * @author DaniS y Libio
 */
public abstract class Carta implements AccionesCarta {
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
    
}
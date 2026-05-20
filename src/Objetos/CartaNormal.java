package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Excepciones.CartaLanzadaNoValida;
import MetodosSecundarios.Calculos;

/**
 * Clase CartaNormal que hereda de carta y se basa en el desarrollo de una
 * carta normal del UNO con sus respectivos métodos
 * 
 * @author DaniS y Libio
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

        if (color == mesa.getColor() || color == Color.NEGRO) {
            mismoColor = true;
        }

        if (mesa instanceof CartaNormal) {
            CartaNormal c = (CartaNormal) mesa;
            if (c.getNumero() == numero) {
                mismoNumero = true;
            }
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
        return super.toString() + numero + "]" + RESETCOLOR;
    }

    // Hash Code
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numero;
        return result;
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CartaNormal other = (CartaNormal) obj;
        return numero == other.numero;
    }

    // Compare to
    // Prioridad en el orden basada en 3. numero
    @Override
    public int compareTo(Carta c) {
        int compareCarta = super.compareTo(c); // Comienza con la comparación definida en la superclase
        if (compareCarta != 0) {
            return compareCarta; // Si tipo o color son distintos, decide el orden aquí.
        }
        if (!(c instanceof CartaNormal)) {
            return 1; // Si el objeto comparado no es una Carta normal, este objeto se pone delante
        }
        CartaNormal n = (CartaNormal) c;
        Integer n1 = (Integer) numero;
        Integer n2 = (Integer) n.getNumero();
        return n1.compareTo(n2); // Si lo anterior es igual, entonces compara por color
    }
}
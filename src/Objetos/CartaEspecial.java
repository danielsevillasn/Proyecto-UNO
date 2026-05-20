package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Enumerados.TiposEspeciales;
import Excepciones.CartaLanzadaNoValida;
import MetodosSecundarios.Calculos;

/**
 * Clase CartaEspecial que hereda de carta y se basa en el desarrollo de una
 * carta especial del UNO con sus respectivos métodos
 * 
 * @author DaniS y Libio
 */
public class CartaEspecial extends Carta {
    // Atributos/////////////////////
    private TiposEspeciales tiposEspeciales;

    // Metodos////////////////////////

    // Constructor por defecto
    public CartaEspecial() {
        tiposEspeciales = TiposEspeciales.values()[Calculos.aleatorio(0, 4)];
    }

    // Constructor para instanciar objeto con tres parametros
    public CartaEspecial(TiposEspeciales tiposEspeciales, Color c) {
        super(c, Tipos.ESPECIAL);
        this.tiposEspeciales = tiposEspeciales;
    }

    // Getter
    public TiposEspeciales getTiposEspeciales() {
        return tiposEspeciales;
    }

    // Setter
    public void setTiposEspeciales(TiposEspeciales tiposEspeciales) {
        this.tiposEspeciales = tiposEspeciales;
    }

    // Otros metodos
    /**
     * Implementación genérica del movimiento de cartas
     * Se puede poner sobre la mesa si coincide color o número
     * Lanza la excepcion de CartaLanzadaNoValida en caso de que la carta que se
     * quiera lanzar no se peda
     * 
     * @param mesa carta que esta en la mesa
     * @throws CartaLanzadaNoValida excepcion que salta cuando no es valida
     */
    @Override
    public boolean puedePonerseSobre(Carta mesa) throws CartaLanzadaNoValida {
        boolean mismoColor = false;
        boolean mismoTipo = false;

        if (mesa == null) {
            return true;
        }

        if (color == mesa.getColor() || color == Color.NEGRO) {
            mismoColor = true;
        }

        if (mesa instanceof CartaEspecial) {
            CartaEspecial c = (CartaEspecial) mesa;
            if (c.getTiposEspeciales() == tiposEspeciales) {
                mismoTipo = true;
            }
        }

        if (mismoColor || mismoTipo) {
            return true;
        } else {
            throw new CartaLanzadaNoValida(
                    "La carta lanzada no es valida, lanza una carta que sea del mismo color o negra");
        }
    }

    // toString
    @Override
    public String toString() {
        String simbolo = "";
        switch (tiposEspeciales) {
            case BLOQUEO:
                simbolo = "BLOQUEO";
                break;
            case REVERSA:
                simbolo = "REVERSA";
                break;
            case CHUPATE2:
                simbolo = "+2";
                break;
            case CHUPATE4:
                simbolo = "+4";
                break;
            case CAMBIOCOLOR:
                simbolo = "CAMBIOCOLOR";
                break;
        }

        return super.toString() + simbolo + "]" + RESETCOLOR;
    }

    // Hash Code
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tiposEspeciales == null) ? 0 : tiposEspeciales.hashCode());
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
        CartaEspecial other = (CartaEspecial) obj;
        return tiposEspeciales == other.tiposEspeciales;
    }

    // Compare to
    // Prioridad en el orden basada en 3. TipoEspecial
    @Override
    public int compareTo(Carta c) {
        int compareCarta = super.compareTo(c); // Comienza con la comparación definida en la superclase
        if (compareCarta != 0) {
            return compareCarta; // Si tipo o color son distintos, decide el orden aquí.
        }
        if (!(c instanceof CartaEspecial)) {
            return 1; // Si el objeto comparado no es una Carta especial, este objeto se pone delante
        }
        CartaEspecial other = (CartaEspecial) c;
        return tiposEspeciales.compareTo(other.tiposEspeciales); // Si lo anterior es igual, entonces compara por
                                                                 // su tipo especial
    }
}

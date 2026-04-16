package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Enumerados.TiposEspeciales;
import Excepciones.CartaLanzadaNoValida;
import MetodosSecundarios.Calculos;

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
     * @param 'ninguno'
     * @throws CartaLanzadaNoValida
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

}

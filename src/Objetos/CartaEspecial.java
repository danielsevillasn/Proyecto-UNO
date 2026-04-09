package Objetos;

import Enumerados.Color;
import Enumerados.Tipos;
import Enumerados.TiposEspeciales;
import Interfaces.EfectosCarta;
import MetodosSecundarios.Calculos;

public class CartaEspecial extends Carta implements EfectosCarta{
    // Atributos/////////////////////
    private TiposEspeciales tiposEspeciales;
    private String simbolo;

    public CartaEspecial() {
        tiposEspeciales = TiposEspeciales.values()[Calculos.aleatorio(0, 4)];
    }

    public CartaEspecial(Color c, Tipos tipo, TiposEspeciales tiposEspeciales) {
        super(c, tipo);
        this.tiposEspeciales = tiposEspeciales;
    }


    
}

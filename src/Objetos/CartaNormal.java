package Objetos;

/**
 * Herencia de la clase carta que es parte del tipo normal y que tiene el polimorfismo de puedePonerseSobre 
 * 
 * @author DaniS y Libio
 */
public class CartaNormal extends Carta {

    public CartaNormal(int n, Color c) {
        super(n, c);
    }

    /*
     * @Override
     * public void chuparCartas() {
     * }
     */

    /**
     * Método para mirar la carta sobre la mesa y ver si la carta seleccionada por el jugador se puede sacar
     * 
     * @param mesa variable que nos indica que carta esta sobre la mesa
     * @return sePuede variable booleana que nos dice si se puede o no
     */
    @Override
    public boolean puedePonerseSobre(Carta mesa) {
        boolean mismoColor;
        boolean mismoNumero;
        boolean sePuede = false;

        if (mesa == null) {
            sePuede = true;
        }

        if (this.getColor() == mesa.getColor()) {
            mismoColor = true;
        } else {
            mismoColor = false;
        }

        if (this.getNumero() == mesa.getNumero()) {
            mismoNumero = true;
        } else {
            mismoNumero = false;
        }

        if(mismoColor || mismoNumero){
            sePuede = true;
        }

        return sePuede;
    }
}

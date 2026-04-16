package MetodosSecundarios;

/**
 * Lógica matemática y algoritmos.
 * Contiene metodos como:
 * aleatorio
 * 
 * @author Dani S
 */
public class Calculos {
    /**
     * Genera un numero aleatorio a partir de un maximo y un minimo
     * 
     * @param min numero entero minimo de la generacion aleatoria
     * @param max numero entero maximo de la generacion aleatoria
     * @return valor entero aleatorio
     */
    public static int aleatorio(int min, int max) {
        return ((int) (Math.random() * (max + 1 - min) + min));
    }
}

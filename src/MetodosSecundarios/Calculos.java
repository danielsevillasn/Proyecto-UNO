package MetodosSecundarios;

/**
 * Lógica matemática y algoritmos.
 * Contiene metodos como:
 * aleatorio y esPrimo
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

    /**
     * Comprueba si un número entero es primo.
     * 
     * @param numero El número a comprobar
     * @return true si es primo, false si no lo es
     */
    public static boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}

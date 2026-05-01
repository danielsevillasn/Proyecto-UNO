package Objetos;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase genérica que sirve como base para cualquier colección de cartas.
 * Implementa la lógica de almacenamiento dinámico y barajado.
 * * @author Proyecto UNO
 * 
 * @param <T> El tipo de carta que almacenará (debe extender de Carta)
 */
public class Contenedor<T extends Carta> {
    // Lista dinámica que cumple con los apuntes sobre Colecciones
    protected ArrayList<T> lista;

    /**
     * Constructor por defecto que inicializa la lista
     */
    public Contenedor() {
        this.lista = new ArrayList<>();
    }

    /**
     * Añade una carta a la colección
     * 
     * @param carta Objeto de tipo T a añadir
     */
    public void añadir(T carta) {
        if (carta != null) {
            lista.add(carta);
        }
    }

    /**
     * Extrae y elimina la carta de una posición específica
     * 
     * @param indice Posición en la lista
     * @return La carta extraída o null si el índice no es válido
     */
    public T extraer(int indice) {
        if (indice >= 0 && indice < lista.size()) {
            return lista.remove(indice);
        }
        return null;
    }

    /**
     * Obtiene una carta sin eliminarla (para validaciones)
     * 
     * @param indice Posición en la lista
     * @return La carta en esa posición
     */
    public T obtener(int indice) {
        if (indice >= 0 && indice < lista.size()) {
            return lista.get(indice);
        }
        return null;
    }

    /**
     * Utiliza el método de utilidad de Collections para mezclar los elementos
     */
    public void barajar() {
        Collections.shuffle(lista);
    }

    /**
     * Devuelve la cantidad de elementos actuales
     * 
     * @return Tamaño de la lista
     */
    public int size() {
        return lista.size();
    }
}
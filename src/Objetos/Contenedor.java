package Objetos;

import java.util.ArrayList;
import java.util.Collections;

import Excepciones.ContenedorLleno;
import Excepciones.ContenedorVacio;
import Interfaces.Barajable;

/**
 * Clase genérica que sirve como base para cualquier colección de cartas.
 * Implementa la lógica de almacenamiento dinámico y barajado.
 * * @author Proyecto UNO
 * 
 * @param <T> El tipo de carta que almacenará (debe extender de Carta)
 */
public class Contenedor<T extends Carta> implements Barajable {
    // Atributos/////////////////////
    protected ArrayList<T> lista;

    // Metodos////////////////////////

    // Constructor por defecto
    public Contenedor() {
        this.lista = new ArrayList<>();
    }

    // Getter
    public ArrayList<T> getLista() {
        return lista;
    }

    // Otros metodos
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
     * Añade una carta a la colección dependiendo de un limite
     * 
     * @param carta Objeto de tipo T a añadir
     * @throws ContenedorLleno Excepcion que salta cuando el contedor esta lleno
     */
    public void añadir(T carta, int limite) throws ContenedorLleno {
        if (carta != null && (limite == -1 || lista.size() < limite)) {
            lista.add(carta);
        } else if (limite > -1) {
            throw new ContenedorLleno("Contenedor lleno");
        }
    }

    /**
     * Extrae y elimina la carta de una posición específica
     * 
     * @param indice Posición en la lista
     * @return La carta extraída o null si el índice no es válido
     */
    public T extraer(int indice) throws ContenedorVacio {
        if (lista.isEmpty()) {
            throw new ContenedorVacio("El contenedor está vacío, no se puede extraer.");
        }
        if (indice < 0 || indice >= lista.size()) {
            throw new ContenedorVacio("Índice fuera de rango: " + indice);
        }
        return lista.remove(indice);
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
     * 
     * @param 'nada'
     */
    public void barajar() {
        Collections.shuffle(lista);
    }

    /**
     * Devuelve la cantidad de elementos actuales
     * 
     * @param 'nada'
     * @return Tamaño de la lista
     */
    public int size() {
        return lista.size();
    }

}
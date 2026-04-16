package Excepciones;

import java.util.ArrayList;

public class NombreUsuarioNoValido extends Exception {
    // Atributos/////////////////////
    private final String nombreRechazado;
    private final ArrayList<String> sugerencias;

    // Metodos////////////////////////

    // Constructor para instanciar una excepcion con un nombre
    public NombreUsuarioNoValido(String nombre) {
        super("El nombre de usuario '" + nombre + "' no cumple con las políticas de seguridad.");
        this.nombreRechazado = nombre;
        this.sugerencias = new ArrayList<String>();

        configurarSugerencias();
    }

    // Getter
    public ArrayList<String> getSugerencias() {
        return sugerencias;
    }

    public String getNombreRechazado() {
        return nombreRechazado;
    }

    // Otros metodos
    /**
     * Método que agrega sugerencias cuando se cumple alguna condicion específica 
     */
    private void configurarSugerencias() {
        if (nombreRechazado.length() < 3) {
            sugerencias.add("Debe tener al menos 3 caracteres.");
        }
        if (nombreRechazado.contains(" ")) {
            sugerencias.add("No puede contener espacios en blanco.");
        }
    }

}
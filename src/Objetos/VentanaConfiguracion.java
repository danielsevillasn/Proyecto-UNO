package Objetos;

import Excepciones.NombreUsuarioNoValido;
import MetodosSecundarios.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Objeto que permite crear una pantalla en la que se pueda configurar la
 * cantidad de los jugadores y los nombres de los jugadores
 * 
 * @author DaniS y Libio
 */
public class VentanaConfiguracion extends JFrame implements ActionListener {
    // Atributos/////////////////////
    private JComboBox<String> cajaCantidadJugadores;
    private JButton botonGuardar;

    private JLabel[] etiquetasNombres = new JLabel[10];
    private JTextField[] textoNombres = new JTextField[10];

    // Metodos////////////////////////

    // Constructor por defecto
    public VentanaConfiguracion() {
        this.setTitle("Configuración de Jugadores - UNO");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        zonaSuperior();

        zonaCentral();

        zonaInferior();
    }

    // Getter

    public JComboBox<String> getCajaCantidadJugadores() {
        return cajaCantidadJugadores;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    public JLabel[] getEtiquetasNombres() {
        return etiquetasNombres;
    }

    public JTextField[] getTextoNombres() {
        return textoNombres;
    }

    // Setter

    public void setCajaCantidadJugadores(JComboBox<String> comboCantidad) {
        this.cajaCantidadJugadores = comboCantidad;
    }

    public void setBotonGuardar(JButton botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public void setEtiquetasNombres(JLabel[] etiquetasNombres) {
        this.etiquetasNombres = etiquetasNombres;
    }

    public void setTextoNombres(JTextField[] textoNombres) {
        this.textoNombres = textoNombres;
    }

    // Otros metodos
    /**
     * Método que configura el título y el selector de cantidad de jugadores
     * 
     * @param 'nada'
     */
    private void zonaSuperior() {
        JLabel etiquetaNumeroJugadores;
        JPanel panelSuperior = new JPanel(new FlowLayout());
        String[] opciones = { "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        etiquetaNumeroJugadores = new JLabel("Número de jugadores: ");
        cajaCantidadJugadores = new JComboBox<>(opciones);
        cajaCantidadJugadores.addActionListener(this);

        panelSuperior.add(etiquetaNumeroJugadores);
        panelSuperior.add(cajaCantidadJugadores);
        this.add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Formulario dinámico dentro de un ScrollPane
     * 
     * @param 'nada'
     */
    private void zonaCentral() {
        JPanel campoNombres;

        campoNombres = new JPanel(new GridLayout(10, 2, 5, 10));

        for (int i = 0; i < 10; i++) {
            etiquetasNombres[i] = new JLabel("Nombre Jugador " + (i + 1) + ":");
            textoNombres[i] = new JTextField("Jugador" + (i + 1), 12);

            if (i >= 2) {
                etiquetasNombres[i].setEnabled(false);
                textoNombres[i].setEnabled(false);
            }

            campoNombres.add(etiquetasNombres[i]);
            campoNombres.add(textoNombres[i]);
        }

        // Metemos el panel de campos en un JScrollPane por si el usuario elige muchos
        // jugadores
        JScrollPane scrollPane = new JScrollPane(campoNombres);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Botón para guardar y regresar
     * 
     * @param 'nada'
     */
    private void zonaInferior() {
        botonGuardar = new JButton("GUARDAR Y REGRESAR AL MENÚ");
        botonGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        botonGuardar.setBackground(new Color(50, 150, 250));
        botonGuardar.setForeground(Color.WHITE);
        botonGuardar.addActionListener(this);
        this.add(botonGuardar, BorderLayout.SOUTH);
    }

    // ActionPerfomed
    // Método que se activa cuando se emplea el action listener
    // En este caso se activa con el combo cantidad y el boton guardar
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cajaCantidadJugadores) {
            int cantidadSeleccionada = Integer.parseInt((String) cajaCantidadJugadores.getSelectedItem());
            for (int i = 0; i < 10; i++) {
                boolean activar = (i < cantidadSeleccionada);
                etiquetasNombres[i].setEnabled(activar);
                textoNombres[i].setEnabled(activar);
            }
        }

        if (e.getSource() == botonGuardar) {
            comprobaciónConfiguracion();
        }
    }

    /**
     * Método que revisa todos los nombres introducidos y mira si son correctos de
     * acuerdo a las sugerencias implementadas en la excepción propia, si no son
     * correctos imprime por pantalla las sugerencias
     * 
     * @param 'nada'
     */
    private void comprobaciónConfiguracion() {
        int cantidadJugadores = Integer.parseInt((String) cajaCantidadJugadores.getSelectedItem());
        ArrayList<String> nombresValidados = new ArrayList<>();
        String mensajeError = "";
        try {
            comprobaciónNombres(cantidadJugadores, nombresValidados);

            Juego.nombresCargados = nombresValidados;
            Juego.cantidadActualJugadores = cantidadJugadores;

            JOptionPane.showMessageDialog(this,
                    "Los " + cantidadJugadores + " nombres de los jugadores son correctos",
                    "Configuración Guardada", JOptionPane.INFORMATION_MESSAGE);

            // Cerramos la ventana
            this.dispose();

        } catch (NombreUsuarioNoValido e) {
            mensajeError = e.getMessage() + "\n\nSugerencias de seguridad:\n";

            for (String sugerencia : e.getSugerencias()) {
                mensajeError = mensajeError + "- " + sugerencia + "\n";
            }

            JOptionPane.showMessageDialog(this, mensajeError,
                    "Nombre Inválido detectado", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Recorre todos los nombres de los campos con los nombres para ver si son
     * correctos o
     * 
     * @param cantidadJugadores
     * @param nombresValidados
     * @throws NombreUsuarioNoValido
     */
    private void comprobaciónNombres(int cantidadJugadores, ArrayList<String> nombresValidados)
            throws NombreUsuarioNoValido {
        String nombre;
        for (int i = 0; i < cantidadJugadores; i++) {
            nombre = textoNombres[i].getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "El campo del Jugador " + (i + 1) + " no puede estar vacío.",
                        "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            } else {

                validacionNombre(nombre);

                nombresValidados.add(nombre);
            }

        }
    }

    /**
     * Método que valida el nombre de un usuario de tal forma que siga las
     * condiciones impuestas en la excepcion
     * 
     * @param nombreUsuario variable tipo String que representa el nombre del
     *                      usuario
     * @throws NombreUsuarioNoValido excepcion que recoge todas las condiciones para
     *                               luego mostrarlas en caso de que no se cumplan
     */
    public static void validacionNombre(String nombreUsuario) throws NombreUsuarioNoValido {
        // Inicalizamos la excepcion pasando por parametro el nombre
        NombreUsuarioNoValido nombreUsuarioNoValido = new NombreUsuarioNoValido(nombreUsuario);

        // Si la excepción detecta que se ha cumplido alguna condicion, por lo cual hay
        // sugerencias, lanzamos una excepcion
        if (!nombreUsuarioNoValido.getSugerencias().isEmpty()) {
            throw nombreUsuarioNoValido;
        }

    }
}
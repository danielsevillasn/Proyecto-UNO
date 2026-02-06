package Objetos;
import MetodosAux.pantallas;
import java.util.Scanner;

public class Juego {

    static Scanner s = new Scanner(System.in);
    private String[] nombresCargados = {"Jugador 1", "Jugador 2"}; // Nombres por defecto
    private int cantidadActual = 2;

    public void ejecutarSistemaCompleto() throws InterruptedException {
        pantallas.PantallaIncio(); // Usando tu pantalla de inicio
        while (true) {
            String op = pantallas.PantallaMenu();
            if (op.equals("1")) {
                pantallas.ModoDeJuego = pantallas.PantallaModosDeJuego();
                pantallas.ModoDejuego = pantallas.ModoDeJuego;
            } else if (op.equals("2")) {
                configurarJugadores();
            } else if (op.equals("3")) {
                pantallas.PantallaReglas();
            } else if (op.equals("4")) {
                partida();
            } else if (op.equals("5")) {
                break;
            }
        }
    }

    public void configurarJugadores() {
        int cantidadActual = pantallas.PantallaJugadores();
        System.out.print("¿Cuántos jugadores (2-4)? ");
        cantidadActual = s.nextInt(); s.nextLine();
        nombresCargados = new String[cantidadActual];
        for (int i = 0; i < cantidadActual; i++) {
            System.out.print("Nombre Jugador " + (i + 1) + ": ");
            nombresCargados[i] = s.nextLine();
        }
        pantallas.Jugadores = String.valueOf(cantidadActual);
    }


    private void partida() throws InterruptedException {
        Tablero t = new Tablero();
        t.inicializar();
        Turno controlador = new Turno();
        Jugador[] lista = new Jugador[cantidadActual];

        for (int i = 0; i < cantidadActual; i++) {
            lista[i] = new Jugador(nombresCargados[i]);
            for (int c = 0; c < 7; c++)
                lista[i].recibirCarta(t.pull());
        }
        t.dejar(t.pull());

        boolean fin = false;
        while (!fin) {
            Jugador j = lista[controlador.actual];
            System.out.println("\n--- TURNO DE: " + j.getNombre() + " ---");
            System.out.println("Mesa: " + t.verMesa());

            for (int i = 0; i < j.getNumCartas(); i++)
                System.out.print(i + ":" + j.mano[i] + " ");
            System.out.println(j.getNumCartas() + ":[ROBAR]");

            System.out.print("Acción: ");
            int sel = s.nextInt();
            s.nextLine();

            if (sel == j.getNumCartas()) {
                j.recibirCarta(t.pull());
            } else {
                Carta c = j.mano[sel];
                if (c.getColor().equals(t.verMesa().getColor()) || c.getNumero() == t.verMesa().getNumero()) {
                    t.dejar(j.jugarCarta(sel));
                    if (j.getNumCartas() == 0) {
                        fin = true;
                        pantallas.NombreJugador = j.getNombre();
                    }
                } else {
                    System.out.println("¡Movimiento no válido!");
                }
            }
            if (!fin)
                controlador.siguiente(cantidadActual);
        }
        pantallas.PantallaFinal();
    }
}

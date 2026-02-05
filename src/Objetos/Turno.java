package Objetos;

public class Turno {
    int actual = 0;

    public void siguiente(int total) {
        actual = (actual + 1) % total;
    }
}
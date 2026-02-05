package Objetos;

public class Tablero {
    private Carta[] chupona = new Carta[108];
    private int topeC = 0;
    private Carta[] descarte = new Carta[108];
    private int topeD = 0;

    public void inicializar() {
        String[] colores = { "Rojo", "Azul", "Verde", "Amarillo" };
        for (String c : colores) {
            for (int n = 0; n <= 9; n++)
                chupona[topeC++] = new CartaNormal(n, c);
        }
        for (int i = 0; i < topeC; i++) {
            int r = (int) (Math.random() * topeC);
            Carta temp = chupona[i];
            chupona[i] = chupona[r];
            chupona[r] = temp;
        }
    }

    public Carta pull() {
        if (topeC > 0) {
            return chupona[--topeC];
        } else {
            return null;
        }
    }

    public void dejar(Carta c) {
        descarte[topeD++] = c;
    }

    public Carta verMesa() {
        return descarte[topeD - 1];
    }
}

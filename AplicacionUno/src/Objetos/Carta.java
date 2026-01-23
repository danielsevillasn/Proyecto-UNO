package Objetos;

public class Carta {
    //Atributos///////////////
    private int numero;
    private String tipo;
    private String color;

    //Metodos/////////////////

    //Constructor por defecto
    public Carta(){
        this.tipo = "";
        this.color = "";
        this.numero = 0;
    }
    //Constructor para dar inicio a la istancia
    
    //Setter
    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

        public void setColor(String color){
        this.color = color;
    }

    //Getter
    public int getNumero(){
        return this.numero;
    }

    public String getTipo(){
        return this.tipo;
    }

    public String getColor(){
        return this.color;
    }

    //Metodo toString
    @Override
    public String toString(){
        return ("La carta "+this.numero+"del tipo "+this.tipo+"y de color "+this.color);
    }
}

package Atividade.Repeticoes.Pesos;

import java.time.LocalDate;

import Atividade.Repeticoes.Repeticoes;
import Utilizador.Utilizador;

public abstract class Pesos extends Repeticoes {
    
    private double peso;

    
    // ------------------- Construtores ------------------- //

    public Pesos() {
        super();
        this.peso = 0;
    }

    public Pesos(String codigo, String descricao, LocalDate data, int duracao, Utilizador user, int repeticoes, double peso) {
        super(codigo, descricao, data, duracao, user, repeticoes);
        this.peso = peso;
    }

    public Pesos(Pesos outro) {
        super(outro);
        this.peso = outro.getPeso();
    }


    // ------------------- Métodos ------------------- //


    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

     
    public String toString() {
        return "Pesos{" +
                super.toString() +
                "peso=" + this.peso +
                '}';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Pesos pesos = (Pesos) o;
        return super.equals(pesos)
                && Double.compare(this.peso,pesos.getPeso()) == 0;
    }

    public abstract Pesos clone();
    public abstract double calorias();
    public abstract int bpm();
}

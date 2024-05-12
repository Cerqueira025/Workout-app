package Atividade.Repeticoes;

import java.time.LocalDateTime;

import Atividade.Atividade;
import Utilizador.Utilizador;

public abstract class Repeticoes extends Atividade {
    
    private int repeticoes;


    // ------------------- Construtores ------------------- //

    public Repeticoes() {
        super();
        this.repeticoes = 0;
    }

    public Repeticoes(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador, int repeticoes) {
        super(codigo, descricao, data, duracao, series, utilizador);
        this.repeticoes = repeticoes;
    }

    public Repeticoes(Repeticoes outro) {
        super(outro);
        this.repeticoes = outro.getRepeticoes();
    }

    // ------------------- Construtores ------------------- //


    public int getRepeticoes() {
        return this.repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

     
    public String toString() {
        return  super.toString() +
                ", repetições = '" + this.repeticoes + '\'';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Repeticoes repeticoes = (Repeticoes) o;
        return super.equals(repeticoes)
                && this.repeticoes == repeticoes.getRepeticoes();
    }


    public abstract Repeticoes clone();
    public abstract double calorias();
    public abstract int bpm();

}

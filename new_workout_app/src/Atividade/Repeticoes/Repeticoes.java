package Atividade.Repeticoes;

import Atividade.Atividade;
import Utilizador.Utilizador;

public abstract class Repeticoes extends Atividade {
    
    private int repeticoes;


    // ------------------- Construtores ------------------- //


    public Repeticoes() {
        super();
        this.repeticoes = 0;
    }

    public Repeticoes(String codigo, String descricao, int duracao, Utilizador user, int repeticoes) {
        super(codigo, descricao, duracao, user);
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
        return "Repetições{" +
                super.toString()+
                "repetições=" + this.repeticoes +
                '}';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Repeticoes repeticoes = (Repeticoes) o;
        return super.equals(repeticoes)
                && this.repeticoes == repeticoes.getRepeticoes();
    }


    public abstract double calorias();
    public abstract Atividade clone();

}

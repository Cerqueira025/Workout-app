package Atividade.Repeticoes.Pesos;

import java.time.LocalDateTime;

import Atividade.Hard;
import Utilizador.Utilizador;

public class Supino extends Pesos implements Hard {
    
    private double inclinacao;

    // ------------------- Construtores ------------------- //
    
    public Supino() {
        super();
        this.inclinacao = 0;
    }

    public Supino(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador, int repeticoes, double peso, double inclinacao) {
        super(codigo, descricao, data, duracao, series, utilizador, repeticoes, peso);
        this.inclinacao = inclinacao;
    }

    public Supino(Supino outro) {
        super(outro);
        this.inclinacao = outro.getInclinacao();
    }


    // ------------------- Métodos getters e setters ------------------- //


    public double getInclinacao() {
        return this.inclinacao;
    }

    public void setInclinacao(double inclinacao) {
        this.inclinacao = inclinacao;
    }

     
    public String toString() {
        return "Supino - " +
                super.toString() +
                ", inclinação = '" + this.inclinacao + '\'';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Supino supino = (Supino) o;
        return super.equals(supino)
                && Double.compare(this.inclinacao,supino.getInclinacao()) == 0;
    }

     
    public Supino clone() {
        return new Supino(this);
    }
    
    public double calorias() {
        return this.getUtilizador().fatorMultiplicativo() * ((this.inclinacao/30 + 1)) * (this.getPeso()/20 + 1) * this.getDuracao() * ((this.bpm()/100) + 1) * this.getSeries() + this.getRepeticoes();
    }

    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 10 * this.getUtilizador().fatorMultiplicativo());
    }
}


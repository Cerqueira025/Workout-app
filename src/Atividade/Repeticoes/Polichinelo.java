package Atividade.Repeticoes;

import java.time.LocalDateTime;

import Utilizador.Utilizador;

public class Polichinelo extends Repeticoes {

    
    // ------------------- Construtores ------------------- //

    public Polichinelo() {
        super();
    }

    public Polichinelo(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador, int repeticoes) {
        super(codigo, descricao, data, duracao, series, utilizador, repeticoes);
    }

    public Polichinelo(Polichinelo outro) {
        super(outro);
    }


    // ------------------- Métodos ------------------- /

     
    public String toString() {
        return "Polichinelo - " +
                super.toString();
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Polichinelo polichinelo = (Polichinelo) o;
        return super.equals(polichinelo);
    }

    public Polichinelo clone() {
      return new Polichinelo(this);
    }
    
    public double calorias() { 
      return this.getUtilizador().fatorMultiplicativo() * (this.getDuracao()/2 + 1) * ((this.bpm()/100) + 1) * this.getSeries() + (this.getRepeticoes()*2);
    }
    
    public int bpm() {
      return (int) (this.getUtilizador().getBpmMedio() + 20 * this.getUtilizador().fatorMultiplicativo());
    }
}

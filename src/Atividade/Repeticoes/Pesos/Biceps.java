package Atividade.Repeticoes.Pesos;

import java.time.LocalDateTime;

import Utilizador.Utilizador;

public class Biceps extends Pesos {
    
    private boolean bilateral;

    // ------------------- Construtores ------------------- //
    
    public Biceps() {
        super();
        this.bilateral = false;
    }

    public Biceps(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador, int repeticoes, double peso, boolean bilateral) {
        super(codigo, descricao, data, duracao, series, utilizador, repeticoes, peso);
        this.bilateral = bilateral;
    }

    public Biceps(Biceps outro) {
        super(outro);
        this.bilateral = outro.getBilateral();
    }


    // ------------------- Métodos getters e setters ------------------- //


    public boolean getBilateral() {
        return this.bilateral;
    }

    public void setBilateral(boolean bilateral) {
        this.bilateral = bilateral;
    }

     
    public String toString() {
        return "Bíceps - " +
                super.toString() +
                ", bilateral = '" + this.bilateral + '\'';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Biceps biceps = (Biceps) o;
        return super.equals(biceps)
                && this.bilateral == biceps.getBilateral();
    }

     
    public Biceps clone() {
        return new Biceps(this);
    }
    
    public double calorias() {
        double mult = this.bilateral ? 1 : 1.2;
        return mult * this.getUtilizador().fatorMultiplicativo() * (this.getPeso()/20 + 1) * this.getDuracao() * ((this.bpm()/100) + 1) * this.getSeries() + this.getRepeticoes();
    }

    public int bpm() {
      return (int) (this.getUtilizador().getBpmMedio() + 9 * this.getUtilizador().fatorMultiplicativo());
    }
}


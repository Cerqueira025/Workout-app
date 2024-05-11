package Atividade.Distancia;

import java.time.LocalDateTime;

import Utilizador.Utilizador;

public class Jogging extends Distancia {

    // ----------------- Construtores ---------------- //
    public Jogging() {
        super();
    }

    public Jogging(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador, double dist) {
        super(codigo, descricao, data, duracao, series, utilizador, dist);
    }

    public Jogging(Jogging outro) {
        super(outro);
    }

    // ----------------- Métodos getters e setters ----------------- //
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Jogging jogging = (Jogging) o;
        return super.equals(jogging);
    }

     
    public String toString() {
        return "Jogging - " +
            super.toString();
    }

     
    public Jogging clone() {
        return new Jogging(this);
    }

    public double calorias() {
        return this.getUtilizador().fatorMultiplicativo() * ((this.getVelocidade()/5 + 1)) * (this.getDuracao()/2 + 1) * ((this.bpm()/100 + 1)) * this.getSeries();
    }

    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 25 * this.getUtilizador().fatorMultiplicativo());
    }
}

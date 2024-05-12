package Atividade.Distancia;

import java.time.LocalDateTime;

import Utilizador.Utilizador;

public class Sprint extends Distancia {

    // ----------------- Construtores ---------------- //
    public Sprint() {
        super();
    }

    public Sprint(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador, double dist) {
        super(codigo, descricao, data, duracao, series, utilizador, dist);
    }

    public Sprint(Sprint outro) {
        super(outro);
    }

    // ----------------- Métodos getters e setters ----------------- //
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return super.equals(sprint);
    }

     
    public String toString() {
        return "Sprint - " +
            super.toString();
    }

     
    public Sprint clone() {
        return new Sprint(this);
    }

    public double calorias() {
        return this.getUtilizador().fatorMultiplicativo() * ((this.getVelocidade()/2 + 1)) * this.getDuracao() * ((this.bpm()/100 + 1)) * this.getSeries();
    }

    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 30 * this.getUtilizador().fatorMultiplicativo());
    }
}

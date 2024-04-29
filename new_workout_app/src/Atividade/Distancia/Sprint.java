package Atividade.Distancia;

import java.time.LocalDate;

import Utilizador.Utilizador;

public class Sprint extends Distancia {

    // ----------------- Construtores ---------------- //
    public Sprint() {
        super();
    }

    public Sprint(String codigo, String descricao, LocalDate data, int duracao, Utilizador utilizador, double dist) {
        super(codigo, descricao, data, duracao, utilizador, dist);
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
        return "Sprint{" +
            super.toString() + '}';
    }

     
    public Sprint clone() {
        return new Sprint(this);
    }

    public double calorias() {
        return this.getUtilizador().fatorMultiplicativo() * (this.getVelocidade()/2) * this.getDuracao() * (this.getBpm()/100);
    }

    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 30 * this.getUtilizador().fatorMultiplicativo());
    }
}

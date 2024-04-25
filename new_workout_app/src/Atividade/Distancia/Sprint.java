package Atividade.Distancia;

import java.time.LocalDate;

import Utilizador.Utilizador;

public class Sprint extends Distancia {

    // ----------------- Construtores ---------------- //
    public Sprint() {
        super();
    }

    public Sprint(String codigo, String descricao, LocalDate data, int duracao, Utilizador user, double dist) {
        super(codigo, descricao, data, duracao, user, dist);
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
        return this.getUser().fatorMultiplicativo() * (this.getVelocidade()/2) * this.getDuracao() * (this.getBpm()/100);
    }

    public int bpm(){
      return (int) (this.getUser().getBpmMedio() + 30 * this.getUser().fatorMultiplicativo());
    }
}

package Atividade.Distancia;

import Utilizador.Utilizador;

public class Sprint extends Distancia {

    public Sprint() {
        super();
    }

    public Sprint(String codigo, String descricao, int duracao, Utilizador user, double dist, double velocidade) {
        super(codigo, descricao, duracao, user, dist, velocidade);
    }

    public Sprint(Sprint outro) {
        super(outro);
    }

     
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
        return 818.32;
    }
}

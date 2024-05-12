package Atividade.Distancia;

import java.time.LocalDateTime;

import Atividade.Atividade;
import Utilizador.Utilizador;

public abstract class Distancia extends Atividade {
    private double distancia;
    private double velocidade;

    // ----------------- Construtores ---------------- //
    public Distancia() {
       super(); 
       distancia = 0;
       velocidade = 0;
    }

    public Distancia(String codigo, String descricao, LocalDateTime data, int duracao, 
            int series, Utilizador utilizador, double dist) {
       super(codigo, descricao, data, duracao, series, utilizador); 
       this.distancia = dist;
       this.velocidade = dist * 60/duracao;
    }

    public Distancia(Distancia outro) {
       super(outro); 
       this.distancia = outro.getDistancia();
       this.velocidade = outro.getVelocidade();
    }

    // ----------------- Métodos getters e setters ----------------- //

    public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

    public double getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }


     
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Distancia distancia = (Distancia) o;
        return super.equals(distancia)
               && Double.compare(this.distancia, distancia.getDistancia()) == 0
               && Double.compare(this.velocidade, distancia.getVelocidade()) == 0;
    }

     
    public String toString() {
        return super.toString() + 
               ", distância = '" + this.distancia + '\'' +
               ", velocidade = '" + this.velocidade + '\'';
    }


    public abstract Distancia clone();
    public abstract double calorias();
    public abstract int bpm();
}

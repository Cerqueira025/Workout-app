package Atividade.Distancia;

import Atividade.Atividade;
import Utilizador.Utilizador;

public abstract class Distancia extends Atividade {
    private double distancia;
    private double velocidade;

    public Distancia() {
       super(); 
       distancia = 0;
    }

    public Distancia(String codigo, String descricao, int duracao, 
            Utilizador user, double dist, double velocidade) {
       super(codigo, descricao, duracao, user); 
       this.distancia = dist;
       this.velocidade = velocidade;
    }

    public Distancia(Distancia outro) {
       super(outro); 
       this.distancia = outro.getDistancia();
       this.velocidade = outro.getVelocidade();
    }

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

    double getVelocidade() {
        return this.velocidade;
    }

    void setVelocidade(double velocidade) {
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
        return "Distancia{" +
            super.toString() + 
            "distancia='" + this.distancia + '\'' +
            ", velocidade='" + this.velocidade + '}';
    }


    public abstract Distancia clone();
    public abstract double calorias();
}

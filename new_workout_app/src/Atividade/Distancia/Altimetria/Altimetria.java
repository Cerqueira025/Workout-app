package Atividade.Distancia.Altimetria;

import Atividade.Distancia.Distancia;
import Utilizador.Utilizador;

public abstract class Altimetria extends Distancia {
    private int altimetria;

    public Altimetria() {
        super();
        this.altimetria = 0;
    }

    public Altimetria(String codigo, String descricao, int duracao, 
            Utilizador user, double dist, double velocidade, 
            int altimetria) {
        super(codigo, descricao, duracao, user, dist, velocidade);
        this.altimetria = altimetria;
    }

    public Altimetria(Altimetria outro) {
        super(outro);
        this.altimetria = outro.getAltimetria();
    }


	public int getAltimetria() {
		return altimetria;
	}

	public void setAltimetria(int altimetria) {
		this.altimetria = altimetria;
	}

    
     
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Altimetria altimetria = (Altimetria) o;
        return super.equals(altimetria) 
                && this.altimetria == altimetria.getAltimetria();
    }

     
    public String toString() {
        return "Altimetria{" +
            super.toString() + 
            ", altimetria='" + this.altimetria + '}';
    }

    public abstract Distancia clone();
    public abstract double calorias();
}

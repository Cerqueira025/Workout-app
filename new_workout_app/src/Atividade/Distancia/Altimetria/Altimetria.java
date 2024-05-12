package Atividade.Distancia.Altimetria;

import java.time.LocalDateTime;

import Atividade.Distancia.Distancia;
import Utilizador.Utilizador;

public abstract class Altimetria extends Distancia {
    private int altimetria;

    // ----------------- Construtores ---------------- //
    public Altimetria() {
        super();
        this.altimetria = 0;
    }

    public Altimetria(String codigo, String descricao, LocalDateTime data, int duracao, 
            int series, Utilizador utilizador, double dist, int altimetria) {
        super(codigo, descricao, data, duracao, series, utilizador, dist);
        this.altimetria = altimetria;
    }

    public Altimetria(Altimetria outro) {
        super(outro);
        this.altimetria = outro.getAltimetria();
    }

    // ----------------- Métodos getters e setters ----------------- //

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
        return super.toString() + 
               ", altimetria = '" + this.altimetria + '\'';
    }

    public abstract Altimetria clone();
    public abstract double calorias();
    public abstract int bpm();
}

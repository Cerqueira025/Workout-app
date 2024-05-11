package Atividade.Distancia.Altimetria;

import java.time.LocalDateTime;

import Atividade.Hard;
import Utilizador.Utilizador;

public class TrailMontanha extends Altimetria implements Hard {
    private boolean bastoes;

    // ----------------- Construtores ---------------- //
    public TrailMontanha() {
        super();
        this.bastoes = false;
    }

    public TrailMontanha(String codigo, String descricao, LocalDateTime data, int duracao, int series, 
            Utilizador utilizador, double dist, int altimetria, boolean bastoes) {
        super(codigo, descricao, data, duracao, series, utilizador, dist, altimetria);
        this.bastoes = bastoes;
    }

    public TrailMontanha(TrailMontanha outro) {
        super(outro);
        this.bastoes = outro.getBastoes();
    }

    // ----------------- Métodos getters e setters ----------------- //

	public boolean getBastoes() {
		return this.bastoes;
	}

	public void setBastoes(boolean bastoes) {
		this.bastoes = bastoes;
	}


     
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        TrailMontanha trail = (TrailMontanha) o;
        return super.equals(trail) 
            && this.bastoes == trail.getBastoes();
    }

     
    public String toString() {
        return "TrailMontanha - " +
            super.toString() + 
            ", bastões = '" + this.bastoes + '\'';
    }

     
    public TrailMontanha clone() {
        return new TrailMontanha(this);
    }

    public double calorias() {
      double mult = this.getBastoes() ? 0.8 : 1;
      return mult * this.getUtilizador().fatorMultiplicativo() * ((this.getVelocidade()/2 + 1)) * ((this.getDuracao()/60 + 1)) * ((this.getAltimetria()/10 + 1)) * ((this.bpm()/100 + 1)) * this.getSeries() * 0.5;
    }

    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 15 * this.getUtilizador().fatorMultiplicativo());
    }
}

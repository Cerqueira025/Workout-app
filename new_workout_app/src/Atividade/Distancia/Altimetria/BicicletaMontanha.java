package Atividade.Distancia.Altimetria;

import java.time.LocalDateTime;

import Atividade.Hard;
import Utilizador.Utilizador;

public class BicicletaMontanha extends Altimetria implements Hard {
    private double variacaoSuspensao; // mm
    private int numeroMudancas;
    private boolean discoTravao;

    // ----------------- Construtores ---------------- //
    public BicicletaMontanha() {
        super();
        this.variacaoSuspensao = 0;
        this.numeroMudancas = 0;
        this.discoTravao = false;
    }

    public BicicletaMontanha(String codigo, String descricao, LocalDateTime data, int duracao, int series, 
            Utilizador utilizador, double dist, int altimetria, double variacaoSuspensao, int numeroMudancas,
            boolean discoTravao) {
        super(codigo, descricao, data, duracao, series, utilizador, dist, altimetria);
        this.variacaoSuspensao = variacaoSuspensao;
        this.numeroMudancas = numeroMudancas;
        this.discoTravao = discoTravao;
    }

    public BicicletaMontanha(BicicletaMontanha outro) {
        super(outro);
        this.variacaoSuspensao = outro.getVariacaoSuspensao();
        this.numeroMudancas = outro.getNumeroMudancas();
        this.discoTravao = outro.hasDiscoTravao();
    }

    // ----------------- Métodos getters e setters ----------------- //

    public double getVariacaoSuspensao() {
		return variacaoSuspensao;
	}
 
	public void setVariacaoSuspensao(double variacaoSuspensao) {
		this.variacaoSuspensao = variacaoSuspensao;
	}

	public int getNumeroMudancas() {
		return numeroMudancas;
	}

	public void setNumeroMudancas(int numeroMudancas) {
		this.numeroMudancas = numeroMudancas;
	}

	public boolean hasDiscoTravao() {
		return discoTravao;
	}

	public void setDiscoTravao(boolean discoTravao) {
		this.discoTravao = discoTravao;
	}


     
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        BicicletaMontanha bicicleta = (BicicletaMontanha) o;
        return super.equals(bicicleta) 
            && Double.compare(this.variacaoSuspensao, bicicleta.getVariacaoSuspensao()) == 0
            && this.numeroMudancas == bicicleta.getNumeroMudancas()
            && this.discoTravao == bicicleta.hasDiscoTravao();
    }

     
    public String toString() {
        return "BicicletaMontanha - " +
            super.toString() + 
            ", variação da Suspenção = '" + this.variacaoSuspensao + '\'' +
            ", numero de Mudanças = '" + this.numeroMudancas + '\'' +
            ", disco Travão = '" + this.discoTravao + '\'';
    }

     
    public BicicletaMontanha clone() {
        return new BicicletaMontanha(this);
    }

    public double calorias() {
      double mult = this.hasDiscoTravao() ? 0.8 : 1;
      return mult * this.getUtilizador().fatorMultiplicativo() * ((this.getVelocidade()/2 + 1)) * ((this.getDuracao()/100 + 1)) * ((this.getAltimetria()/10 + 1)) * ((this.variacaoSuspensao/20 + 1)) * ((this.numeroMudancas/4 + 1)) * ((this.bpm()/100 + 1)) * this.getSeries() * 0.5;
    }

    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 20 * this.getUtilizador().fatorMultiplicativo());
    }
}

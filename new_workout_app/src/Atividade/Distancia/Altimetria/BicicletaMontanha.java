package Atividade.Distancia.Altimetria;

import java.time.LocalDate;

import Utilizador.Utilizador;

public class BicicletaMontanha extends Altimetria {
    private double variacaoSuspensao; // mm
    private int númeroMudancas;
    private boolean discoTravao;

    public BicicletaMontanha() {
        super();
        this.variacaoSuspensao = 0;
        this.númeroMudancas = 0;
        this.discoTravao = false;
    }

    public BicicletaMontanha(String codigo, String descricao, LocalDate data, int duracao, 
            Utilizador user, double dist, int altimetria, double variacaoSuspensao, int númeroMudancas,
            boolean discoTravao) {
        super(codigo, descricao, data, duracao, user, dist, altimetria);
        this.variacaoSuspensao = variacaoSuspensao;
        this.númeroMudancas = númeroMudancas;
        this.discoTravao = discoTravao;
    }

    public BicicletaMontanha(BicicletaMontanha outro) {
        super(outro);
        this.variacaoSuspensao = getVariacaoSuspensao();
        this.númeroMudancas = getNúmeroMudancas();
        this.discoTravao = hasDiscoTravao();
    }

	public double getVariacaoSuspensao() {
		return variacaoSuspensao;
	}
 
	public void setVariacaoSuspensao(double variacaoSuspensao) {
		this.variacaoSuspensao = variacaoSuspensao;
	}

	public int getNúmeroMudancas() {
		return númeroMudancas;
	}

	public void setNúmeroMudancas(int númeroMudancas) {
		this.númeroMudancas = númeroMudancas;
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
            && this.númeroMudancas == bicicleta.getNúmeroMudancas()
            && this.discoTravao == bicicleta.hasDiscoTravao();
    }

     
    public String toString() {
        return "BicicletaMontanha{" +
            super.toString() + 
            "variacaoSuspencao='" + this.variacaoSuspensao + '\'' +
            ", númeroMudancas='" + this.númeroMudancas + '\'' +
            ", discoTravao='" + this.discoTravao + '\'' +
            '}';
    }

     
    public BicicletaMontanha clone() {
        return new BicicletaMontanha(this);
    }

     
    public double calorias() {
      double mult = 1;
      if(this.hasDiscoTravao()){
        mult = 0.5;
      }
      return mult * this.getUser().fatorMultiplicativo() * (this.getVariacaoSuspensao()/2) * this.getNúmeroMudancas() * (this.getBpm()/100);
    }

    public int getBpm(){
      return (int) (this.getUser().getBpmMedio() + 20 * this.getUser().fatorMultiplicativo());
    }
}

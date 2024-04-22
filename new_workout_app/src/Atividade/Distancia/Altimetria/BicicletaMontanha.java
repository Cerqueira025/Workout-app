package Atividade.Distancia.Altimetria;

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

    public BicicletaMontanha(String codigo, String descricao, int duracao, 
            Utilizador user, double dist, double velocidade, 
            int altimetria, double variacaoSuspensao, int númeroMudancas,
            boolean discoTravao) {
        super(codigo, descricao, duracao, user, dist, velocidade, altimetria);
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
        if(!super.equals(o)) return false;
        BicicletaMontanha bicicleta = (BicicletaMontanha) o;
        return Double.compare(this.variacaoSuspensao, bicicleta.getVariacaoSuspensao()) == 0
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
        return 9312.123;
    }
}

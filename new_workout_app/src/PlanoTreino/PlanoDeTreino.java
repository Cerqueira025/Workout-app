package PlanoTreino;

import Atividade.Atividade;
import Atividade.GestorAtividades;

import java.time.LocalDate;

public class PlanoDeTreino {
    private String codigo;
    private LocalDate dataRealizacao;
    private GestorAtividades atividades;
    private int iteracoes;


    // Construtor
    public PlanoDeTreino() {
        this.codigo = "";
        this.dataRealizacao = LocalDate.EPOCH;
        this.atividades = new GestorAtividades();
        this.iteracoes = 0;
    }

    // Construtor
    public PlanoDeTreino(String codigo, LocalDate dataRealizacao, GestorAtividades atividades, int iteracoes) {
        this.codigo = codigo;
        this.dataRealizacao = dataRealizacao;
        this.atividades = atividades.clone();
        this.iteracoes = iteracoes;
    }

    // Construtor
    public PlanoDeTreino(PlanoDeTreino outro) {
        this.codigo = outro.getCodido();
        this.dataRealizacao = outro.getDataRealizacao();
        this.atividades = outro.getAtividades();
        this.iteracoes = outro.getIteracoes();
    }


    // Métodos getters e setters

    public String getCodido() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataRealizacao() {
        return this.dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public GestorAtividades getAtividades() {
        return this.atividades.clone();
    }

    public void setAtividades(GestorAtividades atividades) {
        this.atividades = atividades.clone();
    }

    public int getIteracoes() {
        return this.iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public void addAtividade(Atividade atividade) {
		this.atividades.addAtividade(atividade);
	}

    public String toString() {
        return "Plano de Treino{" +
                "código='" + this.codigo + '\'' +
                "data='" + this.dataRealizacao + '\'' +
                ", atividades='" + this.atividades.toString() + '\'' +
                ", iterações=" + this.iteracoes +
                '}';
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        PlanoDeTreino plano = (PlanoDeTreino) o;
        return this.codigo.equals(plano.getCodido())
                && this.dataRealizacao.isEqual(plano.getDataRealizacao())
                && this.atividades.equals(plano.getAtividades())
                && this.iteracoes == plano.getIteracoes();
    }

    public PlanoDeTreino clone() {
        return new PlanoDeTreino(this);
    }


}

package PlanoTreino;

import Atividade.Atividade;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlanoDeTreino {
    private String codigo;
    private LocalDate dataRealizacao;
    private int iteracoes;
    private Map<String, Atividade> atividades;


    // Construtor
    public PlanoDeTreino() {
        this.codigo = "";
        this.dataRealizacao = LocalDate.EPOCH;
        this.iteracoes = 0;
        this.atividades = new HashMap<>();
    }

    // Construtor
    public PlanoDeTreino(String codigo, LocalDate dataRealizacao, int iteracoes, Map<String, Atividade> atividades) {
        this.codigo = codigo;
        this.dataRealizacao = dataRealizacao;
        this.iteracoes = iteracoes;
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    // Construtor
    public PlanoDeTreino(PlanoDeTreino outro) {
        this.codigo = outro.getCodido();
        this.dataRealizacao = outro.getDataRealizacao();
        this.iteracoes = outro.getIteracoes();
        this.atividades = outro.getAtividades();
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

    public int getIteracoes() {
        return this.iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public Map<String, Atividade> getAtividades() {
		return this.atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
	}

	public void setAtividades(Map<String, Atividade> atividades) {
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
	}

	public void addAtividade(Atividade atividade) {
        this.atividades.put(atividade.getCodigo(), atividade.clone());
    }

    public void removeAtividade(String codigo_atividade) {
        this.atividades.remove(codigo_atividade);
    }

    public Atividade getAtividade(String codigo_atividade) {
        if(!this.atividades.containsKey(codigo_atividade)) return null;
        return this.atividades.get(codigo_atividade).clone();
    }




    public String toString() {
        String a = "Plano de Treino{" +
                "código='" + this.codigo + '\'' +
                ", data='" + this.dataRealizacao + '\'' +
                ", iterações=" + this.iteracoes + '\'' +
                ", atividades={";
        for(Atividade atividade : this.atividades.values()) {
           a += atividade.toString() + ",";
        }
        a += '}';
        return a;
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        PlanoDeTreino plano = (PlanoDeTreino) o;
        return this.codigo.equals(plano.getCodido())
                && this.dataRealizacao.isEqual(plano.getDataRealizacao())
                && this.iteracoes == plano.getIteracoes()
                && this.atividades.equals(plano.getAtividades());
    }

    public PlanoDeTreino clone() {
        return new PlanoDeTreino(this);
    }


}

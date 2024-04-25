package PlanoTreino;

import Atividade.Atividade;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlanoDeTreino {
    private LocalDate dataRealizacao;
    private int duracao; //duração em semanas
    private Map<String, Atividade> atividades;


    // ----------------- Construtores ---------------- //

    public PlanoDeTreino() {
        this.dataRealizacao = LocalDate.EPOCH;
        this.duracao = 0;
        this.atividades = new HashMap<>();
    }

    public PlanoDeTreino(LocalDate dataRealizacao, int duracao, Map<String, Atividade> atividades) {
        this.dataRealizacao = dataRealizacao;
        this.duracao = duracao;
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public PlanoDeTreino(LocalDate dataRealizacao, int duracao) {
      this.dataRealizacao = dataRealizacao;
      this.duracao = duracao;
      this.atividades = new HashMap<>();
    }

    public PlanoDeTreino(PlanoDeTreino outro) {
        this.dataRealizacao = outro.getDataRealizacao();
        this.duracao = outro.getDuracao();
        this.atividades = outro.getAtividades();
    }


    // ----------------- Métodos getters e setters ----------------- //

    public LocalDate getDataRealizacao() {
        return this.dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
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
                "data='" + this.dataRealizacao + '\'' +
                ", duração=" + this.duracao + '\'' +
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
        return this.dataRealizacao.equals(plano.getDataRealizacao())
                && this.duracao == plano.getDuracao()
                && this.atividades.equals(plano.getAtividades());
    }

    public PlanoDeTreino clone() {
        return new PlanoDeTreino(this);
    }


}

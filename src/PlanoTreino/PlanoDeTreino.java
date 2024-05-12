package PlanoTreino;

import Atividade.Atividade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlanoDeTreino implements Serializable {
    private LocalDate dataRealizacao;
    private int duracao; // duração em semanas
    private double caloriasTotais; // calorias de todas as atividades, incluindo daquelas que já foram removidas do plano de treino por terem sido realizadas
    private Map<String, Atividade> atividades;


    // ----------------- Construtores ---------------- //

    public PlanoDeTreino() {
        this.dataRealizacao = LocalDate.now();
        this.duracao = 0;
        this.caloriasTotais = 0;
        this.atividades = new HashMap<>();
    }
    
    public PlanoDeTreino(LocalDate dataRealizacao, int duracao) {
      this.dataRealizacao = dataRealizacao;
      this.duracao = duracao;
      this.caloriasTotais = 0;
      this.atividades = new HashMap<>();
    }

    public PlanoDeTreino(LocalDate dataRealizacao, int duracao, Map<String, Atividade> atividades) {
        this.dataRealizacao = dataRealizacao;
        this.duracao = duracao;
        this.caloriasTotais = atividades.values().stream().mapToDouble(a -> a.calorias()).sum();
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public PlanoDeTreino(PlanoDeTreino outro) {
        this.dataRealizacao = outro.getDataRealizacao();
        this.duracao = outro.getDuracao();
        this.caloriasTotais = outro.getCaloriasTotais();
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

    public double getCaloriasTotais() {
        return this.caloriasTotais;
    }

    public void setCaloriasTotais(double caloriasTotais) {
        this.caloriasTotais = caloriasTotais;
    }

    public Map<String, Atividade> getAtividades() {
		return this.atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
	}

	public void setAtividades(Map<String, Atividade> atividades) {
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
	}


    // ----------------- outros métodos ----------------- //

	public void addAtividade(Atividade atividade) {
        this.atividades.put(atividade.getCodigo(), atividade);
        this.caloriasTotais += atividade.calorias();
    }

    public void removeAtividade(String codigoAtividade) {
        this.atividades.remove(codigoAtividade);
    }

    public void apagaAtividade(String codigoAtividade) {
        this.atividades.remove(codigoAtividade);
        this.caloriasTotais -= this.atividades.get(codigoAtividade).calorias();
    }

    public Atividade getAtividade(String codigoAtividade) {
        return this.atividades.get(codigoAtividade);
    }

    public boolean existeAtividade(String codigoAtividade) {
        return this.atividades.containsKey(codigoAtividade);
    }

    public double mediaCaloriasSemanal() {
        return this.caloriasTotais/this.duracao;
    }

    public List<Atividade> atividadesDoDia(LocalDate data) {
        return this.atividades.values().stream().filter(a -> a.getData().toLocalDate().equals(data)).collect(Collectors.toList());
    }

    public String toString() {
        String a = "Plano de Treino - " + '\n' +
                "data = '" + this.dataRealizacao + '\'' +
                ", duração = '" + this.duracao + '\'' +
                ", calorias totais = '" + this.caloriasTotais + '\'' +
                ", ATIVIDADES [\n";
        for(Atividade atividade : this.atividades.values()) {
           a += atividade.toString() + ",\n";
        }
        a += "]";
        return a;
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        PlanoDeTreino plano = (PlanoDeTreino) o;
        return this.dataRealizacao.equals(plano.getDataRealizacao())
                && this.duracao == plano.getDuracao()
                && Double.compare(this.caloriasTotais, plano.getCaloriasTotais()) == 0
                && this.atividades.equals(plano.getAtividades());
    }

    public PlanoDeTreino clone() {
        return new PlanoDeTreino(this);
    }


}

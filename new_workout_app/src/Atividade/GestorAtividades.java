package Atividade;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorAtividades {
    private Map<String, Atividade> atividades;


    public GestorAtividades() {
        this.atividades = new HashMap<>();
    }

    public GestorAtividades(Map<String, Atividade> atividades) {
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public GestorAtividades(GestorAtividades outro) {
        this.atividades = outro.getAtividades();
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

    public void removeAtividade(String codigo) {
        this.atividades.remove(codigo);
    }
    public Atividade getAtividade(String codigo) {
        if(!this.atividades.containsKey(codigo)) return null;
        return this.atividades.get(codigo).clone();
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        GestorAtividades ga = (GestorAtividades) o;
        return this.atividades.equals(ga.getAtividades());
    }

    public String toString() {
        String a = "";
        for(Atividade atividade : this.atividades.values()) {
           a += atividade.toString() + " ";
        }
        return a;
    }

    public GestorAtividades clone() {
        return new GestorAtividades(this);
    }

}

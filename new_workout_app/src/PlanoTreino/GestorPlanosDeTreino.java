package PlanoTreino;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorPlanosDeTreino {
    private Map<String,PlanoDeTreino> planos;
    

    public GestorPlanosDeTreino() {
        this.planos = new HashMap<>();
    }

    public GestorPlanosDeTreino(Map<String, PlanoDeTreino> planos) {
        this.planos = planos.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public GestorPlanosDeTreino(GestorPlanosDeTreino outro) {
        this.planos = outro.getPlanos();
    }


    public Map<String,PlanoDeTreino> getPlanos() {
        return this.planos.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public void setPlanos(Map<String,PlanoDeTreino> planos) {
        this.planos = planos.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public void addPlanoDeTreino(PlanoDeTreino plano) {
        this.planos.put(plano.getCodido(), plano.clone());
    }

    public void removePlano(String codigo) {
        this.planos.remove(codigo);
    }

    public PlanoDeTreino getPlanoDeTreino(String codigo) {
        if(!this.planos.containsKey(codigo)) return null;
        return this.planos.get(codigo).clone();
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        GestorPlanosDeTreino gpt = (GestorPlanosDeTreino) o;
        return this.planos.equals(gpt.getPlanos());
    }

    public String toString() {
        String a = "";
        for(PlanoDeTreino plano : this.planos.values()) {
           a += plano.toString() + " ";
        }
        return a;
    }

    public GestorPlanosDeTreino clone() {
        return new GestorPlanosDeTreino(this);
    }


}